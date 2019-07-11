CREATE DATABASE IF NOT EXISTS actividadesculturalesdb;
USE actividadesculturalesdb;
SET GLOBAL log_bin_trust_function_creators = 1;

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
    `fecha_evento` DATETIME NOT NULL COMMENT 'fecha evento',
    `estado_evento` INT(2) NOT NULL COMMENT 'estado',
    `fk_id_admin` INT(8) NOT NULL COMMENT 'fk admin',
    CONSTRAINT `pk_evento` PRIMARY KEY (`id_evento`)
);
  
CREATE TABLE IF NOT EXISTS `clienteT` (
    `id_cliente` INT(8) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria',
    `nombre_cliente` VARCHAR(64) NOT NULL COMMENT 'nombre cliente',
    `codigo_cliente` INT(8) NOT NULL COMMENT 'codigo cliente',
    CONSTRAINT `pk_cliente` PRIMARY KEY (`id_cliente`)
);
  
CREATE TABLE IF NOT EXISTS `adminT` (
    `id_admin` INT(8) NOT NULL AUTO_INCREMENT COMMENT 'Clave primaria',
    `nombre_admin` VARCHAR(64) NOT NULL UNIQUE COMMENT 'nombre admin',
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

CREATE PROCEDURE IDS_ARTISTAS (IN id_evento INT)
SELECT id_fk_id_artista FROM actividadesculturalesdb.eventoxartistat WHERE id_fk_id_evento = id_evento;

CREATE PROCEDURE CAPAIDAD_LUGARxEVENTO (IN id_evento INT)
SELECT capacidad_ocupada_exl FROM actividadesculturalesdb.eventoxlugart WHERE id_fk_id_evento = id_evento;

CREATE PROCEDURE LIST_ADMINN()
SELECT adminT.nombre_admin FROM actividadesculturalesdb.adminT;

CREATE PROCEDURE LIST_ADMINC()
SELECT adminT.id_admin, adminT.nombre_admin, adminT.contrasenia_admin FROM actividadesculturalesdb.adminT;

CREATE PROCEDURE CREATE_ADMIN(IN nombre_adminV VARCHAR(64), IN contrasenia_adminV VARCHAR(64))
INSERT INTO actividadesculturalesdb.adminT (adminT.nombre_admin, adminT.contrasenia_admin) VALUES (nombre_adminV,contrasenia_adminV);

CREATE PROCEDURE LIST_ARTISTAS()
SELECT * FROM actividadesculturalesdb.artistat;

CREATE PROCEDURE LIST_EVENT()
SELECT * FROM actividadesculturalesdb.eventot;

CREATE PROCEDURE CREATE_ARTISTA(IN id_artistaV INT(8),IN nombre_artistaV VARCHAR(64), IN ocupacion_artistaV VARCHAR(128))
INSERT INTO actividadesculturalesdb.artistat (artistat.id_artista, artistat.nombre_artista, artistat.ocupacion_artista) VALUES (id_artistaV,nombre_artistaV,ocupacion_artistaV);

CREATE PROCEDURE EDIT_ARTISTA(IN id_artistaV INT(8),IN nombre_artistaV VARCHAR(64), IN ocupacion_artistaV VARCHAR(128))
UPDATE actividadesculturalesdb.artistat SET artistat.nombre_artista=nombre_artistaV, artistat.ocupacion_artista=ocupacion_artistaV WHERE artistat.id_artista=id_artistaV;

CREATE PROCEDURE CREATE_LUGAR( IN nombre_lugar VARCHAR(64), IN cubierta_lugar VARCHAR(128), IN capacidad_lugar INT(8), IN seccion_lugar VARCHAR(64))
INSERT INTO actividadesculturalesdb.lugart ( lugart.nombre_lugar, lugart.cubierta_lugar, lugart.capacidad_lugar, lugart.seccion_lugar) VALUES ( nombre_lugar, cubierta_lugar, capacidad_lugar, seccion_lugar);

CREATE PROCEDURE EDIT_LUGAR(IN id_lugar int(8),IN nombre_lugar VARCHAR(64), IN cubierta_lugar VARCHAR(128), IN capacidad_lugar INT(8), IN seccion_lugar VARCHAR(64))
UPDATE actividadesculturalesdb.lugart SET  lugart.nombre_lugar = nombre_lugar, lugart.cubierta_lugar = cubierta_lugar, lugart.capacidad_lugar = capacidad_lugar, lugart.seccion_lugar = seccion_lugar WHERE lugart.id_lugar = id_lugar;

CREATE PROCEDURE CLOSE_EVENT(IN id_eventoC int(8))
UPDATE actividadesculturalesdb.eventot SET eventot.estado_evento = 1 WHERE eventot.id_evento = id_eventoC;

CREATE PROCEDURE LIST_LUGARES()
SELECT * FROM actividadesculturalesdb.lugart;

CREATE PROCEDURE LIST_RESERVAS(IN idCliente int)
SELECT * FROM actividadesculturalesdb.eventoxclientet WHERE id_fk_id_cliente = idCliente;

DELIMITER //
CREATE PROCEDURE DELETE_RESERVE(IN id_cliente int(8), IN id_evento int(8))
BEGIN
DELETE FROM actividadesculturalesdb.eventoxclientet  WHERE ((id_fk_id_cliente= id_cliente) AND (id_fk_id_evento = id_evento));
UPDATE actividadesculturalesdb.eventoxlugart SET eventoxlugart.capacidad_ocupada_exl = eventoxlugart.capacidad_ocupada_exl + 1 WHERE eventoxlugart.id_fk_id_evento = id_evento ;
END
//DELIMITER



DELIMITER //
CREATE PROCEDURE RESERVE(IN id_cliente int(8), IN id_evento int(8))
BEGIN
INSERT INTO actividadesculturalesdb.eventoxclientet (eventoxclientet.id_fk_id_evento, eventoxclientet.id_fk_id_cliente) VALUES (id_evento, id_cliente);
UPDATE actividadesculturalesdb.eventoxlugart SET eventoxlugart.capacidad_ocupada_exl = eventoxlugart.capacidad_ocupada_exl - 1 WHERE eventoxlugart.id_fk_id_evento = id_evento;
END
//DELIMITER

DELIMITER //
CREATE PROCEDURE DELETE_ARTISTA(IN id_artistaV INT(8))
BEGIN
DELETE FROM actividadesculturalesdb.eventoxartistat WHERE eventoxartistat.id_fk_id_artista=id_artistaV;
DELETE FROM actividadesculturalesdb.artistat WHERE artistat.id_artista=id_artistaV;
END
//DELIMITER

DELIMITER //
CREATE PROCEDURE EDITAR_EVENTO(IN id_eventoC INT, IN nombre_eventoC VARCHAR(64), IN fecha_eventoC DATETIME, IN artista1 INT, IN artista2 INT, IN artista3 INT, IN lugar INT, IN capacidad INT)
BEGIN
	UPDATE actividadesculturalesdb.eventot SET eventot.nombre_evento=nombre_eventoC, eventot.fecha_evento=fecha_eventoC WHERE eventot.id_evento=id_eventoC;
    DELETE FROM actividadesculturalesdb.eventoxartistat WHERE eventoxartistat.id_fk_id_evento = id_eventoC;
    IF(artista1<>0)
    THEN
		INSERT INTO actividadesculturalesdb.eventoxartistat (eventoxartistat.id_fk_id_evento, eventoxartistat.id_fk_id_artista) VALUES(id_eventoC,artista1);
    END IF;
    IF(artista2<>0)
    THEN
		INSERT INTO actividadesculturalesdb.eventoxartistat (eventoxartistat.id_fk_id_evento, eventoxartistat.id_fk_id_artista) VALUES(id_eventoC,artista2);
    END IF;
    IF(artista3<>0)
    THEN
		INSERT INTO actividadesculturalesdb.eventoxartistat (eventoxartistat.id_fk_id_evento, eventoxartistat.id_fk_id_artista) VALUES(id_eventoC,artista3);
    END IF;
    UPDATE actividadesculturalesdb.eventoxlugart SET eventoxlugart.id_fk_id_lugar=lugar, eventoxlugart.capacidad_ocupada_exl=capacidad WHERE eventoxlugart.id_fk_id_evento=id_eventoC;
    END
//DELIMITER 


DELIMITER //
CREATE PROCEDURE CREAR_EVENTO(IN nombre_eventoC VARCHAR(64), IN fecha_eventoC DATETIME, IN artista1 INT, IN artista2 INT, IN artista3 INT, IN lugar INT, IN capacidad INT, IN fk_id_adminC INT)
BEGIN
	INSERT INTO actividadesculturalesdb.eventot (eventot.nombre_evento, eventot.fecha_evento, eventot.fk_id_admin, eventot.estado_evento) VALUES(nombre_eventoC,fecha_eventoC,fk_id_adminC,0);
	IF(artista1<>0)
    THEN
		INSERT INTO actividadesculturalesdb.eventoxartistat (eventoxartistat.id_fk_id_evento, eventoxartistat.id_fk_id_artista) VALUES(LAST_EVENT(),artista1);
    END IF;
    IF(artista2<>0)
    THEN
		INSERT INTO actividadesculturalesdb.eventoxartistat (eventoxartistat.id_fk_id_evento, eventoxartistat.id_fk_id_artista) VALUES(LAST_EVENT(),artista2);
    END IF;
    IF(artista3<>0)
    THEN
		INSERT INTO actividadesculturalesdb.eventoxartistat (eventoxartistat.id_fk_id_evento, eventoxartistat.id_fk_id_artista) VALUES(LAST_EVENT(),artista3);
    END IF;
    INSERT INTO actividadesculturalesdb.eventoxlugart (eventoxlugart.id_fk_id_evento, eventoxlugart.id_fk_id_lugar, eventoxlugart.capacidad_ocupada_exl) VALUES(LAST_EVENT(),lugar,capacidad);
END
//DELIMITER 


DELIMITER //
CREATE FUNCTION CLIENT_EXIST(id_cliente int) RETURNS INT
BEGIN
	DECLARE v_id int;
	IF(EXISTS(SELECT * FROM actividadesculturalesdb.clientet WHERE codigo_cliente = id_cliente)) THEN
		SET v_id = 1;
	ELSE
		SET v_id = 0;
	END IF;	
    RETURN v_id;
END 
//DELIMITER 


CREATE FUNCTION RETURN_ID_CLIENT(codigoCliente int) RETURNS int
RETURN (select clientet.id_cliente FROM actividadesculturalesdb.clientet WHERE codigo_cliente = codigoCliente);

CREATE PROCEDURE RETURN_ID_LUGARP (IN id_evento int)
RETURN (SELECT id_fk_id_lugar FROM actividadesculturalesdb.eventoxlugart WHERE id_fk_id_evento = id_evento);

CREATE FUNCTION RETURN_ID_LUGAR (id_evento int) RETURNS INT
RETURN (SELECT id_fk_id_lugar FROM actividadesculturalesdb.eventoxlugart WHERE id_fk_id_evento = id_evento);

CREATE FUNCTION LAST_EVENT() RETURNS INT
RETURN (SELECT MAX(eventot.id_evento) FROM actividadesculturalesdb.eventot);

SET GLOBAL time_zone = "-5:00";


INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Zachary Justice","41487938");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Upton Rivers","48330359");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Lewis Mendoza","19509566");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Jackson Dawson","9593267");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Chase Mccormick","47697100");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Ryan Mullen","40756556");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Octavius Salazar","47120233");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Jonah Mosley","18040104");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Tate Simon","27971182");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Alan Rowland","9900093");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Quentin Hopkins","29795730");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Lyle Atkinson","32584918");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Dante Oconnor","34297984");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Timothy England","19715846");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Matthew Jefferson","23159626");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Edward Koch","27547403");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Zeph Fuller","39198314");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Cairo Beck","5847118");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Omar Castaneda","46101426");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Fulton Drake","39944999");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Macon Simmons","27239858");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Preston Nixon","37457588");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Arthur Solis","39771250");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Kieran Barber","32759502");
INSERT INTO clientet (nombre_cliente,codigo_cliente) VALUES ("Kuame Pennington","35521630");
	