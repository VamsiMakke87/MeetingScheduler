package model;

public class MeetingRoom  {

    String roomNumber;

    int capacity;

    public MeetingRoom(String roomNumber, int capacity){
        this.roomNumber=roomNumber;
        this.capacity=capacity;
    }

    @Override
    public String toString() {
        return "MeetingRoom{" +
                "roomNumber='" + roomNumber + '\'' +
                ", capacity=" + capacity +
                '}';
    }
}
