package com.como.hogar.referencias;

import com.como.hogar.bussines.services.referencias.ConstantesUtil;
import com.como.hogar.bussines.services.referencias.strategy.JsonFileConfigStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class JsonFileConfigStrategyTest {

    private JsonFileConfigStrategy jsonFileConfigStrategy;

    @Before
    public void setup() {
        jsonFileConfigStrategy = new JsonFileConfigStrategy();
    }

    @Test
    public void testGetStrategyName() {
        String strategyName = jsonFileConfigStrategy.getStrategyName();
        Assert.assertEquals(ConstantesUtil.STRATEGY_NAME_JSON, strategyName);
    }

    @Test
    public void testLoadConfig() {
        jsonFileConfigStrategy.loadConfig();
        List<String> configList = jsonFileConfigStrategy.getConfigList();
        Assert.assertNotNull(configList);
        Assert.assertFalse(configList.isEmpty());
    }

    @Test
    public void testGetConfigList() {
        jsonFileConfigStrategy.loadConfig();
        List<String> configList = jsonFileConfigStrategy.getConfigList();
        Assert.assertNotNull(configList);
        Assert.assertFalse(configList.isEmpty());
    }
}
