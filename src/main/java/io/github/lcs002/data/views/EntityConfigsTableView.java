package io.github.lcs002.data.views;

import io.github.lcs002.data.model.EntityConfig;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.utils.MarkdownUtils;

import java.util.ArrayList;

public class EntityConfigsTableView implements DataView<EntityConfig[]>{
    private final String[] tableContent;
    private final DataView<StatMod[]> statModsView;

    public EntityConfigsTableView(String[] tableContent, DataView<StatMod[]> statModsView) {
        this.tableContent = tableContent;
        this.statModsView = statModsView;
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
                        case EntityConfig.Attributes.STATS -> statModsView.show(entityConfig.stats.stats.toArray(new StatMod[0]), localization);
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
}
