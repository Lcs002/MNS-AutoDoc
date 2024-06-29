package io.github.lcs002.config;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UniqueGearsGeneratorConfig extends GeneratorConfig {

    public UniqueGearsGeneratorConfig() {}

    public UniqueGearsGeneratorConfig(String fileName, String title, String description) {
        super(fileName, title, description);
        this.description = description;
    }

    @Override
    public UniqueGearsGeneratorConfig createDefault() {
        return new UniqueGearsGeneratorConfig(
                "unique-items.md",
                "Unique Items",
                "This is the unique items page. Add custom markdown here."
        );
    }
}
