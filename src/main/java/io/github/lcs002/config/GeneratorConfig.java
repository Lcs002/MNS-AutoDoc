package io.github.lcs002.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, property = "@class")
public abstract class GeneratorConfig implements DefaultConfigProvider<GeneratorConfig> {
    @JsonIgnore
    public Config globalConfig;
    @JsonProperty("file_name")
    public String fileName;
    @JsonProperty("title")
    public String title;
    @JsonProperty("description")
    public String description;

    protected GeneratorConfig(){}

    protected GeneratorConfig(String fileName, String title, String description, Config globalConfig) {
        this.fileName = fileName;
        this.title = title;
        this.description = description;
        this.globalConfig = globalConfig;
    }
}
