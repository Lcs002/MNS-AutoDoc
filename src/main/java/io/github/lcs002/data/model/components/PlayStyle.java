package io.github.lcs002.data.model.components;

public enum PlayStyle {
    DEX("dex"),
    STR("str"),
    INT("int"),
    ;
    PlayStyle(String id) {
        this.id = id;
    }
    public final String id;
}
