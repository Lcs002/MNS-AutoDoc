package io.github.lcs002.data.mmorpg;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OptScaleExactStat {
    @JsonProperty("v1")
    public float v1 = 0;
    @JsonProperty("stat")
    public String stat;
    @JsonProperty("type")
    public String type;
    @JsonProperty("scale_to_lvl")
    public boolean scaleToLvl = false;

    public OptScaleExactStat() {}

    public OptScaleExactStat(float v1, String stat, String type, boolean scaleToLvl) {
        this.v1 = v1;
        this.stat = stat;
        this.type = type;
        this.scaleToLvl = scaleToLvl;
    }
}
