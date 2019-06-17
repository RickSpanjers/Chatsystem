package repository;

import domain.Channel;
import domain.Role;
import domain.User;

import java.util.List;

public class ChannelRepository {
    private IChannel channelContext;

    public ChannelRepository(IChannel channelContext){
        this.channelContext = channelContext;
    }

    public boolean addChannel(Channel c){
        return this.channelContext.addChannel(c);
    }

    public boolean deleteChannel(Channel c){
        return this.channelContext.deleteChannel(c);
    }

    public boolean updateChannel(Channel c){
        return this.channelContext.updateChannel(c);
    }

    public List<Channel> retrieveAllChannels(){
        return this.channelContext.retrieveAllChannels();
    }

    public Channel getChannelById(int id){
        return this.channelContext.getChannelById(id);
    }

    public boolean addUserToChannel(User u, Channel c){
        return this.channelContext.addUserToChannel(u, c);
    }

    public boolean addRoleToChannel(Role r, Channel c){
        return this.channelContext.addRoleToChannel(r, c);
    }

    public boolean addUserToRole(User u, Role r, Channel c){
        return this.channelContext.addUserToRole(u, r, c);
    }

    public boolean deleteUserFromChannel(User u, Channel c){
        return this.channelContext.deleteUserFromChannel(u, c);
    }

    public boolean deleteRoleFromChannel(Role r, Channel c){
        return this.channelContext.deleteRoleFromChannel(r, c);
    }

    public boolean deleteUserFromRole(User u, Channel c, Role r){return this.channelContext.deleteUserFromRole(u,c,r);}
}
