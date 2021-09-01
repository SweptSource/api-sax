package com.merapar.recruitment.services;

import com.merapar.recruitment.domain.AnalysisResult;
import com.merapar.recruitment.domain.ResponseAnalysisResult;
import com.merapar.recruitment.parsing.XmlParserHandler;
import org.springframework.stereotype.Service;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.*;


@Service
public class XmlAnalyserServiceImpl implements XmlAnalyserService {

    private AnalysisResult analysisResult;

    @Override
    public ResponseAnalysisResult analyse(String url) {


        // TODO TO WSZYTKO CHCIALBYM PRZENIESC !!! no nie wiem , ot tutaj juz serwis tylko to przekaze ?
        // HANDLE InputStream and PARSE
        InputStream is = null;

        try {
            is = new URL(url).openStream();
            XmlParserHandler xmlParserHandler = new XmlParserHandler(is);
            analysisResult = xmlParserHandler.parse();
            analysisResult.setAnalyseDate(ZonedDateTime.now());

        } catch (MalformedURLException malformedURLException){
            malformedURLException.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (Throwable e) {
                }
            }
        }

        return new ResponseAnalysisResult(analysisResult);
    }
}
