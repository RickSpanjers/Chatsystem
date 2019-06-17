package websockets.server.messaging;

import chatsystem.IChatSystemLogic;
import com.google.gson.Gson;
import domain.Chatroom;
import domain.User;
import dto.rest.RestResetPasswordDTO;
import dto.sockets.*;
import logging.ILogger;
import logging.Logger;
import sun.plugin2.os.windows.SECURITY_ATTRIBUTES;
import websockets.messaging.ChatGenericMessage;
import websockets.messaging.EnumOperationMessage;
import websockets.messaging.IServerMessageHandler;
import websockets.server.IServerSocket;

import javax.websocket.Session;
import java.util.ArrayList;
import java.util.List;

public class ServerMessageHandler implements IServerMessageHandler {

    private IChatSystemLogic logic;
    private Gson gson = new Gson();
    private IServerSocket serverSocket;
    private ILogger logger = Logger.getInstance();

    public ServerMessageHandler(IChatSystemLogic logic, IServerSocket serverSocket){
        this.logic = logic;
        this.serverSocket = serverSocket;
    }

    @Override
    public void handleMessage(EnumOperationMessage type, String message, Session session) {
        switch (type) {
            case REGISTER:
                register(message, session);
                break;
            case LOGIN:
                login(message, session);
                break;
            case FORGOTMAIL:
                rememberAccount(message, session);
                break;
            case FORGOTPASSWORD:
                forgotPassword(message, session);
                break;
            case RESETPASSWORD:
                resetPassword(message, session);
                break;
            case SENDMSG:
                sendMessage(message, session);
                break;
            case OPENCHAT:
                openChat(message, session);
                break;
            case GETCHATROOMS:
                getChatrooms(message, session);
                break;
            case SEEFRIENDLIST:
                seeFriendList(message, session);
                break;
            case LOGOUT:
                logout(message, session);
                break;
            case LOGOUTBYSESSION:
                logoutBySession(session);
                break;
            case UPDATEPROFILE:
                updateProfile(message, session);
                break;
            case USERPROFILE:
                getUserProfile(message, session);
                break;
            case SEARCH:
                searchUsers(message, session);
                break;
            case ADDFRIEND:
                addFriend(message, session);
                break;
            default:
                break;
        }
    }

    private void register(String data, Session session) {
        User u = gson.fromJson(data, User.class);
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REGISTERFAIL);
        if (logic.register(u.getMail(), u.getUsername(), u.getPassword())) {
            returnMsg = new ChatGenericMessage(EnumOperationMessage.REGISTERSUCCESS);
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void login(String data, Session session) {
        LoginDTO dto = gson.fromJson(data, LoginDTO.class);
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.LOGINFAIL);
        User resultUser = logic.login(dto.getMail(), dto.getPassword());
        if (resultUser.getId() != 0) {
            returnMsg = new ChatGenericMessage(EnumOperationMessage.LOGINSUCCESS);
            returnMsg.setData(gson.toJson(resultUser));
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void forgotPassword(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        ForgotPasswordDTO dto = gson.fromJson(data, ForgotPasswordDTO.class);
        User logicResult = logic.forgotPassword(dto.getMail());
        if(logicResult.getId() != 0){
            returnMsg = new ChatGenericMessage(EnumOperationMessage.FORGOTPASSWORDSUCCESS);
            returnMsg.setData(gson.toJson(logicResult));
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void resetPassword(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        RestResetPasswordDTO resetPasswordDTO = gson.fromJson(data, RestResetPasswordDTO.class);
        if(logic.resetPassword(resetPasswordDTO.getMail(), resetPasswordDTO.getPassword(), resetPasswordDTO.getConfirmPassword())){
            returnMsg = new ChatGenericMessage(EnumOperationMessage.RESETPASSWORDSUCCESS);
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void rememberAccount(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        RememberAccountDTO dto = gson.fromJson(data, RememberAccountDTO.class);
        String resultMail = logic.rememberAccount(dto.getPhone());
        if(resultMail != null){
            returnMsg = new ChatGenericMessage(EnumOperationMessage.FORGOTMAILSUCCESS);
            returnMsg.setData(resultMail);
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void seeFriendList(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        UserAccountDTO dto = gson.fromJson(data, UserAccountDTO.class);
        List<User> onlineList = logic.getUsersOnline(dto, session.getId());
        if(onlineList != null){
            returnMsg.setOperationMessage(EnumOperationMessage.SEEFRIENDLIST);
            returnMsg.setData(gson.toJson(onlineList));
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void logout(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        UserAccountDTO dto = gson.fromJson(data, UserAccountDTO.class);
        if(logic.logout(dto)){
            returnMsg.setOperationMessage(EnumOperationMessage.LOGOUTSUCCESS);
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void logoutBySession(Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        if(logic.logoutBySession(session.getId())){
            returnMsg.setOperationMessage(EnumOperationMessage.LOGOUTSUCCESS);
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void getUserProfile(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        UserAccountDTO dto = gson.fromJson(data, UserAccountDTO.class);
        AccountProfileDTO result = logic.getUserProfile(dto);
        if(result.getUsername() != null){
            returnMsg.setOperationMessage(EnumOperationMessage.USERPROFILE);
            returnMsg.setData(gson.toJson(result));
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void updateProfile(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        AccountProfileDTO dto = gson.fromJson(data, AccountProfileDTO.class);
        if(logic.updateProfile(dto)){
            returnMsg.setOperationMessage(EnumOperationMessage.UPDATEPROFILESUCCESS);
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void openChat(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        OpenChatDTO dto = gson.fromJson(data, OpenChatDTO.class);
        Chatroom result = logic.openChatroom(dto);
        if(result.getUsers() != null){
            returnMsg.setOperationMessage(EnumOperationMessage.OPENCHATSUCCESS);
            returnMsg.setData(gson.toJson(result));
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void sendMessage(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        SendMsgDTO dto = gson.fromJson(data, SendMsgDTO.class);
        SendMsgDTO result = logic.sendPrivateMessage(dto);
        if(result.isSent()){
            returnMsg.setOperationMessage(EnumOperationMessage.SENDMSGSUCCESS);
            returnMsg.setData(gson.toJson(result));
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
        returnMsg.setOperationMessage(EnumOperationMessage.RECEIVEMSG);
        serverSocket.sendToClient(serverSocket.getSessionFromId(logic.getSessionFromUser(dto.getUserThatReceivesId())), gson.toJson(returnMsg));
    }

    private void searchUsers(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        SearchUserDTO dto = gson.fromJson(data, SearchUserDTO.class);
        List<User> userResult = logic.searchUsers(dto);
        if(userResult != null){
            returnMsg.setOperationMessage(EnumOperationMessage.SEARCHRESULT);
            returnMsg.setData(gson.toJson(userResult));
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void addFriend(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        AddFriendDTO dto = gson.fromJson(data, AddFriendDTO.class);
        if(logic.addFriend(dto)){
            returnMsg.setOperationMessage(EnumOperationMessage.ADDFRIENDSUCCESS);
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

    private void getChatrooms(String data, Session session){
        ChatGenericMessage returnMsg = new ChatGenericMessage(EnumOperationMessage.REQUESTFAIL);
        UserAccountDTO dto = gson.fromJson(data, UserAccountDTO.class);
        List<Chatroom> chatrooms = logic.getChatrooms(dto);
        if(chatrooms != null){
            returnMsg.setOperationMessage(EnumOperationMessage.GETCHATROOMSSUCCESS);
            returnMsg.setData(gson.toJson(chatrooms));
        }
        serverSocket.sendToClient(session, gson.toJson(returnMsg));
    }

}
