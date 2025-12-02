package com.github.jaime.translator.parser;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ParserException;

import java.util.Arrays;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;

public class CommandLineService {

    private Logger logger = LogManager.getLogger();

    private static Options options = new Options();
    private CommandLineParser parser = new DefaultParser();
    private CommandLine cmd;

    public CommandLineService() {
        buildOptions();
    }

    public CommandLineService parse(String[] args) throws APIException {
        try {
            logger.debug("Parse arguments from command line {}", Arrays.stream(args).toString());
            this.cmd = parser.parse(options, args);
            
            return this;
        } catch (ParseException exp) {
            logger.error(exp.getMessage());
            throw new ParserException(exp.getMessage());
        }
    }

    private static void buildOptions() {
        options
            .addOption(parseMode())
            .addOption(parseText())
            .addOption(parseApiKey())
            .addOption(parseTargetLanguage())
            .addOption(parseFromLanguage());
    }

    private static Option parseMode() {
        Option modeOption = Option.builder("mode").argName("APIMode").hasArg()
                .desc("Mode option {translate, spelling}").required().get();
        return modeOption;
    }

    private static Option parseText() {
        Option texOption = Option.builder("text").argName("message").hasArg()
                .desc("Text for translation.").required().get();
        return texOption;
    }

    private static Option parseApiKey() {
        Option apiOption = Option.builder("api-key").argName("key").hasArg().desc("DeepL API key")
                .required().get();
        return apiOption;
    }

    private static Option parseTargetLanguage() {
        return Option.builder("target").argName("targetLang").optionalArg(true).desc("Translate to")
                .get();
    }

    private static Option parseFromLanguage() {
        return Option.builder("from").argName("fromLang").optionalArg(true).desc("Translate from")
                .get();
    }

    public String getApiMode() {
        return cmd.getOptionValue("mode");
    }

    public String getMessage() {
        return cmd.getOptionValue("text");
    }

    public String getApiKey() {
        return cmd.getOptionValue("api-key");
    }

    public String getTargetLanguage() {
        return cmd.getOptionValue("target");
    }

    public String getFromLanguage() {
        return cmd.getOptionValue("from");
    }
}
