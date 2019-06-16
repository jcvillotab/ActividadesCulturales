CREATE DATABASE IF NOT EXISTS actividadesculturalesdb;
USE actividadesculturalesdb;

CREATE TABLE IF NOT EXISTS `lugarT` (
    `id_lugar` INT(8) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria',
    `nombre_lugar` VARCHAR(64) NOT NULL COMMENT 'nombre sitio',
    `cubierta_lugar` VARCHAR(128) NOT NULL COMMENT 'nombre cubierta',
    `capacidad_lugar` INT(8) NOT NULL COMMENT 'capacidad del lugar',
    `seccion_lugar` VARCHAR(64) DEFAULT NULL,
    CONSTRAINT `pk_lugar` PRIMARY KEY (`id_lugar`)
);
  
CREATE TABLE IF NOT EXISTS `artistaT` (
    `id_artista` INT(8) NOT NULL COMMENT 'Clave primaria',
    `nombre_artista` VARCHAR(64) NOT NULL COMMENT 'nombre artista',
    `ocupacion_artista` VARCHAR(128) NOT NULL COMMENT 'ocupacion artista',
    CONSTRAINT `pk_artista` PRIMARY KEY (`id_artista`)
);
  
CREATE TABLE IF NOT EXISTS `eventoT` (
    `id_evento` INT(8) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria',
    `nombre_evento` VARCHAR(64) NOT NULL COMMENT 'nombre evento',
    `fecha_evento` DATE NOT NULL COMMENT 'fecha evento',
    `fk_id_admin` INT(8) NOT NULL COMMENT 'fk admin',
    CONSTRAINT `pk_evento` PRIMARY KEY (`id_evento`)
);
  
CREATE TABLE IF NOT EXISTS `clienteT` (
    `id_cliente` INT(8) NOT NULL COMMENT 'Clave primaria',
    `nombre_cliente` VARCHAR(64) NOT NULL COMMENT 'nombre cliente',
    `codigo_cliente` DATE NOT NULL COMMENT 'codigo cliente',
    CONSTRAINT `pk_cliente` PRIMARY KEY (`id_cliente`)
);
  
CREATE TABLE IF NOT EXISTS `adminT` (
    `id_admin` INT(8) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria',
    `nombre_admin` VARCHAR(64) NOT NULL COMMENT 'nombre admin',
    `contrasenia_admin` VARCHAR(64) NOT NULL COMMENT 'contrasenia admin',
    CONSTRAINT `pk_admin` PRIMARY KEY (`id_admin`)
);

CREATE TABLE IF NOT EXISTS `eventoxlugarT` (
    `id_fk_id_evento` INT(8) NOT NULL COMMENT 'Clave primaria de evento',
    `id_fk_id_lugar` INT(8) NOT NULL COMMENT 'Clave primaria de lugar',
    `capacidad_ocupada_exl` INT(8) NOT NULL COMMENT 'capacidad ocupada',
    CONSTRAINT `pk_eventoxlugar` PRIMARY KEY (`id_fk_id_evento` , `id_fk_id_lugar`)
);

CREATE TABLE IF NOT EXISTS `eventoxartistaT` (
    `id_fk_id_evento` INT(8) NOT NULL COMMENT 'Clave primaria de evento',
    `id_fk_id_artista` INT(8) NOT NULL COMMENT 'Clave primaria de artista',
    CONSTRAINT `pk_eventoxartista` PRIMARY KEY (`id_fk_id_evento` , `id_fk_id_artista`)
);

CREATE TABLE IF NOT EXISTS `eventoxclienteT` (
    `id_fk_id_evento` INT(8) NOT NULL COMMENT 'Clave primaria de evento',
    `id_fk_id_cliente` INT(8) NOT NULL COMMENT 'Clave primaria de cliente',
    CONSTRAINT `pk_eventoxcliente` PRIMARY KEY (`id_fk_id_evento` , `id_fk_id_cliente`)
);

ALTER TABLE `eventoT` ADD CONSTRAINT `fk_admin_evento` FOREIGN KEY (`fk_id_admin`) references `adminT`(`id_admin`);
ALTER TABLE `eventoxlugarT` ADD CONSTRAINT `fk_id_eventoxlugar` FOREIGN KEY (`id_fk_id_evento`) references `eventoT`(`id_evento`);
ALTER TABLE `eventoxlugarT` ADD CONSTRAINT `fk_id_lugarxevento` FOREIGN KEY (`id_fk_id_lugar`) references `lugarT`(`id_lugar`);
ALTER TABLE `eventoxartistaT` ADD CONSTRAINT `fk_eventoxartista` FOREIGN KEY (`id_fk_id_evento`) references `eventoT`(`id_evento`);
ALTER TABLE `eventoxartistaT` ADD CONSTRAINT `fk_artistaxevento` FOREIGN KEY (`id_fk_id_artista`) references `artistaT`(`id_artista`);
ALTER TABLE `eventoxclienteT` ADD CONSTRAINT `fk_eventoxcliente` FOREIGN KEY (`id_fk_id_evento`) references `eventoT`(`id_evento`);
ALTER TABLE `eventoxclienteT` ADD CONSTRAINT `fk_clientexevento` FOREIGN KEY (`id_fk_id_cliente`) references `clienteT`(`id_cliente`);

SELECT 
    *
FROM
    actividadesculturalesdb.lugarT;

SELECT 
    *
FROM
    actividadesculturalesdb.eventoT;