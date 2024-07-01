package io.github.lcs002.data.view;

import io.github.lcs002.data.Data;
import io.github.lcs002.data.LocatableData;
import io.github.lcs002.localization.Localization;
import io.github.lcs002.utils.MarkdownUtils;

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
        String[][] tableData = new String[data.length][content.length];
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < content.length; j++) {
                tableData[i][j] = generateDataForAttribute(data[i], content[j], localization);
            }
        }
        return tableData;
    }

    protected String generateDataForAttribute(T data, String attribute, Localization localization) {
        return data.localizeValue(attribute, localization).toString();
    }
}
