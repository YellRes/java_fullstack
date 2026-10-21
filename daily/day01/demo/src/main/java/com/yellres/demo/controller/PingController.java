package com.yellres.demo.controller;

import java.util.Map;
import com.yellres.demo.dto.ReportRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class PingController {

    @GetMapping("/api/ping")
    public Map<String, String> ping() {
        return Map.of("message", "pong");
    }

    @PostMapping("/api/reports")
    public ReportRequest createReport(
        @RequestBody ReportRequest report) {
            return report;
    }
}