package com.merapar.recruitment.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CustomDateTimeFormatter {

    private static final DateTimeFormatter detailsDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSxxx").withZone(ZoneId.of("GMT"));
    private static final DateTimeFormatter analysisDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssxxx").withZone(ZoneId.of("GMT"));
    private static final DateTimeFormatter inputDateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS");

    public static String parseDetailedDate(ZonedDateTime zdt){
        return zdt.format(detailsDateTimeFormatter);
    }

    public static String parseAnalysisDate(ZonedDateTime zdt){
        return zdt.format(analysisDateTimeFormatter);
    }

    public static ZonedDateTime parseInputDate(String inputDate){
        LocalDateTime ldt = LocalDateTime.parse(inputDate, inputDateTimeFormatter);
        ZonedDateTime result = ldt.atZone(ZoneId.of("GMT"));
        return result;
    }


}
