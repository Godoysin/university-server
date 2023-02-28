package com.project.producer.security.jwt;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class DateComparator {

    public static final String UTC_ZONE_ID = "UTC";

    public static LocalDateTime convertToLocalDateTimeViaInstant(Date dateToConvert) {
        return dateToConvert.toInstant()
                .atZone(ZoneId.of(UTC_ZONE_ID))
                .toLocalDateTime();
    }

    public static LocalDateTime convertToLocalDateTimeViaInstant(Long longToConvert) {
        return Instant.ofEpochMilli(longToConvert)
                .atZone(ZoneId.of(UTC_ZONE_ID))
                .toLocalDateTime();
    }

}
