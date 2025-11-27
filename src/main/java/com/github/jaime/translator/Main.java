package com.github.jaime.translator;

import com.github.jaime.translator.configuration.CommandLineService;
import com.github.jaime.translator.configuration.Config;
import com.github.jaime.translator.configuration.ConfigAdapterFromCMD;
import com.github.jaime.translator.exception.APIException;

public class Main {

    public static void main(String[] args) throws APIException {
        System.out.println("Hello world!");

        CommandLineService cmd = new CommandLineService().parse(args);
        Config config = Config.getInstance(new ConfigAdapterFromCMD(cmd));

        /* 
        Config config = Mapper.fromCMD(cmd);

        Config fromCMD(CommandLineService cmd) {
            Language target = 

        }
        */
    }
}
