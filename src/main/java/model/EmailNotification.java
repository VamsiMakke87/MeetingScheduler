package model;

public class EmailNotification implements  Notification{


    String name;

    String email;

    public EmailNotification(String name, String email) {
        this.name = name;
        this.email = email;
        sendNotification(name,email);
    }

    @Override
    public void sendNotification(String name, String email) {
        System.out.println("Email sent to "+name+" on email:"+ email);
    }
}
