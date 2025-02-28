
CREATE TABLE `usuarios` (
    id int AUTO_INCREMENT NOT NULL,
    nombre CHAR(45) UNIQUE NOT NULL,
    password CHAR(200) NOT NULL,
    correo CHAR(100) UNIQUE NOT NULL,
    rol CHAR(45) NOT NULL,
    verificado TINYINT(1) DEFAULT 0,
    token_verificacion CHAR(255),
    fecha_creacion BIGINT NOT NULL,
    foto_perfil CHAR(255) NULL,
    CONSTRAINT pk_usuarios PRIMARY KEY(id)
);

INSERT INTO `usuarios` (
        `nombre`,
        `password`,
        `correo`,
        `rol`,
        `verificado`,
        `token_verificacion`,
        `fecha_creacion`,
        `foto_perfil`
    )
VALUES (
        'root',
        '$2a$10$P0fZ.FcD.rBwolLS9P5bAOZETI3K9E5JsiE/NQC82HgkXccYnFvry',
        'nlamail1529@gmail.com',
        'ROLE_ADMIN',
        1,
        'readumineko',
        UNIX_TIMESTAMP(),
        NULL
    );

INSERT INTO `usuarios` (
        `nombre`,
        `password`,
        `correo`,
        `rol`,
        `verificado`,
        `token_verificacion`,
        `fecha_creacion`,
        `foto_perfil`
    )
VALUES (
        'nalleon',
        '$2a$10$P0fZ.FcD.rBwolLS9P5bAOZETI3K9E5JsiE/NQC82HgkXccYnFvry',
        'nabil14716@gmail.com',
        'ROLE_USER',
        1,
        'ef34fd1b-c8da-4397-9d7e-06b554a2d617',
        UNIX_TIMESTAMP(),
        NULL
    );



CREATE TABLE `partidas` (
    id int AUTO_INCREMENT NOT NULL,
    jugador1 int NOT NULL,
    jugador2 int NULL,
    board VARCHAR(20) NULL,
    finalizado TINYINT(1) DEFAULT 0,
    CONSTRAINT `pk_partidas` PRIMARY KEY(id),
    CONSTRAINT `fk_jugador1` FOREIGN KEY(`jugador1`) REFERENCES `usuarios`(`id`),
    CONSTRAINT `fk_jugador2` FOREIGN KEY(`jugador2`) REFERENCES `usuarios`(`id`),
    CONSTRAINT `uq_partida` UNIQUE (id, jugador1, jugador2)
);