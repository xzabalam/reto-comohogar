package com.como.hogar.data.entities.core;

import java.io.Serializable;

public interface Named extends Serializable {
    String getNombre();

    void setNombre(String nombre);
}
