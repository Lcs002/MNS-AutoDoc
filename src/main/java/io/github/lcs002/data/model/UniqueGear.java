package io.github.lcs002.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.data.Data;
import io.github.lcs002.data.LocatableData;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.localization.LocalizationGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UniqueGear implements Data, LocatableData {
    @JsonIgnore
    public static final Map<String, AttributeLocalizer> attributes = Map.of(
            Attributes.BASE_GEAR, new AttributeLocalizer(Attributes.BASE_GEAR, LocalizationGroup.WORDS, LocalizationGroup.GEAR_TYPES),
            Attributes.FLAVOR_TEXT, new AttributeLocalizer(Attributes.FLAVOR_TEXT, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.FORCE_ITEM_ID, new AttributeLocalizer(Attributes.FORCE_ITEM_ID, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.GUID, new AttributeLocalizer(Attributes.GUID, LocalizationGroup.WORDS, LocalizationGroup.UNIQUE_GEAR),
            Attributes.LEAGUE, new AttributeLocalizer(Attributes.LEAGUE, LocalizationGroup.WORDS, LocalizationGroup.LEAGUE),
            Attributes.MIN_TIER, new AttributeLocalizer(Attributes.MIN_TIER, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.RARITY, new AttributeLocalizer(Attributes.RARITY, LocalizationGroup.WORDS, LocalizationGroup.RARITY),
            Attributes.REPLACES_NAME, new AttributeLocalizer(Attributes.REPLACES_NAME, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.RUNABLE, new AttributeLocalizer(Attributes.RUNABLE, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.UNIQUE_STATS, new AttributeLocalizer(Attributes.UNIQUE_STATS, LocalizationGroup.WORDS, LocalizationGroup.STATS)
    );

    @JsonProperty(Attributes.UNIQUE_STATS)
    public List<StatMod> unique_stats = new ArrayList<>();
    @JsonProperty(Attributes.WEIGHT)
    public int weight = 1000;
    @JsonProperty(Attributes.MIN_TIER)
    public int min_tier = 0;
    @JsonProperty(Attributes.GUID)
    public String guid;
    @JsonProperty(Attributes.FORCE_ITEM_ID)
    public String force_item_id = "";
    @JsonProperty(Attributes.RARITY)
    public String rarity;
    @JsonProperty(Attributes.REPLACES_NAME)
    public boolean replaces_name = true;
    @JsonProperty(Attributes.FLAVOR_TEXT)
    public String flavor_text = "";
    @JsonProperty(Attributes.BASE_GEAR)
    public String base_gear = "";
    @JsonProperty(Attributes.LEAGUE)
    public String league = "";
    @JsonProperty(Attributes.RUNABLE)
    public boolean runable = false;

    public UniqueGear() {}

    public UniqueGear(List<StatMod> unique_stats, int weight, int min_tier, String guid, String force_item_id,
                      String rarity, boolean replaces_name, String flavor_text, String base_gear, String league, boolean runable)
    {
        this.unique_stats = unique_stats;
        this.weight = weight;
        this.min_tier = min_tier;
        this.guid = guid;
        this.force_item_id = force_item_id;
        this.rarity = rarity;
        this.replaces_name = replaces_name;
        this.flavor_text = flavor_text;
        this.base_gear = base_gear;
        this.league = league;
        this.runable = runable;
    }

    public static UniqueGear fromJson(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, UniqueGear.class);
    }

    @Override
    public String localizeKey(String attribute, Localization localization) {
        return attributes.get(attribute).parseKey(localization);
    }

    @Override
    public Object localizeValue(String attribute, Localization localization) {
        Object value;

        Class<? extends UniqueGear> clazz = this.getClass();
        try {
            java.lang.reflect.Field field = clazz.getField(attribute);
            value = field.get(this);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }

        return attributes.get(attribute).parseValue(value, localization);
    }

    @Override
    public String[] getAttributes() {
        return attributes.keySet().toArray(new String[0]);
    }

    public static class Attributes {
        public static final String BASE_GEAR = "base_gear";
        public static final String FLAVOR_TEXT = "flavor_text";
        public static final String FORCE_ITEM_ID = "force_item_id";
        public static final String GUID = "guid";
        public static final String LEAGUE = "league";
        public static final String MIN_TIER = "min_tier";
        public static final String RARITY = "rarity";
        public static final String REPLACES_NAME = "replaces_name";
        public static final String RUNABLE = "runable";
        public static final String UNIQUE_STATS = "unique_stats";
        public static final String WEIGHT = "weight";
    }
}
