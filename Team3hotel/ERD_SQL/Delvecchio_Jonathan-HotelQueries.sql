
-- Write a query that returns a list of reservations that end in July 2023, 
-- including the name of the guest, the room number(s), and the reservation dates.
SELECT guest.firstName, guest.lastName, room.roomNumberID, reservation.startDate, reservation.endDate
FROM guest
JOIN reservation ON guest.guestID = reservation.guestID
JOIN roomReservation ON reservation.reservationID = roomReservation.reservationID
JOIN room ON roomReservation.roomNumberID = room.roomNumberID
WHERE reservation.endDate BETWEEN '2023-07-01' AND '2023-07-31';

/*
firstName,lastName,roomNumberID,startDate,endDate
Jonathan,DelVecchio,205,2023-06-28,2023-07-02
Walter,Holaway,204,2023-07-13,2023-07-14
Wilfred,Vise,401,2023-07-18,2023-07-21
Bettyann,Seery,303,2023-07-28,2023-07-29 
 

Write a query that returns a list of all reservations for rooms with a jacuzzi, 
displaying the guest's name, the room number, and the dates of the reservation. */
SELECT g.firstName, g.lastName, r.roomNumberID, rv.startDate, rv.endDate
FROM guest g
JOIN reservation rv ON g.guestID = rv.guestID
JOIN roomReservation rr ON rv.reservationID = rr.reservationID
JOIN room r ON rr.roomNumberID = r.roomNumberID
JOIN roomAmentities ra ON r.roomNumberID = ra.roomNumberID
JOIN amenity a ON ra.amenityID = a.amenityID
WHERE ra.amenityID = 30001 OR ra.amenityID = 30003;
/* firstName,lastName,roomNumberID,startDate,endDate
Karie,Yang,201,2023-03-06,2023-03-07
Bettyann,Seery,203,2023-02-05,2023-02-10
Karie,Yang,203,2023-09-13,2023-09-15
Walter,Holaway,301,2023-04-09,2023-04-13
Mack,Simmer,301,2023-11-22,2023-11-25
Bettyann,Seery,303,2023-07-28,2023-07-29
Jonathan,DelVecchio,205,2023-06-28,2023-07-02
Wilfred,Vise,207,2023-04-23,2023-04-24
Duane,Cullison,305,2023-02-22,2023-02-24
Bettyann,Seery,305,2023-08-30,2023-09-01
Jonathan,DelVecchio,307,2023-03-17,2023-03-20

Write a query that returns all the rooms reserved for a specific guest, 
including the guest's name, the room(s) reserved, the starting date of the reservation, 
and how many people were included in the reservation. (Choose a guest's name from the existing data.) */
SELECT g.firstName, g.lastName, r.roomNumberID, res.startDate, res.amountAdults, res.amountChildren
FROM guest g
JOIN reservation res ON g.guestID = res.guestID
JOIN roomReservation rr ON res.reservationID = rr.reservationID
JOIN room r ON rr.roomNumberID = r.roomNumberID
WHERE g.firstName = 'Jonathan' AND g.lastName = 'DelVecchio';

/*
firstName,lastName,roomNumberID,startDate,amountAdults,amountChildren
Jonathan,DelVecchio,307,2023-03-17,1,1
Jonathan,DelVecchio,205,2023-06-28,2,0

Write a query that returns a list of rooms, reservation ID, and per-room cost for each reservation. 
The results should include all rooms, whether or not there is a reservation associated with the room. */
SELECT r.roomNumberID, rv.reservationID, 
       (r.basePrice + ((rv.amountAdults + rv.amountChildren) * COALESCE(rt.extraCost, 0))) AS perRoomCost -- Coalsce returns 0, isntead of null
FROM room r 
LEFT JOIN roomType rt ON r.roomTypeID = rt.roomTypeID 
LEFT JOIN roomReservation rr ON r.roomNumberID = rr.roomNumberID 
LEFT JOIN reservation rv ON rr.reservationID = rv.reservationID;

/* 
roomNumberID,reservationID,perRoomCost
201,40004,239.99
202,40007,214.99
203,40002,229.99
203,40021,239.99
204,40016,214.99
205,40015,174.99
206,40012,149.99
206,40023,149.99
207,40010,174.99
208,40013,149.99
208,40020,149.99
301,40009,209.99
301,40024,239.99
302,40006,204.99
302,40025,194.99
303,40018,229.99
304,40014,204.99
305,40003,174.99
305,40019,174.99
306,NULL,NULL
307,40005,174.99
308,40001,149.99
401,40011,519.99
401,40017,519.99
401,40022,479.99
402,NULL,NULL

Write a query that returns all the rooms accommodating at least three guests and that are reserved on any date in April 2023. 
*/

SELECT r.roomNumberID 
FROM room r 
JOIN roomReservation rr ON r.roomNumberID = rr.roomNumberID 
JOIN reservation rv ON rr.reservationID = rv.reservationID 
WHERE (rv.amountAdults + rv.amountChildren) >= 3 
AND (rv.startDate BETWEEN '2023-04-01' AND '2023-04-30' 
     OR rv.endDate BETWEEN '2023-04-01' AND '2023-04-30');

/* 0 rooms meet the criteria 

Write a query that returns a list of all guest names and the number of reservations per guest, 
sorted starting with the guest with the most reservations and then by the guest's last name.
*/
SELECT g.firstName, g.lastName, COUNT(*) AS numReservations
FROM guest g
JOIN reservation r ON g.guestID = r.guestID
GROUP BY g.guestID
ORDER BY numReservations DESC, g.lastName ASC;
/*
firstName,lastName,numReservations
Mack,Simmer,4
Bettyann,Seery,3
Duane,Cullison,2
Jonathan,DelVecchio,2
Walter,Holaway,2
Aurore,Lipton,2
Maritza,Tilton,2
Joleen,Tison,2
Wilfred,Vise,2
Karie,Yang,2
Zachery,Luechtefeld,1

Write a query that displays the name, address, and phone number of a guest based on their 
phone number. (Choose a phone number from the existing data.)
*/
SELECT firstName, lastName, address, phoneNumber
FROM guest
WHERE phoneNumber = '(619) 889-3752';

/*
firstName,lastName,address,phoneNumber
Jonathan,DelVecchio,"2687 La Mesa Blvd","(619) 889-3752"
*/

-- END

