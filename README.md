# Meeting Scheduler

## Overview
The **Meeting Scheduler** project is designed to help users schedule meetings by selecting available meeting rooms based on room capacity and time slots. It allows multiple users (attendees) to book a meeting room and sends notifications to the attendees once the meeting room is successfully booked. The system ensures that the meeting rooms are booked efficiently, and users are notified about their meeting details.

## Features
- **Meeting Room Booking**: Users can choose a meeting room based on their required capacity and available time.
- **Notification System**: Once a meeting room is booked, attendees receive notifications with the meeting details (room number, time, date).
- **Dynamic Meeting Room Management**: The system allows adding meeting rooms with different capacities, which are dynamically considered when booking a meeting.
- **Room Availability Check**: The system checks if a meeting room is available for the requested time slot before confirming the booking.

## How It Works
1. **Room Booking**: The user inputs the meeting's start time, end time, and capacity. The system suggests available rooms based on the meeting's size and time.
2. **Notification**: Once a room is booked, attendees receive notifications with details such as the room number, time, and date.
3. **Scalable Room Management**: The system can easily handle a growing number of meeting rooms and attendees.

---

## Design Patterns Used

### 1. **Singleton Pattern**
- **Used in:** `MeetingCalendar` class.
- **Purpose:** Ensures that only one instance of the `MeetingCalendar` class exists, centralizing the meeting room booking logic and avoiding conflicts with multiple instances.

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
- **Used in:** The `addMeetingRoom()` method to create meeting rooms with specific properties (room number, capacity).
- **Purpose:** Simplifies the creation of meeting rooms without exposing the instantiation logic to the user.

   ```java
   public static void addMeetingRoom(MeetingRoom meetingRoom) {
       meetingRoomsCapacity.putIfAbsent(meetingRoom.capacity, new ArrayList<>());
       meetingRoomsCapacity.get(meetingRoom.capacity).add(meetingRoom.roomNumber);
       meetingRoomsCalendar.put(meetingRoom.roomNumber, new HashMap<>());
   }
   ```

### 3. **Observer Pattern**
- **Used in:** `sendNotification()` method in the `Meeting` class.
- **Purpose:** Notifies attendees when a meeting is scheduled, decoupling the notification mechanism from other core business logic.

   ```java
   public void sendNotificaton(String message) {
       System.out.println(name + " received a message: " + message);
   }
   ```

### 4. **Strategy Pattern**
- **Used in:** Logic for selecting available meeting rooms based on capacity and time.
- **Purpose:** Provides flexibility to choose the best strategy for booking rooms based on dynamic conditions like time and capacity.

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

---

## Future Features and Scalability

### 1. **Extended Notification System**
- **Planned Feature**: Support for different types of notifications such as **SMS** or **Email**.
- **Scalability**: With the use of the **Decorator Pattern**, new notification types can be easily integrated into the system without changing the core logic.

### 2. **User Authentication**
- **Planned Feature**: Implement user authentication to ensure that only authorized users can book meeting rooms and manage their meetings.
- **Scalability**: The system can scale to handle multiple user roles (e.g., Admin, Regular User) with different permissions.

### 3. **Room Management Enhancements**
- **Planned Feature**: Enable admins to manage rooms (add/edit/remove) dynamically.
- **Scalability**: The system can support adding more meeting rooms and enhancing room selection logic with different attributes (e.g., room features, available resources).

### 4. **Advanced Search and Filters**
- **Planned Feature**: Add filters to search for rooms based on features (e.g., projectors, video conferencing).
- **Scalability**: The use of a database and indexes would allow the system to handle a growing number of meeting rooms with more sophisticated search capabilities.

### 5. **Recurring Meetings**
- **Planned Feature**: Support for recurring meetings (e.g., daily, weekly) to automate room bookings.
- **Scalability**: The system can handle multiple recurring meeting schedules and automatically check availability for future dates.

### 6. **Integration with Calendar Services**
- **Planned Feature**: Integrate with external calendar services like **Google Calendar** or **Microsoft Outlook** for seamless scheduling and synchronization.
- **Scalability**: With the use of API integrations, the system can scale to connect with popular calendar platforms and synchronize events in real-time.

### 7. **Cloud-based Storage for Data**
- **Planned Feature**: Store meeting data and attendee information in a cloud-based database (e.g., AWS RDS, MongoDB Atlas).
- **Scalability**: This would allow the system to scale to a global level, handling hundreds of thousands of users and meeting rooms.

---

## Conclusion

The **Meeting Scheduler** project demonstrates an effective and scalable way to manage room bookings, notifications, and meeting scheduling. By utilizing **design patterns** such as Singleton, Factory Method, and Observer, the system ensures maintainability, flexibility, and easy future enhancements.

With the planned features and scalability improvements, the system is well-equipped to handle growing organizational needs, making it an ideal solution for managing meeting room bookings in large organizations.

---

## How to Run the Project

1. Clone the repository to your local machine.
2. Navigate to the project folder in the terminal.
3. Compile and run the Java files:
   ```bash
   javac *.java
   java Main
   ```
4. Follow the on-screen prompts to book meeting rooms and see the notifications.

---
