package io.github.lcs002.localization;

public enum Localization {
    EN_US("en_us", "lang/mmorpg/en_us.json", "lang/autodoc/en_us.json"),
    ES_ES("es_es", "lang/mmorpg/es_es.json", "lang/autodoc/es_es.json"),
    FR_FR("fr_fr", "lang/fr_fr.json", "lang/autodoc/fr_fr.json"),
    KO_KR("ko_kr", "lang/mmorpg/ko_kr.json", "lang/autodoc/ko_kr.json"),
    PT_BR("pt_br", "lang/mmorpg/pt_br.json", "lang/autodoc/pt_br.json"),
    RU_RU("ru_ru", "lang/mmorpg/ru_ru.json", "lang/autodoc/ru_ru.json"),
    ZH_CN("zh_cn", "lang/mmorpg/zh_cn.json", "lang/autodoc/zh_cn.json"),
    ZH_TW("zh_tw", "lang/mmorpg/zh_tw.json", "lang/autodoc/zh_tw.json");

    Localization(String id, String mmorpgFilePath, String generatorFilePath) {
        this.id = id;
        this.mmorpgFilePath = mmorpgFilePath;
        this.generatorFilePath = generatorFilePath;
    }

    public final String id;
    public final String mmorpgFilePath;
    public final String generatorFilePath;
}
