/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.TicketManager.DTO;
import java.util.Date;
import javax.servlet.http.Part;
import javax.validation.constraints.NotNull;

/**
 *The class User stores the detail information of our customers
 * @author Jianhao_LI
 */
public class UserDTO {
    private String UserID;
    private String email;
    private String password;
    private String re_password;
    private String firstName;
    private String lastName;
    private Date dob;
    private String city;
    private String state;
    private String postcode;
    private String address;
    private String phone;
    private Part image;
    private byte[] imageLoad;

    /**
     * the method will return the image in binary
     *
     * @return the image file
     */
    public byte[] getImageLoad() {
        return imageLoad;
    }
    /**
     * the method enable user to store and upload their image for later user
     *
     * @param imageLoad provided to save image file in binary, but it can be null if user
     * decided not to upload their image
     */
    public void setImageLoad(byte[] imageLoad) {
        this.imageLoad = imageLoad;
    }

//    /**
//     * no params required instance constructor
//     */
//    public User() {
//    }
//    /**
//     * the constructor provide a way to initial a new instance of user. all params except UserID is required to call the construction mehod
//     * @param email
//     * @param password
//     * @param firstName
//     * @param lastName
//     * @param dob
//     * @param city
//     * @param state
//     * @param postcode
//     * @param address
//     * @param phone
//     * @param image 
//     */
//    public User(String email, String password, String firstName, String lastName, Date dob, String city, String state, String postcode, String address, String phone, File image) {
//        this.email = email;
//        this.password = password;
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.dob = dob;
//        this.city = city;
//        this.state = state;
//        this.postcode = postcode;
//        this.address = address;
//        this.phone = phone;
//        this.image = image;
//        
//    }
    
    /**
     * the method will return the image and show it in web page
     * @return the image file or null if Image is not provided
     */
    public Part getImage() {
        return image;
    }
    /**
     * the method enable user to store and upload their image for later user
     * @param image provided to save image file, but it can be null if user decided not 
     * to upload their image
     */
    public void setImage(Part image) {
        this.image = image;
    }

    /**
     * the method will return the user ID 
     * @return UserID
     */
    public String getUserID() {
        return UserID;
    }
    /**
     * this method can be user to setup or configure user ID. it cannot be null, otherwise
     * exception of IllegalArgument will be called 
     * @param UserID 
     */
    public void setUserID(String UserID) {
        if (UserID == null) {
            throw new IllegalArgumentException("The UserID cannot be null");
        }
        this.UserID = UserID;
    }

    /**
     * the method will return the user email
     *
     * @return email
     */
    @NotNull
    public String getEmail() {
        return email;
    }
    /**
     * this method can be user to setup or configure email. it cannot be null,
     * otherwise exception of IllegalArgument will be called
     *
     * @param email
     */
    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("The User email cannot be null");
        }
        this.email = email;
    }

    /**
     * the method will return the user password
     * 
     * @return password
     */
    @NotNull
    public String getPassword() {
        return password;
    }
    /**
     * this method can be user to setup or configure password. it cannot be null,
     * otherwise exception of IllegalArgument will be called
     *
     * @param password
     */
    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("The User password cannot be null");
        }
        this.password = password;
    }
    
    /**
     * the method will return the user password that used for sign up and validate the password input is the same
     *
     * @return re_password
     */
    public String getRe_password() {
        return re_password;
    }
    /**
     * this method set second password input when user is registering a new account and double check if the password that user input is correct
     *
     * @param re_password this method is used to double check the user inputed password
     */
    public void setRe_password(String re_password) {
        this.re_password = re_password;
    }

    /**
     * the method will return the user first name
     * @return first name
     */
    public String getFirstName() {
        return firstName;
    }
    /**
     * this method can be user to setup first name of the user. it cannot be
     * null, otherwise exception of IllegalArgument will be called
     *
     * @param firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * the method will return the user last name
     *
     * @return last name
     */
    public String getLastName() {
        return lastName;
    }
    /**
     * this method can be user to setup last name of the user. it cannot be
     * null, otherwise exception of IllegalArgument will be called
     *
     * @param lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * the method will return the user's date of birth
     *
     * @return date of birth
     */
    public Date getDob() {
        return dob;
    }
    /**
     * this method can be user to setup the date of birth 
     *
     * @param dob to set the date of the user birthday, and the data type must be Date
     */
    public void setDob(Date dob) {
        this.dob = dob;
    }
    
    /**
     * the method will return the city that the user is living
     *
     * @return the user's city
     */
    public String getCity() {
        return city;
    }
    /**
     * this method can be user to setup city that the user is living.
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }
    
    /**
     * the method will return which state the user's city belongs to
     *
     * @return the state of the user's city
     */
    public String getState() {
        return state;
    }
    /**
     * this method can be user to setup state that the user is living.
     *
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }
    
    /**
     * the method will return the postcode where the user is living
     *
     * @return postcode
     */
    public String getPostcode() {
        return postcode;
    }
    /**
     * this method can be user to setup postcode that the user is living.
     *
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }
    
    /**
     * the method will return user's address
     *
     * @return address
     */
    public String getAddress() {
        return address;
    }
    /**
     * this method can be user to setup address that the user is living.
     *
     * @param address
     */
    public void setAddress(String address) {
        this.address = address;
    }
    
    /**
     * the method will return user's mobile number
     *
     * @return mobile number
     */
    public String getPhone() {
        return phone;
    }
    /**
     * this method can be user to setup phone number of the user. it cannot be
     * null, otherwise exception of IllegalArgument will be called
     *
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }   
}
