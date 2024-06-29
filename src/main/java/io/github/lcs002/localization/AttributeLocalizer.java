package io.github.lcs002.localization;

import org.javatuples.Pair;

import java.util.*;

public class AttributeLocalizer {
    private final String key;
    private final LocalizationGroup keyLocalizationGroup;
    private final LocalizationGroup valueLocalizationGroup;

    public AttributeLocalizer(
            String key,
            LocalizationGroup keyLocalizationGroup,
            LocalizationGroup valueLocalizationGroup)
    {
        this.key = key;
        this.keyLocalizationGroup = keyLocalizationGroup;
        this.valueLocalizationGroup = valueLocalizationGroup;
    }

    public String parseKey(Localization localization) {
        return localizeKey(key, localization);
    }

    public Object parseValue(Object value, Localization localization) {
        return localizeValue(value, localization);
    }

    public Pair<String, Object> parse(Map.Entry<String, Object> entry, Localization localization) {
        String localizedKey = localizeKey(entry.getKey(), localization);
        Object localizedValue = localizeValue(entry.getValue(), localization);
        return new Pair<>(localizedKey, localizedValue);
    }

    private String localizeKey(String key, Localization localization) {
        String localizedKey = LocalizationHandler.localizationFor(key, keyLocalizationGroup, localization);
        if (localizedKey == null) localizedKey = key;
        return localizedKey;
    }

    private Object localizeValue(Object value, Localization localization) {
        return LocalizationHandler.localizationFor(value.toString(), valueLocalizationGroup, localization);
    }
}
