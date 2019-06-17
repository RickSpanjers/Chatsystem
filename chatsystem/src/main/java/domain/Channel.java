package domain;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    private int id;
    private String name;
    private List<User> users;
    private List<Message> messages;
    private List<Role> roles;
    private int maxusers;
    private String welcomemessage;
    private String description;
    private String favicon;

    public Channel(String name){
        this.name = name;
        users = new ArrayList<>();
        messages = new ArrayList<>();
        roles = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
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

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public void addRole(Role r){
        this.roles.add(r);
    }

    public void addMessage(Message m){
        this.messages.add(m);
    }

    public void addUser(User u){
        this.users.add(u);
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public int getMaxusers() {
        return maxusers;
    }

    public void setMaxusers(int maxusers) {
        this.maxusers = maxusers;
    }

    public String getFavicon() {
        return favicon;
    }

    public void setFavicon(String favicon) {
        this.favicon = favicon;
    }

    public String getWelcomemessage() {
        return welcomemessage;
    }

    public void setWelcomemessage(String welcomemessage) {
        this.welcomemessage = welcomemessage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
