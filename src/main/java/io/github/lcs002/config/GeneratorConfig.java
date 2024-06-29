package io.github.lcs002.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public abstract class GeneratorConfig implements DefaultConfigProvider<GeneratorConfig> {
    @JsonProperty("file_name")
    public String fileName;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;

    public GeneratorConfig(){}

    public GeneratorConfig(String fileName, String title, String description) {
        this.fileName = fileName;
        this.title = title;
        this.description = description;
    }
}
