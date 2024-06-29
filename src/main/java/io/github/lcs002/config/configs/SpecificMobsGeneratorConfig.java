package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.data.mmorpg.EntityConfig;
import io.github.lcs002.data.mmorpg.StatMod;

public class SpecificMobsGeneratorConfig extends GeneratorConfig {
    @JsonProperty("table_content")
    public EntityConfig.Attribute[] tableContent;
    @JsonProperty("stat_content")
    public StatMod.Attribute[] statContent;

    public SpecificMobsGeneratorConfig() {}

    public SpecificMobsGeneratorConfig(String fileName, String title, String description,
                                       EntityConfig.Attribute[] tableContent, StatMod.Attribute[] statContent)
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
                new EntityConfig.Attribute[] {
                        EntityConfig.Attribute.IDENTIFIER, EntityConfig.Attribute.HP_MULTI,
                        EntityConfig.Attribute.DMG_MULTI, EntityConfig.Attribute.EXP_MULTI,
                        EntityConfig.Attribute.LOOT_MULTI, EntityConfig.Attribute.STATS
                },
                new StatMod.Attribute[] {
                        StatMod.Attribute.STAT, StatMod.Attribute.MIN,
                        StatMod.Attribute.MAX, StatMod.Attribute.TYPE,
                }
        );
    }
}
