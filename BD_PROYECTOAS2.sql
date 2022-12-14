CREATE SCHEMA IF NOT EXISTS `bd_vacunacion` DEFAULT CHARACTER SET utf8mb4;
USE `bd_vacunacion`;


CREATE TABLE usuarios
(
  id int auto_increment PRIMARY KEY ,
  usuario  varchar(45),
  password varchar(45) ,
  nombre varchar(60) ,
  email varchar(30) ,
  idTipo int(11) 
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `afiliados` (
`id` int NOT NULL AUTO_INCREMENT,
`nombre` varchar(45) DEFAULT NULL,
`dpi`  varchar(45) DEFAULT NULL,
 `direccion` varchar(45) DEFAULT NULL,
 `telefono` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `vacunas` (
`id` int NOT NULL AUTO_INCREMENT,
`nombre` varchar(45) DEFAULT NULL,
`laboratorio`  varchar(45) DEFAULT NULL,
 `intervalo` DATE,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `centros` (
`id` int NOT NULL AUTO_INCREMENT,
`nombre` varchar(45) DEFAULT NULL,
`direccion`  varchar(45) DEFAULT NULL,
`telefono`  varchar(45) DEFAULT NULL,
`horario`  varchar(45) DEFAULT NULL,
 PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




CREATE TABLE `lote_vacunas` (
`código de identificación` int NOT NULL AUTO_INCREMENT,
`cantidad recibida` int DEFAULT NULL,
`cantidad en stock`   int DEFAULT NULL,
`fecha de entrega`  DATE,
`fecha de caducidad`  date,
 PRIMARY KEY (`código de identificación`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



CREATE TABLE `agendar_cita` (
`código_cita` int NOT NULL AUTO_INCREMENT,
`centro`  varchar(45) DEFAULT NULL,
`fecha_cita`  DATE,
`vacuna` varchar(45) DEFAULT NULL,
`lote_vacuna` int DEFAULT NULL,
PRIMARY KEY (`código_cita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `proceso_vacunacion` (
`código_proceso` int NOT NULL AUTO_INCREMENT,
`nombre`  varchar(45) DEFAULT NULL,
`dpi`  varchar(45) DEFAULT NULL,
`dosis`  varchar(45) DEFAULT NULL,
`fecha_vacunacion`  DATE,
PRIMARY KEY (`código_proceso`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



