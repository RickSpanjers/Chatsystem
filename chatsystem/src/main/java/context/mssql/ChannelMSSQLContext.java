package context.mssql;

import domain.Channel;
import domain.Role;
import domain.User;
import helper.ConnectionHelper;
import logging.ILogger;
import logging.Logger;
import repository.IChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ChannelMSSQLContext implements IChannel {

    private ConnectionHelper helper = new ConnectionHelper();
    private ILogger logger = Logger.getInstance();

    @Override
    public boolean addChannel(Channel c) {
        Boolean result = false;
        String sql = "INSERT INTO Chat.[channel] (name, maxusers, description, welcomemessage, favicon) VALUES (?, ?, ?, ?, ?)";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setString(1, c.getName());
                pstmt.setInt(2, c.getMaxusers());
                pstmt.setString(3, c.getDescription());
                pstmt.setString(4, c.getWelcomemessage());
                pstmt.setString(5, c.getFavicon());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean deleteChannel(Channel c) {
        Boolean result = false;
        String sql = "DELETE FROM Chat.[channel] WHERE ID = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, c.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean updateChannel(Channel c) {
        Boolean result = false;
        String sql = "UPDATE Chat.[Channel SET name=?, maxusers=?, description=?, welcomemessage=?, favicon=? WHERE ID = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setString(1, c.getName());
                pstmt.setInt(2, c.getMaxusers());
                pstmt.setString(3, c.getDescription());
                pstmt.setString(4, c.getWelcomemessage());
                pstmt.setString(5, c.getFavicon());
                pstmt.setInt(6, c.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public List<Channel> retrieveAllChannels() {
        ArrayList<Channel> listOfChannels = new ArrayList<>();
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[channel]");
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String name = myResults.getString("name");
                int maxusers = myResults.getInt("maxusers");
                String description = myResults.getString("description");
                int id = myResults.getInt("id");
                Channel c = new Channel(name);
                c.setDescription(description);
                c.setId(id);
                c.setMaxusers(maxusers);
                listOfChannels.add(c);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return listOfChannels;
    }

    @Override
    public Channel getChannelById(int id) {
        Channel result = new Channel(null);
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] WHERE Chat.[user].id = ?");
            myStmt.setInt(1, id);
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String name = myResults.getString("name");
                int maxusers = myResults.getInt("maxusers");
                String description = myResults.getString("description");
                result = new Channel(name);
                result.setDescription(description);
                result.setMaxusers(maxusers);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean addUserToChannel(User u, Channel c) {
        Boolean result = false;
        String sql = "INSERT INTO Chat.[userchannel] (userId, channelId) VALUES (?, ?)";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, u.getId());
                pstmt.setInt(2, c.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean addRoleToChannel(Role r, Channel c) {
        Boolean result = false;
        String sql = "INSERT INTO Chat.[channelRole] (channelId, roleId) VALUES (?, ?)";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, c.getId());
                pstmt.setInt(2, r.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean addUserToRole(User u, Role r, Channel c) {
        Boolean result = false;
        String sql = "INSERT INTO Chat.[userchannelrole] (userId, roleId, channelId) VALUES (?, ?, ?)";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, u.getId());
                pstmt.setInt(2, r.getId());
                pstmt.setInt(3, c.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean deleteUserFromChannel(User u, Channel c) {
        Boolean result = false;
        String sql = "DELETE FROM Chat.[userChannel] WHERE userId = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, u.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean deleteRoleFromChannel(Role r, Channel c) {
        Boolean result = false;
        String sql = "DELETE FROM Chat.[channelrole] WHERE roleId = ? AND channelId = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, r.getId());
                pstmt.setInt(2, c.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean deleteUserFromRole(User u, Channel c, Role r) {
        Boolean result = false;
        String sql = "DELETE FROM Chat.[userchannelrole] WHERE roleId = ? AND channelId = ? AND userId = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, r.getId());
                pstmt.setInt(2, c.getId());
                pstmt.setInt(3, u.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }
}
