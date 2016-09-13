/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package au.edu.uts.aip.TicketManager.Security;

/**
 * the class is used to encrypt the password using SHA-256 algorithm and store
 * the encrypted to data base
 *
 * @author Jianhao_LI
 */
import java.security.*;

public class Sha {

    /**
     * This method will called to encrypt the password provided before storing
     * into database
     *
     * @param data the original password
     * @return the encrypted password string
     * @throws NoSuchAlgorithmException
     */
    public static String hash256(String data) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(data.getBytes());
        return bytesToHex(md.digest());
    }

    /**
     * this method will convert the btyes to hex
     *
     * @param bytes the original bytes
     * @return the hex string
     */
    public static String bytesToHex(byte[] bytes) {
        StringBuffer result = new StringBuffer();
        for (byte byt : bytes) {
            result.append(Integer.toString((byt & 0xff) + 0x100, 16).substring(1));
        }
        return result.toString();
    }
}
