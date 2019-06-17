package repository;

import domain.Channel;
import domain.Role;
import domain.User;

import java.util.List;

public interface IChannel {
    boolean addChannel(Channel c);
    boolean deleteChannel(Channel c);
    boolean updateChannel(Channel c);
    List<Channel> retrieveAllChannels();
    Channel getChannelById(int id);
    boolean addUserToChannel(User u, Channel c);
    boolean addRoleToChannel(Role r, Channel c);
    boolean addUserToRole(User u, Role r, Channel c);
    boolean deleteUserFromChannel(User u, Channel c);
    boolean deleteRoleFromChannel(Role r, Channel c);
    boolean deleteUserFromRole(User u, Channel c, Role r);
}
