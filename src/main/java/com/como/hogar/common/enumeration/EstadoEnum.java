package com.como.hogar.common.enumeration;

public enum EstadoEnum {
    ACTIVO("A"), INACTIVO("I");

    private final String estado;

    EstadoEnum(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }
}
