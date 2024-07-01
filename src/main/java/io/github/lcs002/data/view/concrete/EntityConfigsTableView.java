package io.github.lcs002.data.view.concrete;

import io.github.lcs002.data.model.Entity;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.data.view.DataView;
import io.github.lcs002.data.view.TableView;
import io.github.lcs002.localization.Localization;

public class EntityConfigsTableView extends TableView<Entity> {
    private final DataView<StatMod[]> statModsView;

    public EntityConfigsTableView(String[] tableContent, DataView<StatMod[]> statModsView) {
        super(tableContent);
        this.statModsView = statModsView;
    }

    @Override
    protected String generateDataForAttribute(Entity data, String attribute, Localization localization) {
        return switch (attribute) {
            case Entity.Attributes.IDENTIFIER -> identifierToName(data.identifier);
            case Entity.Attributes.STATS -> statModsView.show(data.stats.stats.toArray(new StatMod[0]), localization);
            default -> data.localizeValue(attribute, localization).toString();
        };
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
