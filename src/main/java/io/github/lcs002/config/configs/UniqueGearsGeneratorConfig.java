package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.Config;
import io.github.lcs002.config.ResourceGeneratorConfig;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.data.model.UniqueGear;

public class UniqueGearsGeneratorConfig extends ResourceGeneratorConfig<UniqueGear> {
    @JsonProperty("attributes")
    public String[] attributes;
    @JsonProperty("stats")
    public String[] stats;

    public UniqueGearsGeneratorConfig() {}

    public UniqueGearsGeneratorConfig(String fileName, String title, String description, Config config,
                                      String[] attributes, String[] stats) {
        super(fileName, title, description, config);
        this.attributes = attributes;
        this.stats = stats;
    }

    @Override
    public UniqueGearsGeneratorConfig createDefault(Config config) {
        return new UniqueGearsGeneratorConfig(
                "unique-items.md",
                "Unique Items",
                "This is the unique items page. Add custom markdown here.",
                config,
                new String[] {
                        UniqueGear.Attributes.GUID, UniqueGear.Attributes.RARITY, UniqueGear.Attributes.BASE_GEAR, UniqueGear.Attributes.UNIQUE_STATS
                },
                new String[] {
                        StatMod.Attributes.STAT, StatMod.Attributes.MIN, StatMod.Attributes.MAX, StatMod.Attributes.TYPE
                }
        );
    }
}
