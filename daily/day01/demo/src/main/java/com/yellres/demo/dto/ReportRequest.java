package com.yellres.demo.dto;

public record ReportRequest(
    String stockCode,
    Integer year,
    Double revenue
) {
    
}