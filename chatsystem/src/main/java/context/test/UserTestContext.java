package context.test;

import domain.User;
import repository.IUser;

import java.util.ArrayList;
import java.util.List;

public class UserTestContext implements IUser {
    @Override
    public Boolean register(User u) {
        return null;
    }

    @Override
    public Boolean login(String username, String mail, String password) {
        return null;
    }

    @Override
    public User getUserById(int id) {
        return null;
    }

    @Override
    public User getUserByUsername(String name) {
        return null;
    }

    @Override
    public User getUserByMail(String mail) {
        return null;
    }

    @Override
    public User getUserByPhone(String phone) {
        return null;
    }

    @Override
    public Boolean checkIfUsernameExists(User p) {
        return null;
    }

    @Override
    public Boolean checkIfMailExists(User p) {
        return null;
    }

    @Override
    public ArrayList<User> getAllPlayers() {
        return null;
    }

    @Override
    public String toSHA256(String base) {
        return null;
    }

    @Override
    public Boolean validateUsername(String username, List<String> errorList) {
        return null;
    }

    @Override
    public Boolean validatePassword(String password, List<String> errorList) {
        return null;
    }

    @Override
    public Boolean addFriend(User user, User befriendedUser) {
        return null;
    }

    @Override
    public Boolean removeFriend(User user, User befriendedUser) {
        return null;
    }

    @Override
    public Boolean updateProfile(User u) {
        return null;
    }

    @Override
    public ArrayList<User> searchUsers(User account, String searchString) {
        return null;
    }

    @Override
    public List<User> getFriends(User u) {
        return null;
    }

}
