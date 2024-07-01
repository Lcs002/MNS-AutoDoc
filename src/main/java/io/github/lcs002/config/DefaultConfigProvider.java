package io.github.lcs002.config;

public interface DefaultConfigProvider<T extends DefaultConfigProvider<T>>  {
    T createDefault(Config config);
}
