package com.merapar.recruitment.domain;

import com.merapar.recruitment.utils.CustomDateTimeFormatter;

public class ResponseAnalysisResult {
    private String analyseDate;
    private ResponseAnalyseDetails details;

    public String getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(String analyseDate) {
        this.analyseDate = analyseDate;
    }

    public ResponseAnalyseDetails getDetails() {
        return details;
    }

    public void setDetails(ResponseAnalyseDetails details) {
        this.details = details;
    }

    public ResponseAnalysisResult(AnalysisResult analysisResult) {
        this.analyseDate = CustomDateTimeFormatter.parseAnalysisDate(analysisResult.getAnalyseDate());
        this.details = new ResponseAnalyseDetails(analysisResult.getDetails());
    }
}
