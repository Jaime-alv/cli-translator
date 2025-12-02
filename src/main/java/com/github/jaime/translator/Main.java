package com.github.jaime.translator;

import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.parser.CommandLineService;
import com.github.jaime.translator.parser.Config;
import com.github.jaime.translator.parser.ConfigAdapterFromCMD;
import com.github.jaime.translator.parser.TranslateFromConfig;
import com.github.jaime.translator.service.TranslationService;
import com.github.jaime.translator.service.impl.TranslateFromDeepL;

public class Main {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) throws APIException {

        logger.debug("Start new service.");
        CommandLineService cmd = new CommandLineService().parse(args);

        logger.debug("Parse cmd input.");
        Config config = Config.getInstance(new ConfigAdapterFromCMD(cmd));

        TranslationService service;
        switch (config.getMode()) {
        case SPELLING:
            service = null;
            break;
        default:
            service = new TranslateFromDeepL(new TranslateFromConfig(config));
            break;
        }
        Optional<String> v = Optional.ofNullable(service.getResponse().getText());

        logger.debug("Print debugrmation.");
        System.out.println(v.orElse("EMPTY!"));
        /*
         * Config config = Mapper.fromCMD(cmd);
         * 
         * Config fromCMD(CommandLineService cmd) { Language target =
         * 
         * }
         */
    }
}
