package model;

public class NotificationSender {

    private String name;

    private String email;

    private String phoneNumber;

    public NotificationSender(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        sendNotification();
    }

    private void sendNotification() {

        Notification emailNotification= new EmailNotification(name,email);
        Notification textNotification= new TextNotification(name,phoneNumber);

    }


}
