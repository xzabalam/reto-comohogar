package com.como.hogar.common.builders;

import com.como.hogar.common.enumeration.EstadoEnum;
import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import com.como.hogar.data.entities.cliente.Cliente;

import java.util.Arrays;
import java.util.Date;

public final class ClienteBuilder {
    private static Cliente cliente;

    public static ClienteBuilder builder() {
        cliente = new Cliente();
        return new ClienteBuilder();
    }

    public Cliente build() {
        cliente.setFechaCrea(new Date());
        cliente.setUsuarioCrea(0L);
        cliente.setEstado(EstadoEnum.ACTIVO.getEstado());
        return cliente;
    }

    public ClienteBuilder nombre(String nombre) {
        cliente.setNombre(nombre);
        return this;
    }

    public ClienteBuilder email(String email) {
        cliente.setEmail(email);
        return this;
    }

    public ClienteBuilder telefono(String telefono) {
        cliente.setTelefono(telefono);
        return this;
    }

    public ClienteBuilder grupo(GrupoBeneficioEnum grupo) {
        cliente.setGrupoBeneficio(grupo);
        return this;
    }

    public ClienteBuilder beneficio(String beneficio) {
        cliente.setBeneficios(Arrays.asList(beneficio));
        return this;
    }
}
