package context.mssql;

import domain.User;
import helper.ConnectionHelper;
import logging.ILogger;
import logging.LogLevel;
import logging.Logger;
import repository.IUser;

import java.security.MessageDigest;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class UserMSSQLContext implements IUser {

    private ConnectionHelper helper = new ConnectionHelper();
    private ILogger logger = Logger.getInstance();


    public Boolean register(User u) {
        Boolean result = false;
        List<String> errorList = new ArrayList<>();
        String sql = "INSERT INTO Chat.[user] (Username, Mail, Password) VALUES (?, ?, ?)";
        if (!checkIfMailExists(u)) {
            if (validateUsername(u.getUsername(), errorList) && isValid(u.getMail()) && validatePassword(u.getPassword(), errorList)) {
                try {
                    Connection conn = helper.dbConnect();
                    PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    {
                        pstmt.setString(1, u.getUsername());
                        pstmt.setString(2, u.getMail());
                        pstmt.setString(3, toSHA256(u.getPassword()));
                        pstmt.executeUpdate();
                        result = true;
                    }
                } catch (Exception e) {
                    logger.log(e);
                }

            }
        }
        for (String error : errorList) {
            logger.log(error, LogLevel.INFORMATION);
        }
        return result;
    }


    public Boolean login(String username, String mail, String password) {
        Boolean result = false;
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] WHERE Chat.[user].username = ?");
            myStmt.setString(1, username);
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String uname = myResults.getString("username");
                String upassword = myResults.getString("password");

                if (mail == null) {
                    if (password.equals(upassword)) {
                        result = true;
                    }
                } else {
                    if (username.equals(uname) && password.equals(upassword)) {
                        result = true;
                    }
                }
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    public User getUserById(int id) {
        User result = new User(0, null, null, null);
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[User] WHERE Chat.[User].id = ?");
            myStmt.setInt(1, id);
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String username = myResults.getString("username");
                String mail = myResults.getString("mail");
                String password = myResults.getString("password");
                String firstname = myResults.getString("firstname");
                String lastname = myResults.getString("lastname");
                String address = myResults.getString("address");
                String zipcode = myResults.getString("zipcode");
                String place = myResults.getString("place");
                String phone = myResults.getString("phone");
                result = new User(id, username, mail, password);
                result.setFirstname(firstname);
                result.setLastname(lastname);
                result.setAddress(address);
                result.setZipcode(zipcode);
                result.setPlace(place);
                result.setPhone(phone);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    public User getUserByUsername(String name) {
        User result = new User(0, null, null, null);
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] WHERE Chat.[user].username = ?");
            myStmt.setString(1, name);
            ResultSet myResults = myStmt.executeQuery();
            while (myResults.next()) {
                result = doUserResults(myResults);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    public User getUserByMail(String mail) {
        User result = new User(0, null, null, null);
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] WHERE Chat.[user].mail = ?");
            myStmt.setString(1, mail);
            ResultSet myResults = myStmt.executeQuery();
            while (myResults.next()) {
                result = doUserResults(myResults);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public User getUserByPhone(String phone) {
        User result = new User(0, null, null, null);
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] WHERE Chat.[user].phone = ?");
            myStmt.setString(1, phone);
            ResultSet myResults = myStmt.executeQuery();
            while (myResults.next()) {
                result = doUserResults(myResults);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    private User doUserResults(ResultSet myResults) throws SQLException{
        User result;
        int id = myResults.getInt("id");
        String username = myResults.getString("username");
        String mail = myResults.getString("mail");
        String password = myResults.getString("password");
        String firstname = myResults.getString("firstname");
        String lastname = myResults.getString("lastname");
        String address = myResults.getString("address");
        String zipcode = myResults.getString("zipcode");
        String place = myResults.getString("place");
        String phone = myResults.getString("phone");
        result = new User(id, username, mail, password);
        result.setFirstname(firstname);
        result.setLastname(lastname);
        result.setAddress(address);
        result.setZipcode(zipcode);
        result.setPlace(place);
        result.setPhone(phone);
        return result;
    }


    public Boolean checkIfUsernameExists(User u) {
        boolean result = false;
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] WHERE Chat.[user].username =?");
            myStmt.setString(1, u.getUsername());
            ResultSet myResults = myStmt.executeQuery();
            if (myResults.next()) {
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    public Boolean checkIfMailExists(User u) {
        boolean result = false;
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] WHERE Chat.[user].mail =?");
            myStmt.setString(1, u.getMail());
            ResultSet myResults = myStmt.executeQuery();

            if (myResults.next()) {
                result = true;
            }
        } catch (SQLException e) {
            logger.log(e);
        }
        return result;
    }

    public ArrayList<User> getAllPlayers() {
        ArrayList<User> listOfPlayers = new ArrayList<>();
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user]");
            ResultSet myResults = myStmt.executeQuery();

            while (myResults.next()) {
                String username = myResults.getString("username");
                String mail = myResults.getString("mail");
                String password = myResults.getString("password");
                int id = myResults.getInt("id");
                User u = new User(id, username, mail, password);
                listOfPlayers.add(u);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return listOfPlayers;
    }

    private boolean isValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public String toSHA256(String base) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (Exception ex) {
            throw new IllegalArgumentException(ex);
        }
    }

    @Override
    public Boolean validateUsername(String username, List<String> errorList) {
        boolean result = true;
        if (username.length() < 8) {
            result = false;
            errorList.add("Username length must be at least 8 letters long!");
        }
        return result;
    }

    @Override
    public Boolean validatePassword(String password, List<String> errorList) {
        boolean result = true;
        Pattern specialCharPatten = Pattern.compile("[^a-z0-9 ]", Pattern.CASE_INSENSITIVE);
        Pattern upperCasePatten = Pattern.compile("[A-Z ]");
        Pattern lowerCasePatten = Pattern.compile("[a-z ]");
        Pattern digitCasePatten = Pattern.compile("[0-9 ]");

        if (password.length() < 8) {
            errorList.add("Password length must have at least 8 characters");
            result = false;
        } else if (!specialCharPatten.matcher(password).find()) {
            errorList.add("Password must have at least one special character");
            result = false;
        } else if (!upperCasePatten.matcher(password).find()) {
            errorList.add("Password must have atleast one uppercase character");
            result = false;
        } else if (!lowerCasePatten.matcher(password).find()) {
            errorList.add("Password must have at least one lowercase character");
            result = false;
        } else if (!digitCasePatten.matcher(password).find()) {
            errorList.add("Password must have atleast one digit character");
            result = false;
        }
        return result;
    }

    @Override
    public Boolean addFriend(User user, User befriendedUser) {
        Boolean result = false;
        String sql = "INSERT INTO Chat.[friends] (userId, befriendedUserId) VALUES (?, ?)";
            try {
                Connection conn = helper.dbConnect();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                {
                    if(!isUserAlreadyFriend(user, befriendedUser)){
                        pstmt.setInt(1, user.getId());
                        pstmt.setInt(2, befriendedUser.getId());
                        pstmt.executeUpdate();
                        result = true;
                    }
                }
            } catch (Exception e) {
                logger.log(e);
            }
        return result;
    }

    @Override
    public Boolean removeFriend(User user, User befriendedUser) {
        Boolean result = false;
        String sql = "DELETE FROM Chat.[userrole] WHERE userId = ? AND befriendedUserId = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, befriendedUser.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public Boolean updateProfile(User u) {
        Boolean result = false;
        String sql = "UPDATE Chat.[User] SET username=?, password=?, firstname=?, lastname=?, mail=?, address=?, zipcode=?, place=?, phone=? WHERE ID = ?";
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            {
                pstmt.setString(1, u.getUsername());
                pstmt.setString(2, toSHA256(u.getPassword()));
                pstmt.setString(3, u.getFirstname());
                pstmt.setString(4, u.getLastname());
                pstmt.setString(5, u.getMail());
                pstmt.setString(6, u.getAddress());
                pstmt.setString(7, u.getZipcode());
                pstmt.setString(8, u.getPlace());
                pstmt.setString(9, u.getPhone());
                pstmt.setInt(10, u.getId());
                pstmt.executeUpdate();
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }

    @Override
    public ArrayList<User> searchUsers(User account, String searchString) {
        ArrayList<User> listOfUsers = new ArrayList<User>();
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] WHERE Chat.[user].username LIKE ?+'%';  ");
            myStmt.setString(1, searchString);
            ResultSet myResults = myStmt.executeQuery();
            while (myResults.next()) {
                String username = myResults.getString("username");
                String mail = myResults.getString("mail");
                int id = myResults.getInt("id");
                User u = new User(username, mail);
                u.setId(id);
                if(!isUserAlreadyFriend(account, u)){
                    listOfUsers.add(u);
                }
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return listOfUsers;
    }

    @Override
    public List<User> getFriends(User u) {
        ArrayList<User> listOfUsers = new ArrayList<User>();
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[user] AS u INNER JOIN Chat.[friends] as UF on U.ID = UF.befriendedUserId WHERE UF.userId = ?");
            myStmt.setInt(1, u.getId());
            ResultSet myResults = myStmt.executeQuery();
            while (myResults.next()) {
                String username = myResults.getString("username");
                String mail = myResults.getString("mail");
                int id = myResults.getInt("id");
                User friend = new User(username, mail);
                friend.setId(id);
                listOfUsers.add(friend);
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return listOfUsers;
    }

    private boolean isUserAlreadyFriend(User userThatSearches, User potentialFriend){
        boolean result = false;
        try {
            Connection conn = helper.dbConnect();
            PreparedStatement myStmt = conn.prepareStatement("SELECT * FROM Chat.[friends] WHERE Chat.[friends].userId = ? AND Chat.[friends].befriendedUserId = ?");
            myStmt.setInt(1, userThatSearches.getId());
            myStmt.setInt(2, potentialFriend.getId());
            ResultSet myResults = myStmt.executeQuery();
            while (myResults.next()) {
                result = true;
            }
        } catch (Exception e) {
            logger.log(e);
        }
        return result;
    }
}
