package com.como.hogar.bussines.services.referencias.strategy;

import com.como.hogar.bussines.services.referencias.ConstantesUtil;
import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import org.springframework.core.io.ClassPathResource;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class XmlFileConfigStrategy implements FileConfigStrategy {
    private final List<String> configList = new ArrayList<>();

    @Override
    public String getStrategyName() {
        return GrupoBeneficioEnum.TH.name();
    }

    @Override
    public void loadConfig() {
        try {
            ClassPathResource resource = new ClassPathResource(ConstantesUtil.FILE_PATH_XML);
            InputStream input = resource.getInputStream();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(input);
            NodeList nodeList = document.getElementsByTagName(ConstantesUtil.ROOT_LABEL);
            for (int i = 0; i < nodeList.getLength(); i++) {
                configList.add(nodeList.item(i).getTextContent());
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
