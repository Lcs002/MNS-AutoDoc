package io.github.lcs002.data.model.components;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import io.github.lcs002.localization.AttributeLocalizer;
import io.github.lcs002.localization.LocalizationGroup;

@JsonRootName("stat")
public class StatMod {
    @JsonProperty("type")
    public String type;
    @JsonProperty("min")
    public float min = 0;
    @JsonProperty("max")
    public float max = 0;
    @JsonProperty("stat")
    public String stat;

    public StatMod() {}

    public StatMod(float min, float max, String stat, String type) {
        this.min = min;
        this.max = max;
        this.stat = stat;
        this.type = type;
    }

    public enum Attribute {
        STAT(new AttributeLocalizer("stat", LocalizationGroup.WORDS, LocalizationGroup.STATS)),
        MIN(new AttributeLocalizer("min", LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        MAX( new AttributeLocalizer("max",LocalizationGroup.WORDS, LocalizationGroup.NONE)),
        TYPE(new AttributeLocalizer("type", LocalizationGroup.WORDS, LocalizationGroup.NONE))
        ;

        Attribute(AttributeLocalizer localizer) {
            this.localizer = localizer;
        }

        public final AttributeLocalizer localizer;
    }
}
