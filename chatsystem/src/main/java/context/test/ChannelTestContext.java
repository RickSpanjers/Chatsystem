package context.test;

import domain.Channel;
import domain.Role;
import domain.User;
import repository.IChannel;

import java.util.List;

public class ChannelTestContext implements IChannel {
    @Override
    public boolean addChannel(Channel c) {
        return false;
    }

    @Override
    public boolean deleteChannel(Channel c) {
        return false;
    }

    @Override
    public boolean updateChannel(Channel c) {
        return false;
    }

    @Override
    public List<Channel> retrieveAllChannels() {
        return null;
    }

    @Override
    public Channel getChannelById(int id) {
        return null;
    }

    @Override
    public boolean addUserToChannel(User u, Channel c) {
        return false;
    }

    @Override
    public boolean addRoleToChannel(Role r, Channel c) {
        return false;
    }

    @Override
    public boolean addUserToRole(User u, Role r, Channel c) {
        return false;
    }

    @Override
    public boolean deleteUserFromChannel(User u, Channel c) {
        return false;
    }

    @Override
    public boolean deleteRoleFromChannel(Role r, Channel c) {
        return false;
    }

    @Override
    public boolean deleteUserFromRole(User u, Channel c, Role r) {
        return false;
    }
}
