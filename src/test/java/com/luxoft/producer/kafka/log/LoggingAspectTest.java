package com.luxoft.producer.kafka.log;

import org.aspectj.lang.JoinPoint;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoggingAspectTest {

    @InjectMocks
    private LoggingAspect loggingAspect;

    @Mock
    private JoinPoint joinPointMock;

    protected static final Stream<Arguments> testArguments() {
        return Stream.of(
                Arguments.of((Object) new Object[0]),
                Arguments.of((Object) new Object[] {"argument 1"}),
                Arguments.of((Object) new Object[] {1}),
                Arguments.of((Object) new Object[] {1L}),
                Arguments.of((Object) new Object[] {null}),
                Arguments.of((Object) new Object[] {"argument 1", "argument 2"}),
                Arguments.of((Object) new Object[] {1, 2}),
                Arguments.of((Object) new Object[] {1, "argument 2", null})
        );
    }

    @ParameterizedTest
    @MethodSource("testArguments")
    public void shouldDisplayAspectLog(Object[] objects) {
        // given
        String methodName = "chosenMethod";

        SignatureTest signatureTest = new SignatureTest(methodName);
        when(joinPointMock.getSignature()).thenReturn(signatureTest);

        when(joinPointMock.getArgs()).thenReturn(objects);

        // when
        loggingAspect.print(joinPointMock);

        // then
        verify(joinPointMock, times(1)).getSignature();
        verify(joinPointMock, times(1)).getArgs();
    }

}
