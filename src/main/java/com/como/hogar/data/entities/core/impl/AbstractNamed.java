package com.como.hogar.data.entities.core.impl;


import com.como.hogar.data.entities.core.Named;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
public abstract class AbstractNamed<T> extends AbstractEntity<T> implements Named {
    private static final long serialVersionUID = -412218241272244614L;

    @NotNull(message = "{cliente.nombre.notnull}")
    @NotEmpty(message = "{cliente.nombre.notnull}")
    @Size(min = 1, max = 255, message = "{cliente.nombre.size}")
    @Column(name = "nombre")
    private String nombre;

    @Override
    public String getNombre() {
        return nombre;
    }

    @Override
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
