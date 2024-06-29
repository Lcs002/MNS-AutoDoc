package io.github.lcs002.data_attribute;

public enum GearAttribute {
    BASE_GEAR("base_gear", "BG", "gear_type"),
    FLAVOR_TEXT("flavor_text", "FT", "NONE"),
    FORCE_ITEM_ID("force_item_id", "FID", "NONE"),
    GUID("guid", "GUID", "NONE"),
    LEAGUE("league", "LG","NONE"),
    MIN_TIER("min_tier", "Min T", "NONE"),
    RARITY("rarity", "RAR", "rarity"),
    REPLACES_NAME("replaces_name", "RN", "NONE"),
    RUNABLE("runable", "RUN", "NONE"),
    UNIQUE_STATS("unique_stats", "STATS", "NONE")
    ;

    GearAttribute(String configName, String abreviate, String family) {
        this.configName = configName;
        this.shortName = abreviate;
        this.family = family;
    }

    public final String configName;
    public final String shortName;
    public final String family;
}
