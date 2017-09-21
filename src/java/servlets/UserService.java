/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

/**
 *
 * @author 725899
 */
public class UserService {
    boolean login(String username, String password) {
        return (username != null &&
                password != null &&
                !username.equals("") &&
                !password.equals("") &&
                (username.equals("adam") && password.equals("password") ||
                username.equals("betty") && password.equals("password")));
    }
}
