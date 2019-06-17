package dto.rest;

public class RestResetPasswordDTO {
    private String mail;
    private String password;
    private String confirmPassword;

    public RestResetPasswordDTO(String mail, String password, String confirmPassword){
        this.mail = mail;
        this.password = password;
        this.confirmPassword = confirmPassword;
    }

    public RestResetPasswordDTO(){

    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
