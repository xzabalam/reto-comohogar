package com.como.hogar.bussines.services.referencias.strategy;

import java.util.List;

public interface FileConfigStrategy<T> {
    void loadConfig();
    String getStrategyName();
    List<T> getConfigList();
}
