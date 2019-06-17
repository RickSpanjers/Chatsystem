package chatsystem;

import com.google.gson.Gson;
import context.mssql.ChannelMSSQLContext;
import context.mssql.UserMSSQLContext;
import domain.Channel;
import domain.Chatroom;
import domain.Message;
import domain.User;
import dto.rest.RestResultDTO;
import dto.sockets.*;
import logging.ILogger;
import logging.Logger;
import repository.ChannelRepository;
import repository.UserRepository;
import rest.ChatRESTClient;

import java.util.ArrayList;
import java.util.List;

public class ChatSystemLogic implements IChatSystemLogic {

    private ChatRESTClient restClient = new ChatRESTClient();
    private ILogger logger = Logger.getInstance();
    private Gson gson = new Gson();
    private UserRepository userRepository = new UserRepository(new UserMSSQLContext());
    private ChannelRepository channelRepository = new ChannelRepository(new ChannelMSSQLContext());
    private List<User> usersOnline;
    private List<Chatroom> rooms;

    public ChatSystemLogic(){
        usersOnline = new ArrayList<>();
        rooms = new ArrayList<>();
    }

    @Override
    public User login(String mail, String password) {
        User result = new User(null, null);
        if(mail != null && password != null){
            User u = restClient.login(mail,password);
            if(u.getId() != 0){
                result = u;
                if(!isUserAlreadyInSystem(result)){
                    result.setOnline(true);
                    usersOnline.add(result);
                }
            }
        }
        return result;
    }

    @Override
    public boolean logout(UserAccountDTO dto) {
        boolean result = false;
        User userToBeRemoved = new User(null, null);
        for(User u : usersOnline){
            if(u.getId() == dto.getUserId()){
                userToBeRemoved = u;
                u.setOnline(false);
                for(Chatroom r : rooms){
                    for(User user : r.getUsers()){
                        if(user.getId() == u.getId()){
                            user.setOnline(false);
                        }
                    }
                }
                result = true;
            }
        }

        usersOnline.remove(userToBeRemoved);
        return result;
    }

    private boolean isUserAlreadyInSystem(User u){
        boolean result = false;
        if(usersOnline.contains(u)){
            result = true;
        }
        return result;
    }

    @Override
    public boolean register(String mail, String username, String password) {
        boolean result = false;
        if(mail != null && username != null && password != null){
            if(restClient.register(username,mail,password)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public User forgotPassword(String mail) {
        User result = new User(0, null, null, null);
        if(mail != null){
            Object restResult = restClient.forgotPassword(mail);
            if(((RestResultDTO) restResult).getSuccess()){
                result =  gson.fromJson(((RestResultDTO) restResult).getData(), User.class);
            }
        }
        return result;
    }

    @Override
    public boolean resetPassword(String mail, String password, String confirmPassword) {
        boolean result = false;
        if(mail != null && password != null && confirmPassword != null){
            if(restClient.resetPassword(mail, password, confirmPassword)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public String rememberAccount(String phone) {
        String result = null;
        if(phone != null){
            RestResultDTO resultRest = restClient.rememberAccount(phone);
            if(resultRest.getSuccess()){
                result = resultRest.getData();
            }
        }
        return result;
    }

    @Override
    public AccountProfileDTO getUserProfile(UserAccountDTO dto) {
        AccountProfileDTO result = new AccountProfileDTO(null, null);
        User retrievedUser = userRepository.getUserByMail(dto.getUserMail());
        if(retrievedUser.getId() != 0){
            result = new AccountProfileDTO(retrievedUser.getUsername(), retrievedUser.getMail());
            result.setFirstname(retrievedUser.getFirstname());
            result.setLastname(retrievedUser.getLastname());
            result.setAddress(retrievedUser.getAddress());
            result.setPlace(retrievedUser.getPlace());
            result.setZipcode(retrievedUser.getZipcode());
            result.setPhone(retrievedUser.getPhone());
        }
        return result;
    }


    @Override
    public boolean updateProfile(AccountProfileDTO dto) {
        boolean result = false;
        User retrievedUser = userRepository.getUserByMail(dto.getMail());
        if(retrievedUser.getId() != 0){
            if(!dto.getMail().equals(retrievedUser.getMail())){
                retrievedUser.setMail(dto.getMail());
            }
            retrievedUser.setFirstname(dto.getFirstname());
            retrievedUser.setLastname(dto.getLastname());
            retrievedUser.setUsername(dto.getUsername());
            retrievedUser.setPlace(dto.getPlace());
            retrievedUser.setZipcode(dto.getZipcode());
            retrievedUser.setAddress(dto.getAddress());
            retrievedUser.setPhone(dto.getPhone());
            if(dto.getPassword().equals(dto.getConfirmPassword())){
                retrievedUser.setPassword(dto.getPassword());
                if(userRepository.updateProfile(retrievedUser)){
                    result = true;
                }
            }
        }
        return result;
    }

    @Override
    public boolean addFriendToChannel() {
        return false;
    }

    @Override
    public Chatroom openChatroom(OpenChatDTO dto){
        User userOne = userRepository.getUserById(dto.getAccountId());
        User userTwo = userRepository.getUserById(dto.getFriendUserId());
        Chatroom room = getCorrectChatroom(userOne, userTwo);
        if(rooms.isEmpty() || room.getUsers().get(0) == null) {
            int id = userOne.getId() + userTwo.getId();
            room = new Chatroom(id, userOne, userTwo);
            rooms.add(room);
        }
        return room;
    }

    @Override
    public SendMsgDTO sendPrivateMessage(SendMsgDTO dto) {
        dto.setSent(false);
        if(dto.getMessage() != null && dto.getUserThatSendsId() != 0 && dto.getUserThatReceivesId() != 0){

            User sending = userRepository.getUserById(dto.getUserThatSendsId());
            User receiving = userRepository.getUserById(dto.getUserThatReceivesId());
            Message m = new Message(sending, dto.getMessage());
            Chatroom retrievedRoom = getCorrectChatroom(sending, receiving);

            if(retrievedRoom.getUsers().get(0) == null){
                OpenChatDTO newChatDTO = new OpenChatDTO(sending.getId(), receiving.getId(), receiving.getMail());
                Chatroom room = openChatroom(newChatDTO);
                room.addMessage(m);
                dto.setSent(true);
            }
            else{
                retrievedRoom.addMessage(m);
                dto.setSent(true);
            }
        }
        return dto;
    }

    @Override
    public String getSessionFromUser(int userId){
        String result = "";
        User u = userRepository.getUserById(userId);
        for(User user : usersOnline){
            if(u.getId() == user.getId()){
                result = user.getSessionId();
            }
        }
        return result;
    }

    @Override
    public boolean logoutBySession(String sessionId) {
        boolean result = false;
        User retrieved = new User(null, null);
        for(User u: usersOnline){
            if(u.getSessionId().equals(sessionId)){
                retrieved = u;
                result = true;
                for(Chatroom r : rooms){
                    for(User user : r.getUsers()){
                        if(user.getId() == u.getId()){
                            user.setOnline(false);
                        }
                    }
                }
            }
        }
        usersOnline.remove(retrieved);
        return result;
    }

    @Override
    public List<User> searchUsers(SearchUserDTO dto) {
        User account = userRepository.getUserById(dto.getAccountId());
        return userRepository.searchUsers(account, dto.getSearchText());
    }

    @Override
    public List<Chatroom> getChatrooms(UserAccountDTO dto) {
        User account = userRepository.getUserById(dto.getUserId());
        List<Chatroom> chatrooms = new ArrayList<>();
        for(Chatroom r : rooms){
            for(User u : r.getUsers()){
                if(u.getId() == account.getId()){
                    chatrooms.add(r);
                }
            }
        }
        return isUserOnlineChatrooms(chatrooms);
    }

    private Chatroom getCorrectChatroom(User sending, User receiving){
        Chatroom room = new Chatroom(0,null, null);
        for(Chatroom r : rooms){
            if(r.getId() == sending.getId() + receiving.getId()){
                room = r;
            }
        }
        return room;
    }

    @Override
    public boolean createChannel(String name) {
        boolean result = false;
        if(name != null){
            if(channelRepository.addChannel(new Channel(name))){
                result = true;
            }
        }
        return result;
    }

    @Override
    public boolean addFriend(AddFriendDTO dto) {
        boolean result = false;
        User userToGainFriend = userRepository.getUserById(dto.getUserToGainFriend());
        User userToBeFriend = userRepository.getUserById(dto.getUserToBeFriend());
        if(userToGainFriend.getId() != 0 && userToBeFriend.getId() != 0){
            if(userRepository.addFriend(userToGainFriend, userToBeFriend) && userRepository.addFriend(userToBeFriend, userToGainFriend)){
                result = true;
            }
        }
        return result;
    }

    @Override
    public List<User> getUsersOnline(UserAccountDTO dto, String sessionId) {
        List<User> result = new ArrayList<>();
        User u = userRepository.getUserByMail(dto.getUserMail());

        if(u.getId() != 0){
            u.setFriends(userRepository.getFriends(u));
            result = u.getFriends();
            for(User online : usersOnline){
                for(User friend : result){
                    if(friend.getId() == online.getId()){
                        friend.setOnline(true);
                    }
                    if(online.getId() == u.getId()){
                        online.setSessionId(sessionId);
                    }
                }
            }
        }
        return result;
    }

    private List<Chatroom> isUserOnlineChatrooms(List<Chatroom> chatrooms){
        for(Chatroom r : chatrooms) {
            for(User user : r.getUsers()){
                for(User userOnline : usersOnline){
                    if(userOnline.getId() == user.getId()){
                        user.setOnline(true);
                    }
                }
            }
        }
        return chatrooms;
    }
}
