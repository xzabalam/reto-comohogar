package com.como.hogar.web.controllers.impl;

import com.como.hogar.bussines.services.referencias.ReferenceReaderService;
import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import com.como.hogar.web.controllers.BeneficiosApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BeneficiosController implements BeneficiosApi {
    private final ReferenceReaderService referenceReaderService;

    public BeneficiosController(ReferenceReaderService referenceReaderService) {
        this.referenceReaderService = referenceReaderService;
    }

    @Override
    public ResponseEntity<List<String>> getAllGrupos() {
        return new ResponseEntity<>(referenceReaderService.getGrupoBeneficios(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<String>> getByGrupo(GrupoBeneficioEnum grupo) {
        return new ResponseEntity<>(referenceReaderService.getBeneficios(grupo), HttpStatus.OK);
    }
}
