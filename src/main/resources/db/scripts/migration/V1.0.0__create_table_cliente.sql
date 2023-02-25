DROP TABLE IF EXISTS cliente CASCADE;
CREATE TABLE cliente
(
    id                  BIGINT PRIMARY KEY AUTO_INCREMENT,
    nombre              VARCHAR(255) NOT NULL CHECK (nombre IS NOT NULL AND LENGTH(nombre) > 0),
    email               VARCHAR(60)  NOT NULL CHECK (email IS NOT NULL AND LENGTH(email) > 0),
    telefono            VARCHAR(20)  NULL,
    grupo_beneficio     varchar(2)   not null CHECK (grupo_beneficio IN ('TH', 'SK')),
    fecha_crea          DATETIME     not null default current_timestamp,
    fecha_modifica      DATETIME,
    id_usuario_crea     bigint       not null default '',
    id_usuario_modifica bigint       null,
    estado              varchar      not null default 'A'
);

CREATE UNIQUE INDEX indx_cliente_nombre ON cliente (nombre);
CREATE UNIQUE INDEX indx_cliente_email ON cliente (email);