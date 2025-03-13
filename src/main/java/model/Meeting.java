package model;


import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Meeting {

    List<Person> attendes;

    int startTime;

    int endTime;

    Date date;

    MeetingRoom meetingRoom;

    int capacity;

    public Meeting(List<Person> attendes, int startTime, int endTime, Date date, int capacity) {
        this.attendes = attendes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.date = date;
        this.capacity = capacity;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
        sendNotification();
    }

    public void sendNotification() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMMM yyyy");
        String formattedDate = dateFormat.format(date);
        for (Person person : attendes){
            person.sendNotificaton("Meeting scheduled in room number: " + meetingRoom.roomNumber + " on " + formattedDate + " from " + startTime + " to " + endTime);
        }

    }


}
