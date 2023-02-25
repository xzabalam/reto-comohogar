package com.como.hogar.web.dtos;

import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class ClienteTo {
    private Long id;

    @NotNull(message = "{cliente.nombre.notnull}")
    @NotEmpty(message = "{cliente.nombre.notnull}")
    @Size(min = 1, max = 255, message = "{cliente.nombre.size}")
    private String nombre;

    @NotNull(message = "{cliente.email.notnull}")
    @NotEmpty(message = "{cliente.email.empty}")
    @Size(min = 1, max = 60, message = "{cliente.email.size}")
    private String email;

    @Size(min = 1, max = 20, message = "{cliente.telefono.size}")
    private String telefono;

    @NotNull(message = "{cliente.grupo.notnull}")
    private GrupoBeneficioEnum grupoBeneficio;

    @NotNull(message = "{cliente.beneficio.notnull}")
    @NotEmpty(message = "{cliente.beneficio.empty}")
    @Size(min = 1, max = 255, message = "{cliente.beneficio.size}")
    private String beneficio;
}
