package dto.sockets;

public class LoginDTO {
    private String mail;
    private String password;

    public LoginDTO(String mail, String password){
        this.mail = mail;
        this.password = password;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}
