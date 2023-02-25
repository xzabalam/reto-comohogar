package com.como.hogar.referencias;

import com.como.hogar.bussines.services.referencias.ConstantesUtil;
import com.como.hogar.bussines.services.referencias.strategy.XmlFileConfigStrategy;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class XmlFileConfigStrategyTest {

    private XmlFileConfigStrategy xmlFileConfigStrategy;

    @Before
    public void setup() {
        xmlFileConfigStrategy = new XmlFileConfigStrategy();
    }

    @Test
    public void testGetStrategyName() {
        String strategyName = xmlFileConfigStrategy.getStrategyName();
        Assert.assertEquals(ConstantesUtil.STRATEGY_NAME_XML, strategyName);
    }

    @Test
    public void testLoadConfig() {
        xmlFileConfigStrategy.loadConfig();
        List<String> configList = xmlFileConfigStrategy.getConfigList();
        Assert.assertNotNull(configList);
        Assert.assertFalse(configList.isEmpty());
    }

    @Test
    public void testGetConfigList() {
        xmlFileConfigStrategy.loadConfig();
        List<String> configList = xmlFileConfigStrategy.getConfigList();
        Assert.assertNotNull(configList);
        Assert.assertFalse(configList.isEmpty());
    }
}
