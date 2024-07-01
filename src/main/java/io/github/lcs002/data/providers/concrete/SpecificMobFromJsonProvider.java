package io.github.lcs002.data.providers.concrete;

import io.github.lcs002.data.model.Entity;
import io.github.lcs002.data.providers.DataFromJsonProvider;

public class SpecificMobFromJsonProvider extends DataFromJsonProvider<Entity> {
    public SpecificMobFromJsonProvider(String currentDir) {
        super(currentDir + "/" + "mmorpg/mmorpg_entity/specific_mobs", Entity.class);
    }
}
