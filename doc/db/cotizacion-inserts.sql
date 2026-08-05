-- ==========================================
-- 1. INSERTAR CATÁLOGOS Y TABLAS MAESTRAS
-- ==========================================

-- Estados de la cotización
INSERT INTO ESTADOS_COTIZACION (id_estado, descripcion, estado, fec_inicio, fec_fin) VALUES
(1, 'Borrador', 'ACTIVO', '2024-01-01', NULL),
(2, 'Emitida', 'ACTIVO', '2024-01-01', NULL),
(3, 'Rechazada', 'ACTIVO', '2024-01-01', NULL);

-- Marcas de vehículos
INSERT INTO MARCAS_VEHICULO (nombre_marca, pais_origen, abreviatura) VALUES
('Toyota', 'Japón', 'TOY'),
('Chevrolet', 'Estados Unidos', 'CHEV'),
('Mazda', 'Japón', 'MAZ'),
('Renault', 'Francia', 'REN');

-- Tomadores (Clientes)
INSERT INTO TOMADORES (cc_tomador, nombre_tomador, email, telefono, fec_nacimiento, ocupacion, direccion, genero, tip_persona) VALUES
('1088123456', 'Juan Carlos Pérez', 'juan.perez@email.com', '3101234567', '1985-06-15', 'Ingeniero', 'Calle 10 # 4-50', 'M', 'NATURAL'),
('42123456', 'María Fernanda Gómez', 'maria.gomez@email.com', '3009876543', '1990-11-20', 'Comerciante', 'Cra 5 # 12-30', 'F', 'NATURAL'),
('900123456', 'Transportes del Sur SAS', 'contacto@transur.com', '6012345678', '2010-02-10', 'Empresa Transporte', 'Av. Siempre Viva 123', 'N/A', 'JURIDICA');

-- Coberturas
INSERT INTO COBERTURAS (id_cobertura, nombre_cobertura, tasa_publico, tasa_particular) VALUES
('COB-001', 'Responsabilidad Civil Extracontractual', 5.5000, 2.5000),
('COB-002', 'Pérdida Total por Daños', 8.0000, 4.0000),
('COB-003', 'Asistencia en Viaje (Grúa)', 1.2000, 0.5000),
('COB-004', 'Vehículo de Reemplazo', 0.0000, 1.5000); -- Un servicio que a veces no aplica a públicos

-- Deducibles
INSERT INTO DEDUCIBLES (id_deducible, porcentaje, monto_minimo) VALUES
(1, 10.00, 1300000.00), -- 10% de la pérdida, mínimo ~1 SMMLV
(2, 0.00, 0.00),        -- Sin deducible
(3, 15.00, 2000000.00);

-- ==========================================
-- 2. INSERTAR TABLA PRINCIPAL (Cabecera)
-- ==========================================

-- Cotizaciones
INSERT INTO DATOS_DEL_RIESGO (id_cotizacion, placa, cc_tomador, id_estado, fecha_cotizacion, modelo, tipo_servicio) VALUES
('COT-2026-0001', 'ABC123', '1088123456', 2, '2026-07-20', 2024, 'PARTICULAR'), -- Emitida
('COT-2026-0002', 'XYZ987', '900123456', 1, '2026-07-22', 2020, 'PUBLICO'),    -- En Borrador
('COT-2026-0003', 'QWE456', '42123456', 3, '2026-07-15', 2015, 'PARTICULAR');  -- Rechazada

-- ==========================================
-- 3. INSERTAR DETALLES Y TRANSACCIONES
-- ==========================================

-- Coberturas aplicadas a cada cotización
-- Nota: La prima real se calcularía multiplicando el valor del vehículo por la tasa de la tabla COBERTURAS. Aquí ponemos valores de ejemplo.
INSERT INTO COBERTURAS_DEL_RIESGO (id_cotizacion, id_cobertura, id_deducible, prima_cobertura) VALUES
-- Cotización 1: Vehículo Particular (Tiene Responsabilidad, Pérdida total y Grúa)
('COT-2026-0001', 'COB-001', 1, 850000.00),
('COT-2026-0001', 'COB-002', 1, 1200000.00),
('COT-2026-0001', 'COB-003', 2, 50000.00), 

-- Cotización 2: Vehículo Público (Tiene Responsabilidad y Pérdida Total, pero deducibles más altos)
('COT-2026-0002', 'COB-001', 3, 1500000.00),
('COT-2026-0002', 'COB-002', 3, 2100000.00),

-- Cotización 3: Vehículo Particular Rechazado (Solo llegó a cotizar Responsabilidad Civil)
('COT-2026-0003', 'COB-001', 1, 900000.00);

-- Impuestos aplicados a la cotización
INSERT INTO IMPUESTOS_COTIZACION (id_cotizacion, concepto, valor) VALUES
-- Impuestos Cotización 1
('COT-2026-0001', 'IVA (19%)', 399000.00),
('COT-2026-0001', 'Gastos de Expedición RUNT', 25000.00),
-- Impuestos Cotización 2
('COT-2026-0002', 'IVA (19%)', 684000.00),
('COT-2026-0002', 'Gastos de Expedición RUNT', 25000.00);

-- Inspecciones de los vehículos
INSERT INTO INSPECCIONES (id_cotizacion, fecha_programada, estado_inspeccion) VALUES
('COT-2026-0001', '2026-07-18', 'APROBADA'), -- Como ya está emitida, la inspección pasó.
('COT-2026-0002', '2026-07-24', 'PENDIENTE'), -- El borrador está esperando que le hagan peritaje.
('COT-2026-0003', '2026-07-16', 'RECHAZADA'); -- Vehículo muy antiguo o con daños previos.

INSERT INTO users (first_name, last_name, email, username, password)
VALUES
('Santiago', 'Gomez', 'santiago@gmail.com', 'saantt', '123456'),

('Juan', 'Perez', 'juan.perez@gmail.com', 'jperez', '123456'),

('Maria', 'Lopez', 'maria.lopez@gmail.com', 'mlopez', '123456'),

('Carlos', 'Rodriguez', 'carlos.rodriguez@gmail.com', 'crodriguez', '123456'),

('Ana', 'Martinez', 'ana.martinez@gmail.com', 'amartinez', '123456');