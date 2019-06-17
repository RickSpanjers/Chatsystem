package dto.rest;

public class RestForgotMailDTO {
    private String phone;

    public RestForgotMailDTO(){}

    public RestForgotMailDTO(String phone) {
        this.phone = phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }
}
