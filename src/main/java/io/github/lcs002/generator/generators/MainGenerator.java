package io.github.lcs002.generator.generators;

import io.github.lcs002.generator.Generator;
import io.github.lcs002.config.configs.MainGeneratorConfig;

public class MainGenerator extends Generator<MainGeneratorConfig> {

    @Override
    protected void setContentBody(StringBuilder content) {}

    @Override
    public Class<MainGeneratorConfig> getGenConfigClass() {
        return MainGeneratorConfig.class;
    }
}
