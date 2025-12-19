package com.github.jaime.translator;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ParserException;
import com.github.jaime.translator.exception.impl.validation.InvalidKeyException;
import com.github.jaime.translator.exception.impl.validation.ValidationException;
import com.github.jaime.translator.parser.CommandLineService;
import com.github.jaime.translator.parser.ConfigAdapterFromCMD;
import com.github.jaime.translator.parser.QuotaFromConfig;
import com.github.jaime.translator.parser.TranslateFromConfig;
import com.github.jaime.translator.parser.adapter.ConfigAdapter;
import com.github.jaime.translator.service.TranslationService;
import com.github.jaime.translator.service.impl.QuotaFromDeepL;
import com.github.jaime.translator.service.impl.TranslateFromDeepL;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws APIException {

        logger.debug("Start new service.");
        CommandLineService cmd = new CommandLineService().parse(args);

        logger.debug("Parse cmd input.");
        ConfigAdapterFromCMD adapter = new ConfigAdapterFromCMD(cmd);
        logger.debug(adapter.toString());        

        TranslationService service = serviceSelector(adapter);
        Optional<String> v = Optional.ofNullable(service.getResponse().getText());

        System.out.println(v.orElse("EMPTY!"));
    }

    static TranslationService serviceSelector(ConfigAdapter adapter)
            throws InvalidKeyException, ValidationException, ParserException {
        switch (adapter.getApiMode()) {
        case TRANSLATE:
            return new TranslateFromDeepL(new TranslateFromConfig(adapter));
        case QUOTA:
            return new QuotaFromDeepL(new QuotaFromConfig(adapter));
        default:
            logger.debug("No service selected.");
            return null;

        }
    }
}
