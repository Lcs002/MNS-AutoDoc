package io.github.lcs002.data.providers.concrete;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.data.model.SupportGem;
import io.github.lcs002.data.model.UniqueGear;
import io.github.lcs002.data.providers.DataFromJsonProvider;
import io.github.lcs002.data.providers.DataProvider;
import io.github.lcs002.utils.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class SupportGemFromJsonProvider extends DataFromJsonProvider<SupportGem> {
    public SupportGemFromJsonProvider(String currentDir) {
        super(currentDir + "/" + "mmorpg/mmorpg_support_gem", SupportGem.class);
    }
}
