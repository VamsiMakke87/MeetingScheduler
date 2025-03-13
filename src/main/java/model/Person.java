package model;

public class Person {

    String name;

    public Person(String name){
        this.name=name;
    }

    public void sendNotificaton(String message){

        System.out.println(name+" recieved a message: "+message);

    }


}
