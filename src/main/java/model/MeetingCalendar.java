package model;

import java.util.*;
import java.util.function.IntBinaryOperator;

public class MeetingCalendar {

    static Map<String, Map<Date, boolean[]>> meetingRoomsCalendar;

    static TreeMap<Integer, List<String>> meetingRoomsCapacity;
    static MeetingCalendar instance;


    private MeetingCalendar() {
        meetingRoomsCapacity = new TreeMap<>(Collections.reverseOrder());
        meetingRoomsCalendar = new HashMap<>();

    }

    public static synchronized MeetingCalendar getInstance() {
        if (instance == null) {

            instance = new MeetingCalendar();
            loadMeetingRooms();
        }
        return instance;
    }

    private static void loadMeetingRooms() {
        MeetingRoom m1 = new MeetingRoom("A101", 2);
        MeetingRoom m2 = new MeetingRoom("A102", 2);
        MeetingRoom m3 = new MeetingRoom("A103", 4);
        MeetingRoom m4 = new MeetingRoom("A104", 4);

        addMeetingRoom(m1);
        addMeetingRoom(m2);
        addMeetingRoom(m3);
        addMeetingRoom(m4);

    }

    public static void addMeetingRoom(MeetingRoom meetingRoom) {

        meetingRoomsCapacity.putIfAbsent(meetingRoom.capacity, new ArrayList<>());
        meetingRoomsCapacity.get(meetingRoom.capacity).add(meetingRoom.roomNumber);
        meetingRoomsCalendar.put(meetingRoom.roomNumber, new HashMap<>());

    }

    public boolean bookMeetingRoom(String roomNumber, int startTime, int endTime, Date date) {

        if (isMeetingRoomAvailable(roomNumber, startTime, endTime, date)) {
            boolean meetingRoomDayBooking[] = meetingRoomsCalendar.get(roomNumber).get(date);
            for (int i = startTime; i < endTime; i++) {
                meetingRoomDayBooking[i] = true;
            }
            return true;
        }

        return false;
    }

    public boolean isMeetingRoomAvailable(String roomNumber, int startTime, int endTime, Date date) {

        Map<Date, boolean[]> meetingRoomBookings = meetingRoomsCalendar.get(roomNumber);

        meetingRoomBookings.putIfAbsent(date, new boolean[24]);
        boolean meetingRoomDayBooking[] = meetingRoomBookings.get(date);

        for (int i = startTime; i < endTime; i++) {
            if (meetingRoomDayBooking[i]) return false;
        }


        return true;

    }


    public Set<String> getAvailableMeetingRooms(Meeting meeting) {


        Set<String> availableMeetingRooms = new HashSet<>();
        for (int capacity : meetingRoomsCapacity.keySet()) {
            if (capacity < meeting.capacity) break;
            for (String meetingRoomNumber : meetingRoomsCapacity.get(capacity)) {
                if (isMeetingRoomAvailable(meetingRoomNumber, meeting.startTime, meeting.endTime, meeting.date))
                    availableMeetingRooms.add(meetingRoomNumber);
            }
        }
        return availableMeetingRooms;

    }
}
