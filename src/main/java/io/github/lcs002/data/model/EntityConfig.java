package io.github.lcs002.data.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.data.Data;
import io.github.lcs002.data.LocatableData;
import io.github.lcs002.data.model.components.SpecialMobStats;
import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.localization.LocalizationGroup;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EntityConfig implements Data, LocatableData {
    @JsonIgnore
    private static final Map<String, AttributeLocalizer> attributes = new HashMap<>();

    static {
        attributes.put(Attributes.DMG_MULTI, new AttributeLocalizer(Attributes.DMG_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.EXP_MULTI, new AttributeLocalizer(Attributes.EXP_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.HP_MULTI, new AttributeLocalizer(Attributes.HP_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.IDENTIFIER, new AttributeLocalizer(Attributes.IDENTIFIER, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.LOOT_MULTI, new AttributeLocalizer(Attributes.LOOT_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.MAX_LVL, new AttributeLocalizer(Attributes.MAX_LVL, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.MIN_LVL, new AttributeLocalizer(Attributes.MIN_LVL, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.SET_HEALTH_DAMAGE_OVERRIDE, new AttributeLocalizer(Attributes.SET_HEALTH_DAMAGE_OVERRIDE, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.SET_RAR, new AttributeLocalizer(Attributes.SET_RAR, LocalizationGroup.WORDS, LocalizationGroup.MOB_RARITY));
        attributes.put(Attributes.STAT_MULTI, new AttributeLocalizer(Attributes.STAT_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE));
        attributes.put(Attributes.STATS, new AttributeLocalizer(Attributes.STATS, LocalizationGroup.WORDS, LocalizationGroup.NONE));
    }

    @JsonProperty(Attributes.DMG_MULTI)
    public double dmg_multi;
    @JsonProperty(Attributes.EXP_MULTI)
    public double exp_multi;
    @JsonProperty(Attributes.HP_MULTI)
    public double hp_multi;
    @JsonProperty(Attributes.IDENTIFIER)
    public String identifier;
    @JsonProperty(Attributes.LOOT_MULTI)
    public double loot_multi;
    @JsonProperty(Attributes.MAX_LVL)
    public int max_lvl;
    @JsonProperty(Attributes.MIN_LVL)
    public int min_lvl;
    @JsonProperty(Attributes.SET_HEALTH_DAMAGE_OVERRIDE)
    public boolean set_health_damage_override;
    @JsonProperty(Attributes.SET_RAR)
    public String set_rar;
    @JsonProperty(Attributes.STAT_MULTI)
    public double stat_multi;
    @JsonProperty(Attributes.STATS)
    public SpecialMobStats stats;

    public static EntityConfig fromJson(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, EntityConfig.class);
    }

    @Override
    public String localizeKey(String attribute, Localization localization) {
        if (!attributes.containsKey(attribute)) {
            throw new RuntimeException("Attribute '" + attribute + "' in " + this.getClass() + " not found.");
        }
        return attributes.get(attribute).parseKey(localization);
    }

    @Override
    public Object localizeValue(String attribute, Localization localization) {
        Object value;

        Class<? extends EntityConfig> clazz = this.getClass();
        try {
            Field field = clazz.getField(attribute);
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
        public static final String IDENTIFIER = "identifier";
        public static final String SET_RAR = "set_rar";
        public static final String LOOT_MULTI = "loot_multi";
        public static final String EXP_MULTI = "exp_multi";
        public static final String MIN_LVL = "min_lvl";
        public static final String MAX_LVL = "max_lvl";
        public static final String DMG_MULTI = "dmg_multi";
        public static final String HP_MULTI = "hp_multi";
        public static final String STAT_MULTI = "stat_multi";
        public static final String STATS = "stats";
        public static final String SET_HEALTH_DAMAGE_OVERRIDE = "set_health_damage_override";
        private Attributes() {}
    }
}
