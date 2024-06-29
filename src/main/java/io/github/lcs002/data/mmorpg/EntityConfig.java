package io.github.lcs002.data.mmorpg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.LocalizationGroup;

public class EntityConfig {
    @JsonProperty(Constants.DMG_MULTI)
    public double dmgMulti;
    @JsonProperty(Constants.EXP_MULTI)
    public double exp_multi;
    @JsonProperty(Constants.HP_MULTI)
    public double hpMulti;
    @JsonProperty(Constants.IDENTIFIER)
    public String identifier;
    @JsonProperty(Constants.LOOT_MULTI)
    public double lootMulti;
    @JsonProperty(Constants.MAX_LVL)
    public int maxLvl;
    @JsonProperty(Constants.MIN_LVL)
    public int minLvl;
    @JsonProperty(Constants.SET_HEALTH_DAMAGE_OVERRIDE)
    public boolean setHealthDamageOverride;
    @JsonProperty(Constants.SET_RAR)
    public String setRar;
    @JsonProperty(Constants.STAT_MULTI)
    public double statMulti;
    @JsonProperty(Constants.STATS)
    public SpecialMobStats stats;

    public static EntityConfig fromJson(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, EntityConfig.class);
    }

    public enum Attribute {
        DMG_MULTI(new AttributeLocalizer(Constants.DMG_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        EXP_MULTI(new AttributeLocalizer(Constants.EXP_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        HP_MULTI(new AttributeLocalizer(Constants.HP_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        IDENTIFIER(new AttributeLocalizer(Constants.IDENTIFIER, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        LOOT_MULTI(new AttributeLocalizer(Constants.LOOT_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        MAX_LVL(new AttributeLocalizer(Constants.MAX_LVL, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        MIN_LVL(new AttributeLocalizer(Constants.MIN_LVL, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        SET_HEALTH_DAMAGE_OVERRIDE(new AttributeLocalizer(Constants.SET_HEALTH_DAMAGE_OVERRIDE, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        SET_RAR(new AttributeLocalizer(Constants.SET_RAR, LocalizationGroup.WORDS, LocalizationGroup.MOB_RARITY)),
        STAT_MULTI(new AttributeLocalizer(Constants.STAT_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        STATS(new AttributeLocalizer(Constants.STATS, LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        ;

        Attribute(AttributeLocalizer localizer) {
            this.localizer = localizer;
        }

        public final AttributeLocalizer localizer;
    }

    private static class Constants {
        private static final String IDENTIFIER = "identifier";
        private static final String SET_RAR = "set_rar";
        private static final String LOOT_MULTI = "loot_multi";
        private static final String EXP_MULTI = "exp_multi";
        private static final String MIN_LVL = "min_lvl";
        private static final String MAX_LVL = "max_lvl";
        private static final String DMG_MULTI = "dmg_multi";
        private static final String HP_MULTI = "hp_multi";
        private static final String STAT_MULTI = "stat_multi";
        private static final String STATS = "stats";
        private static final String SET_HEALTH_DAMAGE_OVERRIDE = "set_health_damage_override";
    }
}
