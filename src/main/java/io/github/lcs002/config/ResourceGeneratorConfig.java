package io.github.lcs002.config;

public abstract class ResourceGeneratorConfig<K> extends GeneratorConfig {

    protected ResourceGeneratorConfig() {}

    protected ResourceGeneratorConfig(String fileName, String title, String description, Config globalConfig) {
        super(fileName, title, description, globalConfig);
        this.description = description;
    }
}
