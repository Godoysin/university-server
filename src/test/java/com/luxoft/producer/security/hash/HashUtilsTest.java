package com.luxoft.producer.security.hash;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HashUtilsTest {

    private static Stream<Arguments> stringArguments() {
        return Stream.of(
                Arguments.of("testString", "J�\u000B9��vg\t�h�U:�\u001A�PT_�ED���Ψ/�\u0002�"),
                Arguments.of("cITwJh9t7s", "�!|�n\\�C�\tcL!�5\u0016Mt�kH\u0017퀄Q�X-��_")
        );
    }

    @ParameterizedTest
    @MethodSource("stringArguments")
    public void hashingTest(String string, String expectedHash) throws NoSuchAlgorithmException {
        String hashedString = HashUtils.hashString(string);
        assertEquals(expectedHash, hashedString);
    }

    @Test
    public void hashingNullTest() {
        assertThrows(NullPointerException.class, () -> HashUtils.hashString(null));
    }

}
