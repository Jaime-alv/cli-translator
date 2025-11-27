package com.github.jaime.translator.configuration;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.mapping.ConfigAdapter;
import com.github.jaime.translator.series.APIMode;
import com.github.jaime.translator.series.Language;

public class Config {

    private static Logger logger = LogManager.getLogger();

    private static Config instance;

    public final APIMode mode;
    public final Language target;
    public final Language from;
    public final String apiKey;

    private Config(APIMode mode, Language target, Language from, String apiKey) {
        this.mode = mode;
        this.target = target;
        this.from = from;
        this.apiKey = apiKey;
    }

    public static Config getInstance() {
        if (instance == null) {
            logger.debug("No config found. Build default Config.");
        }
        return instance;
    }

    public static Config getInstance(ConfigAdapter adapter) throws ParserException {
        if (instance == null) {
            instance = new Config(adapter.getApiMode(), adapter.getTargetLanguage(), adapter.getFromLanguage(),
                    adapter.getAPIKey());
        }
        return instance;
    }
}
