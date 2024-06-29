package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.data.mmorpg.StatMod;
import io.github.lcs002.data.mmorpg.UniqueGear;

public class UniqueGearsGeneratorConfig extends GeneratorConfig {
    @JsonProperty("table_content")
    public UniqueGear.Attribute[] tableContent;
    @JsonProperty("stat_content")
    public StatMod.Attribute[] statContent;

    public UniqueGearsGeneratorConfig() {}

    public UniqueGearsGeneratorConfig(String fileName, String title, String description,
                                      UniqueGear.Attribute[] tableContent, StatMod.Attribute[] statContent) {
        super(fileName, title, description);
        this.description = description;
        this.tableContent = tableContent;
        this.statContent = statContent;
    }

    @Override
    public UniqueGearsGeneratorConfig createDefault() {
        return new UniqueGearsGeneratorConfig(
                "unique-items.md",
                "Unique Items",
                "This is the unique items page. Add custom markdown here.",
                new UniqueGear.Attribute[] {
                        UniqueGear.Attribute.GUID, UniqueGear.Attribute.RARITY, UniqueGear.Attribute.BASE_GEAR
                },
                new StatMod.Attribute[] {
                        StatMod.Attribute.STAT, StatMod.Attribute.MIN, StatMod.Attribute.MAX, StatMod.Attribute.TYPE
                }
        );
    }
}
