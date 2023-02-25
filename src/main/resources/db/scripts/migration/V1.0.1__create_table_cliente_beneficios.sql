DROP TABLE IF EXISTS cliente_beneficios CASCADE;
CREATE TABLE cliente_beneficios
(
    id         BIGINT PRIMARY KEY AUTO_INCREMENT,
    cliente_id bigint       not null,
    beneficio  VARCHAR(255) NOT NULL CHECK (beneficio IS NOT NULL AND LENGTH(beneficio) > 0),
    FOREIGN KEY (cliente_id) REFERENCES cliente (id)
);

CREATE UNIQUE INDEX indx_cliente_beneficio ON cliente_beneficios (beneficio);
