package com.merapar.recruitment.domain;

import org.springframework.stereotype.Component;
import java.time.ZonedDateTime;

@Component
public class AnalysisResult {
    private ZonedDateTime analyseDate;
    private AnalyseDetails details = new AnalyseDetails();

    public AnalyseDetails getDetails() {
        return details;
    }

    public void setDetails(AnalyseDetails details) {
        this.details = details;
    }

    public ZonedDateTime getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(ZonedDateTime analyseDate) {
        this.analyseDate = analyseDate;
    }
}
