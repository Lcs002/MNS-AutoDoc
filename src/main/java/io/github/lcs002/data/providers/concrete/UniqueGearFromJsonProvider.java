package io.github.lcs002.data.providers.concrete;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.data.model.UniqueGear;
import io.github.lcs002.data.providers.DataFromJsonProvider;
import io.github.lcs002.data.providers.DataProvider;
import io.github.lcs002.utils.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class UniqueGearFromJsonProvider extends DataFromJsonProvider<UniqueGear> {
    public UniqueGearFromJsonProvider(String currentDir) {
        super(currentDir + "/" + "mmorpg/mmorpg_unique_gears", UniqueGear.class);
    }
}
