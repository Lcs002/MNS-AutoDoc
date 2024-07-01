package io.github.lcs002.data.model.components;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.github.lcs002.data.LocatableData;
import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.localization.LocalizationGroup;

import java.util.HashMap;
import java.util.Map;

@JsonRootName("stat")
public class StatMod implements LocatableData {
    @JsonIgnore
    private static final Map<String, AttributeLocalizer> attributes = new HashMap<>();

    static {
        attributes.put(Attributes.STAT, new AttributeLocalizer(Attributes.STAT, LocalizationGroup.WORDS, LocalizationGroup.STATS));
        attributes.put(Attributes.MIN, new AttributeLocalizer(Attributes.MIN, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.MAX, new AttributeLocalizer(Attributes.MAX, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.TYPE, new AttributeLocalizer(Attributes.TYPE, LocalizationGroup.WORDS, LocalizationGroup.NONE));
    }

    @JsonProperty("type")
    public String type;
    @JsonProperty("min")
    public float min = 0;
    @JsonProperty("max")
    public float max = 0;
    @JsonProperty("stat")
    public String stat;

    public StatMod() {}

    public StatMod(float min, float max, String stat, String type) {
        this.min = min;
        this.max = max;
        this.stat = stat;
        this.type = type;
    }

    @Override
    public String localizeKey(String attribute, Localization localization) {
        String key = stat;
        if (attributes.containsKey(attribute)) {
            key = attributes.get(attribute).parseKey(localization);
        }
        return key;
    }

    @Override
    public Object localizeValue(String attribute, Localization localization) {
        Object value = null;
        switch (attribute) {
            case Attributes.STAT -> value = stat;
            case Attributes.MIN -> value = min;
            case Attributes.MAX -> value = max;
            case Attributes.TYPE -> value = type;
        }
        return attributes.get(attribute).parseValue(value, localization);
    }

    public static class Attributes {
        public static final String STAT = "stat";
        public static final String MIN = "min";
        public static final String MAX = "max";
        public static final String TYPE = "type";
    }
}
