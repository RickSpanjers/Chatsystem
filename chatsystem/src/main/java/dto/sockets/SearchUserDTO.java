package dto.sockets;

public class SearchUserDTO {

    private int accountId;
    private String searchText;

    public SearchUserDTO(){

    }

    public SearchUserDTO(String searchText, int accountId){
        this.searchText = searchText;
    }

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getAccountId() {
        return accountId;
    }
}
