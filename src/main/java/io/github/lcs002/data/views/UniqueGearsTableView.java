package io.github.lcs002.data.views;

import io.github.lcs002.data.model.UniqueGear;
import io.github.lcs002.data.model.components.StatMod;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.utils.MarkdownUtils;

import java.util.ArrayList;
import java.util.List;

public class UniqueGearsTableView implements DataView<UniqueGear[]> {
    private final String[] tableContent;
    private final StatMod.Attribute[] statContent;

    public UniqueGearsTableView(String[] tableContent, StatMod.Attribute[] statContent) {
        this.tableContent = tableContent;
        this.statContent = statContent;
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
                        case UniqueGear.Attributes.UNIQUE_STATS -> generateStats(gear.unique_stats, localization);
                        default -> gear.localizeValue(tableContent[i], localization);
                    }
            );
        }

        return gearData;
    }

    private String generateStats(List<StatMod> stats, Localization localization) {
        StringBuilder statsString = new StringBuilder();

        for (StatMod stat : stats) {
            for (int i = 0; i < statContent.length; i++) {
                statsString.append(statContent[i].localizer.parseValue(
                        switch (statContent[i]) {
                            case StatMod.Attribute.STAT -> stat.stat;
                            case StatMod.Attribute.MIN -> stat.min;
                            case StatMod.Attribute.MAX -> stat.max;
                            case StatMod.Attribute.TYPE -> stat.type;
                        },
                        localization
                ));
            }
        }
        return statsString.toString();
    }
}
