package domain;

import java.util.ArrayList;
import java.util.List;

public class Role {

    private int id;
    private String name;
    private String description;
    private List<Permission> permissions;
    private List<User> users;

    public Role(String name){
        this.name = name;
        this.permissions = new ArrayList<>();
        this.users = new ArrayList<>();
    }

    public Role(String name, String description){
        this.name = name;
        this.permissions = new ArrayList<>();
        this.users = new ArrayList<>();
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public void addUser(User u){
        this.users.add(u);
    }

    public void addPermission(Permission p){
        this.permissions.add(p);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
