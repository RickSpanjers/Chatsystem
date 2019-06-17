package dto.rest;

public class RestResultDTO {
    private boolean success;
    private String requestName;
    private String data;

    public RestResultDTO(){

    }

    public RestResultDTO(boolean success, String requestName){
        this.success = success;
        this.requestName = requestName;
    }

    public String getRequestName() {
        return requestName;
    }

    public void setRequestName(String requestName) {
        this.requestName = requestName;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public boolean getSuccess(){
        return success;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
