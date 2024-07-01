package io.github.lcs002.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.utils.FileUtils;
import org.reflections.Reflections;

import java.io.File;
import java.nio.file.Paths;
import java.util.*;

public class ConfigController {
    private static final String CONFIG_FILE = "mns_autodoc_config.json";
    private static ConfigController instance;

    public static ConfigController getInstance() {
        if (instance == null) {
            instance = new ConfigController();
        }
        return instance;
    }

    private Config config;

    private ConfigController() {}

    public void init() {
        String userDir = Paths.get("").toAbsolutePath().toString();
        File configFile = new File(userDir + "/" + CONFIG_FILE);

        if (configFile.exists() && !configFile.isDirectory()) {
            System.out.println("Config file found.");
            String serializedConfig = FileUtils.readConfigFile(configFile.getPath());
            loadConfig(serializedConfig);
        }
        else {
            System.out.println("Config file not found, creating a new one.");
            config = Config.createDefaultConfig();
            String serializedConfig = serializeConfig(config);
            FileUtils.createFile(configFile.getPath());
            FileUtils.writeToFile(configFile.getPath(), serializedConfig);
        }

        if (Objects.equals(config.dataDir, "")) config.dataDir = userDir;
        if (Objects.equals(config.outDir, "")) config.outDir = userDir;
    }

    public static Config getConfig() {
        return getInstance().config;
    }

    private String serializeConfig(Config config) {
        String genConfigSerialized = null;
        try {
            genConfigSerialized = new ObjectMapper()
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(config);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return genConfigSerialized;
    }

    private void loadConfig(String serializedConfig) {
        try {
            config = new ObjectMapper().readValue(serializedConfig, Config.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
