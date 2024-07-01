package io.github.lcs002.data.view.concrete;

import io.github.lcs002.data.model.UniqueGear;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.data.view.DataView;
import io.github.lcs002.data.view.TableView;
import io.github.lcs002.localization.Localization;

public class UniqueGearsTableView extends TableView<UniqueGear> {
    private final DataView<StatMod[]> statModsView;

    public UniqueGearsTableView(String[] tableContent, DataView<StatMod[]> statModsView) {
        super(tableContent);
        this.statModsView = statModsView;
    }

    @Override
    protected String generateDataForAttribute(UniqueGear data, String attribute, Localization localization) {
        return switch (attribute) {
            case UniqueGear.Attributes.UNIQUE_STATS -> statModsView.show(data.unique_stats.toArray(new StatMod[0]), localization);
            default -> data.localizeValue(attribute, localization).toString();
        };
    }
}
