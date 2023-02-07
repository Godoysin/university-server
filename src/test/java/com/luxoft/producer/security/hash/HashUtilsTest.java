package com.luxoft.producer.security.hash;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.security.NoSuchAlgorithmException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class HashUtilsTest {

    protected static final Stream<Arguments> stringArguments() {
        return Stream.of(
                Arguments.of("testString", "J�\u000B9��vg\t�h�U:�\u001A�PT_�ED���Ψ/�\u0002�"),
                Arguments.of("cITwJh9t7s", "�!|�n\\�C�\tcL!�5\u0016Mt�kH\u0017퀄Q�X-��_")
        );
    }

    @InjectMocks
    private HashUtils hashUtilsInjectedMock;

    @BeforeEach
    public void init() {
        ReflectionTestUtils.setField(hashUtilsInjectedMock, "HASH_ALGORITHM", "SHA-256");
    }

    @ParameterizedTest
    @MethodSource("stringArguments")
    public void hashingTest(String string, String expectedHash) throws NoSuchAlgorithmException {
        String hashedString = hashUtilsInjectedMock.hashString(string);
        assertEquals(expectedHash, hashedString);
    }

    @Test
    public void hashingNullTest() {
        assertThrows(NullPointerException.class, () -> hashUtilsInjectedMock.hashString(null));
    }

    @Test
    public void nullHashingAlgorithmTest() {
        ReflectionTestUtils.setField(hashUtilsInjectedMock, "HASH_ALGORITHM", null);
        assertThrows(NullPointerException.class, () -> hashUtilsInjectedMock.hashString(null));
    }

}
