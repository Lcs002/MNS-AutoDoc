package io.github.lcs002.generator;

import io.github.lcs002.config.Config;
import io.github.lcs002.config.ConfigController;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.data.DataProvider;
import io.github.lcs002.utils.FileUtils;
import io.github.lcs002.utils.JsonUtils;

import java.util.Map;

public abstract class ResourceGenerator<T extends GeneratorConfig, K> extends Generator<T>{
    protected DataProvider<K> dataProvider;

    protected ResourceGenerator(Config config, DataProvider<K> dataProvider) {
        super(config);
        this.dataProvider = dataProvider;
    }
}
