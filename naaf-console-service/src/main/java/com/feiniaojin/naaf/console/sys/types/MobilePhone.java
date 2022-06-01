package com.feiniaojin.naaf.console.sys.types;

import java.util.Objects;

public class MobilePhone {

    private final String value;

    public MobilePhone(String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
