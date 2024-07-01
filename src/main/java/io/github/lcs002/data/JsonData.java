package io.github.lcs002.data;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface JsonData<T> extends Data {
    String toJson() throws JsonProcessingException;
    T fromJson(String json) throws JsonProcessingException;
}
