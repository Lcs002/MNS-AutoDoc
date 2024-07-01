package io.github.lcs002.data.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.data.model.components.PlayStyle;
import io.github.lcs002.data.model.components.StatMod;

import java.util.ArrayList;
import java.util.List;

public class SupportGem {
    @JsonProperty("id")
    public String id;
    @JsonProperty("style")
    public PlayStyle style;
    @JsonProperty("min_lvl")
    public int min_lvl;
    @JsonProperty("mana_multi")
    public float manaMulti;
    @JsonProperty("stats")
    public List<StatMod> stats;
    @JsonProperty("weight")
    public int weight;
    @JsonProperty("one_of_a_kind")
    public String one_of_a_kind;

    public SupportGem() {
        this.stats = new ArrayList<>();
    }

    public SupportGem(String id, PlayStyle style, int min_lvl, float manaMulti, List<StatMod> stats,
                      int weight, String one_of_a_kind)
    {
        this.id = id;
        this.style = style;
        this.min_lvl = min_lvl;
        this.manaMulti = manaMulti;
        this.stats = stats;
        this.weight = weight;
        this.one_of_a_kind = one_of_a_kind;
    }
}
