package io.github.lcs002.generator;

import io.github.lcs002.config.ResourceGeneratorConfig;
import io.github.lcs002.data.providers.DataProvider;
import io.github.lcs002.data.view.DataView;

public class ResourceGenerator<T extends ResourceGeneratorConfig<K>, K> extends Generator<T>{
    protected DataProvider<K> dataProvider;
    protected DataView<K[]> view;

    protected ResourceGenerator(T config, DataProvider<K> dataProvider, DataView<K[]> view) {
        super(config);
        this.dataProvider = dataProvider;
        this.view = view;
    }

    @Override
    protected void setContentBody(StringBuilder content) {
        content.append(view.show(dataProvider.getData(), generatorConfig.globalConfig.localization));
    }
}
