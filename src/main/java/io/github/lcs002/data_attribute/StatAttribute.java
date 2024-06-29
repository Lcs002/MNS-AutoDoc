package io.github.lcs002.data_attribute;

import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.LocalizationGroup;

public enum StatAttribute {
    STAT("stat", new AttributeLocalizer("stat", LocalizationGroup.WORDS, LocalizationGroup.STATS)),
    MIN("min", new AttributeLocalizer("min", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    MAX("max", new AttributeLocalizer("max",LocalizationGroup.WORDS, LocalizationGroup.NONE)),
    TYPE("type", new AttributeLocalizer("type", LocalizationGroup.WORDS, LocalizationGroup.NONE))
    ;

    StatAttribute(String configName, AttributeLocalizer localizer) {
        this.configName = configName;
        this.localizer = localizer;
    }

    public final String configName;
    public final AttributeLocalizer localizer;

    public static StatAttribute fromString(String str) {
        for (StatAttribute statAttribute : StatAttribute.values()) {
            if (statAttribute.configName.equals(str)) {
                return statAttribute;
            }
        }
        return null;
    }
}