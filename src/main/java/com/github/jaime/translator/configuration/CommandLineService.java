package com.github.jaime.translator.configuration;

import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.github.jaime.translator.exception.APIException;
import com.github.jaime.translator.exception.impl.ParserException;
import java.util.Optional;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Option;

public class CommandLineService {

    private static Logger logger = LogManager.getLogger();

    private static Options options = new Options();
    private CommandLineParser parser = new DefaultParser();
    private CommandLine cmd;

    public CommandLineService() {
        buildOptions();
    }

    public CommandLineService parse(String[] args) throws APIException {
        try {
            this.cmd = parser.parse(options, args);
            return this;
        } catch (ParseException exp) {
            logger.error(exp.getMessage());
            throw new ParserException(exp.getMessage());
        }
    }

    private static void buildOptions() {
        options.addOption(parseMode());
        options.addOption(parseText());
        options.addOption(parseApiKey());
        options.addOption(parseTargetLanguage());
    }

    private static Option parseMode() {
        Option modeOption = Option
                .builder("mode")
                .argName("APIMode")
                .hasArg()
                .desc("Mode option {translate, spelling}")
                .required()
                .get();
        return modeOption;
    }

    private static Option parseText() {
        Option texOption = Option.builder("text")
                .argName("message")
                .hasArg()
                .desc("Text for translation.")
                .required()
                .get();
        return texOption;
    }

    private static Option parseApiKey() {
        Option apiOption = Option
                .builder("api-key")
                .argName("key")
                .hasArg()
                .desc("DeepL API key")
                .required()
                .get();
        return apiOption;
    }

    private static Option parseTargetLanguage() {
        return Option
                .builder("target")
                .argName("targetLang")
                .optionalArg(true)
                .desc("Translate to")
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

    public Optional<String> getTargetLanguage() {
        return Optional.ofNullable(cmd.getOptionValue("target"));
    }

    public Optional<String> getFromLanguage() {
        return Optional.ofNullable(cmd.getOptionValue("from"));
    }
}
