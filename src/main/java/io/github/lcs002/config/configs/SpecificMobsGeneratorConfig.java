package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.data_attribute.StatAttribute;
import io.github.lcs002.data_attribute.EntityAttribute;

public class SpecificMobsGeneratorConfig extends GeneratorConfig {
    @JsonProperty("table_content")
    public EntityAttribute[] tableContent;
    @JsonProperty("stat_content")
    public StatAttribute[] statContent;

    public SpecificMobsGeneratorConfig() {}

    public SpecificMobsGeneratorConfig(String fileName, String title, String description,
                                       EntityAttribute[] tableContent, StatAttribute[] statContent)
    {
        super(fileName, title, description);
        this.description = description;
        this.tableContent = tableContent;
        this.statContent = statContent;
    }

    @Override
    public SpecificMobsGeneratorConfig createDefault() {
        return new SpecificMobsGeneratorConfig(
                "specific_mobs.md",
                "Specific Mobs",
                "This is the specific mobs page. Add custom markdown here.",
                new EntityAttribute[] {
                        EntityAttribute.IDENTIFIER, EntityAttribute.HP_MULTI,
                        EntityAttribute.DMG_MULTI, EntityAttribute.EXP_MULTI,
                        EntityAttribute.LOOT_MULTI, EntityAttribute.STATS
                },
                new StatAttribute[] {
                        StatAttribute.STAT, StatAttribute.MIN,
                        StatAttribute.MAX, StatAttribute.TYPE,
                }
        );
    }
}
