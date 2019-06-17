package dto.sockets;

public class UserAccountDTO {

    private int userId;
    private String userMail;

    public UserAccountDTO(){

    }

    public UserAccountDTO(int userId, String userMail){
        this.userId = userId;
        this.userMail = userMail;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }
}
