package com.como.hogar.bussines.services.referencias.strategy;

import com.como.hogar.bussines.services.referencias.ConstantesUtil;
import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class JsonFileConfigStrategy implements FileConfigStrategy<String> {

    private final List<String> configList = new ArrayList<>();

    @Override
    public String getStrategyName() {
        return GrupoBeneficioEnum.SK.name();
    }

    @Override
    public void loadConfig() {
        try {
            ClassPathResource resource = new ClassPathResource(ConstantesUtil.FILE_PATH_JSON);
            InputStream input = resource.getInputStream();
            ObjectMapper mapper = new ObjectMapper();
            JsonNode rootNode = mapper.readTree(input);
            JsonNode configNode = rootNode.get(rootNode.fieldNames().next());
            for (JsonNode node : configNode) {
                configList.add(node.get(ConstantesUtil.ROOT_LABEL).asText());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getConfigList() {
        return configList;
    }
}
