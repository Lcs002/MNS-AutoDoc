package io.github.lcs002.utils;

public class MarkdownUtils {
    public static String header(int level, String text) {
        return "#".repeat(level) + " " + text + "\n";
    }

    public static String list(String text) {
        return "* " + text + "\n";
    }

    public static String bold(String text) {
        int firstNonSpace = findFirstNonSpace(text);
        int lastNonSpace = findLastNonSpace(text);

        // insert "**" before first non-space
        text = text.substring(0, firstNonSpace) + "**" + text.substring(firstNonSpace);

        // insert "**" after last non-space
        text = text.substring(0, lastNonSpace + 3) + "**" + text.substring(lastNonSpace + 3);

        return text;
    }

    public static String lineBreak() {
        return "<br>\n";
    }

    public static String italic(String text) {
        int firstNonSpace = findFirstNonSpace(text);
        int lastNonSpace = findLastNonSpace(text);

        // insert "*" before first non-space
        text = text.substring(0, firstNonSpace) + "*" + text.substring(firstNonSpace);

        // insert "*" after last non-space
        text = text.substring(0, lastNonSpace+2) + "*" + text.substring(lastNonSpace+2);

        return text;
    }

    public static String link(String text, String url) {
        return "[" + text + "](" + url + ")";
    }

    public static String image(String altText, String url) {
        return "![" + altText + "](" + url + ")";
    }

    public static String code(String text) {
        return "`" + text + "`";
    }

    public static String table(String[] headers, String[][] data) {
        StringBuilder table = new StringBuilder();
        table.append("|");
        for (String header : headers) {
            table.append(header).append("|");
        }
        table.append("\n");

        table.append("|");
        for (String header : headers) {
            table.append(":").append("-".repeat(header.length())).append(":").append("|");
        }
        table.append("\n");

        for (String[] row : data) {
            table.append("|");
            for (String cell : row) {
                table.append(cell).append("|");
            }
            table.append("\n");
        }

        return table.toString();
    }

    private static int findFirstNonSpace(String text) {
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' ') {
                return i;
            }
        }
        return -1;
    }

    private static int findLastNonSpace(String text) {
        for (int i = text.length() - 1; i >= 0; i--) {
            if (text.charAt(i) != ' ') {
                return i;
            }
        }
        return -1;
    }
}
