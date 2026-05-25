package com.spoorthi.resumeanalyzer.service;

import com.spoorthi.resumeanalyzer.dto.JobMatchResponse;
import com.spoorthi.resumeanalyzer.dto.ResumeResponse;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class ResumeService {

    public ResumeResponse analyzeResume(MultipartFile file) {

        try {

            try (PDDocument document = Loader.loadPDF(file.getBytes())) {

                PDFTextStripper pdfStripper = new PDFTextStripper();

                String text = pdfStripper.getText(document);

                String email = extractEmail(text);

                String phone = extractPhone(text);

                String skills = extractSkills(text);

                String education = text.toLowerCase().contains("engineering")
                        ? "Engineering Degree Found"
                        : "Education not found";

                String experience = text.toLowerCase().contains("project")
                        ? "Experience Section Found"
                        : "No experience found";

                String suggestions = "Add more projects to improve resume";

                String atsScore = "80%";

                return new ResumeResponse(
                        email,
                        phone,
                        skills,
                        text,
                        education,
                        experience,
                        suggestions,
                        atsScore
                );
            }

        } catch (Exception e) {

            throw new RuntimeException("Error analyzing resume");

        }
    }

    public JobMatchResponse matchResumeWithJob(MultipartFile file, String jobDescription) {

        try {

            try (PDDocument document = Loader.loadPDF(file.getBytes())) {

                PDFTextStripper pdfStripper = new PDFTextStripper();

                String text = pdfStripper.getText(document);

                int score = calculateMatch(text, jobDescription);

                String matchPercentage = score + "%";

                String missingSkills = "";

                if (!text.toLowerCase().contains("spring")) {
                    missingSkills += "Spring Boot, ";
                }

                if (!text.toLowerCase().contains("sql")) {
                    missingSkills += "SQL, ";
                }

                if (!text.toLowerCase().contains("react")) {
                    missingSkills += "React, ";
                }

                String recommendation = score < 60
                        ? "Improve missing skills before applying"
                        : "Good match for this role";

                return new JobMatchResponse(
                        matchPercentage,
                        missingSkills,
                        recommendation
                );
            }

        } catch (Exception e) {

            throw new RuntimeException("Error matching resume");

        }
    }

    private int calculateMatch(String resumeText, String jobDescription) {

        int score = 0;

        if (resumeText.toLowerCase().contains("java")) {
            score += 20;
        }

        if (resumeText.toLowerCase().contains("spring")) {
            score += 20;
        }

        if (resumeText.toLowerCase().contains("sql")) {
            score += 20;
        }

        if (resumeText.toLowerCase().contains("react")) {
            score += 20;
        }

        if (resumeText.toLowerCase().contains("python")) {
            score += 20;
        }

        return score;
    }

    private String extractEmail(String text) {

        Pattern pattern = Pattern.compile(
                "[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}"
        );

        Matcher matcher = pattern.matcher(text);

        return matcher.find() ? matcher.group() : "";
    }

    private String extractPhone(String text) {

        Pattern pattern = Pattern.compile(
                "(\\+91[- ]?)?[0-9]{10}"
        );

        Matcher matcher = pattern.matcher(text);

        return matcher.find() ? matcher.group() : "";
    }

    private String extractSkills(String text) {

        List<String> skills = new ArrayList<>();

        if (text.toLowerCase().contains("java")) {
            skills.add("Java");
        }

        if (text.toLowerCase().contains("spring")) {
            skills.add("Spring Boot");
        }

        if (text.toLowerCase().contains("sql")) {
            skills.add("SQL");
        }

        if (text.toLowerCase().contains("python")) {
            skills.add("Python");
        }

        if (text.toLowerCase().contains("react")) {
            skills.add("React");
        }

        return String.join(", ", skills);
    }
}
