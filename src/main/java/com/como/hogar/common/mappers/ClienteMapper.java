package com.como.hogar.common.mappers;

import com.como.hogar.common.builders.ClienteBuilder;
import com.como.hogar.common.builders.ClienteToBuilder;
import com.como.hogar.data.entities.cliente.Cliente;
import com.como.hogar.web.dtos.ClienteTo;

public final class ClienteMapper {
    private ClienteMapper() {
    }

    public static Cliente toEntity(ClienteTo clienteTo) {
        return ClienteBuilder.builder()
                .nombre(clienteTo.getNombre())
                .email(clienteTo.getEmail())
                .telefono(clienteTo.getTelefono())
                .grupo(clienteTo.getGrupoBeneficio())
                .beneficio(clienteTo.getBeneficio())
                .build();
    }

    public static ClienteTo toTo(Cliente cliente) {
        return ClienteToBuilder.builder()
                .id(cliente.getId())
                .nombre(cliente.getNombre())
                .email(cliente.getEmail())
                .telefono(cliente.getTelefono())
                .grupo(cliente.getGrupoBeneficio())
                .beneficio(cliente.getBeneficios())
                .build();
    }
}
