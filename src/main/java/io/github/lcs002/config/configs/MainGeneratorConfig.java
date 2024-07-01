package io.github.lcs002.config.configs;

import io.github.lcs002.config.Config;
import io.github.lcs002.config.GeneratorConfig;

public class MainGeneratorConfig extends GeneratorConfig {
    public MainGeneratorConfig() {}

    public MainGeneratorConfig(String fileName, String title, String description, Config config) {
        super(fileName, title, description, config);
    }

    @Override
    public MainGeneratorConfig createDefault(Config config) {
        return new MainGeneratorConfig(
                "mns-autodoc.md",
                "Main Page",
                "This is the main page. Add custom markdown here.",
                config
        );
    }
}
