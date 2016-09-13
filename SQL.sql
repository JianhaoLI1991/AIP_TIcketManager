/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  Jianhao_LI
 * Created: Sep 1, 2016
 */

-- create table UserInfo(
-- 	userId int not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
-- 	email varchar(100) not null,
-- 	password varchar(100) not null,
-- 	firstName varchar(50),
-- 	lastName varchar(50),
-- 	dob date,
-- 	city varchar(50),
-- 	state varchar(50),
-- 	postcode varchar(10),
-- 	address varchar(100),
-- 	phone varchar(20),
-- 	image blob
-- )

-- create table TicketDetail (
-- 	ticketId int not null primary key GENERATED ALWAYS AS IDENTITY(START WITH 1, INCREMENT BY 1),
-- 	userId int not null,
--      flightNumber varchar(20) not null,
-- 	origin varchar(20) not null,
-- 	destination varchar(20) not null,
-- 	departureTime date not null,
-- 	arriveTime date not null,
--      fromtime time not null,
--      totime time not null,
-- 	FOREIGN KEY (userId) REFERENCES UserInfo (userId)
-- ); 

-- insert into USERINFO (email,password) values ('kardy3415@hotmail.com','123456');