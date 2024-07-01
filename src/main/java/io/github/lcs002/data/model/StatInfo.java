package io.github.lcs002.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.data.JsonData;
import io.github.lcs002.data.LocatableData;
import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.localization.LocalizationGroup;
import io.github.lcs002.utils.Printer;

import java.util.HashMap;
import java.util.Map;

public class StatInfo implements JsonData<StatInfo>, LocatableData<StatInfo> {

    private static final Map<String, AttributeLocalizer> attributes = new HashMap<>();

    static {
        attributes.put(Attributes.ID, new AttributeLocalizer(Attributes.ID, LocalizationGroup.WORDS, LocalizationGroup.STATS));
        attributes.put(Attributes.DESC, new AttributeLocalizer(Attributes.DESC, LocalizationGroup.WORDS, LocalizationGroup.DESC));
    }

    @JsonProperty("id")
    public String id;

    @Override
    public String[] getAttributes() {
        return new String[0];
    }

    @Override
    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    @Override
    public StatInfo fromJson(String json) throws JsonProcessingException {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .readValue(json, StatInfo.class);
    }

    @Override
    public String localizeKey(String attribute, Localization localization) {
        String key;
        if (attributes.containsKey(attribute)) {
            key = attributes.get(attribute).parseKey(localization);
        } else {
            key = attribute;
        }
        return key;
    }

    @Override
    public Object localizeValue(String attribute, Localization localization) {
        if (id == null) {
            Printer.printDebug("Cannot localize value for attribute " + attribute + " because id is null");
            return "NULL";
        }

        return switch (attribute) {
            case Attributes.ID, Attributes.DESC -> attributes.get(attribute).parseValue(id, localization);
            default -> "NULL";
        };
    }

    public static class Attributes {
        public static final String ID = "id";
        public static final String DESC = "description";

        private Attributes() {}
    }
}
