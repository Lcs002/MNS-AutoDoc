package io.github.lcs002.data;

import io.github.lcs002.localization.AttributeLocalizer;

public interface AttributeProvider<T> {
    AttributeLocalizer getLocalizer();
    String getName();
}
