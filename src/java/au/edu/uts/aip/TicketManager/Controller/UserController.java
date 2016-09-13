/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.TicketManager.Controller;

import au.edu.uts.aip.TicketManager.DAO.UserDAO;
import au.edu.uts.aip.TicketManager.DTO.UserDTO;
import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;
import javax.enterprise.context.*;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.*;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * This is a controller of the user including some logical control of user
 * account like login, logout and loading profile
 *
 * @author Jianhao_LI
 */
@Named
@SessionScoped
public class UserController implements Serializable {

    private static String userID = null;
    private UserDTO user = new UserDTO();

    /**
     * this method can create a new UserDTO instance and pass to user.
     */
    public void initial() {
        user = new UserDTO();
    }

    /**
     * this method return the user object
     *
     * @return user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * this method is used to user id. it can only be used when loading user
     * profile.
     *
     * @param userID
     */
    public static void setUserID(String userID) {
        UserController.userID = userID;
    }

    /**
     * this method is used to return the userID
     *
     * @return userID
     */
    public static String getUserID() {
        return userID;
    }

    /**
     * the function of this method cab ensure user going back to login page or new account register page.
     * @return it re-directs the page to ticket list page
     */
    public String loginStatus() {
        if (userID != null) {
            return "secret/ticketlist?faces-redirect=true";
        } else {
            return null;
        }
    }

    /**
     * this method is used to login and redirect the page to ticket list page
     *
     * @return the page name that is going to load
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     *
     */
    public String login() throws SQLException, NamingException {
//        System.out.println("userEmail:" + user.getEmail() + "password:" + user.getPassword());
        return UserDAO.login(user.getEmail(), user.getPassword());
//        if (ID == null) {
//            showErrorMessage("Email or password is incorrect");
//            return null;
//        } else {
//            UserController.setUserID(ID);
//            System.out.print("User id is: "+userID);
//            return "ticketlist?faces-redirect=true";
//        } 
    }

    /**
     * this method help user update their password
     *
     * @return redirect the page to profile
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     * @throws NoSuchAlgorithmException the exception will called when the
     * algorithm not exist
     */
    public String changePassword() throws NamingException, SQLException, NoSuchAlgorithmException {
        if (!user.getPassword().equals(user.getRe_password())) {
            showErrorMessage("The two new password is not same");
            return null;
        }
        UserDAO.updatePassword(user);
        return "profile?faces-redirect=true";
    }

    /**
     * this method is used in registering user. it navigates the user to
     * different step of new account registration
     *
     * @param step used to indicate which step is going to finish
     * @return return the page of different step or the ticket list page if new
     * account has been registered successfully
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     * @throws IOException the exception will be called that there has illegal
     * input.
     * @throws NoSuchAlgorithmException the exception will called when the
     * algorithm not exist
     */
    public String next(int step) throws SQLException, NamingException, IOException, NoSuchAlgorithmException {
        switch (step) {
            case 2:
                if (!UserDAO.checkEmail(user.getEmail())) {
                    user.setEmail(" ");
                    showErrorMessage("The email has been registered");
                    return null;
                } else if (!user.getPassword().equals(user.getRe_password())) {
                    showErrorMessage("The two passwords input is not the same");
                    return null;
                } else {
                    return "personalinfo?faces-redirect=true";
                }
            case 3:
                return "contactinfo?faces-redirect=true";
            case 4:
                return UserDAO.signUp(user);
            default:
                return "login?faces-redirect=true";
        }
    }

    /**
     * this method is used to load user when the user is viewing profile
     *
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     */
    public void loadUser() throws NamingException, SQLException {
        System.out.println("loading user");
        user = new UserDTO();
        user = UserDAO.loadUser(userID);
    }

    /**
     * this method will be call when user click log out
     *
     * @return login page
     */
    public String logout() {
        System.out.println("user log out, user id is :" + UserController.getUserID());
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        try {
            request.logout();
        } catch (ServletException e) {
            // (you could also log the exception to the server log)
            context.addMessage(null, new FacesMessage(e.getMessage()));
        }
        UserController.setUserID(null);
        user = new UserDTO();
        return "/login?faces-redirect=true";
    }

    /**
     * this method is used to update the information of the user
     *
     * @return go back to page profile
     * @throws SQLException when the SQL access is illegal, exception will
     * called
     * @throws NamingException when user input illegal email or password to find
     * the javadb, exception will be called
     * @throws IOException the exception will be called that there has illegal
     * input.
     */
    public String updateUser() throws NamingException, SQLException, IOException {
//        System.out.println(user.getDob());
        UserDAO.updateUser(user);
        return "profile?faces-redirect=true";
    }

    /**
     * the method is used to show error message when there has error happened
     *
     * @param msg the message that will be displayed when error happened
     */
    public static void showErrorMessage(String msg) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(msg));
    }
}
