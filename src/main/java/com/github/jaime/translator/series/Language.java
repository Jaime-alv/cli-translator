package com.github.jaime.translator.series;

public enum Language {

    ENGLISH("EN"),
    BRITISH("EN-GB"),
    AMERICAN("EN-US"),
    DEUTSCH("DE"),
    FRENCH("FR"),
    SPANISH("ES"),
    OTHER(null);

    public String value;

    private static void setOther(String unknownLanguage) {
        Language.OTHER.value = unknownLanguage;
    }

    private Language(String lang) {
        this.value = lang;
    }

    public static Language parse(String lang) {
        String normalizedLang = lang.toUpperCase().strip();
        switch (normalizedLang) {
            case "GB":
                return Language.BRITISH;
            case "US":
                return Language.AMERICAN;
            default:
                try {
                    return Language.valueOf(normalizedLang);
                } catch (Exception e) {
                    setOther(normalizedLang);
                    return Language.OTHER;
                }
        }
    }

}