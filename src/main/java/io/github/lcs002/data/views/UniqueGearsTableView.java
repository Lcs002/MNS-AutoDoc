package io.github.lcs002.data.views;

import io.github.lcs002.data.model.UniqueGear;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.utils.MarkdownUtils;

import java.util.ArrayList;

public class UniqueGearsTableView implements DataView<UniqueGear[]> {
    private final String[] tableContent;
    private final DataView<StatMod[]> statModsView;

    public UniqueGearsTableView(String[] tableContent, DataView<StatMod[]> statModsView) {
        this.tableContent = tableContent;
        this.statModsView = statModsView;
    }

    @Override
    public String show(UniqueGear[] data, Localization localization) {
        String[] localizedHeaders = generateHeader(localization);
        String[][] localizedContent = generateContent(data, localization);
        return MarkdownUtils.table(localizedHeaders, localizedContent);
    }

    private String[] generateHeader(Localization localization) {
        String[] localizedHeaders = new String[tableContent.length];
        for (int i = 0; i < tableContent.length; i++) {
            localizedHeaders[i] = new UniqueGear().localizeKey(tableContent[i], localization);
        }
        return localizedHeaders;
    }

    private String[][] generateContent(UniqueGear[] data, Localization localization) {
        ArrayList<String[]> uniqueGearsList = new ArrayList<>();
        for (UniqueGear gear : data) {
            uniqueGearsList.add(generateGear(gear, localization));
        }
        return uniqueGearsList.toArray(new String[0][0]);
    }

    private String[] generateGear(UniqueGear gear, Localization localization) {
        System.out.println("Generating gear: " + gear.guid);

        String[] gearData = new String[tableContent.length];
        for (int i = 0; i < tableContent.length; i++) {
            gearData[i] = String.valueOf(
                    switch (tableContent[i]) {
                        case UniqueGear.Attributes.UNIQUE_STATS -> statModsView.show(gear.unique_stats.toArray(new StatMod[0]), localization);
                        default -> gear.localizeValue(tableContent[i], localization);
                    }
            );
        }

        return gearData;
    }
}
