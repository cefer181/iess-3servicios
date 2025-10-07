-- =====================
-- Esquema mínimo IESS
-- =====================

-- Tabla de clientes
CREATE TABLE IF NOT EXISTS clientes (
  id SERIAL PRIMARY KEY,
  identificacion VARCHAR(50) UNIQUE NOT NULL,
  nombre VARCHAR(120) NOT NULL,
  contrasena VARCHAR(120) NOT NULL,
  estado BOOLEAN NOT NULL DEFAULT TRUE
);

-- Tabla de cuentas (cada cuenta pertenece a un cliente)
CREATE TABLE IF NOT EXISTS cuentas (
  id SERIAL PRIMARY KEY,
  numero VARCHAR(30) UNIQUE NOT NULL,
  tipo VARCHAR(20) NOT NULL,                 -- AHORRO | CORRIENTE
  saldo NUMERIC(18,2) NOT NULL DEFAULT 0,
  estado BOOLEAN NOT NULL DEFAULT TRUE,
  cliente_id INTEGER NOT NULL REFERENCES clientes(id)
);

-- Tabla de movimientos (crédito/débito)
CREATE TABLE IF NOT EXISTS movimientos (
  id SERIAL PRIMARY KEY,
  cuenta_id INTEGER NOT NULL REFERENCES cuentas(id),
  tipo VARCHAR(20) NOT NULL,                 -- CREDITO | DEBITO
  valor NUMERIC(18,2) NOT NULL,
  fecha TIMESTAMP NOT NULL DEFAULT NOW()
);

-- Datos de ejemplo para probar rápido
INSERT INTO clientes (identificacion, nombre, contrasena, estado)
VALUES ('ID-AP-001','Ana Pérez','1234',true)
ON CONFLICT (identificacion) DO NOTHING;

INSERT INTO cuentas (numero, tipo, saldo, estado, cliente_id)
SELECT '777001','AHORRO',500,true,id FROM clientes WHERE identificacion='ID-AP-001'
ON CONFLICT (numero) DO NOTHING;