package com.como.hogar.common.builders;

import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import com.como.hogar.web.dtos.ClienteTo;

import java.util.List;

public final class ClienteToBuilder {
    private static ClienteTo cliente;

    public static ClienteToBuilder builder() {
        cliente = new ClienteTo();
        return new ClienteToBuilder();
    }

    public ClienteTo build() {
        return cliente;
    }

    public ClienteToBuilder id(Long id) {
        cliente.setId(id);
        return this;
    }

    public ClienteToBuilder nombre(String nombre) {
        cliente.setNombre(nombre);
        return this;
    }

    public ClienteToBuilder email(String email) {
        cliente.setEmail(email);
        return this;
    }

    public ClienteToBuilder telefono(String telefono) {
        cliente.setTelefono(telefono);
        return this;
    }

    public ClienteToBuilder grupo(GrupoBeneficioEnum grupo) {
        cliente.setGrupoBeneficio(grupo);
        return this;
    }

    public ClienteToBuilder beneficio(List<String> beneficio) {
        cliente.setBeneficio(beneficio.toString());
        return this;
    }
}
