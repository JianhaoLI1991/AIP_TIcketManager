/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.TicketManager.DAO;

import au.edu.uts.aip.TicketManager.DTO.UserDTO;
import au.edu.uts.aip.TicketManager.Security.Sha;
import au.edu.uts.aip.TicketManager.Controller.UserController;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import javax.faces.context.FacesContext;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.sql.*;

/**
 * this class is UserDAO that used to connect and CRUD(create, read, update,
 * delete) data with table userinfo in javadb
 *
 * @author Jianhao_LI
 */
public class UserDAO {

    /**
     * this method will be called when user login to her/his account, and check
     * if the input email and password are correct
     *
     * @param email the user input email
     * @param psw the user input password
     * @return it will return userID if user log in successfully, otherwise
     * return null
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     */
    public static String login(String email, String psw) throws SQLException, NamingException {
//         your code goes here
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.login(email, psw);
        } catch (ServletException e) {
            UserController.showErrorMessage("Incorrect username or password");
            e.printStackTrace();
            return null;
        }
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("select * from userinfo  where email = '" + email + "'");
        ResultSet rs = ps.executeQuery();
//        System.out.print(rs.getString("userid"));
        rs.next();
        UserController.setUserID(rs.getString("userid"));
//        System.out.println("rs.getString(\"userid\")"+rs.getString("userid"));
        conn.close();
        return "secret/ticketlist?faces-redirect=true";
    }

    /**
     * This method can check if the email has been registered or not
     *
     * @param email the email that used for the new account
     * @return return false if the email has been registered, otherwise return
     * true
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     */
    public static boolean checkEmail(String email) throws SQLException, NamingException {
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("select * from USERINFO  where email = '" + email + "'");
        ResultSet rs = ps.executeQuery();
        conn.close();
        if (rs.next()) {
            return false;
        } else {
            return true;
        }
    }

    /**
     *
     * the method is used to register a new account and store the user
     * information into javadb
     *
     * @param user the information of the new user
     * @return page name that navigates to ticket list page after the user
     * account has been stored into the database
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     * @throws IOException the exception will be called that there has illegal
     * input.
     * @throws NoSuchAlgorithmException the exception will called when the
     * algorithm not exist
     */
    public static String signUp(UserDTO user) throws NamingException, SQLException, IOException, NoSuchAlgorithmException {
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("insert into userinfo (email,password,firstname,lastname,dob,city,state,postcode,address,phone,image) values (?,?,?,?,?,?,?,?,?,?,?)");
        ps.setString(1, user.getEmail());
        ps.setString(2, Sha.hash256(user.getPassword()));
        ps.setString(3, user.getFirstName());
        ps.setString(4, user.getLastName());
        ps.setDate(5, new java.sql.Date(user.getDob().getTime()));
        ps.setString(6, user.getCity());
        ps.setString(7, user.getState());
        ps.setString(8, user.getPostcode());
        ps.setString(9, user.getAddress());
        ps.setString(10, user.getPhone());
        ps.setBinaryStream(11, user.getImage().getInputStream(), (int) user.getImage().getSize());
        ps.executeUpdate();
        ps = conn.prepareStatement("select userid from userinfo where email='" + user.getEmail() + "'");
        ResultSet rs = ps.executeQuery();
        rs.next();
        UserController.setUserID(rs.getString("userid"));
        conn.close();
        UserDAO.login(user.getEmail(), user.getPassword());
        return "secret/ticketlist?faces-redirect=true";
    }

    /**
     * the method will find the user and return all information of the user
     *
     * @param id used to set condition that indicates which user's information
     * is going to find
     * @return the userDTO
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     */
    public static UserDTO loadUser(String id) throws NamingException, SQLException {
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("select * from USERINFO  where userid =" + Integer.parseInt(id));
        ResultSet rs = ps.executeQuery();
        conn.close();
        UserDTO user = new UserDTO();
        while (rs.next()) {
            user.setUserID(rs.getString("userid"));
            user.setEmail(rs.getString("email"));
            user.setPassword(rs.getString("password"));
            user.setRe_password(rs.getString("password"));
            user.setFirstName(rs.getString("firstname"));
            user.setLastName(rs.getString("lastname"));
            user.setDob(rs.getDate("dob"));
            user.setCity(rs.getString("city"));
            user.setState(rs.getString("state"));
            user.setPostcode(rs.getString("postcode"));
            user.setAddress(rs.getString("address"));
            user.setPhone(rs.getString("phone"));
            user.setImageLoad(rs.getBytes("image"));
        }
        System.out.print(user);
        return user;
    }

    /**
     * This method will be called when user save their profile edit
     *
     * @param user the updated user information
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     * @throws IOException the exception will be called that there has illegal
     * input.
     */
    public static void updateUser(UserDTO user) throws NamingException, SQLException, IOException {
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        if (user.getImage() != null) {
            ps = conn.prepareStatement("update userinfo set email=?, firstname=?,lastname=?,dob=?, city=?, state=?, postcode=?, address=?, phone=?, image=? where userid=?");
        } else {
            ps = conn.prepareStatement("update userinfo set email=?, firstname=?,lastname=?,dob=?, city=?, state=?, postcode=?, address=?, phone=? where userid=?");
        }
        ps.setString(1, user.getEmail());
//        ps.setString(2, user.getPassword());
        ps.setString(2, user.getFirstName());
        ps.setString(3, user.getLastName());
        ps.setDate(4, new java.sql.Date(user.getDob().getTime()));
        ps.setString(5, user.getCity());
        ps.setString(6, user.getState());
        ps.setString(7, user.getPostcode());
        ps.setString(8, user.getAddress());
        ps.setString(9, user.getPhone());
        if (user.getImage() != null) {
            ps.setBinaryStream(10, user.getImage().getInputStream(), (int) user.getImage().getSize());
            ps.setInt(11, Integer.parseInt(user.getUserID()));
        } else {
            ps.setInt(10, Integer.parseInt(user.getUserID()));
        }
        ps.executeUpdate();
        conn.close();
    }

    /**
     * this method is used to update the new password. it will encrypt the
     * password that the user input using SHA-256 algorithm and then update the
     * new encrypted password to data base
     *
     * @param user the user that provides userid to determine which user's password is going to update and the new password for updating.
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     * @throws NoSuchAlgorithmException the exception will called when the
     * algorithm not exist
     */
    public static void updatePassword(UserDTO user) throws NamingException, SQLException, NoSuchAlgorithmException {
        DataSource ds = (DataSource) InitialContext.doLookup("jdbc/aip");
        Connection conn = ds.getConnection();
        PreparedStatement ps;
        ps = conn.prepareStatement("update userinfo set password = ? where userid = ?");
        ps.setString(1, Sha.hash256(user.getPassword()));
        ps.setString(2, user.getUserID());
        ps.executeUpdate();
        conn.close();

    }
}
