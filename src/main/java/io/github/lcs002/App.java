package io.github.lcs002;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.config.Config;
import io.github.lcs002.config.ConfigController;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.utils.FileUtils;
import io.github.lcs002.generator.GeneratorController;

import java.io.File;
import java.nio.file.Paths;
import java.util.Objects;

/**
 * Hello world!
 *
 */
public class App 
{


    public static void main( String[] args )
    {
        ConfigController.getInstance().init();
        GeneratorController.getInstance().init();
        GeneratorController.getInstance().generate();
    }
}
