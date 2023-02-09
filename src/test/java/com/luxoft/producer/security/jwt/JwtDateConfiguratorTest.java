package com.luxoft.producer.security.jwt;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class JwtDateConfiguratorTest {

    @InjectMocks
    private JwtDateConfigurator jwtDateConfiguratorMock;

    @Test
    public void shouldReturnThirtyDaysInMilliseconds() {
        // given
        long thirtyDaysMillisecondsLong = 1000L * 60L * 60L * 24L * 30L;

        // when
        long expireDurationLong = jwtDateConfiguratorMock.getExpireDuration();

        // then
        assertEquals(thirtyDaysMillisecondsLong, expireDurationLong);
    }

    @Test
    public void shouldReturnDateFromToday() {
        // given
        Date testDate = new Date();

        // when
        Date generatedDate = jwtDateConfiguratorMock.generateDate();

        // then
        LocalDateTime testLocalDateTime = DateComparator.convertToLocalDateTimeViaInstant(testDate);
        LocalDateTime generatedLocalDateTime = DateComparator.convertToLocalDateTimeViaInstant(generatedDate);

        assertEquals(testLocalDateTime.getYear(), generatedLocalDateTime.getYear());
        assertEquals(testLocalDateTime.getDayOfYear(), generatedLocalDateTime.getDayOfYear());
        assertEquals(testLocalDateTime.getDayOfYear(), generatedLocalDateTime.getDayOfYear());
        assertEquals(testLocalDateTime.getHour(), generatedLocalDateTime.getHour());
        assertEquals(testLocalDateTime.getMinute(), generatedLocalDateTime.getMinute());
    }

}
