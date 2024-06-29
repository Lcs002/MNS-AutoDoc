package io.github.lcs002.data.mmorpg;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.LocalizationGroup;

import java.util.ArrayList;
import java.util.List;

public class UniqueGear {
    @JsonProperty("unique_stats")
    public List<StatMod> uniqueStats = new ArrayList<>();
    @JsonProperty("weight")
    public int weight = 1000;
    @JsonProperty("min_tier")
    public int minTier = 0;
    @JsonProperty("guid")
    public String guid;
    @JsonProperty("force_item_id")
    public String forceItemId = "";
    @JsonProperty("rarity")
    public String rarity;
    @JsonProperty("replaces_name")
    public boolean replacesName = true;
    @JsonProperty("flavor_text")
    public String flavorText = "";
    @JsonProperty("base_gear")
    public String baseGear = "";
    @JsonProperty("league")
    public String league = "";
    @JsonProperty("runable")
    public boolean runable = false;

    public UniqueGear() {}

    public UniqueGear(List<StatMod> uniqueStats, int weight, int minTier, String guid, String forceItemId,
                      String rarity, boolean replacesName, String flavorText, String baseGear, String league, boolean runable)
    {
        this.uniqueStats = uniqueStats;
        this.weight = weight;
        this.minTier = minTier;
        this.guid = guid;
        this.forceItemId = forceItemId;
        this.rarity = rarity;
        this.replacesName = replacesName;
        this.flavorText = flavorText;
        this.baseGear = baseGear;
        this.league = league;
        this.runable = runable;
    }

    public static UniqueGear fromJson(String json) throws JsonProcessingException {
        return new ObjectMapper().readValue(json, UniqueGear.class);
    }

    public enum Attribute {
        BASE_GEAR(new AttributeLocalizer("gear", LocalizationGroup.WORDS, LocalizationGroup.GEAR_TYPES)),
        FLAVOR_TEXT(new AttributeLocalizer("flavor_text", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        FORCE_ITEM_ID(new AttributeLocalizer("force_item_id", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        GUID(new AttributeLocalizer("guid", LocalizationGroup.WORDS, LocalizationGroup.UNIQUE_GEAR)),
        LEAGUE(new AttributeLocalizer("league", LocalizationGroup.WORDS, LocalizationGroup.LEAGUE)),
        MIN_TIER(new AttributeLocalizer("min_tier", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        RARITY(new AttributeLocalizer("rarity", LocalizationGroup.WORDS, LocalizationGroup.RARITY)),
        REPLACES_NAME(new AttributeLocalizer("replaces_name", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        RUNABLE(new AttributeLocalizer("runable", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        UNIQUE_STATS(new AttributeLocalizer("unique_stats", LocalizationGroup.WORDS, LocalizationGroup.STATS)),
        ;

        Attribute(AttributeLocalizer localizer) {
            this.localizer = localizer;
        }

        public AttributeLocalizer localizer;
    }
}
