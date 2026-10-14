package com.yellres.demo.controller;

import java.util.Map;
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
    public String createReport(
        @RequestBody Map<String, Object> report) {
            Object year = report.get("year");
            return year.getClass().getSimpleName();
            // return report;
    }
}