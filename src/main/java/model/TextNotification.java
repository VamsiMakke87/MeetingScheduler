package model;

public class TextNotification implements Notification {


    String name;

    String phoneNumber;

    public TextNotification(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        sendNotification(name, phoneNumber);
    }

    @Override
    public void sendNotification(String name, String phoneNumber) {
        System.out.println("Text Message sent to " + name + " on phone number:" + phoneNumber);
    }
}
