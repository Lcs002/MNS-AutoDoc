package io.github.lcs002.data.providers;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.data.DataProvider;
import io.github.lcs002.data.mmorpg.EntityConfig;
import io.github.lcs002.data.mmorpg.UniqueGear;
import io.github.lcs002.utils.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class UniqueGearProvider implements DataProvider<UniqueGear> {
    @Override
    public UniqueGear[] getData() {
        ArrayList<UniqueGear> gears = new ArrayList<>();

        Path[] files = FileUtils.getJsonFilesFromDir(ConfigController.getConfig().dataDir + "/" + "mmorpg/mmorpg_unique_gears");

        for (Path file : files) {
            try {
                String serialized = new String(Files.readAllBytes(file));
                UniqueGear gear = UniqueGear.fromJson(serialized);
                gears.add(gear);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return gears.toArray(new UniqueGear[0]);
    }
}
