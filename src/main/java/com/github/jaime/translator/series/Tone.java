package com.github.jaime.translator.series;

public enum Tone {

    FORMAL("formal"),
    CASUAL("casual");

    public final String value;

    private Tone(String mode) {
        this.value = mode;
    }
}
