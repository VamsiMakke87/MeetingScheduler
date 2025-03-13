**Meeting Scheduler**

## Overview
The **Meeting Scheduler** project is designed to streamline the process of scheduling meetings by allowing users to book meeting rooms based on their capacity and availability. The system ensures efficient room allocation and sends notifications to attendees once a meeting is confirmed.

## Features
- **Meeting Room Booking**: Users can select meeting rooms based on required capacity and available time slots.
- **Notification System**: Attendees receive notifications upon successful booking.
- **Dynamic Room Management**: Administrators can add, edit, and manage meeting rooms.
- **Room Availability Check**: Ensures rooms are available before confirming bookings.
- **Recurring Meetings**: Supports scheduling of daily, weekly, or custom recurring meetings.
- **Integration with Calendar Services**: Allows synchronization with external services like Google Calendar.

## How It Works
1. **Room Booking**: Users provide the meeting's start time, end time, and required capacity. The system suggests available rooms.
2. **Notification**: After booking, attendees receive notifications containing details such as room number, time, and date.
3. **Scalability**: The system can accommodate an increasing number of rooms and users efficiently.

## Design Patterns Used

### 1. **Singleton Pattern**
Used in: `MeetingCalendar` class

**Purpose**: Ensures a single instance of `MeetingCalendar`, centralizing booking logic.
```java
public static synchronized MeetingCalendar getInstance() {
    if (instance == null) {
        instance = new MeetingCalendar();
        loadMeetingRooms();
    }
    return instance;
}
```

### 2. **Factory Method Pattern**
Used in: `addMeetingRoom()` method

**Purpose**: Simplifies room creation without exposing instantiation logic.
```java
public static void addMeetingRoom(MeetingRoom meetingRoom) {
    meetingRoomsCapacity.putIfAbsent(meetingRoom.capacity, new ArrayList<>());
    meetingRoomsCapacity.get(meetingRoom.capacity).add(meetingRoom.roomNumber);
    meetingRoomsCalendar.put(meetingRoom.roomNumber, new HashMap<>());
}
```

### 3. **Observer Pattern**
Used in: `NotificationService` interface and `MeetingNotification` class

**Purpose**: Sends notifications to attendees when a meeting is booked.
```java
public interface NotificationService {
    void sendNotification(String message);
}

public class EmailNotification implements NotificationService {
    public void sendNotification(String message) {
        System.out.println("Email sent: " + message);
    }
}
```

### 4. **Strategy Pattern**
Used in: Room selection logic based on dynamic conditions

**Purpose**: Provides flexibility to choose the best room based on availability and capacity.
```java
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
```

## Future Enhancements
1. **Extended Notifications**: Add SMS and push notifications using the **Decorator Pattern**.
2. **User Authentication**: Implement roles (Admin, User) with permission-based access.
3. **Advanced Search & Filters**: Search rooms based on features (e.g., projectors, whiteboards).
4. **Cloud-Based Storage**: Store meeting details in **AWS RDS** or **MongoDB Atlas**.
5. **Calendar Integration**: Synchronize with external calendars for automated scheduling.

## How to Run the Project
1. Clone the repository to your local machine.
2. Navigate to the project folder in the terminal.
3. Compile and run the Java files:
   ```sh
   javac *.java
   java Main
   ```
4. Follow the on-screen prompts to book rooms and receive notifications.

## Conclusion
The **Meeting Scheduler** efficiently manages meeting room bookings, ensuring seamless scheduling and notifications. With planned enhancements, it aims to scale for enterprise-level meeting management.

