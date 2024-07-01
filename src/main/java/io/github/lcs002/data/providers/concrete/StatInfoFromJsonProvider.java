package io.github.lcs002.data.providers.concrete;

import io.github.lcs002.data.model.StatInfo;
import io.github.lcs002.data.providers.DataFromJsonProvider;

public class StatInfoFromJsonProvider extends DataFromJsonProvider<StatInfo> {
    public StatInfoFromJsonProvider(String path) {
        super(path + "/mmorpg/mmorpg_stat", StatInfo.class);
    }

}
