package dto.sockets;

public class ForgotPasswordDTO {
    private String mail;

    public ForgotPasswordDTO(String mail){
        this.mail = mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
