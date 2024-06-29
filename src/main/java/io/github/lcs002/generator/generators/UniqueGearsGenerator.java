package io.github.lcs002.generator.generators;

import io.github.lcs002.config.configs.UniqueGearsGeneratorConfig;
import io.github.lcs002.data_attribute.UniqueGearAttribute;
import io.github.lcs002.generator.ResourceGenerator;
import io.github.lcs002.utils.MarkdownUtils;

import java.util.ArrayList;
import java.util.Map;

public class UniqueGearsGenerator extends ResourceGenerator<UniqueGearsGeneratorConfig> {

    @Override
    protected void setContentResource(StringBuilder content, String[] files) {
        content.append(generateUniqueGears(files));
    }

    @Override
    protected String getDir() {
        return "mmorpg/mmorpg_unique_gears";
    }

    @Override
    protected Class<UniqueGearsGeneratorConfig> getGenConfigClass() {
        return UniqueGearsGeneratorConfig.class;
    }

    private String generateUniqueGears(String[] filesPath) {
        String[] localizedHeaders = generateHeader();
        String[][] localizedContent = generateContent(filesPath);
        return MarkdownUtils.table(localizedHeaders, localizedContent);
    }

    private String[] generateHeader() {
        String[] localizedHeaders = new String[genConfig.tableContent.length];
        for (int i = 0; i < genConfig.tableContent.length; i++) {
            UniqueGearAttribute uniqueGearAttribute = genConfig.tableContent[i];
            localizedHeaders[i] = uniqueGearAttribute.localizer.parseKey(config.localization);
        }
        return localizedHeaders;
    }

    private String[][] generateContent(String[] filesPath) {
        ArrayList<String[]> uniqueGearsList = new ArrayList<>();
        for (String filePath : filesPath) {
            Map<String, Object> uniqueGear = loadResource(filePath);
            if (uniqueGear.isEmpty() || uniqueGear.get(UniqueGearAttribute.GUID.configName) == null) continue;
            uniqueGearsList.add(generateGear(uniqueGear));
        }
        return uniqueGearsList.toArray(new String[0][0]);
    }

    private String[] generateGear(Map<String, Object> gear) {
        System.out.println("Generating gear: " + gear.get(UniqueGearAttribute.GUID.configName));

        String[] gearData = new String[genConfig.tableContent.length];
        for (int i = 0; i < genConfig.tableContent.length; i++) {
            UniqueGearAttribute uniqueGearAttribute = genConfig.tableContent[i];
            if (uniqueGearAttribute == UniqueGearAttribute.UNIQUE_STATS) gearData[i] = generateStats(gear);
            else gearData[i] = uniqueGearAttribute.localizer.parseValue(gear.get(uniqueGearAttribute.configName), config.localization).toString();
        }
        return gearData;
    }

    private String generateStats(Map<String, Object> stats) {
          StringBuilder statsString = new StringBuilder();
          for (int i = 0; i < genConfig.statContent.length; i++) {
              statsString.append(genConfig.statContent[i].localizer.parseValue(stats.get(genConfig.statContent[i].configName), config.localization));
              statsString.append(" ");
          }
          return statsString.toString();
    }
}
