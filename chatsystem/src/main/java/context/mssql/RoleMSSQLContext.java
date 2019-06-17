package context.mssql;

import domain.Permission;
import domain.Role;
import helper.ConnectionHelper;
import logging.ILogger;
import logging.Logger;
import repository.IRole;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RoleMSSQLContext implements IRole {

    private ConnectionHelper helper = new ConnectionHelper();
    private ILogger logger = Logger.getInstance();

    @Override
    public boolean addRole(Role r) {
        Boolean result = false;
        String sql = "INSERT INTO Chat.[Role] (name, description) VALUES (?, ?)";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setString(1, r.getName());
                pstmt.setString(3, r.getDescription());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean deleteRole(Role r) {
        Boolean result = false;
        String sql = "DELETE FROM Chat.[permission] WHERE ID = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, r.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean updateRole(Role r) {
        Boolean result = false;
        String sql = "UPDATE Chat.[Role] SET name=?, description=? WHERE ID = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setString(1, r.getName());
                pstmt.setString(2, r.getDescription());
                pstmt.setInt(3, r.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public List<Role> retrieveAllRoles() {
        ArrayList<Role> listOfRoles = new ArrayList<>();
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[Role]");
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String name = myResults.getString("name");
                String description = myResults.getString("description");
                int id = myResults.getInt("id");
                Role r = new Role(name, description);
                r.setId(id);
                listOfRoles.add(r);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return listOfRoles;
    }

    @Override
    public Role getRoleById(int id) {
        Role result = new Role(null);
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[Role] WHERE Chat.[Role].id = ?");
            myStmt.setInt(1, id);
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String name = myResults.getString("name");
                String description = myResults.getString("description");
                result = new Role(name);
                result.setDescription(description);
                result.setId(id);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean addPermissionToRole(Permission p, Role r) {
        Boolean result = false;
        String sql = "INSERT INTO Chat.[rolepermission] (roleId, permissionId) VALUES (?, ?)";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, r.getId());
                pstmt.setInt(2, p.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean deletePermissionFromRole(Permission p, Role r) {
        Boolean result = false;
        String sql = "DELETE FROM Chat.[rolepermission] WHERE permissionId = ? AND roleId = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, p.getId());
                pstmt.setInt(2, r.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }
}
