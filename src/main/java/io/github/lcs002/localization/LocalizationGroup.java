package io.github.lcs002.localization;

import java.util.List;

public enum LocalizationGroup {
    NONE(List.of()),
    ITEMS(List.of(
            "item.mmorpg.currency",
            "item.mmorpg.stat_soul.family"
    )),
    GEAR_SLOTS(List.of(
            "mmorpg.gear_slot"
    )),
    GEAR_TYPES(List.of(
            "mmorpg.gear_type"
    )),
    UNIQUE_GEAR(List.of(
            "mmorpg.unique_gear"
    )),
    WORDS(List.of(
            "mmorpg.word",
            "autodoc.word"
    )),
    STATS(List.of(
            "mmorpg.stat",
            "mmorpg.stat_group",
            "autodoc.stat"
    )),
    MOB_RARITY(List.of(
            "mmorpg.mob_rarity"
    )),
    RARITY(List.of(
            "mmorpg.rarity"
    )),
    DESC(List.of(
            "mmorpg.stat_desc",
            "spell.desc"
    )),
    AURA(List.of(
            "mmorpg.aura"
    )),
    LEAGUE(List.of(
            "mmorpg.league"
    )),
    ;
    LocalizationGroup(List<String> groups) {
        this.groups = groups;
    }

    public final List<String> groups;
}
