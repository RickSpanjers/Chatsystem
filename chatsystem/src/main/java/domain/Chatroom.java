package domain;

import java.util.ArrayList;
import java.util.List;

public class Chatroom {

    private int id;
    private List<User> users;
    private List<Message> messages;

    public Chatroom(int id, User sending, User receiving){
        this.id = id;
        users = new ArrayList<>();
        messages = new ArrayList<>();
        users.add(sending);
        users.add(receiving);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message m){
        this.messages.add(m);
    }

    public void addUser(User u){
        this.users.add(u);
    }
}
