package io.github.lcs002.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.data_attribute.StatAttribute;
import io.github.lcs002.data_attribute.EntityAttribute;

public class SpecificMobsGeneratorConfig extends GeneratorConfig {
    @JsonProperty("table_content")
    public String[] tableContent;
    @JsonProperty("stat_content")
    public String[] statContent;

    public SpecificMobsGeneratorConfig() {}

    public SpecificMobsGeneratorConfig(String fileName, String title, String description,
                                       String[] tableContent, String[] statContent)
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
                new String[] {
                        EntityAttribute.IDENTIFIER.configName, EntityAttribute.HP_MULTI.configName,
                        EntityAttribute.DMG_MULTI.configName, EntityAttribute.EXP_MULTI.configName,
                        EntityAttribute.LOOT_MULTI.configName, EntityAttribute.STATS.configName
                },
                new String[] {
                        StatAttribute.STAT.configName, StatAttribute.MIN.configName,
                        StatAttribute.MAX.configName, StatAttribute.TYPE.configName,
                }
        );
    }
}
