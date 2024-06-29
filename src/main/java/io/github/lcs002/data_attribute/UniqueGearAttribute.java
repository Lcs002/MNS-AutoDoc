package io.github.lcs002.data_attribute;

import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.LocalizationGroup;

public enum UniqueGearAttribute {
    BASE_GEAR("base_gear", new AttributeLocalizer("gear", LocalizationGroup.WORDS, LocalizationGroup.GEAR_TYPES)),
    FLAVOR_TEXT("flavor_text", new AttributeLocalizer("flavor_text", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    FORCE_ITEM_ID("force_item_id", new AttributeLocalizer("force_item_id", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    GUID("guid", new AttributeLocalizer("guid", LocalizationGroup.WORDS, LocalizationGroup.UNIQUE_GEAR)),
    LEAGUE("league", new AttributeLocalizer("league", LocalizationGroup.WORDS, LocalizationGroup.LEAGUE)),
    MIN_TIER("min_tier", new AttributeLocalizer("min_tier", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    RARITY("rarity", new AttributeLocalizer("rarity", LocalizationGroup.WORDS, LocalizationGroup.RARITY)),
    REPLACES_NAME("replaces_name", new AttributeLocalizer("replaces_name", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    RUNABLE("runable", new AttributeLocalizer("runable", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    UNIQUE_STATS("unique_stats", new AttributeLocalizer("unique_stats", LocalizationGroup.WORDS, LocalizationGroup.STATS)),
    ;

    UniqueGearAttribute(String configName, AttributeLocalizer localizer) {
        this.configName = configName;
        this.localizer = localizer;
    }

    public final String configName;
    public AttributeLocalizer localizer;
}
