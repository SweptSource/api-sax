package com.merapar.recruitment.utils;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.*;

class CustomDateTimeFormatterTest {


    @Test
    public void testImputFileCreationDateConvertion(){
        String creationDate="2015-07-14T18:39:27.757";
        ZonedDateTime zdt = CustomDateTimeFormatter.parseInputDate(creationDate);
        assertNotNull(zdt);
    }

    @Test
    public void testParseDetailedDate(){
        ZonedDateTime zdt = ZonedDateTime.of(2018, 01, 01, 01, 12, 35, 765000000, ZoneId.of("UTC"));
        String parsedDateAsString = CustomDateTimeFormatter.parseDetailedDate(zdt);
        assertEquals("2018-01-01T01:12:35.765+00:00", parsedDateAsString);
    }

    @Test
    public void testParseAnalysisDate(){
        ZonedDateTime zdt = ZonedDateTime.of(2018, 01, 01, 01, 12, 35, 765000000, ZoneId.of("UTC"));
        String parsedDateAsString = CustomDateTimeFormatter.parseAnalysisDate(zdt);
        assertEquals("2018-01-01T01:12:35+00:00", parsedDateAsString);
    }

}