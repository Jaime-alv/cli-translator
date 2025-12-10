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
    public Language getTargetLanguage() throws ParserException {
        String lang = cmd.getTargetLanguage();
        if (lang == null) {
            return null;
        }
        return Language.parse(lang);
    }

    @Override
    public Language getFromLanguage() throws ParserException {
        String lang = cmd.getFromLanguage();
        if (lang == null) {
            return null;
        }
        return Language.parse(lang);
    }

    @Override
    public APIMode getApiMode() throws ParserException {
        return APIMode.parse(cmd.getApiMode());
    }

    @Override
    public String getApiKey(){
        return cmd.getApiKey();
    }

    @Override
    public String getTextToTranslate(){
        return cmd.getMessage();
    }
}
