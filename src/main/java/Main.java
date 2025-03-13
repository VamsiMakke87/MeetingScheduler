import model.Meeting;
import model.MeetingCalendar;
import model.MeetingRoom;
import model.Person;

import java.time.LocalDateTime;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        try {

            MeetingCalendar meetingCalendar = MeetingCalendar.getInstance();

            Scanner sc = new Scanner(System.in);

            Date date = new Date();


            while (true) {
                System.out.println("Enter -1 to exit, any number to proceed:");
                int exit = sc.nextInt();
                if (exit == -1) break;
                int startTime = 0, endTime = 0;
                while (true) {
                    System.out.println("Enter start time between [0-23]:");
                    startTime = sc.nextInt();
                    if (startTime >= 0 && startTime < 24) break;
                }

                while (true) {
                    System.out.println("Enter end time between [0-23]:");
                    endTime = sc.nextInt();
                    if (startTime < endTime) {
                        if (endTime >= 0 && endTime < 24) break;
                    } else {
                        System.out.println("End time must be greater than start time");
                    }
                }

                int capacity = 0;

                while (true) {
                    System.out.println("Enter capacity:");
                    capacity = sc.nextInt();
                    if (capacity <= 0) {
                        System.out.println("Capacity must be greater than zero");
                    } else {
                        break;
                    }
                }
                List<Person> attendes = new ArrayList<>();

                for (int i = 0; i < capacity; i++) {
                    System.out.println("Enter attende " + i + " name:");
                    String name = sc.next();
                    System.out.println("Enter " + name + "'s email:");
                    String email = sc.next();
                    System.out.println("Enter " + name + "'s phone number:");
                    String phoneNumber = sc.next();

                    attendes.add(new Person(name, email, phoneNumber));
                }
                Meeting meeting = new Meeting(attendes, startTime, endTime, date, capacity);

                // get list of avilable meeting rooms as per the meeting time and capacity

                // then ask for which room they want to book and then book the room

                Set<String> availableMeetingRooms = meetingCalendar.getAvailableMeetingRooms(meeting);

                System.out.println("Available Meeting rooms: " + availableMeetingRooms);
                String bookMeetingRoom = null;

                while (true) {

                    System.out.println("Enter the meeting room number that you want to book:");

                    bookMeetingRoom = sc.next().toUpperCase();

                    if (availableMeetingRooms.contains(bookMeetingRoom)) break;
                    else {
                        System.out.println("Please enter available room number only!");
                    }

                }


                meetingCalendar.bookMeetingRoom(bookMeetingRoom, startTime, endTime, date);
                // book one of the available meeting rooms and set meeting room for that meeting


                meeting.setMeetingRoom(new MeetingRoom(bookMeetingRoom, 10));

            }

        } catch (Exception e) {
            System.out.println("Error occured, please try again");
        }


    }
}
