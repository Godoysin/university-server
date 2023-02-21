package com.luxoft.producer.kafka.log;

import org.aspectj.lang.Signature;

public class SignatureTest implements Signature {

    private final String name;

    public SignatureTest(String name) {
        this.name = name;
    }

    @Override
    public String toShortString() {
        return null;
    }

    @Override
    public String toLongString() {
        return null;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getModifiers() {
        return 0;
    }

    @Override
    public Class getDeclaringType() {
        return null;
    }

    @Override
    public String getDeclaringTypeName() {
        return null;
    }
}
