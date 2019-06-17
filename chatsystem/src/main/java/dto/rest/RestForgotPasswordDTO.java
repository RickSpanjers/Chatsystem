package dto.rest;

public class RestForgotPasswordDTO {

    private String mail;

    public RestForgotPasswordDTO(){}

    public RestForgotPasswordDTO(String mail) {
        this.mail = mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getMail() {
        return mail;
    }
}
