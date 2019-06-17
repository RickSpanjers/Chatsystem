package repository;

import domain.User;

import java.util.ArrayList;
import java.util.List;

public interface IUser{

    Boolean register(User u);

    Boolean login(String username, String mail, String password);

    User getUserById(int id);

    User getUserByUsername(String name);

    User getUserByMail(String mail);

    User getUserByPhone(String phone);

    Boolean checkIfUsernameExists(User p);

    Boolean checkIfMailExists(User p);

    ArrayList<User> getAllPlayers();

    String toSHA256(String base);

    Boolean validateUsername(String username, List<String> errorList);

    Boolean validatePassword(String password, List<String> errorList);

    Boolean addFriend(User user, User befriendedUser);

    Boolean removeFriend(User user, User befriendedUser);

    Boolean updateProfile(User u);

    ArrayList<User> searchUsers(User account, String searchString);

    List<User> getFriends(User u);

}
