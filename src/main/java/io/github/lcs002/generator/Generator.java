package io.github.lcs002.generator;

import io.github.lcs002.utils.MarkdownUtils;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.utils.FileUtils;

import java.util.ArrayList;
import java.util.List;

public class Generator<T extends GeneratorConfig> {
    protected T generatorConfig;

    protected Generator(T generatorConfig) {
        this.generatorConfig = generatorConfig;
    }

    public final void generate() {
        System.out.println("Generating " + generatorConfig.fileName);
        StringBuilder content = new StringBuilder();
        content.append(createTabs());
        content.append(MarkdownUtils.header(1, generatorConfig.title));
        content.append(generatorConfig.description).append("\n\n");
        setContentBody(content);
        createFile(content.toString());
    }

    private String createTabs() {
        List<String> tabs = new ArrayList<>();
        // TODO Automate this
        tabs.add(MarkdownUtils.link(generatorConfig.globalConfig.mainGeneratorConfig.title, generatorConfig.globalConfig.mainGeneratorConfig.fileName));
        tabs.add(MarkdownUtils.link(generatorConfig.globalConfig.specificMobsGeneratorConfig.title, generatorConfig.globalConfig.specificMobsGeneratorConfig.fileName));
        tabs.add(MarkdownUtils.link(generatorConfig.globalConfig.uniqueGearsGeneratorConfig.title, generatorConfig.globalConfig.uniqueGearsGeneratorConfig.fileName));
        return MarkdownUtils.table(tabs.toArray(new String[0]), new String[][]{});
    }

    private void createFile(String content) {
        String outDir = generatorConfig.globalConfig.outDir + "/mns_autodoc_generated";
        if (!FileUtils.exists(outDir)) FileUtils.createDir(outDir);
        FileUtils.createFile(outDir + "/" + generatorConfig.fileName);
        FileUtils.writeToFile(outDir + "/" + generatorConfig.fileName, content);
    }

    protected void setContentBody(StringBuilder content) {};
}
