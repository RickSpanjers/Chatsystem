package chatsystem;

import domain.Channel;
import domain.Chatroom;
import domain.Message;
import domain.User;
import dto.sockets.*;

import java.util.List;

public interface IChatSystemLogic {
    User login(String mail, String password);
    boolean logout(UserAccountDTO dto);
    boolean register(String mail, String username, String password);
    User forgotPassword(String mail);
    boolean resetPassword(String mail, String password, String confirmPassword);
    String rememberAccount(String phone);
    AccountProfileDTO getUserProfile(UserAccountDTO dto);
    boolean updateProfile(AccountProfileDTO dto);
    Chatroom openChatroom(OpenChatDTO dto);
    SendMsgDTO sendPrivateMessage(SendMsgDTO dto);
    boolean addFriend(AddFriendDTO dto);
    boolean createChannel(String name);
    boolean addFriendToChannel();
    List<User> getUsersOnline(UserAccountDTO dto, String sessionId);
    String getSessionFromUser(int userId);
    boolean logoutBySession(String sessionId);
    List<User> searchUsers(SearchUserDTO dto);
    List<Chatroom> getChatrooms(UserAccountDTO dto);
}
