package io.github.lcs002.data.views;

import io.github.lcs002.config.ConfigController;
import io.github.lcs002.data.model.EntityConfig;
import io.github.lcs002.data.model.components.SpecialMobStats;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.utils.MarkdownUtils;

import java.util.ArrayList;
import java.util.List;

public class EntityConfigsTableView implements DataView<EntityConfig[]>{
    private final String[] tableContent;
    private final StatMod.Attribute[] statContent;

    public EntityConfigsTableView(String[] tableContent, StatMod.Attribute[] statContent) {
        this.tableContent = tableContent;
        this.statContent = statContent;
    }

    @Override
    public String show(EntityConfig[] data, Localization localization) {
        ArrayList<String[]> mobsList = new ArrayList<>();
        for (EntityConfig entityConfig : data) {
            mobsList.add(generateMob(entityConfig, localization));
        }

        String[][] mobs = new String[mobsList.size()][tableContent.length];
        for (int i = 0; i < mobsList.size(); i++) {
            mobs[i] = mobsList.get(i);
        }

        String[] localizedHeaders = new String[tableContent.length];
        for (int i = 0; i < tableContent.length; i++) {
            localizedHeaders[i] = new EntityConfig().localizeKey(tableContent[i], localization);
        }

        return MarkdownUtils.table(localizedHeaders, mobs);
    }

    private String[] generateMob(EntityConfig entityConfig, Localization localization) {
        System.out.println("Generating mob: " + entityConfig.identifier);

        String[] mobData = new String[tableContent.length];
        for (int i = 0; i < tableContent.length; i++) {
            mobData[i] = String.valueOf(
                    switch (tableContent[i]) {
                        case EntityConfig.Attributes.IDENTIFIER -> identifierToName(String.valueOf(entityConfig.identifier));
                        case EntityConfig.Attributes.STATS -> getStats(entityConfig.stats);
                        default -> entityConfig.localizeValue(tableContent[i], localization);
            });
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

    private String getStats(SpecialMobStats stats) {
        StringBuilder stringBuilder = new StringBuilder();
        List<StatMod> statsList = stats.stats;
        for (StatMod stat : statsList) {
            for (StatMod.Attribute statContent : statContent) {
                switch (statContent) {
                    case STAT:
                        stringBuilder.append(MarkdownUtils.bold(StatMod.Attribute.STAT.localizer.parseValue(stat.stat, ConfigController.getConfig().localization).toString()));
                        stringBuilder.append(" ");
                        break;
                    case TYPE:
                        stringBuilder.append(stat.type.toUpperCase());
                        break;
                    case MIN:
                        stringBuilder.append(MarkdownUtils.italic(StatMod.Attribute.MIN.localizer.parseValue(stat.min, ConfigController.getConfig().localization).toString()));
                        break;
                    case MAX:
                        if (statsList.contains(stat.min) && stat.min != (stat.max)) {
                            stringBuilder.append(MarkdownUtils.italic(" to "));
                            stringBuilder.append(MarkdownUtils.italic(StatMod.Attribute.MAX.localizer.parseValue(stat.max, ConfigController.getConfig().localization).toString()));
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
