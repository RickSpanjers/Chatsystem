package dto.sockets;

import domain.Channel;
import domain.User;

public class AddFriendToChannelDTO {
    private Channel channel;
    private User friend;

    public AddFriendToChannelDTO(Channel c, User u){
        this.channel = c;
        this.friend = u;
    }

    public Channel getChannel() {
        return channel;
    }

    public void setChannel(Channel channel) {
        this.channel = channel;
    }

    public User getFriend() {
        return friend;
    }

    public void setFriend(User friend) {
        this.friend = friend;
    }
}
