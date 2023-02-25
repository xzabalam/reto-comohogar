package com.como.hogar.bussines.services.referencias;

import com.como.hogar.bussines.services.referencias.strategy.FileConfigStrategy;
import com.como.hogar.bussines.services.referencias.strategy.JsonFileConfigStrategy;
import com.como.hogar.bussines.services.referencias.strategy.XmlFileConfigStrategy;
import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import com.como.hogar.common.exceptions.BeneficioException;
import com.como.hogar.web.dtos.ClienteTo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.locks.StampedLock;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@Slf4j
public class ReferenceReaderService {
    private static List<FileConfigStrategy> fileStrategies;

    static {
        fileStrategies = new ArrayList<>();
        fileStrategies.add(new JsonFileConfigStrategy());
        fileStrategies.add(new XmlFileConfigStrategy());
    }

    private Map<String, List<String>> references;
    private StampedLock lock;

    public ReferenceReaderService() {
        lock = new StampedLock();
    }

    @PostConstruct
    public void init() {
        references = new HashMap<>();
        setReferences();
    }

    @Caching(evict = {
            @CacheEvict("GRUPO"),
            @CacheEvict("BENEFICIO")
    })
    public void reloadConfigs() {
        references.clear();
        setReferences();
    }

    @Cacheable("GRUPO")
    public List<String> getGrupoBeneficios() {
        return Arrays.stream(GrupoBeneficioEnum.values()).map(grupo -> grupo.name()).collect(Collectors.toList());
    }

    @Cacheable("BENEFICIO")
    public List<String> getBeneficios(GrupoBeneficioEnum grupo) {
        return references.get(grupo.name());
    }


    @Transactional
    public void seleccionarBeneficio(ClienteTo clienteTo) {
        long stamp = lock.writeLock();

        try {
            String beneficio = clienteTo.getBeneficio();
            List<String> beneficios = getBeneficios(clienteTo.getGrupoBeneficio());

            if (beneficios.isEmpty() || !beneficios.contains(beneficio)) {
                throw new BeneficioException(String.format("El beneficio seleccionado %s, para el grupo %s no se " +
                        "encuentra disponible.", beneficio, clienteTo.getGrupoBeneficio()));
            }

            beneficios.remove(beneficio);
        } finally {
            lock.unlockWrite(stamp);
        }
    }

    private void setReferences() {
        for (FileConfigStrategy strategy : fileStrategies) {
            strategy.loadConfig();
            references.put(strategy.getStrategyName(), strategy.getConfigList());
        }
    }
}