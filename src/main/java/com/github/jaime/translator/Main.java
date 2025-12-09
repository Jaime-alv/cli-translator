package com.github.jaime.translator;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.InvalidKeyException;
import com.github.jaime.translator.exception.impl.ValidationException;
import com.github.jaime.translator.parser.CommandLineService;
import com.github.jaime.translator.parser.Config;
import com.github.jaime.translator.parser.ConfigAdapterFromCMD;
import com.github.jaime.translator.parser.QuotaFromConfig;
import com.github.jaime.translator.parser.TranslateFromConfig;
import com.github.jaime.translator.service.TranslationService;
import com.github.jaime.translator.service.impl.QuotaFromDeepL;
import com.github.jaime.translator.service.impl.TranslateFromDeepL;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws APIException {

        logger.debug("Start new service.");
        CommandLineService cmd = new CommandLineService().parse(args);

        logger.debug("Parse cmd input.");
        Config config = Config.getInstance(new ConfigAdapterFromCMD(cmd));

        TranslationService service = serviceSelector(config);
        Optional<String> v = Optional.ofNullable(service.getResponse().getText());

        System.out.println(v.orElse("EMPTY!"));
    }

    static TranslationService serviceSelector(Config config)
            throws InvalidKeyException, ValidationException {
        switch (config.getMode()) {
        case TRANSLATE:
            return new TranslateFromDeepL(new TranslateFromConfig(config));
        case QUOTA:
            return new QuotaFromDeepL(new QuotaFromConfig(config));
        default:
            logger.debug("No service selected.");
            return null;

        }
    }
}
