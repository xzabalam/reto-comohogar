package com.como.hogar.web.controllers;

import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@RequestMapping("/api/beneficios")
@Validated
public interface BeneficiosApi {

    @GetMapping("/grupos")
    @Operation(summary = "Permite obtener el listado de grupos de beneficios.")
    ResponseEntity<List<String>> getAllGrupos();

    @GetMapping("/grupos/{grupo}")
    @Operation(summary = "Permite obtener el listado de beneficios por grupo.")
    ResponseEntity<List<String>> getByGrupo(@Valid @NotNull @PathVariable("grupo") GrupoBeneficioEnum grupo);
}
