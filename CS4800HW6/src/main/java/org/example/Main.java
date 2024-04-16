package org.example;

import java.util.Iterator;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        ChatServer chatServer = new ChatServer();
        User carson = new User("Carson", chatServer);
        User john = new User("John", chatServer);
        User nima = new User("Nima", chatServer);
        User mark = new User("Mark", chatServer);

        chatServer.registerUser(carson);
        chatServer.registerUser(john);
        chatServer.registerUser(nima);
        chatServer.registerUser(mark);

        carson.sendMessage(new User[]{john, nima, mark}, "Hello, world!");
        nima.sendMessage(new User[]{carson}, "How's the weather?");
        john.sendMessage(new User[]{mark}, "Oh hi Mark");
        mark.sendMessage(new User[]{carson, nima}, "who is this john guy?");
        nima.sendMessage(new User[]{mark, carson}, "What's up?");

        Iterator carsonHistory = carson.iterator(nima);
        System.out.println("\n\nMessages sent from Nima to Carson: \n");
        while(carsonHistory.hasNext()){
            System.out.println(carsonHistory.next());
        }
    }
}