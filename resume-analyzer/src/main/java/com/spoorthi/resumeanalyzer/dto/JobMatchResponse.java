package com.spoorthi.resumeanalyzer.dto;

public record JobMatchResponse(
        String matchPercentage,
        String missingSkills,
        String recommendation
) {
}
