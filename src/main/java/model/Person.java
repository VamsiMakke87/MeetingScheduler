package model;

public class Person {

    String name;

    String email;

    String phoneNumber;


    public Person(String name, String email, String phoneNumber) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public void sendNotificaton(String message){

        NotificationSender notificationSender= new NotificationSender(name,email,phoneNumber);

    }


}
