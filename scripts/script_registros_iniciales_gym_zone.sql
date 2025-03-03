-- Insertar tipos de grupos musculares
INSERT INTO grupos_musculares (nombre, tipo_tren) VALUES 
    ('Pecho', 'Superior'),
    ('Espalda', 'Superior'),
    ('Brazo', 'Superior'),
    ('Abdominales', 'Superior'),
    ('Pierna', 'Inferior');

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


INSERT INTO `usuarios` (`dni`, `tipo`, `nombre`, `apellidos`, `genero`, `correo`, `nombre_usuario`, `contrasenna`)
VALUES ('44455566G', 'Administrador', 'Pablo', 'Fernandez Gomez', 'Masculino', 'pablofer45@gmail.com', 'pablof12', 'Pablo123');

INSERT INTO `monitores` (`dni`, `funcion`)
VALUES ('44455566G', 'Administracion');

INSERT INTO `cuentas_bancarias` (`nombre_titular`, `numero_cuenta`, `banco`, `saldo`, `dni`)
VALUES ('Pablo Fernandez Gomez', 'ES7620770024003102575766', 'BBVA', 1000.50, '44455566G');

INSERT INTO `servicios` (`nombre`, `precio`, `dias_duracion`, `id_monitor`)
VALUES ('Gimnasio', 30.00, 30, 1),
       ('Entrenamiento Monitoreado', 55.00, 25, 1),
       ('Consulta Dieta', 50.00, 20, 1),
       ('Consulta Rutina', 45.00, 30, 1);
