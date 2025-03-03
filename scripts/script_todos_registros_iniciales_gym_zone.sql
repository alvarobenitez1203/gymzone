-- GRUPOS_MUSCULARES


-- Insertar tipos de grupos musculares
INSERT INTO grupos_musculares (nombre, tipo_tren) VALUES 
    ('Pecho', 'Superior'),
    ('Espalda', 'Superior'),
    ('Brazo', 'Superior'),
    ('Abdominales', 'Superior'),
    ('Pierna', 'Inferior');

	-- MUSCULOS

   -- Insertar músculos para el grupo muscular "Pecho"
INSERT INTO musculos (nombre, id_grupo_muscular) 
VALUES 
    ('Pectoral Mayor', 1),
    ('Pectoral Menor', 1);

-- Insertar músculos para el grupo muscular "Espalda"
INSERT INTO musculos (nombre, id_grupo_muscular) 
VALUES 
    ('Dorsal Ancho', 2),
    ('Trapecio', 2);

-- Insertar músculos para el grupo muscular "Brazo"
INSERT INTO musculos (nombre, id_grupo_muscular) 
VALUES 
    ('Bíceps Braquial', 3),
    ('Tríceps Braquial', 3),
    ('Deltoides', 3);

-- Insertar músculos para el grupo muscular "Abdominales"
INSERT INTO musculos (nombre, id_grupo_muscular) 
VALUES 
    ('Recto Abdominal', 4),
    ('Oblicuo Externo', 4),
    ('Oblicuo Interno', 4);

-- Insertar músculos para el grupo muscular "Pierna"
INSERT INTO musculos (nombre, id_grupo_muscular) 
VALUES 
    ('Cuádriceps Femoral', 5),
    ('Isquiotibiales', 5),
    ('Gemelo Medial', 5),
    ('Gemelo Lateral', 5),
    ('Sóleo', 5),
    ('Tibial Anterior', 5);

-- EJERCICIOS

-- Insertar ejercicios para el grupo muscular "Pecho"
INSERT INTO ejercicios (nombre, descripcion) VALUES 
    ('Press de Banca', 'Ejercicio para desarrollar el pectoral mayor'),
    ('Fondos en Paralelas', 'Ejercicio para trabajar el pectoral menor y tríceps'),
    ('Aperturas con Mancuernas', 'Ejercicio para aislar el pectoral'),
    ('Flexiones de Pecho', 'Ejercicio básico para el pectoral y tríceps'),
    ('Pullover con Mancuerna', 'Ejercicio para estirar el pectoral y trabajar los dorsales'),
    ('Press Inclinado con Barra', 'Ejercicio para desarrollar la parte superior del pectoral'),
    ('Cruce de Cables', 'Ejercicio de aislamiento para el pectoral'),
    ('Flexiones Declinadas', 'Variación de las flexiones para trabajar el pectoral inferior'),
    ('Press con Máquina de Pecho', 'Ejercicio guiado para el pectoral'),
    ('Flyes en Máquina', 'Ejercicio para aislar el pectoral y reducir la tensión en los hombros');

-- Insertar ejercicios para el grupo muscular "Espalda"
INSERT INTO ejercicios (nombre, descripcion) VALUES 
    ('Dominadas', 'Ejercicio básico para la espalda y brazos'),
    ('Remo con Barra', 'Ejercicio compuesto para desarrollar los dorsales'),
    ('Polea al Pecho', 'Ejercicio para aislar los dorsales'),
    ('Pull Over con Polea Alta', 'Ejercicio para estirar los dorsales y trabajar el pectoral'),
    ('Remo con Mancuerna', 'Ejercicio unilateral para equilibrar la musculatura de la espalda'),
    ('Pulldown en Polea', 'Ejercicio para desarrollar la anchura de la espalda'),
    ('Hiperextensiones', 'Ejercicio para fortalecer la zona lumbar'),
    ('Remo en Máquina', 'Ejercicio guiado para los dorsales'),
    ('Peso Muerto', 'Ejercicio compuesto para la espalda baja y piernas'),
    ('Pull Up', 'Variante de las dominadas para la espalda');

-- Insertar ejercicios para el grupo muscular "Brazo"
INSERT INTO ejercicios (nombre, descripcion) VALUES 
    ('Curl de Bíceps Martillo', 'Ejercicio básico para desarrollar el bíceps'),
    ('Curl Concentrado', 'Ejercicio para aislar el bíceps'),
    ('Curl de Bíceps con Mancuernas', 'Variante del curl de bíceps con mancuernas'),
    ('Fondos en Paralelas', 'Ejercicio para trabajar tríceps y pecho'),
    ('Press Francés con Barra', 'Ejercicio para aislar y desarrollar el tríceps'),
    ('Extensión de Tríceps en Polea Alta', 'Ejercicio para trabajar la cabeza larga del tríceps'),
    ('Extensiones de Tríceps en Banco', 'Ejercicio para trabajar la cabeza larga del tríceps'),
    ('Elevaciones Laterales', 'Ejercicio para desarrollar el deltoides lateral'),
    ('Press Militar', 'Ejercicio compuesto para el deltoides anterior'),
    ('Pájaros', 'Ejercicio para el deltoides posterior');

-- Insertar ejercicios para el grupo muscular "Abdominales"
INSERT INTO ejercicios (nombre, descripcion) VALUES 
    ('Crunch', 'Ejercicio básico para trabajar los abdominales'),
    ('Plancha', 'Ejercicio estático para fortalecer la zona media'),
    ('Elevación de Piernas', 'Ejercicio para trabajar los abdominales inferiores'),
    ('Giros Rusos', 'Ejercicio para trabajar los oblicuos'),
    ('Abdominales en Máquina', 'Ejercicio guiado para los abdominales'),
    ('Elevación de Piernas Colgado', 'Ejercicio para los abdominales inferiores y oblicuos'),
    ('Crunch con Mancuerna', 'Variante del crunch con resistencia adicional'),
    ('Elevación de Tronco en Suelo', 'Ejercicio para trabajar los abdominales superiores'),
    ('Plancha Lateral', 'Ejercicio para fortalecer los oblicuos'),
    ('Rodillas al Pecho', 'Ejercicio para trabajar los abdominales inferiores');

-- Insertar ejercicios para el grupo muscular "Pierna"
INSERT INTO ejercicios (nombre, descripcion) VALUES 
    ('Sentadillas', 'Ejercicio básico para las piernas y glúteos'),
    ('Prensa de Piernas', 'Ejercicio compuesto para cuádriceps y glúteos'),
    ('Extensiones de Cuádriceps', 'Ejercicio de aislamiento para cuádriceps'),
    ('Curl de Piernas Tumbado', 'Ejercicio para los isquiotibiales'),
    ('Peso Muerto', 'Ejercicio compuesto para la espalda baja y piernas'),
    ('Zancadas', 'Ejercicio para trabajar los cuádriceps y glúteos'),
    ('Elevación de Talones', 'Ejercicio para los gemelos'),
    ('Abducción de Cadera', 'Ejercicio para los abductores'),
    ('Aductores en Máquina', 'Ejercicio para los aductores'),
    ('Elevación de Talones Sentado', 'Variante del ejercicio para los gemelos');


-- EJERCICIOS_MUSCULOS

-- Insertar relaciones entre ejercicios y músculos en la tabla ejercicios_musculos
-- Press de Banca
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(1, 1), -- Pectoral Mayor
(1, 2), -- Pectoral Menor
(1, 6); -- Tríceps Braquial

-- Fondos en Paralelas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(2, 1), -- Pectoral Mayor
(2, 2), -- Pectoral Menor
(2, 6); -- Tríceps Braquial

-- Aperturas con Mancuernas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(3, 1), -- Pectoral Mayor
(3, 2); -- Pectoral Menor

-- Flexiones de Pecho
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(4, 1), -- Pectoral Mayor
(4, 2), -- Pectoral Menor
(4, 6); -- Tríceps Braquial

-- Pullover con Mancuerna
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(5, 1), -- Pectoral Mayor
(5, 3); -- Dorsal Ancho

-- Press Inclinado con Barra
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(6, 1), -- Pectoral Mayor
(6, 2), -- Pectoral Menor
(6, 6); -- Tríceps Braquial

-- Cruce de Cables
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(7, 1), -- Pectoral Mayor
(7, 2); -- Pectoral Menor

-- Flexiones Declinadas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(8, 1), -- Pectoral Mayor
(8, 2); -- Pectoral Menor

-- Press con Máquina de Pecho
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(9, 1), -- Pectoral Mayor
(9, 2); -- Pectoral Menor

-- Flyes en Máquina
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(10, 1), -- Pectoral Mayor
(10, 2); -- Pectoral Menor

-- Dominadas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(11, 3), -- Dorsal Ancho
(11, 4); -- Trapecio

-- Remo con Barra
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(12, 3), -- Dorsal Ancho
(12, 4); -- Trapecio

-- Polea al Pecho
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(13, 3), -- Dorsal Ancho
(13, 4); -- Trapecio

-- Pull Over con Polea Alta
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(14, 1), -- Pectoral Mayor
(14, 3); -- Dorsal Ancho

-- Remo con Mancuerna
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(15, 3), -- Dorsal Ancho
(15, 4); -- Trapecio

-- Pulldown en Polea
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(16, 3); -- Dorsal Ancho

-- Hiperextensiones
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(17, 3), -- Dorsal Ancho
(17, 4); -- Trapecio

-- Remo en Máquina
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(18, 3); -- Dorsal Ancho

-- Peso Muerto
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(19, 3), -- Dorsal Ancho
(19, 11), -- Cuádriceps Femoral
(19, 12); -- Isquiotibiales

-- Pull Up
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(20, 3), -- Dorsal Ancho
(20, 4); -- Trapecio


-- Curl de Bíceps Martillo
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(21, 5); -- Bíceps Braquial

-- Curl Concentrado
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(22, 5); -- Bíceps Braquial

-- Curl de Bíceps con Mancuernas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(23, 5); -- Bíceps Braquial

-- Fondos en Paralelas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(24, 1), -- Pectoral Mayor
(24, 6); -- Tríceps Braquial

-- Press Francés con Barra
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(25, 6); -- Tríceps Braquial

-- Extensión de Tríceps en Polea Alta
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(26, 6); -- Tríceps Braquial

-- Extensiones de Tríceps en Banco
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(27, 6); -- Tríceps Braquial

-- Elevaciones Laterales
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(28, 7); -- Deltoides

-- Press Militar
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(29, 7); -- Deltoides

-- Pájaros
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(30, 7); -- Deltoides

-- Crunch
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(31, 8); -- Recto Abdominal

-- Plancha
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(32, 8), -- Recto Abdominal
(32, 9), -- Oblicuo Externo
(32, 10); -- Oblicuo Interno

-- Elevación de Piernas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(33, 8); -- Recto Abdominal

-- Giros Rusos
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(34, 9), -- Oblicuo Externo
(34, 10); -- Oblicuo Interno

-- Abdominales en Máquina
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(35, 8); -- Recto Abdominal

-- Elevación de Piernas Colgado
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(36, 8), -- Recto Abdominal
(36, 9), -- Oblicuo Externo
(36, 10); -- Oblicuo Interno

-- Crunch con Mancuerna
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(37, 8); -- Recto Abdominal

-- Elevación de Tronco en Suelo
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(38, 8); -- Recto Abdominal

-- Plancha Lateral
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(39, 9), -- Oblicuo Externo
(39, 10); -- Oblicuo Interno


-- Rodillas al Pecho
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(40, 8); -- Recto Abdominal

-- Sentadillas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(41, 11), -- Cuádriceps Femoral
(41, 12), -- Isquiotibiales
(41, 13), -- Gemelo Medial
(41, 14), -- Gemelo Lateral
(41, 15), -- Sóleo
(41, 16); -- Tibial Anterior

-- Prensa de Piernas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(42, 11), -- Cuádriceps Femoral
(42, 13), -- Gemelo Medial
(42, 14); -- Gemelo Lateral

-- Extensiones de Cuádriceps
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(43, 11); -- Cuádriceps Femoral

-- Curl de Piernas Tumbado
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(44, 12); -- Isquiotibiales

-- Peso Muerto
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(45, 3), -- Dorsal Ancho
(45, 11), -- Cuádriceps Femoral
(45, 12); -- Isquiotibiales

-- Zancadas
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(46, 11), -- Cuádriceps Femoral
(46, 13), -- Gemelo Medial
(46, 14); -- Gemelo Lateral

-- Elevación de Talones
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(47, 13), -- Gemelo Medial
(47, 14), -- Gemelo Lateral
(47, 15); -- Sóleo

-- Abducción de Cadera
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(48, 8), -- Recto Abdominal
(48, 10); -- Oblicuo Interno

-- Aductores en Máquina
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(49, 16); -- Tibial Anterior

-- Elevación de Talones Sentado
INSERT INTO ejercicios_musculos (id_ejercicio, id_musculo) VALUES
(50, 13), -- Gemelo Medial
(50, 14), -- Gemelo Lateral
(50, 15); -- Sóleo


-- ALIMENTOS

-- Crear alimentos de grupo 'Frutas'
INSERT INTO alimentos (nombre, grupo_alimentacion, calorias_porcion, proteinas_porcion, carbohidratos_porcion, grasas_porcion)
VALUES 
    ('Manzana', 'Frutas', 52, 0.26, 13.81, 0.17),
    ('Banana', 'Frutas', 89, 1.09, 22.84, 0.33),
    ('Naranja', 'Frutas', 47, 0.94, 11.75, 0.12),
    ('Uvas', 'Frutas', 69, 0.72, 18.1, 0.16),
    ('Pera', 'Frutas', 58, 0.38, 15.5, 0.12),
    ('Fresas', 'Frutas', 32, 0.67, 7.68, 0.3),
    ('Kiwi', 'Frutas', 61, 1.14, 14.66, 0.52),
    ('Piña', 'Frutas', 50, 0.54, 13.12, 0.12),
    ('Mango', 'Frutas', 60, 0.82, 15, 0.38),
    ('Sandía', 'Frutas', 30, 0.61, 7.55, 0.15);

-- Crear alimentos de grupo 'Verduras'
INSERT INTO alimentos (nombre, grupo_alimentacion, calorias_porcion, proteinas_porcion, carbohidratos_porcion, grasas_porcion)
VALUES 
    ('Lechuga', 'Verduras', 15, 1.36, 2.87, 0.15),
    ('Tomate', 'Verduras', 18, 0.88, 3.89, 0.2),
    ('Pepino', 'Verduras', 15, 0.65, 3.63, 0.11),
    ('Zanahoria', 'Verduras', 41, 0.93, 9.58, 0.24),
    ('Espinaca', 'Verduras', 23, 2.86, 3.63, 0.39),
    ('Brócoli', 'Verduras', 34, 2.82, 6.64, 0.37),
    ('Pimiento', 'Verduras', 20, 0.99, 4.64, 0.17),
    ('Cebolla', 'Verduras', 40, 1.1, 9.34, 0.1),
    ('Coliflor', 'Verduras', 25, 1.92, 4.97, 0.28),
    ('Calabacín', 'Verduras', 17, 1.21, 3.11, 0.23);

-- Crear alimentos de grupo 'Carbohidratos'
INSERT INTO alimentos (nombre, grupo_alimentacion, calorias_porcion, proteinas_porcion, carbohidratos_porcion, grasas_porcion)
VALUES 
    ('Arroz', 'Carbohidratos', 130, 2.66, 28.73, 0.28),
    ('Avena', 'Carbohidratos', 389, 16.89, 66.27, 6.9),
    ('Pasta', 'Carbohidratos', 131, 4.96, 25.22, 0.86),
    ('Pan', 'Carbohidratos', 265, 9.43, 49.33, 3.51),
    ('Patatas', 'Carbohidratos', 77, 2.02, 17.47, 0.1),
    ('Maíz', 'Carbohidratos', 86, 3.27, 19.02, 0.99),
    ('Cereales', 'Carbohidratos', 365, 9.35, 74.96, 2.38),
    ('Quinoa', 'Carbohidratos', 120, 4.4, 21.3, 1.9),
    ('Trigo', 'Carbohidratos', 340, 12.15, 71.18, 2.5),
    ('Centeno', 'Carbohidratos', 323, 10.6, 65.79, 1.2);

-- Crear alimentos de grupo 'Proteínas'
INSERT INTO alimentos (nombre, grupo_alimentacion, calorias_porcion, proteinas_porcion, carbohidratos_porcion, grasas_porcion)
VALUES 
    ('Pollo', 'Proteínas', 165, 31, 0, 3.6),
    ('Huevo', 'Proteínas', 155, 12.58, 0.77, 11.11),
    ('Atún', 'Proteínas', 184, 25.53, 0, 8.1),
    ('Salmón', 'Proteínas', 208, 20.42, 0, 13.84),
    ('Carne', 'Proteínas', 250, 25.92, 0, 16.19),
    ('Tofu', 'Proteínas', 70, 8.19, 1.56, 3.5),
    ('Lentejas', 'Proteínas', 116, 9.02, 20.13, 0.38),
    ('Garbanzos', 'Proteínas', 164, 8.86, 27.42, 2.59),
    ('Cacahuetes', 'Proteínas', 567, 25.8, 16.13, 49.24),
    ('Almendras', 'Proteínas', 579, 21.15, 21.55, 49.42);

-- Crear alimentos de grupo 'Productos Lácteos'
INSERT INTO alimentos (nombre, grupo_alimentacion, calorias_porcion, proteinas_porcion, carbohidratos_porcion, grasas_porcion)
VALUES 
    ('Leche', 'Productos Lácteos', 42, 3.4, 5.04, 1.04),
    ('Queso', 'Productos Lácteos', 402, 25, 1.33, 33.14),
    ('Yogur', 'Productos Lácteos', 61, 3.47, 4.66, 3.3),
    ('Mantequilla', 'Productos Lácteos', 717, 0.85, 0.06, 81.11),
    ('Nata', 'Productos Lácteos', 341, 2.11, 3.41, 36.67),
    ('Helado', 'Productos Lácteos', 207, 3.88, 28.29, 9.06),
    ('Kéfir', 'Productos Lácteos', 50, 3.3, 4.5, 1.8),
    ('Leche de almendras', 'Productos Lácteos', 13, 0.38, 0.58, 1.08),
    ('Leche de coco', 'Productos Lácteos', 230, 2.29, 5.5, 23.84),
    ('Queso crema', 'Productos Lácteos', 342, 6.98, 4.09, 34.13);

-- Crear alimentos de grupo 'Grasas y aceites'
INSERT INTO alimentos (nombre, grupo_alimentacion, calorias_porcion, proteinas_porcion, carbohidratos_porcion, grasas_porcion)
VALUES 
    ('Aceite de oliva', 'Grasas y aceites', 119, 0, 0, 13.5),
    ('Aceite de coco', 'Grasas y aceites', 862, 0, 0, 100),
    ('Mantequilla de cacahuate', 'Grasas y aceites', 589, 25.09, 16.13, 49.24),
    ('Margarina', 'Grasas y aceites', 361, 0.13, 0.06, 40.8),
    ('Manteca de cerdo', 'Grasas y aceites', 902, 0, 0.06, 100),
    ('Aceite de girasol', 'Grasas y aceites', 884, 0, 0, 100),
    ('Aceite de maíz', 'Grasas y aceites', 884, 0, 0, 100),
    ('Mantequilla de cacao', 'Grasas y aceites', 884, 0, 0, 100),
    ('Aceite de linaza', 'Grasas y aceites', 884, 0, 0, 100),
    ('Aceite de soja', 'Grasas y aceites', 884, 0, 0, 100);

-- Crear alimentos de grupo 'Azucares y dulces'
INSERT INTO alimentos (nombre, grupo_alimentacion, calorias_porcion, proteinas_porcion, carbohidratos_porcion, grasas_porcion)
VALUES 
    ('Azúcar', 'Azucares y dulces', 16, 0, 4.2, 0),
    ('Miel', 'Azucares y dulces', 304, 0.3, 82.4, 0),
    ('Jarabe de maíz', 'Azucares y dulces', 281, 0, 76.5, 0),
    ('Melaza', 'Azucares y dulces', 290, 3.9, 74.7, 0),
    ('Sirope de arce', 'Azucares y dulces', 260, 0.4, 67, 0.5),
    ('Dulce de leche', 'Azucares y dulces', 320, 6, 56, 9),
    ('Chocolate', 'Azucares y dulces', 546, 5.5, 57.5, 31.5),
    ('Piruletas', 'Azucares y dulces', 50, 0, 13, 0),
    ('Caramelos', 'Azucares y dulces', 394, 0, 98, 0),
    ('Gominolas', 'Azucares y dulces', 324, 0, 81, 0);


-- CLIENTES

-- Cliente 1
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('12345678A', 'Cliente', 'Juan', 'Perez Gomez', 'Masculino', 'juanper24@hotmail.com', 'juanit93', 'Juani123');

INSERT INTO `clientes` (`fecha_nacimiento`, `altura`, `peso`, `objetivo_corporal`, `dni`)
VALUES ('1990-05-15', 175, 75.5, 'Bajada peso', '12345678A');

-- Cliente 2
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('98765432B', 'Cliente', 'Maria', 'Lopez Martinez', 'Femenino', 'marialop96@gmail.com', 'marialo13', 'Maria456');

INSERT INTO `clientes` (`fecha_nacimiento`, `altura`, `peso`, `objetivo_corporal`, `dni`)
VALUES ('1985-08-20', 160, 65.2, 'Mantenimiento', '98765432B');

-- Cliente 3
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('11122233C', 'Cliente', 'Pedro', 'Gonzalez Rodriguez', 'Masculino', 'pedrogonz47@gmail.com', 'pedrog73', 'Pedro789');

INSERT INTO `clientes` (`fecha_nacimiento`, `altura`, `peso`, `objetivo_corporal`, `dni`)
VALUES ('1995-02-10', 180, 80.0, 'Subida peso', '11122233C');


-- NUTRICIONISTAS


-- Nutricionista 1
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('33344455D', 'Nutricionista', 'Ana', 'Sanchez Fernandez', 'Femenino', 'anasan23@gmail.com', 'anasan23', 'Anasa123');

INSERT INTO `nutricionistas` (`dni`, `especialidad`)
VALUES ('33344455D', 'Deportiva');

-- Nutricionista 2
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('55566677E', 'Nutricionista', 'Carlos', 'Martinez Lopez', 'Masculino', 'carlosmar56z@hotmail.com', 'carlosm23', 'Carlos456');

INSERT INTO `nutricionistas` (`dni`, `especialidad`)
VALUES ('55566677E', 'Clinica');

-- Nutricionista 3
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('77788899F', 'Nutricionista', 'Elena', 'Garcia Ruiz', 'Femenino', 'elenagar56@hotmail.com', 'elenagr12', 'Elena789');

INSERT INTO `nutricionistas` (`dni`, `especialidad`)
VALUES ('77788899F', 'Exclusion');

-- MONITORES

-- Monitor 1
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('66677788H', 'Monitor', 'Laura', 'Diaz Rodriguez', 'Femenino', 'lauradi56@hotmail.com', 'lauradr10', 'Laura456');

INSERT INTO `monitores` (`dni`, `funcion`)
VALUES ('66677788H', 'Gimnasio');

-- Monitor 2
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('88899900I', 'Monitor', 'Antonio', 'Gomez Perez', 'Masculino', 'antoniogom55@gmail.com', 'toniog13', 'Antonio789');

INSERT INTO `monitores` (`dni`, `funcion`)
VALUES ('88899900I', 'Cardio');


-- Monitor 3
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('33445566K', 'Monitor', 'Javier', 'Gonzalez Fernandez', 'Masculino', 'javiergon32@gmail.com', 'javirg13', 'Javier123');

INSERT INTO `monitores` (`dni`, `funcion`)
VALUES ('33445566K', 'Crossfit');



-- ADMINISTRADORES

-- Monitor 1 (Administrador)
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('44455566G', 'Administrador', 'Pablo', 'Fernandez Gomez', 'Masculino', 'pablofer45@gmail.com', 'pablof12', 'Pablo123');

INSERT INTO `monitores` (`dni`, `funcion`)
VALUES ('44455566G', 'Administracion');

-- Monitor 2 (Administrador)
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('11223344J', 'Administrador', 'Isabel', 'Lopez Martinez', 'Femenino', 'isabello99@gmail.com', 'isabell12', 'Isabel123');

INSERT INTO `monitores` (`dni`, `funcion`)
VALUES ('11223344J', 'Administracion');


-- Monitor 3 (Administrador)
INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('77889900L', 'Administrador', 'Pedro', 'Garcia Fernandez', 'Masculino', 'pedrogar23@hotmail.com', 'pedrog45', 'Pedro123');

INSERT INTO `monitores` (`dni`, `funcion`)
VALUES ('77889900L', 'Administracion');


-- CUENTAS BANCARIAS

-- Cuentas bancarias para Nutricionistas

-- Cuenta bancaria para Nutricionista Ana (BBVA)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Ana Sanchez Fernandez', '1234-5678-90-1234567890', 'BBVA', 1500.00, '33344455D');

-- Cuenta bancaria para Nutricionista Carlos (Santander)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Carlos Martinez Lopez', '5678-1234-56-0987654321', 'Santander', 1800.00, '55566677E');

-- Cuenta bancaria para Nutricionista Elena (CaixaBank)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Elena Garcia Ruiz', '9876-5432-10-5432109876', 'CaixaBank', 2000.00, '77788899F');

-- Cuentas bancarias para Monitores

-- Cuenta bancaria para Monitor Laura (BBVA)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Laura Diaz Rodriguez', '2345-6789-01-2345678901', 'BBVA', 1600.00, '66677788H');

-- Cuenta bancaria para Monitor Antonio (Santander)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Antonio Gomez Perez', '6789-0123-45-8765432109', 'Santander', 1900.00, '88899900I');

-- Cuenta bancaria para Monitor Javier (CaixaBank)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Javier Gonzalez Fernandez', '8765-4321-09-9876543210', 'CaixaBank', 2100.00, '33445566K');

-- Cuentas bancarias para Administradores

-- Cuenta bancaria para Administrador Pablo (BBVA)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Pablo Fernandez Gomez', '4321-0987-65-4321098765', 'BBVA', 2200.00, '44455566G');

-- Cuenta bancaria para Administrador Isabel (Santander)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Isabel Lopez Martinez', '8901-2345-67-8901234567', 'Santander', 2300.00, '11223344J');

-- Cuenta bancaria para Administrador Pedro (CaixaBank)
INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Pedro Garcia Fernandez', '5432-1098-76-5432109876', 'CaixaBank', 2400.00, '77889900L');


-- RUTINAS

-- Insertar Rutina 1 para "Subida peso" con id_monitor 1 (Laura Diaz Rodriguez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (1, 'Fuerza y Masa Muscular', 'Subida peso', 1);

-- Insertar Rutina 2 para "Subida peso" con id_monitor 2 (Antonio Gomez Perez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (2, 'Hipertrofia Muscular', 'Subida peso', 2);


-- Insertar Rutina 3 para "Subida peso" con id_monitor 3 (Javier Gonzalez Fernandez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (3, 'Desarrollo Muscular Completo', 'Subida peso', 3);

-- Insertar Rutina 4 para "Bajada peso" con id_monitor 1 (Laura Diaz Rodriguez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (4, 'Cardio y Quema de Grasa', 'Bajada peso', 1);


-- Insertar Rutina 5 para "Bajada peso" con id_monitor 2 (Antonio Gomez Perez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (5, 'Entrenamiento Funcional y Resistencia', 'Bajada peso', 2);


-- Insertar Rutina 6 para "Bajada peso" con id_monitor 3 (Javier Gonzalez Fernandez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (6, 'Cardio y Tonificación', 'Bajada peso', 3);


-- Insertar Rutina 7 para "Recomposición Corporal" con id_monitor 1 (Laura Diaz Rodriguez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (7, 'Fuerza y Hipertrofia', 'Recomposicion Corporal', 1);


-- Insertar Rutina 8 para "Recomposición Corporal" con id_monitor 2 (Antonio Gomez Perez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (8, 'Circuitos y Cardio', 'Recomposicion Corporal', 2);


-- Insertar Rutina 9 para "Recomposición Corporal" con id_monitor 3 (Javier Gonzalez Fernandez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (9, 'Funcional y Estabilidad', 'Recomposicion Corporal', 3);


-- Insertar Rutina 10 para "Mantenimiento" con id_monitor 1 (Laura Diaz Rodriguez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (10, 'Mantenimiento General', 'Mantenimiento', 1);


-- Insertar Rutina 11 para "Mantenimiento" con id_monitor 2 (Antonio Gomez Perez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (11, 'Mantenimiento Total', 'Mantenimiento', 2);


-- Insertar Rutina 12 para "Mantenimiento" con id_monitor 3 (Javier Gonzalez Fernandez)
INSERT INTO `rutinas` (`id_rutina`, `nombre`, `objetivo`, `id_monitor`)
VALUES (12, 'Mantenimiento Equilibrado', 'Mantenimiento', 3);



-- EJERCICIOS_RUTINAS


-- Ejercicios de la Rutina 1

-- Día 1: Fuerza Pectoral y Espalda
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (1, 1, 1),   -- Press de Banca
       (1, 3, 1),   -- Aperturas con Mancuernas
       (1, 12, 1),  -- Remo con Barra
       (1, 19, 1),  -- Peso Muerto
       (1, 21, 1);  -- Curl de Bíceps Martillo

-- Día 2: Hipertrofia Pectoral y Tríceps
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (1, 2, 2),   -- Fondos en Paralelas
       (1, 10, 2),  -- Flyes en Máquina
       (1, 18, 2),  -- Remo en Máquina
       (1, 31, 2),  -- Crunch
       (1, 6, 2);   -- Press Inclinado con Barra

-- Día 3: Espalda y Abdominales
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (1, 15, 3),  -- Remo con Mancuerna
       (1, 16, 3),  -- Pulldown en Polea
       (1, 40, 3),  -- Rodillas al Pecho
       (1, 11, 3),  -- Dominadas
       (1, 5, 3);   -- Pullover con Mancuerna


-- Ejercicios de la Rutina 2

-- Día 1: Fuerza y Desarrollo Muscular
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (2, 1, 1),   -- Press de Banca
       (2, 12, 1),  -- Remo con Barra
       (2, 19, 1),  -- Peso Muerto
       (2, 21, 1),  -- Curl de Bíceps Martillo
       (2, 2, 1);   -- Fondos en Paralelas

-- Día 2: Espalda y Hombros
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (2, 13, 2),  -- Polea al Pecho
       (2, 20, 2),  -- Pull Up
       (2, 29, 2),  -- Press Militar
       (2, 6, 2),   -- Press Inclinado con Barra
       (2, 3, 2);   -- Aperturas con Mancuernas

-- Día 3: Piernas y Abdominales
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (2, 41, 3),  -- Sentadillas
       (2, 42, 3),  -- Prensa de Piernas
       (2, 43, 3),  -- Extensiones de Cuádriceps
       (2, 34, 3),  -- Giros Rusos
       (2, 5, 3);   -- Pullover con Mancuerna


-- Ejercicios de la Rutina 3

-- Día 1: Pecho y Tríceps
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (3, 1, 1),   -- Press de Banca
       (3, 4, 1),   -- Flexiones de Pecho
       (3, 10, 1),  -- Flyes en Máquina
       (3, 25, 1),  -- Press Francés con Barra
       (3, 27, 1);  -- Extensiones de Tríceps en Banco

-- Día 2: Espalda y Bíceps
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (3, 12, 2),  -- Remo con Barra
       (3, 15, 2),  -- Remo con Mancuerna
       (3, 16, 2),  -- Pulldown en Polea
       (3, 22, 2),  -- Curl Concentrado
       (3, 23, 2);  -- Curl de Bíceps con Mancuernas

-- Día 3: Piernas y Abdominales
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (3, 41, 3),  -- Sentadillas
       (3, 42, 3),  -- Prensa de Piernas
       (3, 43, 3),  -- Extensiones de Cuádriceps
       (3, 31, 3),  -- Crunch
       (3, 33, 3);  -- Elevación de Piernas       


-- Ejercicios de la Rutina 4

-- Día 1: Cardio Intenso
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (4, 14, 1),  -- Pull Over con Polea Alta
       (4, 17, 1),  -- Hiperextensiones
       (4, 26, 1),  -- Extensión de Tríceps en Polea Alta
       (4, 38, 1),  -- Elevación de Tronco en Suelo
       (4, 48, 1);  -- Elevación de Talones Sentado

-- Día 2: Cardio y Core
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (4, 32, 2),  -- Plancha
       (4, 35, 2),  -- Abdominales en Máquina
       (4, 36, 2),  -- Elevación de Piernas Colgado
       (4, 39, 2),  -- Plancha Lateral
       (4, 40, 2);  -- Rodillas al Pecho

-- Día 3: Cardio y Piernas
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (4, 44, 3),  -- Curl de Piernas Tumbado
       (4, 45, 3),  -- Peso Muerto
       (4, 46, 3),  -- Zancadas
       (4, 47, 3),  -- Elevación de Talones
       (4, 50, 3);  -- Elevación de Talones Sentado


-- Ejercicios de la Rutina 5


-- Día 1: Circuito de Funcional
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (5, 11, 1),   -- Dominadas
       (5, 13, 1),   -- Polea al Pecho
       (5, 18, 1),   -- Remo en Máquina
       (5, 28, 1),   -- Elevaciones Laterales
       (5, 30, 1);   -- Pájaros

-- Día 2: Cardio HIIT y Core
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (5, 2, 2),    -- Fondos en Paralelas
       (5, 9, 2),    -- Press con Máquina de Pecho
       (5, 16, 2),   -- Pulldown en Polea
       (5, 33, 2),   -- Elevación de Piernas
       (5, 37, 2);   -- Crunch con Mancuerna

-- Día 3: Cardio y Resistencia
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (5, 3, 3),    -- Aperturas con Mancuernas
       (5, 6, 3),    -- Press Inclinado con Barra
       (5, 22, 3),   -- Curl Concentrado
       (5, 23, 3),   -- Curl de Bíceps con Mancuernas
       (5, 24, 3);   -- Fondos en Paralelas


-- Ejercicios de la Rutina 6

-- Día 1: Cardio y Core
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (6, 14, 1),   -- Pull Over con Polea Alta
       (6, 17, 1),   -- Hiperextensiones
       (6, 26, 1),   -- Extensión de Tríceps en Polea Alta
       (6, 32, 1),   -- Plancha
       (6, 40, 1);   -- Rodillas al Pecho

-- Día 2: Cardio y Brazos
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (6, 21, 2),   -- Curl de Bíceps Martillo
       (6, 25, 2),   -- Press Francés con Barra
       (6, 26, 2),   -- Extensión de Tríceps en Polea Alta
       (6, 30, 2),   -- Pájaros
       (6, 46, 2);   -- Zancadas

-- Día 3: Cardio y Piernas
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (6, 41, 3),   -- Sentadillas
       (6, 42, 3),   -- Prensa de Piernas
       (6, 46, 3),   -- Zancadas
       (6, 47, 3),   -- Elevación de Talones
       (6, 48, 3);   -- Abducción de Cadera

-- Ejercicios de la Rutina 7

-- Día 1: Pecho y Tríceps
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (7, 1, 1),    -- Press de Banca
       (7, 2, 1),    -- Fondos en Paralelas
       (7, 6, 1),    -- Press Inclinado con Barra
       (7, 9, 1),    -- Press con Máquina de Pecho
       (7, 10, 1);   -- Flyes en Máquina

-- Día 2: Espalda y Bíceps
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (7, 12, 2),   -- Remo con Barra
       (7, 15, 2),   -- Remo con Mancuerna
       (7, 18, 2),   -- Remo en Máquina
       (7, 21, 2),   -- Curl de Bíceps Martillo
       (7, 22, 2);   -- Curl Concentrado

-- Día 3: Piernas y Core
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (7, 41, 3),   -- Sentadillas
       (7, 42, 3),   -- Prensa de Piernas
       (7, 46, 3),   -- Zancadas
       (7, 32, 3),   -- Plancha
       (7, 33, 3);   -- Elevación de Piernas

-- Ejercicios de la Rutina 8

-- Día 1: Circuito de Cuerpo Completo
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (8, 1, 1),    -- Press de Banca
       (8, 12, 1),   -- Remo con Barra
       (8, 21, 1),   -- Curl de Bíceps Martillo
       (8, 41, 1),   -- Sentadillas
       (8, 33, 1);   -- Elevación de Piernas

-- Día 2: Cardio HIIT y Core
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (8, 14, 2),   -- Pull Over con Polea Alta
       (8, 16, 2),   -- Pulldown en Polea
       (8, 26, 2),   -- Extensión de Tríceps en Polea Alta
       (8, 32, 2),   -- Plancha
       (8, 37, 2);   -- Crunch con Mancuerna

-- Día 3: Cardio y Resistencia
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (8, 6, 3),    -- Press Inclinado con Barra
       (8, 17, 3),   -- Hiperextensiones
       (8, 23, 3),   -- Curl de Bíceps con Mancuernas
       (8, 42, 3),   -- Prensa de Piernas
       (8, 47, 3);   -- Elevación de Talones


-- Ejercicios de la Rutina 9


-- Día 1: Funcional y Core
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (9, 11, 1),   -- Dominadas
       (9, 13, 1),   -- Polea al Pecho
       (9, 28, 1),   -- Elevaciones Laterales
       (9, 30, 1),   -- Pájaros
       (9, 32, 1);   -- Plancha

-- Día 2: Cardio y Estabilidad
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (9, 14, 2),   -- Pull Over con Polea Alta
       (9, 18, 2),   -- Remo en Máquina
       (9, 23, 2),   -- Curl de Bíceps con Mancuernas
       (9, 42, 2),   -- Prensa de Piernas
       (9, 48, 2);   -- Abducción de Cadera

-- Día 3: Fuerza y Equilibrio
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (9, 2, 3),    -- Fondos en Paralelas
       (9, 15, 3),   -- Remo con Mancuerna
       (9, 22, 3),   -- Curl Concentrado
       (9, 41, 3),   -- Sentadillas
       (9, 46, 3);   -- Zancadas


-- Ejercicios de la Rutina 10


-- Día 1: Fuerza y Resistencia
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (10, 1, 1),   -- Press de Banca
       (10, 12, 1),  -- Remo con Barra
       (10, 41, 1),  -- Sentadillas
       (10, 32, 1),  -- Plancha
       (10, 47, 1);  -- Elevación de Talones

-- Día 2: Cardio y Flexibilidad
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (10, 14, 2),  -- Pull Over con Polea Alta
       (10, 18, 2),  -- Remo en Máquina
       (10, 33, 2),  -- Elevación de Piernas
       (10, 39, 2),  -- Plancha Lateral
       (10, 48, 2);  -- Abducción de Cadera

-- Día 3: Entrenamiento Funcional
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (10, 6, 3),   -- Press Inclinado con Barra
       (10, 17, 3),  -- Hiperextensiones
       (10, 28, 3),  -- Elevaciones Laterales
       (10, 30, 3),  -- Pájaros
       (10, 40, 3);  -- Rodillas al Pecho


-- Ejercicios de la Rutina 11


-- Día 1: Entrenamiento Integral
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (11, 3, 1),   -- Aperturas con Mancuernas
       (11, 15, 1),  -- Remo con Mancuerna
       (11, 22, 1),  -- Curl Concentrado
       (11, 42, 1),  -- Prensa de Piernas
       (11, 36, 1);  -- Elevación de Piernas Colgado

-- Día 2: Cardio y Core
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (11, 9, 2),   -- Press con Máquina de Pecho
       (11, 16, 2),  -- Pulldown en Polea
       (11, 23, 2),  -- Curl de Bíceps con Mancuernas
       (11, 37, 2),  -- Crunch con Mancuerna
       (11, 45, 2);  -- Peso Muerto

-- Día 3: Flexibilidad y Equilibrio
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (11, 8, 3),   -- Flexiones Declinadas
       (11, 19, 3),  -- Peso Muerto
       (11, 24, 3),  -- Fondos en Paralelas
       (11, 38, 3),  -- Elevación de Tronco en Suelo
       (11, 49, 3);  -- Aductores en Máquina


-- Ejercicios de la Rutina 12

-- Día 1: Core y Estabilidad
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (12, 5, 1),   -- Pullover con Mancuerna
       (12, 10, 1),  -- Flyes en Máquina
       (12, 26, 1),  -- Extensión de Tríceps en Polea Alta
       (12, 33, 1),  -- Elevación de Piernas
       (12, 40, 1);  -- Rodillas al Pecho

-- Día 2: Cardio y Resistencia
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (12, 7, 2),   -- Cruce de Cables
       (12, 13, 2),  -- Polea al Pecho
       (12, 24, 2),  -- Fondos en Paralelas
       (12, 34, 2),  -- Giros Rusos
       (12, 46, 2);  -- Zancadas

-- Día 3: Fuerza y Flexibilidad
INSERT INTO `rutinas_ejercicios` (`id_rutina`, `id_ejercicio`, `dia`)
VALUES (12, 2, 3),   -- Fondos en Paralelas
       (12, 18, 3),  -- Remo en Máquina
       (12, 28, 3),  -- Elevaciones Laterales
       (12, 38, 3),  -- Elevación de Tronco en Suelo
       (12, 50, 3);  -- Elevación de Talones Sentado




-- DIETAS 


-- Dieta 1: Subida de Peso - Nutricionista 1 (Ana Sanchez Fernandez)
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (1, 'Aumento de calorias', 'Subida peso', 'Dieta para ganar peso de manera saludable', 1);


-- Dieta 2: Subida de Peso - Nutricionista 2 (Carlos Martinez Lopez)
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (2, 'Aumento de masa muscular', 'Subida peso', 'Dieta para incrementar masa muscular', 2);

-- Dieta 3: Subida de Peso - Nutricionista 3 (Elena Garcia Ruiz)
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (3, 'Aumento de calorias', 'Subida peso', 'Dieta para aumentar masa muscular de forma equilibrada', 3);


-- Dieta 4: Bajada de Peso
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (4, 'Bajada de calorias', 'Bajada peso', 'Dieta para perder peso de manera saludable', 1);


-- Dieta 5: Bajada de Peso
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (5, 'Bajada de grasas', 'Bajada peso', 'Dieta para reducir peso de forma equilibrada', 2);



-- Dieta 6: Bajada de Peso
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (6, 'Bajada de nutrientes', 'Bajada peso', 'Dieta para perder grasa de manera efectiva', 3);


-- Dieta 7: Recomposición Corporal
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (7, 'Recomposicion de musculatura', 'Recomposicion corporal', 'Dieta para mejorar la composición corporal', 1);


-- Dieta 8: Recomposición Corporal
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (8, 'Recomposicion de grasa', 'Recomposicion corporal', 'Dieta para optimizar la masa muscular y reducir grasa', 2);



-- Dieta 9: Recomposición Corporal
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (9, 'Recomposición de musculo y grasa', 'Recomposicion corporal', 'Dieta para ganar músculo y perder grasa simultáneamente', 3);


-- Dieta 10: Mantenimiento
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (10, 'Mantenimiento general', 'Mantenimiento', 'Dieta para mantener el peso corporal', 1);

-- Dieta 11: Mantenimiento
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (11, 'Mantenimiento equilibrada en nutrientes', 'Mantenimiento', 'Dieta equilibrada para el mantenimiento del peso', 2);

-- Dieta 12: Mantenimiento
INSERT INTO `dietas` (`id_dieta`, `nombre`, `tipo`, `descripcion`, `id_nutricionista`)
VALUES (12, 'Mantenimiento para la musculatura', 'Mantenimiento', 'Dieta para mantener el peso y la salud general', 3);



-- COMIDAS 


-- Comidas de la dieta 1

-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (1, 'Desayuno', 1, 1),
       (2, 'Almuerzo', 1, 1),
       (3, 'Merienda', 1, 1),
       (4, 'Cena', 1, 1);


-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (5, 'Desayuno', 2, 1),
       (6, 'Almuerzo', 2, 1),
       (7, 'Merienda', 2, 1),
       (8, 'Cena', 2, 1);


-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (9, 'Desayuno', 3, 1),
       (10, 'Almuerzo', 3, 1),
       (11, 'Merienda', 3, 1),
       (12, 'Cena', 3, 1);


-- Comidas de la dieta 2

-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (13, 'Desayuno', 1, 2),
       (14, 'Almuerzo', 1, 2),
       (15, 'Merienda', 1, 2),
       (16, 'Cena', 1, 2);

-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (17, 'Desayuno', 2, 2),
       (18, 'Almuerzo', 2, 2),
       (19, 'Merienda', 2, 2),
       (20, 'Cena', 2, 2);

-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (21, 'Desayuno', 3, 2),
       (22, 'Almuerzo', 3, 2),
       (23, 'Merienda', 3, 2),
       (24, 'Cena', 3, 2);


-- Comidas de la dieta 3

-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (25, 'Desayuno', 1, 3),
       (26, 'Almuerzo', 1, 3),
       (27, 'Merienda', 1, 3),
       (28, 'Cena', 1, 3);


-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (29, 'Desayuno', 2, 3),
       (30, 'Almuerzo', 2, 3),
       (31, 'Merienda', 2, 3),
       (32, 'Cena', 2, 3);


-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (33, 'Desayuno', 3, 3),
       (34, 'Almuerzo', 3, 3),
       (35, 'Merienda', 3, 3),
       (36, 'Cena', 3, 3);



-- Comidas de la dieta 4


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (37, 'Desayuno', 1, 4),
       (38, 'Almuerzo', 1, 4),
       (39, 'Merienda', 1, 4),
       (40, 'Cena', 1, 4);


-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (41, 'Desayuno', 2, 4),
       (42, 'Almuerzo', 2, 4),
       (43, 'Merienda', 2, 4),
       (44, 'Cena', 2, 4);

-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (45, 'Desayuno', 3, 4),
       (46, 'Almuerzo', 3, 4),
       (47, 'Merienda', 3, 4),
       (48, 'Cena', 3, 4);


-- Comidas de la dieta 5


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (49, 'Desayuno', 1, 5),
       (50, 'Almuerzo', 1, 5),
       (51, 'Merienda', 1, 5),
       (52, 'Cena', 1, 5);


-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (53, 'Desayuno', 2, 5),
       (54, 'Almuerzo', 2, 5),
       (55, 'Merienda', 2, 5),
       (56, 'Cena', 2, 5);


-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (57, 'Desayuno', 3, 5),
       (58, 'Almuerzo', 3, 5),
       (59, 'Merienda', 3, 5),
       (60, 'Cena', 3, 5);



-- Comidas de la dieta 6


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (61, 'Desayuno', 1, 6),
       (62, 'Almuerzo', 1, 6),
       (63, 'Merienda', 1, 6),
       (64, 'Cena', 1, 6);

-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (65, 'Desayuno', 2, 6),
       (66, 'Almuerzo', 2, 6),
       (67, 'Merienda', 2, 6),
       (68, 'Cena', 2, 6);

-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (69, 'Desayuno', 3, 6),
       (70, 'Almuerzo', 3, 6),
       (71, 'Merienda', 3, 6),
       (72, 'Cena', 3, 6);




-- Comidas de la dieta 7


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (73, 'Desayuno', 1, 7),
       (74, 'Almuerzo', 1, 7),
       (75, 'Merienda', 1, 7),
       (76, 'Cena', 1, 7);



-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (77, 'Desayuno', 2, 7),
       (78, 'Almuerzo', 2, 7),
       (79, 'Merienda', 2, 7),
       (80, 'Cena', 2, 7);


-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (81, 'Desayuno', 3, 7),
       (82, 'Almuerzo', 3, 7),
       (83, 'Merienda', 3, 7),
       (84, 'Cena', 3, 7);



-- Comidas de la dieta 8


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (85, 'Desayuno', 1, 8),
       (86, 'Almuerzo', 1, 8),
       (87, 'Merienda', 1, 8),
       (88, 'Cena', 1, 8);

-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (89, 'Desayuno', 2, 8),
       (90, 'Almuerzo', 2, 8),
       (91, 'Merienda', 2, 8),
       (92, 'Cena', 2, 8);


-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (93, 'Desayuno', 3, 8),
       (94, 'Almuerzo', 3, 8),
       (95, 'Merienda', 3, 8),
       (96, 'Cena', 3, 8);



-- Comidas de la dieta 9


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (97, 'Desayuno', 1, 9),
       (98, 'Almuerzo', 1, 9),
       (99, 'Merienda', 1, 9),
       (100, 'Cena', 1, 9);

-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (101, 'Desayuno', 2, 9),
       (102, 'Almuerzo', 2, 9),
       (103, 'Merienda', 2, 9),
       (104, 'Cena', 2, 9);


-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (105, 'Desayuno', 3, 9),
       (106, 'Almuerzo', 3, 9),
       (107, 'Merienda', 3, 9),
       (108, 'Cena', 3, 9);



-- Comidas de la dieta 10


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (109, 'Desayuno', 1, 10),
       (110, 'Almuerzo', 1, 10),
       (111, 'Merienda', 1, 10),
       (112, 'Cena', 1, 10);

-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (113, 'Desayuno', 2, 10),
       (114, 'Almuerzo', 2, 10),
       (115, 'Merienda', 2, 10),
       (116, 'Cena', 2, 10);


-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (117, 'Desayuno', 3, 10),
       (118, 'Almuerzo', 3, 10),
       (119, 'Merienda', 3, 10),
       (120, 'Cena', 3, 10);


-- Comidas de la dieta 11


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (121, 'Desayuno', 1, 11),
       (122, 'Almuerzo', 1, 11),
       (123, 'Merienda', 1, 11),
       (124, 'Cena', 1, 11);


-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (125, 'Desayuno', 2, 11),
       (126, 'Almuerzo', 2, 11),
       (127, 'Merienda', 2, 11),
       (128, 'Cena', 2, 11);



-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (129, 'Desayuno', 3, 11),
       (130, 'Almuerzo', 3, 11),
       (131, 'Merienda', 3, 11),
       (132, 'Cena', 3, 11);




-- Comidas de la dieta 12


-- Día 1
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (133, 'Desayuno', 1, 12),
       (134, 'Almuerzo', 1, 12),
       (135, 'Merienda', 1, 12),
       (136, 'Cena', 1, 12);


-- Día 2
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (137, 'Desayuno', 2, 12),
       (138, 'Almuerzo', 2, 12),
       (139, 'Merienda', 2, 12),
       (140, 'Cena', 2, 12);


-- Día 3
INSERT INTO `comidas` (`id_comida`, `tipo`, `dia`, `id_dieta`)
VALUES (141, 'Desayuno', 3, 12),
       (142, 'Almuerzo', 3, 12),
       (143, 'Merienda', 3, 12),
       (144, 'Cena', 3, 12);



-- COMIDAS_ALIMENTOS


-- Alimentos para cada comida del Día 1 de la Dieta 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 1),    -- Banana
       (21, 1),   -- Arroz
       (32, 1);   -- Huevo

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 2),   -- Tomate
       (25, 2),   -- Patatas
       (35, 2),   -- Carne
       (41, 2);   -- Queso

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (3, 3),    -- Naranja
       (27, 3),   -- Cereales
       (36, 3);   -- Tofu

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (14, 4),   -- Zanahoria
       (31, 4),   -- Pollo
       (52, 4);   -- Aceite de coco



-- Alimentos para cada comida del Día 2 de la Dieta 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (7, 5),    -- Kiwi
       (22, 5),   -- Avena
       (33, 5);   -- Atún

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (15, 6),   -- Espinaca
       (26, 6),   -- Maíz
       (37, 6);   -- Lentejas

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 7),    -- Manzana
       (46, 7);   -- Helado

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 8),   -- Lechuga
       (34, 8),   -- Salmón
       (61, 8);   -- Azúcar


-- Alimentos para cada comida del Día 3 de la Dieta 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (4, 9),    -- Uvas
       (23, 9),   -- Pasta
       (36, 9);   -- Tofu

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (13, 10),  -- Pepino
       (30, 10),  -- Pájaros
       (35, 10);  -- Carne

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (8, 11),   -- Piña
       (40, 11);  -- Almendras

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (18, 12),  -- Coliflor
       (33, 12),  -- Atún
       (51, 12);  -- Aceite de oliva


-- Alimentos para cada comida del Día 1 de la Dieta 2


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 13),    -- Banana
       (21, 13),   -- Arroz
       (32, 13);   -- Huevo

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 14),   -- Tomate
       (25, 14),   -- Patatas
       (35, 14),   -- Carne
       (41, 14);   -- Queso

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (3, 15),    -- Naranja
       (27, 15),   -- Cereales
       (36, 15);   -- Tofu

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (14, 16),   -- Zanahoria
       (31, 16),   -- Pollo
       (52, 16);   -- Aceite de coco




-- Alimentos para cada comida del Día 2 de la Dieta 2


-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (7, 17),    -- Kiwi
       (22, 17),   -- Avena
       (33, 17);   -- Atún

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (15, 18),   -- Espinaca
       (26, 18),   -- Maíz
       (37, 18);   -- Lentejas

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 19),    -- Manzana
       (46, 19);   -- Helado

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 20),   -- Lechuga
       (34, 20),   -- Salmón
       (61, 20);   -- Azúcar

-- Alimentos para cada comida del Día 3 de la Dieta 2


-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (4, 21),    -- Uvas
       (23, 21),   -- Pasta
       (36, 21);   -- Tofu

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (13, 22),   -- Pepino
       (30, 22),   -- Pájaros
       (35, 22);   -- Carne

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (8, 23),    -- Piña
       (40, 23);   -- Almendras

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (18, 24),   -- Coliflor
       (33, 24),   -- Atún
       (51, 24);   -- Aceite de oliva


-- Alimentos para cada comida del Día 1 de la Dieta 3


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 25),    -- Banana
       (21, 25),   -- Arroz
       (32, 25);   -- Huevo

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 26),   -- Tomate
       (25, 26),   -- Patatas
       (35, 26),   -- Carne
       (41, 26);   -- Queso

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (3, 27),    -- Naranja
       (27, 27),   -- Cereales
       (36, 27);   -- Tofu

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (14, 28),   -- Zanahoria
       (31, 28),   -- Pollo
       (52, 28);   -- Aceite de coco



-- Alimentos para cada comida del Día 2 de la Dieta 3


-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (7, 29),    -- Kiwi
       (22, 29),   -- Avena
       (33, 29);   -- Atún

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (15, 30),   -- Espinaca
       (26, 30),   -- Maíz
       (37, 30);   -- Lentejas

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 31),    -- Manzana
       (46, 31);   -- Helado

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 32),   -- Lechuga
       (34, 32),   -- Salmón
       (61, 32);   -- Azúcar



-- Alimentos para cada comida del Día 3 de la Dieta 3

-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (4, 33),    -- Uvas
       (23, 33),   -- Pasta
       (36, 33);   -- Tofu

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (13, 34),   -- Pepino
       (30, 34),   -- Pájaros
       (35, 34);   -- Carne

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (8, 35),    -- Piña
       (40, 35);   -- Almendras

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (18, 36),   -- Coliflor
       (33, 36),   -- Atún
       (51, 36);   -- Aceite de oliva


-- Alimentos para cada comida del Día 1 de la Dieta 4


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 37),   -- Tomate
       (14, 37),   -- Zanahoria
       (16, 37);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 38),   -- Lechuga
       (13, 38),   -- Pepino
       (15, 38);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 39),    -- Manzana
       (3, 39);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 40),    -- Banana
       (4, 40),    -- Uvas
       (7, 40);    -- Kiwi



-- Alimentos para cada comida del Día 2 de la Dieta 4


-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 41),   -- Tomate
       (13, 41),   -- Pepino
       (16, 41);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 42),   -- Lechuga
       (15, 42),   -- Espinaca
       (18, 42);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 43),    -- Manzana
       (3, 43);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 44),    -- Banana
       (4, 44),    -- Uvas
       (7, 44);    -- Kiwi


-- Alimentos para cada comida del Día 3 de la Dieta 4

-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 45),   -- Tomate
       (13, 45),   -- Pepino
       (16, 45);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 46),   -- Lechuga
       (15, 46),   -- Espinaca
       (18, 46);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 47),    -- Manzana
       (3, 47);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 48),    -- Banana
       (4, 48),    -- Uvas
       (7, 48);    -- Kiwi


-- Alimentos para cada comida del Día 1 de la Dieta 5

-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 49),   -- Tomate
       (14, 49),   -- Zanahoria
       (16, 49);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 50),   -- Lechuga
       (13, 50),   -- Pepino
       (15, 50);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 51),    -- Manzana
       (3, 51);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 52),    -- Banana
       (4, 52),    -- Uvas
       (7, 52);    -- Kiwi



-- Alimentos para cada comida del Día 2 de la Dieta 5


-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 53),   -- Tomate
       (13, 53),   -- Pepino
       (16, 53);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 54),   -- Lechuga
       (15, 54),   -- Espinaca
       (18, 54);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 55),    -- Manzana
       (3, 55);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 56),    -- Banana
       (4, 56),    -- Uvas
       (7, 56);    -- Kiwi



-- Alimentos para cada comida del Día 3 de la Dieta 5


-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 57),   -- Tomate
       (13, 57),   -- Pepino
       (16, 57);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 58),   -- Lechuga
       (15, 58),   -- Espinaca
       (18, 58);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 59),    -- Manzana
       (3, 59);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 60),    -- Banana
       (4, 60),    -- Uvas
       (7, 60);    -- Kiwi



-- Alimentos para cada comida del Día 1 de la Dieta 6


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 61),   -- Tomate
       (14, 61),   -- Zanahoria
       (16, 61);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 62),   -- Lechuga
       (13, 62),   -- Pepino
       (15, 62);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 63),    -- Manzana
       (3, 63);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 64),    -- Banana
       (4, 64),    -- Uvas
       (7, 64);    -- Kiwi



-- Alimentos para cada comida del Día 2 de la Dieta 6



-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 65),   -- Tomate
       (13, 65),   -- Pepino
       (16, 65);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 66),   -- Lechuga
       (15, 66),   -- Espinaca
       (18, 66);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 67),    -- Manzana
       (3, 67);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 68),    -- Banana
       (4, 68),    -- Uvas
       (7, 68);    -- Kiwi




-- Alimentos para cada comida del Día 3 de la Dieta 6


-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 69),   -- Tomate
       (13, 69),   -- Pepino
       (16, 69);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 70),   -- Lechuga
       (15, 70),   -- Espinaca
       (18, 70);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 71),    -- Manzana
       (3, 71);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 72),    -- Banana
       (4, 72),    -- Uvas
       (7, 72);    -- Kiwi



-- Alimentos para cada comida del Día 1 de la Dieta 7


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 73),   -- Tomate
       (14, 73),   -- Zanahoria
       (16, 73);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 74),   -- Lechuga
       (13, 74),   -- Pepino
       (15, 74);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 75),    -- Manzana
       (3, 75);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 76),    -- Banana
       (4, 76),    -- Uvas
       (7, 76);    -- Kiwi



-- Alimentos para cada comida del Día 2 de la Dieta 7


-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 77),   -- Tomate
       (13, 77),   -- Pepino
       (16, 77);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 78),   -- Lechuga
       (15, 78),   -- Espinaca
       (18, 78);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 79),    -- Manzana
       (3, 79);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 80),    -- Banana
       (4, 80),    -- Uvas
       (7, 80);    -- Kiwi



-- Alimentos para cada comida del Día 3 de la Dieta 7


-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 81),   -- Tomate
       (13, 81),   -- Pepino
       (16, 81);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 82),   -- Lechuga
       (15, 82),   -- Espinaca
       (18, 82);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 83),    -- Manzana
       (3, 83);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 84),    -- Banana
       (4, 84),    -- Uvas
       (7, 84);    -- Kiwi


-- Alimentos para cada comida del Día 1 de la Dieta 8

-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 85),   -- Tomate
       (14, 85),   -- Zanahoria
       (16, 85);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 86),   -- Lechuga
       (13, 86),   -- Pepino
       (15, 86);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 87),    -- Manzana
       (3, 87);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 88),    -- Banana
       (4, 88),    -- Uvas
       (7, 88);    -- Kiwi



-- Alimentos para cada comida del Día 2 de la Dieta 8

-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 89),   -- Tomate
       (13, 89),   -- Pepino
       (16, 89);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 90),   -- Lechuga
       (15, 90),   -- Espinaca
       (18, 90);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 91),    -- Manzana
       (3, 91);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 92),    -- Banana
       (4, 92),    -- Uvas
       (7, 92);    -- Kiwi



-- Alimentos para cada comida del Día 3 de la Dieta 8

-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 93),   -- Tomate
       (13, 93),   -- Pepino
       (16, 93);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 94),   -- Lechuga
       (15, 94),   -- Espinaca
       (18, 94);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 95),    -- Manzana
       (3, 95);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 96),    -- Banana
       (4, 96),    -- Uvas
       (7, 96);    -- Kiwi


-- Alimentos para cada comida del Día 1 de la Dieta 9


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 97),   -- Tomate
       (14, 97),   -- Zanahoria
       (16, 97);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 98),   -- Lechuga
       (13, 98),   -- Pepino
       (15, 98);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 99),    -- Manzana
       (3, 99);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 100),    -- Banana
       (4, 100),    -- Uvas
       (7, 100);    -- Kiwi



-- Alimentos para cada comida del Día 2 de la Dieta 9


-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 101),   -- Tomate
       (13, 101),   -- Pepino
       (16, 101);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 102),   -- Lechuga
       (15, 102),   -- Espinaca
       (18, 102);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 103),    -- Manzana
       (3, 103);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 104),    -- Banana
       (4, 104),    -- Uvas
       (7, 104);    -- Kiwi



-- Alimentos para cada comida del Día 3 de la Dieta 9


-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 105),   -- Tomate
       (13, 105),   -- Pepino
       (16, 105);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 106),   -- Lechuga
       (15, 106),   -- Espinaca
       (18, 106);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 107),    -- Manzana
       (3, 107);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 108),    -- Banana
       (4, 108),    -- Uvas
       (7, 108);    -- Kiwi



-- Alimentos para cada comida del Día 1 de la Dieta 10


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 109),   -- Tomate
       (14, 109),   -- Zanahoria
       (16, 109);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 110),   -- Lechuga
       (13, 110),   -- Pepino
       (15, 110);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 111),    -- Manzana
       (3, 111);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 112),    -- Banana
       (4, 112),    -- Uvas
       (7, 112);    -- Kiwi





-- Alimentos para cada comida del Día 2 de la Dieta 10


-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 113),   -- Tomate
       (13, 113),   -- Pepino
       (16, 113);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 114),   -- Lechuga
       (15, 114),   -- Espinaca
       (18, 114);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 115),    -- Manzana
       (3, 115);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 116),    -- Banana
       (4, 116),    -- Uvas
       (7, 116);    -- Kiwi



-- Alimentos para cada comida del Día 3 de la Dieta 10


-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 117),   -- Tomate
       (13, 117),   -- Pepino
       (16, 117);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 118),   -- Lechuga
       (15, 118),   -- Espinaca
       (18, 118);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 119),    -- Manzana
       (3, 119);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 120),    -- Banana
       (4, 120),    -- Uvas
       (7, 120);    -- Kiwi


-- Alimentos para cada comida del Día 1 de la Dieta 11


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 121),   -- Tomate
       (14, 121),   -- Zanahoria
       (16, 121);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 122),   -- Lechuga
       (13, 122),   -- Pepino
       (15, 122);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 123),    -- Manzana
       (3, 123);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 124),    -- Banana
       (4, 124),    -- Uvas
       (7, 124);    -- Kiwi



-- Alimentos para cada comida del Día 2 de la Dieta 11


-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 125),   -- Tomate
       (13, 125),   -- Pepino
       (16, 125);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 126),   -- Lechuga
       (15, 126),   -- Espinaca
       (18, 126);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 127),    -- Manzana
       (3, 127);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 128),    -- Banana
       (4, 128),    -- Uvas
       (7, 128);    -- Kiwi



-- Alimentos para cada comida del Día 3 de la Dieta 11


-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 129),   -- Tomate
       (13, 129),   -- Pepino
       (16, 129);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 130),   -- Lechuga
       (15, 130),   -- Espinaca
       (18, 130);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 131),    -- Manzana
       (3, 131);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 132),    -- Banana
       (4, 132),    -- Uvas
       (7, 132);    -- Kiwi


-- Alimentos para cada comida del Día 1 de la Dieta 12


-- Alimentos para cada comida del Día 1

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 133),   -- Tomate
       (14, 133),   -- Zanahoria
       (16, 133);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 134),   -- Lechuga
       (13, 134),   -- Pepino
       (15, 134);   -- Espinaca

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 135),    -- Manzana
       (3, 135);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 136),    -- Banana
       (4, 136),    -- Uvas
       (7, 136);    -- Kiwi


-- Alimentos para cada comida del Día 2 de la Dieta 12


-- Alimentos para cada comida del Día 2

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 137),   -- Tomate
       (13, 137),   -- Pepino
       (16, 137);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 138),   -- Lechuga
       (15, 138),   -- Espinaca
       (18, 138);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 139),    -- Manzana
       (3, 139);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 140),    -- Banana
       (4, 140),    -- Uvas
       (7, 140);    -- Kiwi


-- Alimentos para cada comida del Día 3 de la Dieta 12


-- Alimentos para cada comida del Día 3

-- Desayuno
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (12, 141),   -- Tomate
       (13, 141),   -- Pepino
       (16, 141);   -- Brócoli

-- Almuerzo
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (11, 142),   -- Lechuga
       (15, 142),   -- Espinaca
       (18, 142);   -- Coliflor

-- Merienda
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (1, 143),    -- Manzana
       (3, 143);    -- Naranja

-- Cena
INSERT INTO `alimentos_comidas` (`id_alimento`, `id_comida`)
VALUES (2, 144),    -- Banana
       (4, 144),    -- Uvas
       (7, 144);    -- Kiwi


-- PRODUCTOS 


-- Insertar productos para Monitor 1 (Pablo Fernandez Gomez) con id_monitor = 4
INSERT INTO `productos` (`nombre`, `precio_unidad`, `cantidad_disponible`, `id_monitor`)
VALUES ('Proteína Whey', 30.50, 100, 4),
       ('Creatina Monohidratada', 25.00, 150, 4),
       ('Aminoácidos BCAA', 20.75, 80, 4);

-- Insertar productos para Monitor 2 (Isabel Lopez Martinez) con id_monitor = 5
INSERT INTO `productos` (`nombre`, `precio_unidad`, `cantidad_disponible`, `id_monitor`)
VALUES ('Batido de Proteínas', 35.00, 120, 5),
       ('Glutamina', 18.40, 60, 5),
       ('Multivitamínico', 12.50, 200, 5);

-- Insertar productos para Monitor 3 (Pedro Garcia Fernandez) con id_monitor = 6
INSERT INTO `productos` (`nombre`, `precio_unidad`, `cantidad_disponible`, `id_monitor`)
VALUES ('Pre-entrenamiento', 40.00, 90, 6),
       ('Omega 3', 15.00, 130, 6),
       ('Quemador de Grasa', 28.90, 70, 6),
       ('Electrolitos', 10.00, 150, 6);


-- SERVICIOS


-- Insertar servicios para Monitor 3 (Pedro Garcia Fernandez) con id_monitor = 6
INSERT INTO `servicios` (`nombre`, `precio`, `dias_duracion`, `id_monitor`)
VALUES ('Gimnasio', 30.00, 30, 6),
       ('Entrenamiento Monitoreado', 55.00, 25, 6),
       ('Consulta Dieta', 50.00, 20, 6),
       ('Consulta Rutina', 45.00, 30, 6);

-- SALAS

-- Insertar salas sin monitores asignados
INSERT INTO `salas` (`nombre`, `capacidad_personas`, `id_monitor`)
VALUES ('Sala superior', 20, NULL),
       ('Sala media', 30, NULL),
       ('Sala inferior', 25, NULL);

-- Insertar salas con Monitor 1 (Laura Diaz Rodriguez) con id_monitor = 1
INSERT INTO `salas` (`nombre`, `capacidad_personas`, `id_monitor`)
VALUES ('Sala Gimnasio', 50, 1);

-- Insertar salas con Monitor 2 (Antonio Gomez Perez) con id_monitor = 2
INSERT INTO `salas` (`nombre`, `capacidad_personas`, `id_monitor`)
VALUES ('Sala Cardio', 40, 2);

-- Insertar salas con Monitor 3 (Javier Gonzalez Fernandez) con id_monitor = 3
INSERT INTO `salas` (`nombre`, `capacidad_personas`, `id_monitor`)
VALUES ('Sala Crossfit', 35, 3);