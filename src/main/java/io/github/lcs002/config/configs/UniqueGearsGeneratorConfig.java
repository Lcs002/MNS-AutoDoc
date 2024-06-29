package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.data_attribute.StatAttribute;
import io.github.lcs002.data_attribute.UniqueGearAttribute;

public class UniqueGearsGeneratorConfig extends GeneratorConfig {
    @JsonProperty("table_content")
    public UniqueGearAttribute[] tableContent;
    @JsonProperty("stat_content")
    public StatAttribute[] statContent;

    public UniqueGearsGeneratorConfig() {}

    public UniqueGearsGeneratorConfig(String fileName, String title, String description,
                                      UniqueGearAttribute[] tableContent, StatAttribute[] statContent) {
        super(fileName, title, description);
        this.description = description;
        this.tableContent = tableContent;
    }

    @Override
    public UniqueGearsGeneratorConfig createDefault() {
        return new UniqueGearsGeneratorConfig(
                "unique-items.md",
                "Unique Items",
                "This is the unique items page. Add custom markdown here.",
                new UniqueGearAttribute[] {
                        UniqueGearAttribute.GUID, UniqueGearAttribute.RARITY, UniqueGearAttribute.BASE_GEAR
                },
                new StatAttribute[] {
                        StatAttribute.STAT, StatAttribute.MIN, StatAttribute.MAX, StatAttribute.TYPE
                }
        );
    }
}
