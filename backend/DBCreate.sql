-- Crear la base de datos (si aún no existe)
CREATE DATABASE control2_grupo3
    WITH OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Spanish_Chile.1252'
    LC_CTYPE = 'Spanish_Chile.1252'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1
    IS_TEMPLATE = False;

-- Conectarse a la base
\connect control2_grupo3;

-- Activar la extensión PostGIS
CREATE EXTENSION IF NOT EXISTS postgis;

-- Tabla: Usuario (con ubicación geográfica)
CREATE TABLE Usuario (
    ID SERIAL PRIMARY KEY,
    username VARCHAR(25) NOT NULL,
    contrasena VARCHAR(255) NOT NULL,
    correo VARCHAR(255) UNIQUE NOT NULL,
    ubicacion GEOGRAPHY(Point, 4326) -- Punto geográfico
);

-- Tabla: Sector (zona temática/geográfica asociada a tareas)
CREATE TABLE Sector (
    ID SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    ubicacion GEOGRAPHY(Point, 4326) -- Centro del sector
);

-- Tabla: Tarea (con relación a usuario y sector)
CREATE TABLE Tarea (
    ID SERIAL PRIMARY KEY, 
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT NOT NULL CHECK (LENGTH(descripcion) <= 500),
    fechaTermino DATE NOT NULL, 
    idUser INT NOT NULL REFERENCES Usuario(ID), 
    idSector INT REFERENCES Sector(ID),
    status BOOLEAN NOT NULL DEFAULT FALSE
);

-- Tabla: Notificaciones asociadas a tareas
CREATE TABLE Notificaciones (
    ID SERIAL PRIMARY KEY,
    idUser INT NOT NULL REFERENCES Usuario(ID),
    idTarea INT NOT NULL REFERENCES Tarea(ID),
    mensaje TEXT NOT NULL,
    leida BOOLEAN NOT NULL
);