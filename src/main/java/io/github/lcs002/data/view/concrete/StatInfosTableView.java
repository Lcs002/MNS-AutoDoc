package io.github.lcs002.data.view.concrete;

import io.github.lcs002.data.model.StatInfo;
import io.github.lcs002.data.view.TableView;
import io.github.lcs002.localization.Localization;

public class StatInfosTableView extends TableView<StatInfo> {
    public StatInfosTableView(String[] content) {
        super(content);
    }

    @Override
    protected boolean excludeData(StatInfo data, String attribute, Localization localization) {
        return data.id == null;
    }
}
