package io.github.lcs002.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.data.Data;
import io.github.lcs002.data.JsonData;
import io.github.lcs002.data.LocatableData;
import io.github.lcs002.data.model.components.PlayStyle;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.localization.LocalizationGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SupportGem implements JsonData<SupportGem>, LocatableData<SupportGem> {
    private static final Map<String, AttributeLocalizer> attributes = Map.of(
            Attributes.ID, new AttributeLocalizer(Attributes.ID, LocalizationGroup.WORDS, LocalizationGroup.SUPPORT_GEMS),
            Attributes.STYLE, new AttributeLocalizer(Attributes.STYLE, LocalizationGroup.WORDS, LocalizationGroup.PLAY_STYLES),
            Attributes.MIN_LVL, new AttributeLocalizer(Attributes.MIN_LVL, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.MANA_MULTI, new AttributeLocalizer(Attributes.MANA_MULTI, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.STATS, new AttributeLocalizer(Attributes.STATS, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.WEIGHT, new AttributeLocalizer(Attributes.WEIGHT, LocalizationGroup.WORDS, LocalizationGroup.NONE),
            Attributes.ONE_OF_A_KIND, new AttributeLocalizer(Attributes.ONE_OF_A_KIND, LocalizationGroup.WORDS, LocalizationGroup.NONE)
    );

    @JsonProperty(Attributes.ID)
    public String id;
    @JsonProperty(Attributes.STYLE)
    public PlayStyle style;
    @JsonProperty(Attributes.MIN_LVL)
    public int minLvl;
    @JsonProperty(Attributes.MANA_MULTI)
    public float manaMulti;
    @JsonProperty(Attributes.STATS)
    public List<StatMod> stats;
    @JsonProperty(Attributes.WEIGHT)
    public int weight;
    @JsonProperty(Attributes.ONE_OF_A_KIND)
    public String oneOfAKind;

    public SupportGem() {
        this.stats = new ArrayList<>();
    }

    public SupportGem(String id, PlayStyle style, int minLvl, float manaMulti, List<StatMod> stats,
                      int weight, String oneOfAKind)
    {
        this.id = id;
        this.style = style;
        this.minLvl = minLvl;
        this.manaMulti = manaMulti;
        this.stats = stats;
        this.weight = weight;
        this.oneOfAKind = oneOfAKind;
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
        Object value = switch (attribute) {
            case Attributes.ID -> id;
            case Attributes.STYLE -> style;
            case Attributes.MIN_LVL -> minLvl;
            case Attributes.MANA_MULTI -> manaMulti;
            case Attributes.STATS -> stats;
            case Attributes.WEIGHT -> weight;
            case Attributes.ONE_OF_A_KIND -> oneOfAKind;
            default -> throw new IllegalStateException("Unexpected value: " + attribute);
        };
        return attributes.get(attribute).parseValue(value, localization);
    }

    @Override
    public String[] getAttributes() {
        return attributes.keySet().toArray(new String[0]);
    }

    @Override
    public String toJson() throws JsonProcessingException {
        return new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(this);
    }

    @Override
    public SupportGem fromJson(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, SupportGem.class);
    }

    public static class Attributes {
        public static final String ID = "id";
        public static final String STYLE = "style";
        public static final String MIN_LVL = "min_lvl";
        public static final String MANA_MULTI = "manaMulti";
        public static final String STATS = "stats";
        public static final String WEIGHT = "weight";
        public static final String ONE_OF_A_KIND = "one_of_a_kind";
        private Attributes() {}
    }
}
