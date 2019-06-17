package dto.sockets;

public class SendMsgDTO {
    private int userThatSendsId;
    private int userThatReceivesId;
    private String message;
    private boolean sent;

    public SendMsgDTO(){

    }

    public SendMsgDTO(int userThatSendsId, int userThatReceivesId, String message, boolean sent){
        this.userThatSendsId = userThatSendsId;
        this.userThatReceivesId = userThatReceivesId;
        this.message = message;
        this.sent = sent;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public int getUserThatReceivesId() {
        return userThatReceivesId;
    }

    public int getUserThatSendsId() {
        return userThatSendsId;
    }

    public void setUserThatReceivesId(int userThatReceivesId) {
        this.userThatReceivesId = userThatReceivesId;
    }

    public void setUserThatSendsId(int userThatSendsId) {
        this.userThatSendsId = userThatSendsId;
    }

    public void setSent(boolean sent) {
        this.sent = sent;
    }

    public boolean isSent() {
        return sent;
    }
}
