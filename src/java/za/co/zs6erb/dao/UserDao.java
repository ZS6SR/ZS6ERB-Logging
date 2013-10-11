/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package za.co.zs6erb.dao;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import za.co.zs6erb.model.User;
import za.co.zs6erb.util.DbUtil;

/**
 *
 * @author SeanR
 */
public class UserDao {

    private Connection connection;

    public UserDao() {
        connection = DbUtil.getConnection();
    }

    public void addUser(User usr) {
        
        byte[] salt = null;
        byte[] pwd = null;
        try {
            salt = generateSalt();
            pwd = getEncryptedPassword(usr.getpwd() ,salt);
            
            System.out.println("salt:" + za.co.zs6erb.util.Base64.encodeBytes(salt) + " pwd:" + za.co.zs6erb.util.Base64.encodeBytes(pwd));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("**ERROR - " + ex.getMessage());
        } catch (InvalidKeySpecException kex) {
            System.out.println("**ERROR - " + kex.getMessage());
        }
        
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("insert into users(callsign, firstname, lastname, pwd, pwd_) values (?,?,?,?,?)");
            // Parameters start with 1
            preparedStatement.setString(1, usr.getcallSign());
            preparedStatement.setString(2, usr.getfirstName());
            preparedStatement.setString(3, usr.getlastName());
            preparedStatement.setString(4, za.co.zs6erb.util.Base64.encodeBytes(pwd));
            preparedStatement.setString(5, za.co.zs6erb.util.Base64.encodeBytes(salt));
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteUser(int usrId) {
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("delete from users where user_id=?");
            // Parameters start with 1
            preparedStatement.setInt(1, usrId);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateUser(User usr) {
        
        byte[] salt = null;
        byte[] pwd = null;
        try {
            salt = generateSalt();
            pwd = getEncryptedPassword(usr.getpwd() ,salt);
            
            System.out.println("salt:" + za.co.zs6erb.util.Base64.encodeBytes(salt) + " pwd:" + za.co.zs6erb.util.Base64.encodeBytes(pwd));
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("**ERROR - " + ex.getMessage());
        } catch (InvalidKeySpecException kex) {
            System.out.println("**ERROR - " + kex.getMessage());
        }
        
        try {
            PreparedStatement preparedStatement = connection
                    .prepareStatement("update users set callsign=?, firstname=?, lastname=?, pwd=?, pwd_=? where user_id=?");
            // Parameters start with 1
            preparedStatement.setString(1, usr.getcallSign());
            preparedStatement.setString(2, usr.getfirstName());
            preparedStatement.setString(3, usr.getlastName());
            preparedStatement.setString(4, za.co.zs6erb.util.Base64.encodeBytes(pwd));
            preparedStatement.setString(5, za.co.zs6erb.util.Base64.encodeBytes(salt));
            preparedStatement.setInt(6, usr.getID());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> usrList = new ArrayList<User>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("select * from users order by callsign");
            while (rs.next()) {
                User usr = new User();
                usr.setID(rs.getInt("user_id"));
                usr.setcallSign(rs.getString("callsign"));
                usr.setfirstName(rs.getString("firstname"));
                usr.setlastName(rs.getString("lastname"));
                usr.setrole(rs.getInt("role"));
                usrList.add(usr);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usrList;
    }

    public User getUserById(int usrid) {
        User usr = new User();
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select * from users where user_id=?");
            preparedStatement.setInt(1, usrid);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                usr.setID(rs.getInt("user_id"));
                usr.setcallSign(rs.getString("callsign"));
                usr.setfirstName(rs.getString("firstname"));
                usr.setlastName(rs.getString("lastname"));
                usr.setpwd(rs.getString("pwd"));
                usr.setrole(rs.getInt("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return usr;
    }

    public int checkUser(String usr, String pwd) {
        int userId = 0;
        String pwdDb, salt;
        int authenticated = 0;

        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select user_id,pwd,pwd_ from users where callsign=?");
            preparedStatement.setString(1, usr.toUpperCase());
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                userId = rs.getInt("user_id");
                pwdDb = rs.getString("pwd");
                salt = rs.getString("pwd_");
                try {
                    if (authenticate(pwd, za.co.zs6erb.util.Base64.decode(pwdDb), za.co.zs6erb.util.Base64.decode(salt))) {
                        authenticated = userId;
                    }
                } catch (NoSuchAlgorithmException ex) {
                    System.out.println("**ERROR - Unable to Authenticate " + ex.getMessage());
                } catch (InvalidKeySpecException ex) {
                    System.out.println("**ERROR - Unable to Authenticate " + ex.getMessage());
                } catch (IOException ex) {
                    System.out.println("**ERROR - Unable to Authenticate " + ex.getMessage());
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authenticated;
    }

    public int checkGroup(int uId) {
        int userGroup = 0;
        try {
            PreparedStatement preparedStatement = connection.
                    prepareStatement("select role from users where user_id=?");
            preparedStatement.setInt(1, uId);
            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                userGroup = rs.getInt("role");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userGroup;
    }

    private byte[] generateSalt() throws NoSuchAlgorithmException {
        // VERY important to use SecureRandom instead of just Random
        SecureRandom random = SecureRandom.getInstance("SHA1PRNG");

        // Generate a 8 byte (64 bit) salt as recommended by RSA PKCS5
        byte[] salt = new byte[8];
        random.nextBytes(salt);
        //System.out.println("SALT = " + za.co.zs6erb.util.Base64.encodeBytes(salt));
        return salt;
    }

    private byte[] getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {
        // PBKDF2 with SHA-1 as the hashing algorithm. Note that the NIST
        // specifically names SHA-1 as an acceptable hashing algorithm for PBKDF2
        String algorithm = "PBKDF2WithHmacSHA1";
        // SHA-1 generates 160 bit hashes, so that's what makes sense here
        int derivedKeyLength = 160;
        // Pick an iteration count that works for you. The NIST recommends at
        // least 1,000 iterations:
        // http://csrc.nist.gov/publications/nistpubs/800-132/nist-sp800-132.pdf
        // iOS 4.x reportedly uses 10,000:
        // http://blog.crackpassword.com/2010/09/smartphone-forensics-cracking-blackberry-backup-passwords/
        int iterations = 20000;

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, derivedKeyLength);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);
        //System.out.println("PASSWORD = " + za.co.zs6erb.util.Base64.encodeBytes(f.generateSecret(spec).getEncoded()));
        return f.generateSecret(spec).getEncoded();
    }

    private boolean authenticate(String attemptedPassword, byte[] encryptedPassword, byte[] salt)
            throws NoSuchAlgorithmException, InvalidKeySpecException {
        // Encrypt the clear-text password using the same salt that was used to
        // encrypt the original password
        byte[] encryptedAttemptedPassword = getEncryptedPassword(attemptedPassword, salt);

        // Authentication succeeds if encrypted password that the user entered
        // is equal to the stored hash
        return Arrays.equals(encryptedPassword, encryptedAttemptedPassword);
    }
}
