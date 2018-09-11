
/*
Queries:
1.  Insert user
2.  Delete user
3.  Insert team 
4.  Delete team
5.  Rooms available in a given time slot
6.  Add new meetings, should not overlap with booked ones
7.  Delete booked meetings
8.  Occupation list for all rooms on a given date
9.  List over the booked meetings and who's booked them
10. Show participants of specific meeting
11. Cost accrued by teams over given time interval
*/

1.
INSERT INTO staff VALUES (,'CEO',999);

2.
UPDATE staff SET staff_removed = TRUE
WHERE staff_id = 0;

3.
INSERT INTO teams VALUES (999);

4.
UPDATE teams SET team_removed = TRUNCATE
WHERE team_id = 10;

5.
WITH CTE1 AS
(
    SELECT resources.room_id,resources.room_name,meeting.room FROM resources
    INNER JOIN meeting ON resources.room_id = meeting.room
    WHERE meeting.time_booked && tsrange('2018-03-22 10:00', '2018-03-22 17:00')
)
SELECT room_id,room_name FROM resources
WHERE room_name NOT IN (select room_name from CTE1);

6.
INSERT INTO meeting VALUES (413,32,'[2018-02-02 15:00,2018-02-02 17:00]',000);

7.
DELETE FROM meeting WHERE meeting.meeting_id = 000
AND LOWER(meeting.time_booked) > now()::date;

8.
SELECT resources.room_name AS booked_rooms FROM meeting INNER JOIN resources ON meeting.room = resources.room_id
WHERE meeting.time_booked && tsrange('2018-03-22 10:00', '2018-03-22 17:00');

9.
SELECT meeting.meeting_id,meeting.booke FROM meeting

10.

WITH staff_participants(meeting,staff_id,staff_name) AS
(
    SELECT meeting.meeting_id,meeting_participant.staff,staff.staff_name FROM meeting
    INNER JOIN meeting_participant ON meeting_participant.meeting = meeting.meeting_id
    INNER JOIN staff ON staff.staff_id = meeting_participant.staff
    WHERE meeting.meeting_id = 1
),
bp_participants(meeting,bp_id,bp_name) AS 
(
    SELECT meeting.meeting_id,bp_meeting_participant.business_partner,business_partner.bp_name FROM meeting
    INNER JOIN bp_meeting_participant ON bp_meeting_participant.meeting = meeting.meeting_id
    INNER JOIN business_partner ON business_partner.bp_id = bp_meeting_participant.business_partner
    WHERE meeting.meeting_id = 1
),
unioned(meeting, name) AS
(
    SELECT meeting,staff_name FROM staff_participants
    UNION ALL
    SELECT meeting,bp_name FROM bp_participants
)
SELECT meeting, string_agg(name, ', ') FROM unioned
GROUP BY meeting;




SELECT meeting_participant.staff,staff.staff_name FROM meeting_participant
INNER JOIN staff ON staff.staff_id = meeting_participant.staff
INNER JOIN meeting ON meeting.meeting_id = meeting_participant.meeting
WHERE meeting.meeting_id = 41

11.
WITH CTE1(meeting_id,bookee,belongs_to_team,cost) AS
(
    SELECT meeting.meeting_id, meeting.bookee,staff.belongs_to_team,resources.cost FROM meeting
    INNER JOIN staff ON staff.staff_id = meeting.bookee
    INNER JOIN resources ON meeting.room = resources.room_id
    WHERE meeting.time_booked && tsrange('2018-01-01 10:00', '2018-02-02 17:00')
)
SELECT belongs_to_team AS team,SUM(cost) AS total_cost FROM CTE1
GROUP BY belongs_to_team;