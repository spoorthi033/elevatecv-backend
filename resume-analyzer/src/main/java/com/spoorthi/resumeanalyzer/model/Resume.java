package com.spoorthi.resumeanalyzer.model;

import jakarta.persistence.*;

@Entity
@Table(name = "resumes")
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileName;

    private String candidateName;

    private double atsScore;

    private String matchedJob;

    public Resume() {
    }

    public Resume(String fileName, String candidateName, double atsScore, String matchedJob) {
        this.fileName = fileName;
        this.candidateName = candidateName;
        this.atsScore = atsScore;
        this.matchedJob = matchedJob;
    }

    public Long getId() {
        return id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public double getAtsScore() {
        return atsScore;
    }

    public void setAtsScore(double atsScore) {
        this.atsScore = atsScore;
    }

    public String getMatchedJob() {
        return matchedJob;
    }

    public void setMatchedJob(String matchedJob) {
        this.matchedJob = matchedJob;
    }
}