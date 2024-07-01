package io.github.lcs002.config.configs;

import io.github.lcs002.config.Config;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.config.ResourceGeneratorConfig;
import io.github.lcs002.data.model.StatInfo;

public class StatInfoGeneratorConfig extends ResourceGeneratorConfig<StatInfo> {
    public String[] attributes;

    public StatInfoGeneratorConfig() {}

    public StatInfoGeneratorConfig(String fileName, String title, String description, Config config, String[] attributes) {
        super(fileName, title, description, config);
        this.attributes = attributes;
    }

    @Override
    public StatInfoGeneratorConfig createDefault(Config config) {
        return new StatInfoGeneratorConfig(
            "stat_info.md",
            "Stat Info",
            "This is the stat info page. Add custom markdown here.",
            config,
            new String[] {
                StatInfo.Attributes.ID, StatInfo.Attributes.DESC
            }
        );
    }
}
