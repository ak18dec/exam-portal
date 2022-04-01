package com.exam.analytics.service;

import com.exam.analytics.repository.AnalyticsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnalyticsService {
    @Autowired
    private AnalyticsRepository analyticsRepository;
}
