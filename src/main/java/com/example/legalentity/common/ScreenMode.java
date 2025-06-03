package com.example.legalentity.common;

import lombok.Getter;

@Getter
public enum ScreenMode {

    QUERY("0"),
    NEW("1"),
    MODIFY("2"),
    CANCEL("3");

    private String value;

    ScreenMode(final String value) {
        this.value = value;
    }

    public static ScreenMode fromValue(final String value) {

        return null;
    }

}
