package dto.sockets;

public class RememberAccountDTO {

    private String phone;

    public RememberAccountDTO(){

    }

    public RememberAccountDTO(String phone){
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
