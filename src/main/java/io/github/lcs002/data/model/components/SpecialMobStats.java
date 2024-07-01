package io.github.lcs002.data.model.components;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;

public class SpecialMobStats {
    @JsonProperty("stats")
    public List<StatMod> stats;

    public SpecialMobStats() {
        stats = new ArrayList<>();
    }

    public SpecialMobStats(List<StatMod> stats) {
        this.stats = stats;
    }
}
