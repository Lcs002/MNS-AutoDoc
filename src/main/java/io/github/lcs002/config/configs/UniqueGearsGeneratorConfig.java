package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.Config;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.config.ResourceGeneratorConfig;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.data.model.UniqueGear;

public class UniqueGearsGeneratorConfig extends ResourceGeneratorConfig<UniqueGear> {
    @JsonProperty("table_content")
    public String[] tableContent;
    @JsonProperty("stat_content")
    public StatMod.Attribute[] statContent;

    public UniqueGearsGeneratorConfig() {}

    public UniqueGearsGeneratorConfig(String fileName, String title, String description, Config config,
                                      String[] tableContent, StatMod.Attribute[] statContent) {
        super(fileName, title, description, config);
        this.tableContent = tableContent;
        this.statContent = statContent;
    }

    @Override
    public UniqueGearsGeneratorConfig createDefault(Config config) {
        return new UniqueGearsGeneratorConfig(
                "unique-items.md",
                "Unique Items",
                "This is the unique items page. Add custom markdown here.",
                config,
                new String[] {
                        UniqueGear.Attributes.GUID, UniqueGear.Attributes.RARITY, UniqueGear.Attributes.BASE_GEAR
                },
                new StatMod.Attribute[] {
                        StatMod.Attribute.STAT, StatMod.Attribute.MIN, StatMod.Attribute.MAX, StatMod.Attribute.TYPE
                }
        );
    }
}
