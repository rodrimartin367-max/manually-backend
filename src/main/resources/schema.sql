-- 1. Tabla Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    correo VARCHAR(255) UNIQUE NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    rol VARCHAR(50) DEFAULT 'USER',
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- 2. Tabla Categorias
CREATE TABLE IF NOT EXISTS categorias (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

-- 3. Tabla Productos
CREATE TABLE IF NOT EXISTS productos (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    categoria_id BIGINT REFERENCES categorias(id) ON DELETE CASCADE,
    destacado BOOLEAN DEFAULT FALSE
);

-- 4. Tabla Comentarios (Foro)
CREATE TABLE IF NOT EXISTS comentarios (
    id BIGSERIAL PRIMARY KEY,
    texto TEXT NOT NULL,
    fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT REFERENCES usuarios(id) ON DELETE CASCADE,
    producto_id BIGINT REFERENCES productos(id) ON DELETE CASCADE
);

-- 5. Tabla Videos
CREATE TABLE IF NOT EXISTS videos (
    id BIGSERIAL PRIMARY KEY,
    titulo VARCHAR(255) NOT NULL,
    url VARCHAR(500) NOT NULL,
    fecha_publicacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    usuario_id BIGINT REFERENCES usuarios(id) ON DELETE CASCADE,
    producto_id BIGINT REFERENCES productos(id) ON DELETE CASCADE
);

-- 6. Tabla Piezas
CREATE TABLE IF NOT EXISTS piezas (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    precio DECIMAL(10,2) NOT NULL,
    url_compra VARCHAR(500) NOT NULL,
    producto_id BIGINT REFERENCES productos(id) ON DELETE CASCADE
);

-- --- DATOS DE PRUEBA ---
-- Usamos ON CONFLICT (id) DO NOTHING para que no se dupliquen cada vez que reinicias el servidor

INSERT INTO categorias (id, nombre) VALUES 
(1, 'Móviles'), 
(2, 'Portátiles'), 
(3, 'Audio') 
ON CONFLICT (id) DO NOTHING;

INSERT INTO productos (id, nombre, categoria_id, destacado) VALUES 
(1, 'iPhone 17', 1, true),
(2, 'Samsung S25', 1, true),
(3, 'MacBook Pro 16"', 2, false)
ON CONFLICT (id) DO NOTHING;