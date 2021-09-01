package com.merapar.recruitment.parsing;

import com.merapar.recruitment.domain.AnalysisResult;
import com.merapar.recruitment.utils.CustomDateTimeFormatter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

public class XmlParserHandler extends DefaultHandler {
    private static Log logger = LogFactory.getLog(XmlParserHandler.class);

    private SAXParser parser;
    private InputStream in;
    private AnalysisResult analysisResult;

    private static final String POSTS = "posts";
    private static final String ROW = "row";

    public XmlParserHandler(InputStream in) {
        this.in = in;
        try {
            this.parser = SAXParserFactory.newInstance().newSAXParser();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

    public AnalysisResult parse() {
        try {
            parser.parse(in, this);
        } catch (SAXException error) {
            throw new RuntimeException("Unable to parse input stream.", error);
        } catch (IOException error) {
            throw new RuntimeException("Unable to parse input stream.", error);
        }
        return analysisResult;
    }

    @Override
    public void startElement(String uri, String lName, String qName, Attributes attr) throws SAXException {

        switch (qName) {
            case POSTS:
                logger.info("Logging: POSTS");
                analysisResult = new AnalysisResult();
                break;
            case ROW:
                //first post is set only once
                if (analysisResult.getDetails().getTotalPosts() == 0) {
                    setFirstPost(attr.getValue("CreationDate"));
                }
                incrementTotalPosts();
                incrementTotalAcceptedPosts(attr.getValue("AcceptedAnswerId"));
                increaseScoreSum(attr.getValue("Score"));
                //last post is overritten with each ROW
                setLastPost(attr.getValue("CreationDate"));
                break;
        }
    }

    private void incrementTotalAcceptedPosts(String acceptedAnswerId) {
        if (acceptedAnswerId != null) {
            analysisResult.getDetails().incrementTotalAcceptedPosts();
        }
    }

    private void incrementTotalPosts() {
        analysisResult.getDetails().incrementTotalPosts();
    }

    private void increaseScoreSum(String score) {
        if (score != null) {
            Integer tempScore = Integer.valueOf(score);
            if (tempScore != null) {
                analysisResult.getDetails().increaseScoreSum(tempScore);
            }
        }
    }
    private void setFirstPost(String firstPost) {
        analysisResult.getDetails().setFirstPost(CustomDateTimeFormatter.parseInputDate(firstPost));
    }


    private void setLastPost(String lastPost) {
        analysisResult.getDetails().setLastPost(CustomDateTimeFormatter.parseInputDate(lastPost));
    }

}
