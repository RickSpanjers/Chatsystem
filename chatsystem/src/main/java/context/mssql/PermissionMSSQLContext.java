package context.mssql;

import domain.Permission;
import helper.ConnectionHelper;
import logging.ILogger;
import logging.Logger;
import repository.IPermission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PermissionMSSQLContext implements IPermission {

    private ConnectionHelper helper = new ConnectionHelper();
    private ILogger logger = Logger.getInstance();

    @Override
    public boolean addPermission(Permission p) {
        Boolean result = false;
        String sql = "INSERT INTO Chat.[permission] (name, description) VALUES (?, ?)";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setString(1, p.getName());
                pstmt.setString(3, p.getDescription());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean deletePermission(Permission p) {
        Boolean result = false;
        String sql = "DELETE FROM Chat.[channel] WHERE ID = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, p.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public boolean updatePermission(Permission p) {
        Boolean result = false;
        String sql = "UPDATE Chat.[Permission] SET name=?, description=? WHERE ID = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setString(1, p.getName());
                pstmt.setString(2, p.getDescription());
                pstmt.setInt(3, p.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public List<Permission> retrieveAllPermissions() {
        ArrayList<Permission> listOfPermissions = new ArrayList<>();
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[permission]");
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String name = myResults.getString("name");
                String description = myResults.getString("description");
                int id = myResults.getInt("id");
                Permission p = new Permission(name, description);
                p.setId(id);
                listOfPermissions.add(p);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return listOfPermissions;
    }

    @Override
    public Permission getPermissionById(int id) {
        Permission result = new Permission(null);
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[permission] WHERE Chat.[permission].id = ?");
            myStmt.setInt(1, id);
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String name = myResults.getString("name");
                String description = myResults.getString("description");
                result = new Permission(name);
                result.setDescription(description);
                result.setId(id);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }
}
