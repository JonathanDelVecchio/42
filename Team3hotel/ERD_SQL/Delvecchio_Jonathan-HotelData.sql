INSERT INTO amenity (amenityID, amenityName) VALUES
(30001, 'Microwave, Jacuzzi'),
(30002, 'Microwave, Refrigerator'),
(30003, 'Microwave, Refrigerator, Jacuzzi'),
(30004, 'Microwave, Refrigerator, Oven'),
(30005, 'Refrigerator');

INSERT INTO guest (guestID, firstName, lastName, address, city, state, zip, phoneNumber) VALUES
(10001, 'Jonathan', 'DelVecchio', '2687 La Mesa Blvd', 'La Mesa', 'CA', 91942, '(619) 889-3752'),
(10002, 'Mack', 'Simmer', '379 Old Shore Street', 'Council Bluffs', 'IA', 51501, '(291) 553-0508'),
(10003, 'Bettyann', 'Seery', '750 Wintergreen Drive', 'Wasilla', 'AK', 99654, '(478) 277-9632'),
(10004, 'Duane', 'Cullison', '9662 Foxrun Lane', 'Harlingen', 'TX', 78552, '(308) 494-0198'),
(10005, 'Karie', 'Yang', '9378 West Augusta Avenue', 'West Deptford', 'NJ', 08096, '(214) 730-0298'),
(10006, 'Aurore', 'Lipton', '762 Wild Rose Street', 'Saginaw', 'MI', 48601, '(377) 507-0974'),
(10007, 'Zachery', 'Luechtefeld', '7 Poplar Drive', 'Arvada', 'CO', 80003, '(814) 485-2615'),
(10008, 'Jeremiah', 'Pendergrass', '70 Oakwood Street', 'Zion', 'IL', 60099, '(279) 491-0960'),
(10009, 'Walter', 'Holaway', '7556 Arrowhead Street', 'Cumberland', 'RI', 2864, '(446) 396-6785'),
(10010, 'Wilfred', 'Vise', '77 West Surrey Street', 'Oswego', 'NY', 13126, '(834) 727-1001'),
(10011, 'Maritza', 'Tilton', '939 Linda Road', 'Burke', 'VA', 22015, '(446) 351-6860'),
(10012, 'Joleen', 'Tison', '87 Queen Street', 'Drexel Hill', 'PA', 19026, '(231) 893-2755');

INSERT INTO RoomType (roomTypeID, typeName, standardOccupancy, maximumOccupancy, extraCost)
VALUES 
	ROW(20001, 'Double', 2, 4, 10.00),
    ROW(20002, 'Single', 2 ,2, NULL),
    ROW(20003, 'Suite', 3, 8, 20.00);
    
INSERT INTO reservation (reservationID, guestID, amountAdults, amountChildren, startDate, endDate, totalRoomCost)
VALUES 
(40001,10002,1,0,'2023-02-02','2023-02-04',299.98),
(40002,10003,2,1,'2023-02-05','2023-02-10',999.95),
(40003,10004,2,0,'2023-02-22','2023-02-24',349.98),
(40004,10005,2,2,'2023-03-06','2023-03-07',199.99),
(40005,10001,1,1,'2023-03-17','2023-03-20',524.97),
(40006,10006,3,0,'2023-03-18','2023-03-23',924.95),
(40007,10007,2,2,'2023-03-29','2023-03-31',349.98),
(40008,10008,2,0,'2023-03-31','2023-04-05',874.95),
(40009,10009,1,0,'2023-04-09','2023-04-13',799.96),
(40010,10010,1,1,'2023-04-23','2023-04-24',174.99),
(40011,10011,2,4,'2023-05-30','2023-06-02',1199.97),
(40012,10012,2,0,'2023-06-10','2023-06-14',599.96),
(40013,10012,1,0,'2023-06-10','2023-06-14',599.96),
(40014,10006,3,0,'2023-06-17','2023-06-18',184.99),
(40015,10001,2,0,'2023-06-28','2023-07-02',699.96),
(40016,10009,3,1,'2023-07-13','2023-07-14',184.99),
(40017,10010,4,2,'2023-07-18','2023-07-21',1259.97),
(40018,10003,2,1,'2023-07-28','2023-07-29',199.99),
(40019,10003,1,0,'2023-08-30','2023-09-01',349.98),
(40020,10002,2,0,'2023-09-16','2023-09-17',149.99),
(40021,10005,2,2,'2023-09-13','2023-09-15',399.98),
(40022,10004,2,2,'2023-11-22','2023-11-25',1199.97),
(40023,10002,2,0,'2023-11-22','2023-11-25',449.97),
(40024,10002,2,2,'2023-11-22','2023-11-25',599.97),
(40025,10011,2,0,'2023-12-24','2023-12-28',699.96);


INSERT INTO room (roomNumberID, roomTypeID, adaAccessible, basePrice)
VALUES
(201, 20001, 0, 199.99),
(202, 20001, 1, 174.99),
(203, 20001, 0, 199.99),
(204, 20001, 1, 174.99),
(205, 20002, 0, 174.99),
(206, 20002, 1, 149.99),
(207, 20002, 0, 174.99),
(208, 20002, 1, 149.99),
(301, 20001, 0, 199.99),
(302, 20001, 1, 174.99),
(303, 20001, 0, 199.99),
(304, 20001, 1, 174.99),
(305, 20002, 0, 174.99),
(306, 20002, 1, 149.99),
(307, 20002, 0, 174.99),
(308, 20002, 1, 149.99),
(401, 20003, 1, 399.99),
(402, 20003, 1, 399.99);

INSERT INTO roomAmentities (roomNumberID, amenityID)
VALUES 
(201, 30001),
(203, 30001),
(301, 30001),
(303, 30001),
(206, 30002),
(208, 30002),
(308, 30002),
(306, 30002),
(205, 30003),
(207, 30003),
(305, 30003),
(307, 30003),
(401, 30004),
(402, 30004),
(202, 30005),
(204, 30005),
(302, 30005),
(304, 30005);

INSERT INTO roomReservation (roomNumberID, reservationID) 
VALUES
(308,40001),
(203,40002),
(305,40003),
(201,40004),
(307,40005),
(302,40006),
(202,40007),
(304,40008),
(301,40009),
(207,40010),
(401,40011),
(206,40012),
(208,40013),
(304,40014),
(205,40015),
(204,40016),
(401,40017),
(303,40018),
(305,40019),
(208,40020),
(203,40021),
(401,40022),
(206,40023),
(301,40024),
(302,40025);


-- Delete Jeremiah Pendergrass and his reservations from the database 

-- Find his Guest ID
SELECT guestID FROM guest WHERE firstName='Jeremiah' AND lastName='Pendergrass';
-- 10008

-- First roomReservations
DELETE FROM roomReservation 
WHERE reservationID IN (
    SELECT reservationID 
    FROM reservation 
    WHERE guestID = 10008
);
-- Records Before 25 After 24

-- Second roomReservations
DELETE FROM reservation 
WHERE guestID = 10008;
-- Records Before 25 After 24

-- Last roomReservations
DELETE FROM guest
WHERE guestID = 10008;
-- Records Before 12 After 11
