package com.merapar.recruitment.controllers;

import com.merapar.recruitment.domain.ResponseAnalysisResult;
import com.merapar.recruitment.domain.XmlUrl;
import com.merapar.recruitment.services.XmlAnalyserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class XmlAnalyserController {

    @Autowired
    private XmlAnalyserService xmlAnalyserService;

    @RequestMapping(value = "/analyze", method = RequestMethod.POST)
    public ResponseAnalysisResult analyzeXmlDocument(@RequestBody XmlUrl xmlUrl){
        return xmlAnalyserService.analyse(xmlUrl.getUrl());
    }
}
