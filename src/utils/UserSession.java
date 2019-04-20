/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Aditya Sinha
 */
public final class UserSession {

    private static boolean isSessionOn = false; 
    private static String userName = "";
    private static Set<String> privileges = null;

    public static void createUser(String username,Set<String> privilege) {
        userName = username;
        privileges = privilege;
        isSessionOn = true;
    }

    public static String getUserName() {
        return userName;
    }

    public static Set<String> getPrivileges() {
        return privileges;
    }

    public static void cleanUserSession() {
        userName = "";// or null
        privileges = new HashSet<>();// or null
        isSessionOn = false;
    }
    public static boolean isExistUser()
    {
        return isSessionOn;
    }

    
}
/**
 * and use the UserSession whenever you need. When you do login you just call: UserSession.getInstace(userName, privileges) 
 * and when you do log out: UserSession.cleanUserSession()

*/
