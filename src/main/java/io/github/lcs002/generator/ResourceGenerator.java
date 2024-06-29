package io.github.lcs002.generator;

import io.github.lcs002.config.Config;
import io.github.lcs002.config.ConfigController;
import io.github.lcs002.config.GeneratorConfig;
import io.github.lcs002.utils.FileUtils;
import io.github.lcs002.utils.JsonUtils;

import java.util.Map;

public abstract class ResourceGenerator<T extends GeneratorConfig> extends Generator<T>{
    protected String dir;

    @Override
    public void init(Config config) {
        super.init(config);
        dir = getDir();
    }

    @Override
    protected void setContentBody(StringBuilder content) {
        String[] files = FileUtils.getJsonFilesFromDir(ConfigController.getConfig().dataDir + "/" + dir);
        if (files.length == 0) return;
        setContentResource(content, files);
    }

    protected Map<String, Object> loadResource(String filePath) {
        return JsonUtils.fileToMap(ConfigController.getConfig().dataDir + "/" + dir + "/" + filePath);
    }

    protected abstract void setContentResource(StringBuilder content, String[] files);
    protected abstract String getDir();
}
