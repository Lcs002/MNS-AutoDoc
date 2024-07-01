package io.github.lcs002.data;

import io.github.lcs002.localization.Localization;

public interface LocatableData<T extends Data> {
    String localizeKey(String attribute, Localization localization);
    Object localizeValue(String attribute, Localization localization);
}
