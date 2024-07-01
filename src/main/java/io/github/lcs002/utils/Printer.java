package io.github.lcs002.utils;

import org.jline.jansi.Ansi;

public class Printer {
    private static boolean debugEnabled = false;

    public static void printDebug(String message) {
        if (debugEnabled) System.out.println(message);
    }

    public static void printMisc(String message) {
        if (!debugEnabled) System.out.println(message);
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static void setDebugEnabled(boolean debugEnabled) {
        Printer.debugEnabled = debugEnabled;
    }
}
