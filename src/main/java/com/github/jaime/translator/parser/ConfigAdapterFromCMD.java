package com.github.jaime.translator.parser;

import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.parser.adapter.ConfigAdapter;
import com.github.jaime.translator.series.APIMode;
import com.github.jaime.translator.series.Language;

public class ConfigAdapterFromCMD implements ConfigAdapter {

    private final CommandLineService cmd;

    public ConfigAdapterFromCMD(CommandLineService cmd) {
        this.cmd = cmd;
    }

    @Override
    public Language getTargetLanguage() {
        String lang = cmd.getTargetLanguage();
        return ConfigAdapter.intoLanguage(lang);
    }

    @Override
    public Language getFromLanguage() {
        String lang = cmd.getFromLanguage();
        return ConfigAdapter.intoLanguage(lang);
    }

    @Override
    public APIMode getApiMode() throws ParserException {
        return APIMode.parse(cmd.getApiMode());
    }

    @Override
    public String getApiKey() {
        return cmd.getApiKey();
    }

    @Override
    public String getTextToTranslate() {
        return cmd.getMessage();
    }

    @Override
    public String getContext() {
        return cmd.getContext();
    }

    @Override
    public String toString() {
        return "ConfigAdapterFromCMD [getTargetLanguage()=" + targetLang() + ", getFromLanguage()="
                + fromLang() + ", getApiMode()=" + apiMode() + "]";

    }

    String fromLang() {
        return getFromLanguage() == null ? "not set" : getFromLanguage().value;
    }

    String targetLang() {
        return getTargetLanguage() == null ? "not set" : getTargetLanguage().value;
    }

    String apiMode() {
        try {
            return getApiMode().toString();
        } catch (ParserException e) {
            return "not set";
        }
    }
}
