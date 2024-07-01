package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.Config;
import io.github.lcs002.config.ResourceGeneratorConfig;
import io.github.lcs002.data.model.SupportGem;
import io.github.lcs002.data.model.components.StatMod;

public class SupportGemGeneratorConfig extends ResourceGeneratorConfig<SupportGem> {
    @JsonProperty("attributes")
    public String[] attributes;
    @JsonProperty("stats")
    public String[] stats;

    public SupportGemGeneratorConfig() {}

    public SupportGemGeneratorConfig(String fileName, String title, String description, Config config,
                                     String[] attributes, String[] statModContent)
    {
        super(fileName, title, description, config);
        this.attributes = attributes;
        this.stats = statModContent;
    }

    @Override
    public SupportGemGeneratorConfig createDefault(Config config) {
        return new SupportGemGeneratorConfig(
                "support_gems.md",
                "Support Gems",
                "This is the support gems page. Add custom markdown here.",
                config,
                new String[] {
                        SupportGem.Attributes.ID, SupportGem.Attributes.MIN_LVL,
                        SupportGem.Attributes.STYLE, SupportGem.Attributes.STATS
                },
                new String[] {
                        StatMod.Attributes.STAT, StatMod.Attributes.MIN,
                        StatMod.Attributes.MAX, StatMod.Attributes.TYPE
                }
        );
    }
}
