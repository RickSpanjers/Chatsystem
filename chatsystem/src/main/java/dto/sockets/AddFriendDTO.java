package dto.sockets;

import domain.User;

public class AddFriendDTO {
    private int userToGainFriend;
    private int userToBeFriend;

    public AddFriendDTO(int userToGainFriend, int userToBeFriend){
       this.userToGainFriend = userToGainFriend;
       this.userToBeFriend = userToBeFriend;
    }

    public int getUserToBeFriend() {
        return userToBeFriend;
    }

    public int getUserToGainFriend() {
        return userToGainFriend;
    }

    public void setUserToBeFriend(int userToBeFriend) {
        this.userToBeFriend = userToBeFriend;
    }

    public void setUserToGainFriend(int userToGainFriend) {
        this.userToGainFriend = userToGainFriend;
    }
}
