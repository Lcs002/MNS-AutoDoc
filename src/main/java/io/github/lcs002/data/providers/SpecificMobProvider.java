package io.github.lcs002.data.providers;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.data.DataProvider;
import io.github.lcs002.data.mmorpg.EntityConfig;
import io.github.lcs002.utils.FileUtils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class SpecificMobProvider implements DataProvider<EntityConfig> {
    @Override
    public EntityConfig[] getData() {
        ArrayList<EntityConfig> mobsList = new ArrayList<>();

        Path[] files = FileUtils.getJsonFilesFromDir(ConfigController.getConfig().dataDir + "/" + "mmorpg/mmorpg_entity/specific_mobs");
        System.out.println(ConfigController.getConfig().dataDir + "/" + "mmorpg/mmorpg_entity/specific_mobs");
        for (Path file : files) {
            try {
                String serialized = new String(Files.readAllBytes(file));
                EntityConfig entityConfig = EntityConfig.fromJson(serialized);
                mobsList.add(entityConfig);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }

        return mobsList.toArray(new EntityConfig[0]);
    }
}
