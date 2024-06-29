package io.github.lcs002.generator;

import io.github.lcs002.config.Config;
import io.github.lcs002.utils.MarkdownUtils;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public abstract class Generator<T extends GeneratorConfig> {
    protected Config config;
    protected T genConfig;

    public void init(Config config) {
        this.config = config;
        genConfig = getGenConfig();
    }

    private void createFile(String content) {
        FileUtils.createFile(config.dataDir + "/" + genConfig.fileName);
        FileUtils.writeToFile(config.dataDir + "/" + genConfig.fileName, content);
    }

    protected String createTabs() {
        List<String> tabs = new ArrayList<>();

        for (GeneratorConfig generatorConfig : config.configs) {
            tabs.add(MarkdownUtils.link(generatorConfig.title, generatorConfig.fileName));
        }

        return MarkdownUtils.table(tabs.toArray(new String[0]), new String[][]{});
    }

    private T getGenConfig() {
        return config.forGenerator(getGenConfigClass());
    }

    public final void generate() {
        System.out.println("Generating " + genConfig.fileName);
        StringBuilder content = new StringBuilder();
        content.append(createTabs());
        content.append(MarkdownUtils.header(1, genConfig.title));
        content.append(genConfig.description).append("\n\n");
        setContentBody(content);
        createFile(content.toString());
    }

    protected abstract void setContentBody(StringBuilder content);
    protected abstract Class<T> getGenConfigClass();
}
