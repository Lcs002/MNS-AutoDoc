package io.github.lcs002.localization;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.lcs002.utils.Printer;
import io.github.lcs002.utils.ResourceUtils;
import io.github.lcs002.utils.JsonUtils;

import java.io.IOException;
import java.util.Map;

public class LocalizationHandler {
    private ObjectMapper objectMapper = new ObjectMapper();

    public static String localizationFor(String key, LocalizationGroup family, Localization localization) {
        if (family == LocalizationGroup.NONE) return key;

        String localized = null;

        Map<String, Object> localizationData = LocalizationHandler.getMMORPGLocalization(localization);
        for (String group : family.groups) {
            if (localizationData.containsKey(group+"."+key)) {
                localized = localizationData.get(group+"."+key).toString();
            }
        }

        if (localized == null) {
            localizationData = LocalizationHandler.getGeneratorLocalization(localization);
            for (String group : family.groups) {
                if (localizationData.containsKey(group+"."+key)) {
                    localized = localizationData.get(group+"."+key).toString();
                }
            }
        }

        if (localized == null) {
            Printer.printDebug("Localization for '" + localization.id + "' not found for entry '" + key + "' in family '" + family + "'.");
            localized = key;

            if (localization != Localization.EN_US) {
                Printer.printDebug("Defaulting to english localization.");

                localizationData = LocalizationHandler.getMMORPGLocalization(Localization.EN_US);
                for (String group : family.groups) {
                    if (localizationData.containsKey(group+"."+key)) {
                        localized = localizationData.get(group+"."+key).toString();
                    }
                }

                if (localized == null) {
                    localizationData = LocalizationHandler.getGeneratorLocalization(Localization.EN_US);
                    for (String group : family.groups) {
                        if (localizationData.containsKey(group+"."+key)) {
                            localized = localizationData.get(group+"."+key).toString();
                        }
                    }
                }

                if (localized == null) {
                    Printer.printDebug("Localization for 'en_us' not found for entry '" + key + "' in family '" + family + "'.");
                    localized = key;
                }
            }
        }
        return localized;
    }

    private static Map<String, Object> getMMORPGLocalization(Localization localization) {
        String localizationPath = localization.mmorpgFilePath;

        String localizationContent = null;
        try {
            localizationContent = ResourceUtils.getResourceAsString(localizationPath);
        }
        catch (IOException e) {
            Printer.print("Error while reading localization file '" + localizationPath + "'.\n" + e.getMessage());
            throw new RuntimeException(e);
        }

        return JsonUtils.stringToMap(localizationContent);
    }

    private static Map<String, Object> getGeneratorLocalization(Localization localization) {
        String localizationPath = localization.generatorFilePath;

        String localizationContent = null;
        try {
            localizationContent = ResourceUtils.getResourceAsString(localizationPath);
        }
        catch (IOException e) {
            Printer.print("Error while reading localization file '" + localizationPath + "'.\n" + e.getMessage());
            throw new RuntimeException(e);
        }

        return JsonUtils.stringToMap(localizationContent);
    }
}
