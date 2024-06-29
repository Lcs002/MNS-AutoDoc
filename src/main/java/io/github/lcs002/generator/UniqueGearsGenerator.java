package io.github.lcs002.generator;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.config.UniqueGearsGeneratorConfig;
import io.github.lcs002.data_attribute.EntityAttribute;
import io.github.lcs002.utils.JsonUtils;

import java.util.ArrayList;
import java.util.Map;

public class UniqueGearsGenerator extends Generator<UniqueGearsGeneratorConfig> {
    private static final String DIR = "mmorpg/mmorpg_unique_gears";

    @Override
    protected void setContentBody(StringBuilder content) {

    }

    private String[][] generateUniqueGears(String[] filesPath) {

        return null;
    }

    private String[] generateGear(Map<String, Object> gear) {
        return null;
    }

    @Override
    protected Class<UniqueGearsGeneratorConfig> getGenConfigClass() {
        return UniqueGearsGeneratorConfig.class;
    }
}
