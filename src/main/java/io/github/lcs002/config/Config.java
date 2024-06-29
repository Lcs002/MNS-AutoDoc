package io.github.lcs002.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.localization.Localization;

public class Config implements DefaultConfigProvider<Config> {
    @JsonProperty("data_dir")
    public String dataDir;
    @JsonProperty("out_dir")
    public String outDir;
    @JsonProperty("localization")
    public Localization localization;
    @JsonProperty("configs")
    public GeneratorConfigList configs = new GeneratorConfigList();

    public Config() {}

    @Override
    public Config createDefault()
    {
        Config config = new Config();
        config.dataDir = "";
        config.outDir = "";
        config.localization = Localization.EN_US;
        return config;
    }

    public <T extends GeneratorConfig> T forGenerator(Class<T> clazz) {
        for (GeneratorConfig config : configs) {
            if (clazz.isInstance(config)) {
                return (T) config;
            }
        }
        return null;
    }
}
