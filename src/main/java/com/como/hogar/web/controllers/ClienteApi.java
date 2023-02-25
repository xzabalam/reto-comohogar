package com.como.hogar.web.controllers;

import com.como.hogar.web.dtos.ClienteTo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@RequestMapping("/api/cliente")
@Validated
public interface ClienteApi {

    @GetMapping("/all")
    @Operation(summary = "Permite obtener el listado de clientes con paginacion.")
    ResponseEntity<Page<ClienteTo>> getAll(
            @RequestParam(value = "page", defaultValue = "0") @Min(0) int page,
            @RequestParam(value = "size", defaultValue = "10") @Min(1) int size);

    @PostMapping
    @Operation(summary = "Permite crear un cliente")
    ResponseEntity<ClienteTo> crearCliente(@Valid @NotNull @RequestBody ClienteTo clienteTo);

    @PutMapping("/{clienteId}")
    @Operation(summary = "Actualiza un cliente.")
    ResponseEntity<ClienteTo> actualizarCliente(@PathVariable Long clienteId, @Valid @RequestBody ClienteTo clienteTo);

    @DeleteMapping("/{clienteId}")
    @Operation(summary = "Elimina un cliente.")
    ResponseEntity<Void> eliminarCliente(@PathVariable Long clienteId);

}
