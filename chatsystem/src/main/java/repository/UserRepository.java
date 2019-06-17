package repository;

import domain.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    private IUser userContext;

    public UserRepository(IUser context) {
        userContext = context;
    }

    public Boolean registerUser(User u) {
        return userContext.register(u);
    }

    public Boolean loginUser(String username, String mail, String password) {
        return userContext.login(username, mail, password);
    }

    public User getUserById(int id) {
        return userContext.getUserById(id);
    }

    public User getUserByUsername(String name) {
        return userContext.getUserByUsername(name);
    }

    public User getUserByMail(String mail) {
        return userContext.getUserByMail(mail);
    }

    public User getUserByPhone(String phone) {
        return userContext.getUserByPhone(phone);
    }

    public boolean checkIfUsernameExists(User u) {
        return userContext.checkIfUsernameExists(u);
    }

    public boolean checkIfMailExists(User u) {
        return userContext.checkIfMailExists(u);
    }

    public ArrayList<User> getAllPlayers() {
        return userContext.getAllPlayers();
    }

    public String toSHA256(String base){
        return userContext.toSHA256(base);
    }

    public Boolean validateUsername(String username, List<String> errorList){
        return this.userContext.validateUsername(username, errorList);
    }

    public Boolean validatePassword(String password, List<String> errorList){
        return this.userContext.validatePassword(password, errorList);
    }

    public Boolean addFriend(User user, User befriendedUser){
        return this.userContext.addFriend(user, befriendedUser);
    }

    public Boolean removeFriend(User user, User befriendedUser){
        return this.userContext.removeFriend(user, befriendedUser);
    }

    public Boolean updateProfile(User u){
        return this.userContext.updateProfile(u);
    }

    public ArrayList<User> searchUsers(User account, String searchString){
        return this.userContext.searchUsers(account, searchString);
    }

    public List<User> getFriends(User u){
        return this.userContext.getFriends(u);
    }
}
