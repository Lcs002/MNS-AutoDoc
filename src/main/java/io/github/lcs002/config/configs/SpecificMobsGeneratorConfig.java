package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.Config;
import io.github.lcs002.config.ResourceGeneratorConfig;
import io.github.lcs002.data.model.EntityConfig;
import io.github.lcs002.data.model.components.StatMod;

public class SpecificMobsGeneratorConfig extends ResourceGeneratorConfig<EntityConfig> {
    @JsonProperty("table_content")
    public String[] tableContent;
    @JsonProperty("stat_content")
    public String[] statContent;

    public SpecificMobsGeneratorConfig() {}

    public SpecificMobsGeneratorConfig(String fileName, String title, String description,
                                       Config config, String[] statContent, String[] tableContent)
    {
        super(fileName, title, description, config);
        this.tableContent = tableContent;
        this.statContent = statContent;
    }

    @Override
    public SpecificMobsGeneratorConfig createDefault(Config config) {
        return new SpecificMobsGeneratorConfig(
                "specific_mobs.md",
                "Specific Mobs",
                "This is the specific mobs page. Add custom markdown here.",
                config,
                new String[] {
                        StatMod.Attributes.STAT, StatMod.Attributes.MIN,
                        StatMod.Attributes.MAX, StatMod.Attributes.TYPE,
                }, new String[] {
                        EntityConfig.Attributes.IDENTIFIER, EntityConfig.Attributes.HP_MULTI,
                        EntityConfig.Attributes.DMG_MULTI, EntityConfig.Attributes.EXP_MULTI,
                        EntityConfig.Attributes.LOOT_MULTI, EntityConfig.Attributes.STATS
                }
        );
    }
}
