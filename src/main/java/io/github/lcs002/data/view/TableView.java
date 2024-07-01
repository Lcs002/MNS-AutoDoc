package io.github.lcs002.data.view;

import io.github.lcs002.App;
import io.github.lcs002.data.Data;
import io.github.lcs002.data.LocatableData;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.utils.MarkdownUtils;
import io.github.lcs002.utils.Printer;
import org.jline.jansi.Ansi;
import org.jline.utils.InfoCmp;

import java.util.ArrayList;

public class TableView<T extends Data & LocatableData<T>> implements DataView<T[]>  {
    String[] content;

    protected TableView(String[] content) {
        this.content = content;
    }

    @Override
    public String show(T[] data, Localization localization) {
        String[] tableHeaders = generateHeaders(data[0], localization);
        String[][] tableData = generateData(data, localization);
        return MarkdownUtils.table(tableHeaders, tableData);
    }

    private String[] generateHeaders(T data, Localization localization) {
        String[] tableHeaders = new String[content.length];
        for (int i = 0; i < content.length; i++) {
            tableHeaders[i] = data.localizeKey(content[i], localization);
        }
        return tableHeaders;
    }

    private String[][] generateData(T[] data, Localization localization) {
        // For fun
        // Loading Bar
        int barLength = 80;
        double stepLength = (double) barLength / data.length;
        double currentData = 0;

        Printer.printMisc("Generating: " + Ansi.ansi().fgGreen().a("░".repeat(barLength)).reset().toString());
        App.terminal.puts(InfoCmp.Capability.cursor_up, 1);

        ArrayList<String[]> tableData = new ArrayList<>();
        for (T datum : data) {
            currentData++;
            int currentBars = (int) Math.ceil(currentData * stepLength);
            int leftBars = barLength - currentBars;

            Printer.printMisc("Generating: " + Ansi.ansi().fgGreen().a("█".repeat(currentBars) + "░".repeat(leftBars)).reset().toString());
            App.terminal.puts(InfoCmp.Capability.cursor_up, 1);

            boolean excluded = false;
            String[] rowData = new String[content.length];
            for (int j = 0; j < content.length && !excluded; j++) {
                if (excludeData(datum, content[j], localization)) {
                    excluded = true;
                    continue;
                }
                rowData[j] = generateDataForAttribute(datum, content[j], localization);
            }
            if (!excluded) tableData.add(rowData);
        }
        Printer.printMisc("");
        return tableData.toArray(new String[0][0]);
    }

    protected String generateDataForAttribute(T data, String attribute, Localization localization) {
        return data.localizeValue(attribute, localization).toString();
    }

    protected boolean excludeData(T data, String attribute, Localization localization) {
        return false;
    }
}
