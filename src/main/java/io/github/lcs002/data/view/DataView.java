package io.github.lcs002.data.view;

import io.github.lcs002.localization.Localization;

public interface DataView<T> {
    String show(T data, Localization localization);
}
