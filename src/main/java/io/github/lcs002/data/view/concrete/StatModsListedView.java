package io.github.lcs002.data.view.concrete;

import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.data.view.DataView;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.utils.MarkdownUtils;

import java.util.Arrays;

public class StatModsListedView implements DataView<StatMod[]> {
    public final String[] tableContent;

    public StatModsListedView(String[] tableContent) {
        this.tableContent = tableContent;
    }

    @Override
    public String show(StatMod[] data, Localization localization) {
        StringBuilder stringBuilder = new StringBuilder();
        for (StatMod stat : data) {
            for (String statContent : tableContent) {
                String localizedStat = stat.localizeValue(statContent, localization).toString();
                switch (statContent) {
                    case StatMod.Attributes.STAT:
                        stringBuilder.append(MarkdownUtils.bold(localizedStat));
                        stringBuilder.append(" ");
                        break;
                    case StatMod.Attributes.TYPE:
                        stringBuilder.append(localizedStat);
                        break;
                    case StatMod.Attributes.MIN:
                        stringBuilder.append(MarkdownUtils.italic(localizedStat));
                        break;
                    case StatMod.Attributes.MAX:
                        if (Arrays.stream(tableContent).toList().contains(StatMod.Attributes.STAT) && stat.min != (stat.max)) {
                            stringBuilder.append(MarkdownUtils.italic(" to "));
                            stringBuilder.append(MarkdownUtils.italic(localizedStat));
                        }
                        stringBuilder.append(" ");
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + statContent);
                }
            }
            stringBuilder.append("<br>");
        }
        return stringBuilder.toString();
    }
}
