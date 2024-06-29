package io.github.lcs002.generator;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.config.SpecificMobsGeneratorConfig;
import io.github.lcs002.data_attribute.EntityAttribute;
import io.github.lcs002.data_attribute.StatAttribute;
import io.github.lcs002.utils.MarkdownUtils;
import io.github.lcs002.utils.FileUtils;
import io.github.lcs002.utils.JsonUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SpecificMobsGenerator extends Generator<SpecificMobsGeneratorConfig> {
    private static final String DIR = "mmorpg/mmorpg_entity/specific_mobs";

    @Override
    protected void setContentBody(StringBuilder content) {
        String[] filePaths = FileUtils.getJsonFilesFromDir(ConfigController.getConfig().dataDir + "/" + DIR);
        if (filePaths.length == 0) return;
        content.append(generateMobs(filePaths));
    }

    @Override
    public Class<SpecificMobsGeneratorConfig> getGenConfigClass() {
        return SpecificMobsGeneratorConfig.class;
    }

    private String generateMobs(String[] filesPath)
    {
        ArrayList<String[]> mobsList = new ArrayList<>();
        for (String filePath : filesPath) {
            Map<String, Object> mob = JsonUtils.fileToMap(ConfigController.getConfig().dataDir + "/" + DIR + "/" + filePath);
            if (mob.isEmpty() || mob.get(EntityAttribute.IDENTIFIER.configName) == null) continue;
            mobsList.add(generateMob(mob));
        }

        String[][] mobs = new String[mobsList.size()][genConfig.tableContent.length];
        for (int i = 0; i < mobsList.size(); i++) {
            mobs[i] = mobsList.get(i);
        }

        String[] localizedHeaders = new String[genConfig.tableContent.length];
        for (int i = 0; i < genConfig.tableContent.length; i++) {
            EntityAttribute entityAttributeFormat = EntityAttribute.fromString(genConfig.tableContent[i]);
            localizedHeaders[i] = entityAttributeFormat.localizer.parseKey(ConfigController.getConfig().localization);
        }

        return MarkdownUtils.table(localizedHeaders, mobs);
    }

    private String[] generateMob(Map<String, Object> mob) {
        System.out.println("Generating mob: " + mob.get(EntityAttribute.IDENTIFIER.configName));

        String[] mobData = new String[genConfig.tableContent.length];
        for (int i = 0; i < genConfig.tableContent.length; i++) {
            EntityAttribute entityAttributeFormat = EntityAttribute.fromString(genConfig.tableContent[i]);
            mobData[i] = switch (entityAttributeFormat) {
                case null -> "N/A";
                case IDENTIFIER -> identifierToName(String.valueOf(mob.get(entityAttributeFormat.configName)));
                case STATS -> getStats((Map<String, Object>) mob.get(entityAttributeFormat.configName));
                default -> String.valueOf(mob.get(entityAttributeFormat.configName));
            };
        }

        return mobData;
    }

    private String identifierToName(String name) {
        name = name.toLowerCase();
        name = name.split(":")[1];
        name = name.replace('_', ' ');
        char[] nameChars = name.toCharArray();
        if (nameChars.length > 0) {
            nameChars[0] = Character.toUpperCase(nameChars[0]);
            for (int i = 0; i < name.length(); i++)
                if (nameChars[i] == ' ') nameChars[i+1] = Character.toUpperCase(nameChars[i+1]);

        }
        return new String(nameChars);
    }

    private String getStats(Map<String, Object> stats) {
        StringBuilder stringBuilder = new StringBuilder();
        List<Map<String, Object>> statsList = (List<Map<String, Object>>) stats.get(EntityAttribute.STATS.configName);
        for (Map<String, Object> stat : statsList) {
            for (String statContent : genConfig.statContent) {
                switch (StatAttribute.fromString(statContent)) {
                    case STAT:
                        stringBuilder.append(MarkdownUtils.bold(StatAttribute.STAT.localizer.parseValue(stat.get(StatAttribute.STAT.configName).toString(), ConfigController.getConfig().localization).toString()));
                        stringBuilder.append(" ");
                        break;
                    case TYPE:
                        stringBuilder.append(stat.get(StatAttribute.TYPE.configName).toString().toUpperCase());
                        break;
                    case MIN:
                        stringBuilder.append(MarkdownUtils.italic(StatAttribute.MIN.localizer.parseValue(stat.get(StatAttribute.MIN.configName).toString(), ConfigController.getConfig().localization).toString()));
                        break;
                    case MAX:
                        if (!stat.get(StatAttribute.MIN.configName).equals(stat.get(StatAttribute.MAX.configName))) {
                            stringBuilder.append(MarkdownUtils.italic(" to "));
                            stringBuilder.append(MarkdownUtils.italic(StatAttribute.MAX.localizer.parseValue(stat.get(StatAttribute.MAX.configName).toString(), ConfigController.getConfig().localization).toString()));
                        }
                        stringBuilder.append(" ");
                        break;
                    case null:
                        break;
                }
            }
            stringBuilder.append("<br>");
        }
        return stringBuilder.toString();
    }
}
