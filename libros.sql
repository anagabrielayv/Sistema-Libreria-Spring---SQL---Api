use master
go

CREATE DATABASE DB_Libros;
GO

USE DB_Libros;
GO

CREATE TABLE AUTOR
(
 idUsuario int NOT NULL PRIMARY KEY IDENTITY,
 firstName   varchar(50) NOT NULL ,
 lastName   varchar(50) NOT NULL ,
 biography   varchar(100) NOT NULL ,
)
GO

CREATE TABLE LIBRO
(
 idLibro    int NOT NULL PRIMARY KEY IDENTITY,
 isbn       varchar(100) NOT NULL ,
 price      float NOT NULL ,
 numberOfPages   int NOT NULL ,
 publicationDate datetime NOT NULL ,
 authorId int NOT NULL FOREIGN KEY REFERENCES AUTOR(idUsuario)
)
GO

