package io.github.lcs002.config.configs;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.Config;
import io.github.lcs002.config.ResourceGeneratorConfig;
import io.github.lcs002.data.model.Entity;
import io.github.lcs002.data.model.components.StatMod;

public class SpecificMobsGeneratorConfig extends ResourceGeneratorConfig<Entity> {
    @JsonProperty("attributes")
    public String[] attributes;
    @JsonProperty("stats")
    public String[] stats;

    public SpecificMobsGeneratorConfig() {}

    public SpecificMobsGeneratorConfig(String fileName, String title, String description,
                                       Config config, String[] stats, String[] attributes)
    {
        super(fileName, title, description, config);
        this.attributes = attributes;
        this.stats = stats;
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
                        Entity.Attributes.IDENTIFIER, Entity.Attributes.HP_MULTI,
                        Entity.Attributes.DMG_MULTI, Entity.Attributes.EXP_MULTI,
                        Entity.Attributes.LOOT_MULTI, Entity.Attributes.STATS
                }
        );
    }
}
