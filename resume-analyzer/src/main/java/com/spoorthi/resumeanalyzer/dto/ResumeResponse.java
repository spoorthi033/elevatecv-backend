package com.spoorthi.resumeanalyzer.dto;

public record ResumeResponse(
        String email,
        String phone,
        String skills,
        String resumeText,
        String education,
        String experience,
        String suggestions,
        String atsScore
) {

    public ResumeResponse(String email, String phone, String skills, String resumeText) {
        this(email, phone, skills, resumeText, "", "", "", "");
    }
}
