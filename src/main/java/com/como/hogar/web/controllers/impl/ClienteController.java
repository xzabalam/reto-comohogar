package com.como.hogar.web.controllers.impl;

import com.como.hogar.bussines.services.clientes.ClienteService;
import com.como.hogar.common.util.ClientValidationUtil;
import com.como.hogar.web.controllers.ClienteApi;
import com.como.hogar.web.dtos.ClienteTo;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController implements ClienteApi {

    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Override
    @Operation(summary = "Permite obtener todos los clientes registrados")
    public ResponseEntity<Page<ClienteTo>> getAll(int page, int size) {
        return ResponseEntity.ok(clienteService.getAll(page, size));
    }

    @Override
    @Operation(summary = "Permite crear un cliente")
    public ResponseEntity<ClienteTo> crearCliente(ClienteTo clienteTo) {
        ClientValidationUtil.checkNull(clienteTo);
        ClientValidationUtil.checkValidClienteTo(clienteTo);

        ClienteTo cliente = clienteService.crearCliente(clienteTo);

        return new ResponseEntity<>(cliente, HttpStatus.CREATED);
    }

    @Override
    @Operation(summary = "Actualiza un cliente.")
    public ResponseEntity<ClienteTo> actualizarCliente(Long idCliente, ClienteTo clienteTo) {
        ClientValidationUtil.checkNull(clienteTo);
        ClientValidationUtil.checkValidClienteTo(clienteTo);
        clienteService.actualizarCliente(clienteTo, idCliente);
        return new ResponseEntity<>(clienteTo, HttpStatus.OK);
    }

    @Override
    @Operation(summary = "Elimina un cliente.")
    public ResponseEntity<Void> eliminarCliente(Long clienteId) {
        clienteService.eliminarCliente(clienteId);
        return ResponseEntity.noContent().build();
    }
}
