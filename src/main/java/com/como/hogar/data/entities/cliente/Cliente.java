package com.como.hogar.data.entities.cliente;

import com.como.hogar.common.enumeration.GrupoBeneficioEnum;
import com.como.hogar.data.entities.core.impl.AbstractNamed;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "cliente", uniqueConstraints = {
        @UniqueConstraint(name = "client_unique_nombre", columnNames = {"nombre"}),
        @UniqueConstraint(name = "dept_unique_email", columnNames = {"email"})})
@Getter
@Setter
public class Cliente extends AbstractNamed<Long> {

    @NotNull(message = "{cliente.email.notnull}")
    @NotEmpty(message = "{cliente.email.empty}")
    @Size(min = 1, max = 60, message = "{cliente.email.size}")
    @Column(name = "email")
    private String email;

    @Column(name = "telefono")
    @Size(min = 1, max = 20, message = "{cliente.telefono.size}")
    private String telefono;

    @NotNull(message = "{cliente.grupo.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "grupo_beneficio")
    private GrupoBeneficioEnum grupoBeneficio;

    @ElementCollection
    @CollectionTable(name = "cliente_beneficios")
    @Column(name = "beneficio")
    private List<String> beneficios;

}
