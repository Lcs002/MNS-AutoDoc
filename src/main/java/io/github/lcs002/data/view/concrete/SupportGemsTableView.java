package io.github.lcs002.data.view.concrete;

import io.github.lcs002.data.model.SupportGem;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.data.view.DataView;
import io.github.lcs002.data.view.TableView;
import io.github.lcs002.localization.Localization;

public class SupportGemsTableView extends TableView<SupportGem> {
    private final DataView<StatMod[]> statModsView;

    public SupportGemsTableView(String[] content, DataView<StatMod[]> statModsView) {
        super(content);
        this.statModsView = statModsView;
    }

    @Override
    protected String generateDataForAttribute(SupportGem data, String attribute, Localization localization) {
        return switch (attribute) {
            case SupportGem.Attributes.STATS -> statModsView.show(data.stats.toArray(new StatMod[0]), localization);
            default -> data.localizeValue(attribute, localization).toString();
        };
    }
}
