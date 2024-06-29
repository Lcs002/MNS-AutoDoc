package io.github.lcs002.data_attribute;

import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.LocalizationGroup;

public enum EntityAttribute {
    DMG_MULTI("dmg_multi",  new AttributeLocalizer("dmg_multi", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    EXP_MULTI("exp_multi",  new AttributeLocalizer("exp_multi", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    HP_MULTI("hp_multi",  new AttributeLocalizer("hp_multi", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    IDENTIFIER("identifier",  new AttributeLocalizer("identifier", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    LOOT_MULTI("loot_multi",  new AttributeLocalizer("loot_multi", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    MAX_LVL("max_lvl",  new AttributeLocalizer("max_lvl", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    MIN_LVL("min_lvl",  new AttributeLocalizer("min_lvl", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    SET_HEALTH_DAMAGE_OVERRIDE("set_health_damage_override", new AttributeLocalizer("set_health_damage_override", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    SET_RAR("set_rar",  new AttributeLocalizer("set_rar", LocalizationGroup.WORDS, LocalizationGroup.MOB_RARITY)),
    STAT_MULTI("stat_multi",  new AttributeLocalizer("stat_multi", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    STATS("stats", new AttributeLocalizer("stats", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    ;

    EntityAttribute(String configName, AttributeLocalizer localizer) {
        this.configName = configName;
        this.localizer = localizer;
    }

    public final String configName;
    public final AttributeLocalizer localizer;

    public static EntityAttribute fromString(String str) {
        for (EntityAttribute entityAttribute : EntityAttribute.values()) {
            if (entityAttribute.configName.equals(str)) {
                return entityAttribute;
            }
        }
        return null;
    }
}