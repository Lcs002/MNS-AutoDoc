package io.github.lcs002.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.lcs002.config.configs.MainGeneratorConfig;
import io.github.lcs002.config.configs.SpecificMobsGeneratorConfig;
import io.github.lcs002.config.configs.UniqueGearsGeneratorConfig;
import io.github.lcs002.localization.Localization;

public class Config {
    @JsonProperty("data_dir")
    public String dataDir;
    @JsonProperty("out_dir")
    public String outDir;
    @JsonProperty("localization")
    public Localization localization;
    @JsonProperty("main_generator_config")
    public MainGeneratorConfig mainGeneratorConfig;
    @JsonProperty("specific_mobs_generator_config")
    public SpecificMobsGeneratorConfig specificMobsGeneratorConfig;
    @JsonProperty("unique_gears_generator_config")
    public UniqueGearsGeneratorConfig uniqueGearsGeneratorConfig;

    public Config() {}


    public static Config createDefaultConfig()
    {
        Config config = new Config();
        config.dataDir = "";
        config.outDir = "";
        config.localization = Localization.EN_US;
        config.mainGeneratorConfig = new MainGeneratorConfig().createDefault(config);
        config.specificMobsGeneratorConfig = new SpecificMobsGeneratorConfig().createDefault(config);
        config.uniqueGearsGeneratorConfig = new UniqueGearsGeneratorConfig().createDefault(config);
        return config;
    }
}
