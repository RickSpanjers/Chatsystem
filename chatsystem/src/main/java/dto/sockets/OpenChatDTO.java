package dto.sockets;

public class OpenChatDTO {
    private int accountId;
    private int friendUserId;
    private String friendUserMail;

    public OpenChatDTO(){

    }

    public OpenChatDTO(int accountId, int friendUserId, String friendUserMail){
        this.accountId = accountId;
        this.friendUserId = friendUserId;
        this.friendUserMail = friendUserMail;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getFriendUserId() {
        return friendUserId;
    }

    public String getFriendUserMail() {
        return friendUserMail;
    }

    public void setFriendUserId(int friendUserId) {
        this.friendUserId = friendUserId;
    }

    public void setFriendUserMail(String friendUserMail) {
        this.friendUserMail = friendUserMail;
    }
}
