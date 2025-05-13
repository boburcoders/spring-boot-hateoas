package com.company.SpiringBootDataJpa;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class SimpleController {

    private final ReportService reportService;

    @GetMapping("/report")
    public String report() {
//        reportService.sendReportWithThreadJavaThread();
//        reportService.sendReportWithoutNewThread();
        reportService.sendReportWithAsync();
        return "Send report successfully";
    }
}
