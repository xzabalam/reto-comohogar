package com.como.hogar;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
@OpenAPIDefinition(info = @Info(title = "API para el control de clientes y beneficios", version =
        "1.0", description = "Como Hogar."))
public class RetoComoHogarApplication {

    public static void main(String[] args) {
        SpringApplication.run(RetoComoHogarApplication.class, args);
    }

}
