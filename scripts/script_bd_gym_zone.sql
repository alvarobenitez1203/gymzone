DROP DATABASE IF EXISTS gym_zone;
CREATE DATABASE gym_zone;
USE gym_zone;


CREATE TABLE `usuarios` (
  `dni` varchar(9) NOT NULL,
  `tipo` enum('Cliente','Nutricionista','Monitor','Administrador') NOT NULL,
  `nombre` varchar(75) NOT NULL,
  `apellidos` varchar(150) NOT NULL,
  `genero` enum('Masculino','Femenino') NOT NULL,
  `correo` varchar(100) NOT NULL,
  `nombre_usuario` varchar(30) NOT NULL,
  `contrasenna` varchar(20) NOT NULL,
  PRIMARY KEY (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `cuentas_bancarias` (
  `id_cuenta_bancaria` int(11) AUTO_INCREMENT,
  `nombre_titular` varchar(100) NOT NULL,
  `numero_cuenta` varchar(24) NOT NULL,
  `banco` enum('BBVA', 'Santander', 'CaixaBank') NOT NULL,
  `saldo` double NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  PRIMARY KEY (`id_cuenta_bancaria`),
  KEY `cuentas_bancarias_FK` (`dni`),
  CONSTRAINT `cuentas_bancarias_FK` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `monitores` (
  `id_monitor` int(11) AUTO_INCREMENT,
  `dni` varchar(9) NOT NULL,
  `funcion` enum('Gimnasio','Cardio','Crossfit','Administracion') NOT NULL,
  PRIMARY KEY (`id_monitor`),
  KEY `monitores_FK` (`dni`),
  CONSTRAINT `monitores_FK` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `nutricionistas` (
  `id_nutricionista` int(11) AUTO_INCREMENT,
  `dni` varchar(9) DEFAULT NULL,
  `especialidad` enum('Deportiva','Clinica','Exclusion') NOT NULL,
  PRIMARY KEY (`id_nutricionista`),
  KEY `nutricionistas_FK` (`dni`),
  CONSTRAINT `nutricionistas_FK` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `grupos_musculares` (
  `id_grupo_muscular` int(11) AUTO_INCREMENT,
  `nombre` varchar(50) DEFAULT NULL,
  `tipo_tren` enum('Superior','Inferior') DEFAULT NULL,
  PRIMARY KEY (`id_grupo_muscular`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `musculos` (
  `id_musculo` int(11) AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `id_grupo_muscular` int(11) NOT NULL,
  PRIMARY KEY (`id_musculo`),
  KEY `musculos_FK` (`id_grupo_muscular`),
  CONSTRAINT `musculos_FK` FOREIGN KEY (`id_grupo_muscular`) REFERENCES `grupos_musculares` (`id_grupo_muscular`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ejercicios` (
  `id_ejercicio` int(11) AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  PRIMARY KEY (`id_ejercicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `ejercicios_musculos` (
  `id_ejercicio` int(11) NOT NULL,
  `id_musculo` int(11) NOT NULL,
  PRIMARY KEY (`id_ejercicio`, `id_musculo`),
  KEY `ejercicios_musculos_FK` (`id_ejercicio`),
  KEY `ejercicios_musculos_FK_1` (`id_musculo`),
  CONSTRAINT `ejercicios_musculos_FK` FOREIGN KEY (`id_ejercicio`) REFERENCES `ejercicios` (`id_ejercicio`),
  CONSTRAINT `ejercicios_musculos_FK_1` FOREIGN KEY (`id_musculo`) REFERENCES `musculos` (`id_musculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `rutinas` (
  `id_rutina` int(11) AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `objetivo` enum('Subida peso','Bajada peso','Recomposicion Corporal','Mantenimiento') NOT NULL,
  `id_monitor` int(11) NOT NULL,
  PRIMARY KEY (`id_rutina`),
  KEY `rutinas_FK` (`id_monitor`),
  CONSTRAINT `rutinas_FK` FOREIGN KEY (`id_monitor`) REFERENCES `monitores` (`id_monitor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `rutinas_ejercicios` (
  `id_rutina` int(11) NOT NULL,
  `id_ejercicio` int(11) NOT NULL,
  `dia` int(11) NOT NULL,
  PRIMARY KEY (`id_rutina`, `id_ejercicio`, `dia`),
  KEY `rutinas_ejercicios_FK` (`id_ejercicio`),
  CONSTRAINT `rutinas_ejercicios_FK` FOREIGN KEY (`id_ejercicio`) REFERENCES `ejercicios` (`id_ejercicio`),
  CONSTRAINT `rutinas_ejercicios_FK_1` FOREIGN KEY (`id_rutina`) REFERENCES `rutinas` (`id_rutina`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




CREATE TABLE `productos` (
  `id_producto` int(11) AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio_unidad` double NOT NULL,
  `cantidad_disponible` int(11) NOT NULL,
  `id_monitor` int(11) NOT NULL,
  PRIMARY KEY (`id_producto`),
  KEY `productos_FK` (`id_monitor`),
  CONSTRAINT `productos_FK` FOREIGN KEY (`id_monitor`) REFERENCES `monitores` (`id_monitor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `dietas` (
  `id_dieta` int(11) AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `tipo` enum('Subida peso','Bajada peso','Recomposicion Corporal','Mantenimiento') NOT NULL,
  `descripcion` varchar(150) NOT NULL,
  `id_nutricionista` int(11) NOT NULL,
  PRIMARY KEY (`id_dieta`),
  KEY `dietas_FK` (`id_nutricionista`),
  CONSTRAINT `dietas_FK` FOREIGN KEY (`id_nutricionista`) REFERENCES `nutricionistas` (`id_nutricionista`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `comidas` (
  `id_comida` int(11) AUTO_INCREMENT,
  `tipo` enum('Desayuno','Almuerzo','Merienda','Cena') NOT NULL,
  `dia` int(11) NOT NULL,
  `id_dieta` int(11) NOT NULL,
  PRIMARY KEY (`id_comida`),
  KEY `comidas_FK` (`id_dieta`),
  CONSTRAINT `comidas_FK` FOREIGN KEY (`id_dieta`) REFERENCES `dietas` (`id_dieta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `alimentos` (
  `id_alimento` int(11) AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `grupo_alimentacion` enum('Frutas','Verduras','Carbohidratos','Proteinas','Productos Lacteos','Grasas y aceites','Azucares y dulces') NOT NULL,
  `calorias_porcion` double NOT NULL,
  `proteinas_porcion` double NOT NULL,
  `carbohidratos_porcion` double NOT NULL,
  `grasas_porcion` double NOT NULL,
  PRIMARY KEY (`id_alimento`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `alimentos_comidas` (
  `id_alimento` int(11) NOT NULL,
  `id_comida` int(11) NOT NULL,
  PRIMARY KEY (`id_alimento`, `id_comida`),
  FOREIGN KEY (`id_alimento`) REFERENCES `alimentos` (`id_alimento`),
  FOREIGN KEY (`id_comida`) REFERENCES `comidas` (`id_comida`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `clientes` (
  `id_cliente` int(11) AUTO_INCREMENT,
  `fecha_nacimiento` date NOT NULL,
  `altura` int(11) NOT NULL,
  `peso` double NOT NULL,
  `objetivo_corporal` enum('Subida peso','Bajada peso','Recomposicion Corporal','Mantenimiento') NOT NULL,
   `dni` varchar(9) NOT NULL,
  `id_dieta` int(11) DEFAULT NULL,
  `id_rutina` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `usuarios_FK_1` (`dni`),
  KEY `usuarios_FK_2` (`id_rutina`),
  KEY `usuarios_FK_3` (`id_dieta`),
  CONSTRAINT `usuarios_FK_1` FOREIGN KEY (`dni`) REFERENCES `usuarios` (`dni`),
  CONSTRAINT `usuarios_FK_2` FOREIGN KEY (`id_rutina`) REFERENCES `rutinas` (`id_rutina`),
  CONSTRAINT `usuarios_FK_3` FOREIGN KEY (`id_dieta`) REFERENCES `dietas` (`id_dieta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



CREATE TABLE `ventas` (
  `id_producto` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `cantidad_vendida` int(11) NOT NULL,
  `fecha_venta` DATETIME NOT NULL,
  PRIMARY KEY (`id_producto`, `id_cliente`, `fecha_venta`),
  KEY `ventas_FK` (`id_producto`),
  KEY `ventas_FK_1` (`id_cliente`),
  CONSTRAINT `ventas_FK` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`),
  CONSTRAINT `ventas_FK_1` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `salas` (
  `id_sala` int(11) AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `capacidad_personas` int(11) NOT NULL,
  `id_monitor` int(11) NULL,
  PRIMARY KEY (`id_sala`),
  CONSTRAINT `salas_FK` FOREIGN KEY (`id_monitor`) REFERENCES `monitores` (`id_monitor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `reservas` (
  `id_cliente` int(11) NOT NULL,
  `id_sala` int(11) NOT NULL,
  `fecha_entrada` DATETIME NOT NULL,
  `fecha_salida` DATETIME NOT NULL,
  `estado` enum('Realizada','Cancelada') NOT NULL,
  PRIMARY KEY (`id_cliente`, `id_sala`, `fecha_entrada`),
  KEY `reservas_FK` (`id_cliente`),
  KEY `reservas_FK_2` (`id_sala`),
  CONSTRAINT `reservas_FK` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `reservas_FK_2` FOREIGN KEY (`id_sala`) REFERENCES `salas` (`id_sala`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `servicios` (
  `id_servicio` int(11) AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `precio` double NOT NULL,
  `dias_duracion` int(11) NOT NULL,
  `id_monitor` int(11) NOT NULL,
  PRIMARY KEY (`id_servicio`),
  KEY `servicios_FK` (`id_monitor`),
  CONSTRAINT `servicios_FK` FOREIGN KEY (`id_monitor`) REFERENCES `monitores` (`id_monitor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `pagos_servicios` (
  `id_cliente` int(11) NOT NULL,
  `id_servicio` int(11) NOT NULL,
  `estado` enum('No pagada','Pagada') NOT NULL,
  `fecha_pagado` DATETIME NOT NULL,
  PRIMARY KEY (`id_cliente`, `id_servicio`),
  KEY `pagos_FK` (`id_cliente`),
  KEY `pagos_FK_1` (`id_servicio`),
  CONSTRAINT `pagos_FK` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  CONSTRAINT `pagos_FK_1` FOREIGN KEY (`id_servicio`) REFERENCES `servicios` (`id_servicio`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;



