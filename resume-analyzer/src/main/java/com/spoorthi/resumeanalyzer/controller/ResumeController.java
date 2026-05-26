package com.spoorthi.resumeanalyzer.controller;

import com.spoorthi.resumeanalyzer.dto.JobMatchResponse;
import com.spoorthi.resumeanalyzer.dto.ResumeResponse;
import com.spoorthi.resumeanalyzer.service.ResumeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@CrossOrigin(origins = "hhtps://elevatecv.onrender.com")
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;

    @PostMapping("/upload")
    public ResumeResponse uploadResume(@RequestParam("file") MultipartFile file) {

        return resumeService.analyzeResume(file);
    }

    @PostMapping("/match")
    public JobMatchResponse matchResume(
            @RequestParam("file") MultipartFile file,
            @RequestParam("jobDescription") String jobDescription) {

        return resumeService.matchResumeWithJob(file, jobDescription);
    }
}
