/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business.UserAccounts;

import Business.Profiles.Profile;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author kal bugrara
 */
public class UserAccount {

    private final String hashingAlgorithm = "SHA-256";
    
    Profile profile;
    String username;
    String password;

    public UserAccount(Profile profile, String un, String pw) {
        username = un;
        password = hashPassword(pw);
        this.profile = profile;

    }

    public String getPersonId() {
        return profile.getPerson().getPersonId();
    }

    public String getUserLoginName() {
        return username;
    }

    public boolean isMatch(String id) {
        if (getPersonId().equals(id)) {
            return true;
        }
        return false;
    }

    public boolean IsValidUser(String un, String pw) {

        String encodedPassword = hashPassword(pw);
        
        if (username.equalsIgnoreCase(un) && password.equals(encodedPassword)) {
            return true;
        } else {
            return false;
        }

    }

    private String hashPassword(String passwordToHash) {
        String generatedPassword = null;
        try {
            MessageDigest md = MessageDigest.getInstance(hashingAlgorithm);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(String.format("%02x", bytes[i]));
            }
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }

    public String getRole() {
        return profile.getRole();
    }

    public Profile getAssociatedPersonProfile() {
        return profile;
    }

    @Override
    public String toString() {

        return getUserLoginName();
    }

}
