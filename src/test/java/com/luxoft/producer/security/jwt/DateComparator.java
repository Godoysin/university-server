package com.luxoft.producer.security.jwt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateComparator {

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

}
