package com.exam.analytics.controller;

import com.exam.analytics.service.AnalyticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/analytics")
@CrossOrigin("*")
public class AnalyticsController {
    @Autowired
    private AnalyticsService analyticsService;
}
