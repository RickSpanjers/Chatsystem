package websockets;

public class ClientMessageGenerator {

//    private IClientSocket clientSocket;
//    private Gson gson = new Gson();
//
//    public ClientMessageGenerator(IClientSocket socket) {
//        this.clientSocket = socket;
//    }
//
//
//    @Override
//    public void register(String username, String mail, String password) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.REGISTER);
//        User u = new User(0, username, mail, password);
//        msg.setData(gson.toJson(u));
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//    }
//
//    @Override
//    public void login(String mail, String password) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.LOGIN);
//        LoginDTO dto = new LoginDTO(mail, password);
//        msg.setData(gson.toJson(dto));
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//    }
//
//    @Override
//    public void forgotPassword(String mail) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.FORGOTPASSWORD);
//        msg.setData(mail);
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//    }
//
//    @Override
//    public void resetPassword(User u) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.RESETPASSWORD);
//        msg.setData(gson.toJson(u));
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//    }
//
//    @Override
//    public void updateProfile(User u) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.UPDATEPROFILE);
//        msg.setData(gson.toJson(u));
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//    }
//
//    @Override
//    public void addFriend(User u, User userToBefriend) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.UPDATEPROFILE);
//        AddFriendDTO dto = new AddFriendDTO(u, userToBefriend);
//        msg.setData(gson.toJson(dto));
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//    }
//
//    @Override
//    public void sendPrivateMessage(Message m, User u) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.SENDMSG);
//        SendMsgDTO dto = new SendMsgDTO(u, m);
//        msg.setData(gson.toJson(dto));
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//
//    }
//
//    @Override
//    public void createChannel(String name) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.CREATECHANNEL);
//        msg.setData(name);
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//    }
//
//    @Override
//    public void addFriendToChannel(User u, Channel c) {
//        ChatGenericMessage msg = new ChatGenericMessage(EnumOperationMessage.CREATECHANNEL);
//        AddFriendToChannelDTO dto = new AddFriendToChannelDTO(c, u);
//        msg.setData(gson.toJson(dto));
//        String json = gson.toJson(msg);
//        clientSocket.send(json);
//    }


}
