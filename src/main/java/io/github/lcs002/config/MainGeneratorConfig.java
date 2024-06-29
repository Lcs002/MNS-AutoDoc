package io.github.lcs002.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainGeneratorConfig extends GeneratorConfig {
    public MainGeneratorConfig() {}

    public MainGeneratorConfig(String fileName, String title, String description) {
        super(fileName, title, description);
        this.description = description;
    }

    @Override
    public MainGeneratorConfig createDefault() {
        return new MainGeneratorConfig(
                "main.md",
                "Main Page",
                "This is the main page. Add custom markdown here."
        );
    }
}
