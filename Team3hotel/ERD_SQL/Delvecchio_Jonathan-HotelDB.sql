-- Drop the Database, added If exists so no error code 
DROP DATABASE IF EXISTS team3hotel;

-- Create the Database 
CREATE DATABASE team3hotel;

-- Making team3hotel the active Database 
USE team3hotel;

-- Drop All tables if existent*/
DROP TABLE IF EXISTS roomType;
DROP TABLE IF EXISTS amenity;
DROP TABLE IF EXISTS guest;
DROP TABLE IF EXISTS room;
DROP TABLE IF EXISTS reservation;
DROP TABLE IF EXISTS roomReservation;
DROP TABLE IF EXISTS roomAmentities;

-- Primary Tables: Room Type, Amentity, Guest, 

CREATE TABLE roomType (
	roomTypeID INT NOT NULL,
    typeName VARCHAR(10) NOT NULL,
    standardOccupancy TINYINT NOT NULL,
    maximumOccupancy  TINYINT NOT NULL,
    extraCost DECIMAL(5,2),
    CONSTRAINT pk_roomType 
        PRIMARY KEY (roomTypeID)
);

CREATE TABLE amenity (
	amenityID INT NOT NULL,
    amenityName VARCHAR(50) NOT NULL,
    CONSTRAINT pk_amenity 
        PRIMARY KEY (amenityID)
);

CREATE TABLE guest (
	guestID INT NOT NULL,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30) NOT NULL,
    address VARCHAR(30) NOT NULL,
    state VARCHAR(3) NOT NULL,
    city VARCHAR(30) NOT NULL,
    zip MEDIUMINT NOT NULL,
    phoneNumber VARCHAR(30) NOT NULL,
    CONSTRAINT pk_guest 
        PRIMARY KEY (guestID)
);

-- Related tables: Room, Reservation, RoomReservation, RoomAmentities 

CREATE TABLE room (
	roomNumberID INT NOT NULL,
    roomTypeID INT NOT NULL,
    adaAccessible BOOLEAN NOT NULL,
    basePrice DECIMAL(10,2) NOT NULL,
    CONSTRAINT pk_room 
        PRIMARY KEY (roomNumberID),
	CONSTRAINT fk_roomType
    	FOREIGN KEY (roomTypeID)
    	REFERENCES roomType(roomTypeID)
);

CREATE TABLE reservation (
	reservationID INT NOT NULL,
    guestID INT NOT NULL,
    amountAdults INT NOT NULL,
    amountChildren INT NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    totalRoomCost DECIMAL (10,2),
    CONSTRAINT pk_reservation 
        PRIMARY KEY (reservationID),
	CONSTRAINT fk_guest
    	FOREIGN KEY (guestID)
    	REFERENCES guest(guestID)
);

CREATE TABLE roomReservation (
    roomNumberID INT NOT NULL,
    reservationID INT NOT NULL,
    CONSTRAINT pk_roomReservation 
    	PRIMARY KEY (roomNumberID, reservationID),
    CONSTRAINT fk_roomReservation_room
    	FOREIGN KEY (roomNumberID)
    	REFERENCES room(roomNumberID),
    CONSTRAINT fk_roomReservation_reservation
    	FOREIGN KEY (reservationID)
    	REFERENCES reservation(reservationID)
);

CREATE TABLE roomAmentities (
    roomNumberID INT NOT NULL,
    amenityID INT NOT NULL,
    CONSTRAINT pk_roomAmentities 
    	PRIMARY KEY (roomNumberID, amenityID),
    CONSTRAINT fk_roomAmentities_room
    	FOREIGN KEY (roomNumberID)
    	REFERENCES room(roomNumberID),
    CONSTRAINT fk_roomAmentities_amenity
    	FOREIGN KEY (amenityID)
    	REFERENCES amenity(amenityID)
);

-- Now, checking the database

SHOW TABLES;



