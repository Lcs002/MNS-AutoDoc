package io.github.lcs002.generator;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.utils.MarkdownUtils;
import io.github.lcs002.config.MainGeneratorConfig;

public class MainGenerator extends Generator<MainGeneratorConfig> {

    @Override
    protected void setContentBody(StringBuilder content) {}

    @Override
    public Class<MainGeneratorConfig> getGenConfigClass() {
        return MainGeneratorConfig.class;
    }
}
