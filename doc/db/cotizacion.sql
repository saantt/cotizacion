-- ==========================================
-- 1. CREACIÓN DE CATÁLOGOS Y TABLAS MAESTRAS
-- ==========================================

CREATE TABLE ESTADOS_COTIZACION (
    id_estado SERIAL PRIMARY KEY, -- SERIAL es el autoincremental en PostgreSQL
    descripcion VARCHAR(100) NOT NULL,
    estado VARCHAR(20) NOT NULL, -- Ej: 'ACTIVO', 'INACTIVO'
    fec_inicio DATE,
    fec_fin DATE
);

CREATE TABLE MARCAS_VEHICULO (
    id_marca SERIAL PRIMARY KEY,
    nombre_marca VARCHAR(50) NOT NULL,
    pais_origen VARCHAR(50),
    abreviatura VARCHAR(10)
);

CREATE TABLE TOMADORES (
    cc_tomador VARCHAR(20) PRIMARY KEY,
    nombre_tomador VARCHAR(150) NOT NULL,
    email VARCHAR(100),
    telefono VARCHAR(20),
    fec_nacimiento DATE,
    ocupacion VARCHAR(100),
    direccion VARCHAR(150),
    genero VARCHAR(20),
    tip_persona VARCHAR(20) -- Ej: 'NATURAL', 'JURIDICA'
);

CREATE TABLE COBERTURAS (
    id_cobertura VARCHAR(20) PRIMARY KEY,
    nombre_cobertura VARCHAR(100) NOT NULL,
    tasa_publico NUMERIC(10, 4) NOT NULL,      -- PostgreSQL usa NUMERIC en lugar de DECIMAL
    tasa_particular NUMERIC(10, 4) NOT NULL
);

CREATE TABLE DEDUCIBLES (
    id_deducible SERIAL PRIMARY KEY,
    porcentaje NUMERIC(5, 2) NOT NULL, 
    monto_minimo NUMERIC(12, 2) NOT NULL 
);

-- ==========================================
-- 2. TABLA PRINCIPAL DE LA COTIZACIÓN (Cabecera)
-- ==========================================

CREATE TABLE DATOS_DEL_RIESGO (
    id_cotizacion VARCHAR(50) PRIMARY KEY,
    placa VARCHAR(10) NOT NULL, 
    cc_tomador VARCHAR(20) NOT NULL,
    id_estado INT NOT NULL,
    id_marca INT, -- Columna integrada directamente aquí
    fecha_cotizacion DATE NOT NULL DEFAULT CURRENT_DATE,
    modelo INT NOT NULL,
    tipo_servicio VARCHAR(20) CHECK (tipo_servicio IN ('PUBLICO', 'PARTICULAR')),
    
    -- Llaves Foráneas
    CONSTRAINT fk_datos_tomador FOREIGN KEY (cc_tomador) REFERENCES TOMADORES(cc_tomador) ON DELETE RESTRICT,
    CONSTRAINT fk_datos_estado FOREIGN KEY (id_estado) REFERENCES ESTADOS_COTIZACION(id_estado) ON DELETE RESTRICT,
    CONSTRAINT fk_datos_marca FOREIGN KEY (id_marca) REFERENCES MARCAS_VEHICULO(id_marca) ON DELETE RESTRICT -- FK integrada
);

-- ==========================================
-- 3. TABLAS DE DETALLES (Dependientes de la cotización)
-- ==========================================

CREATE TABLE COBERTURAS_DEL_RIESGO (
    id_cotizacion VARCHAR(50),
    id_cobertura VARCHAR(20),
    id_deducible INT,
    prima_cobertura NUMERIC(12, 2) NOT NULL,
    
    -- Llave primaria compuesta
    PRIMARY KEY (id_cotizacion, id_cobertura),
    
    -- Llaves Foráneas
    CONSTRAINT fk_cobriesgo_cotizacion FOREIGN KEY (id_cotizacion) REFERENCES DATOS_DEL_RIESGO(id_cotizacion) ON DELETE CASCADE,
    CONSTRAINT fk_cobriesgo_cobertura FOREIGN KEY (id_cobertura) REFERENCES COBERTURAS(id_cobertura) ON DELETE RESTRICT,
    CONSTRAINT fk_cobriesgo_deducible FOREIGN KEY (id_deducible) REFERENCES DEDUCIBLES(id_deducible) ON DELETE SET NULL
);

CREATE TABLE IMPUESTOS_COTIZACION (
    id_impuesto_cot SERIAL PRIMARY KEY,
    id_cotizacion VARCHAR(50) NOT NULL,
    concepto VARCHAR(50) NOT NULL,
    valor NUMERIC(12, 2) NOT NULL,
    
    -- Llave Foránea
    CONSTRAINT fk_impuestos_cotizacion FOREIGN KEY (id_cotizacion) REFERENCES DATOS_DEL_RIESGO(id_cotizacion) ON DELETE CASCADE
);

CREATE TABLE INSPECCIONES (
    id_inspeccion SERIAL PRIMARY KEY,
    id_cotizacion VARCHAR(50) NOT NULL,
    fecha_programada DATE NOT NULL,
    estado_inspeccion VARCHAR(30) DEFAULT 'PENDIENTE',
    
    -- Llave Foránea
    CONSTRAINT fk_inspecciones_cotizacion FOREIGN KEY (id_cotizacion) REFERENCES DATOS_DEL_RIESGO(id_cotizacion) ON DELETE CASCADE
);


CREATE TABLE USERS (
    id_user int NOT NULL PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);