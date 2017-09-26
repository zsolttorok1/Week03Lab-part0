/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import user.User;

/**
 *
 * @author 725899
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        
        //throw new IOException(); //comment out for testing purposes

        String message = (String) request.getParameter("message");
        
        if (message != null && message.equals("Logged out."))
            request.setAttribute("message", message);
        
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response); 
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
      
        String username = (String) request.getParameter("username");
        String password = (String) request.getParameter("password");
        
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        
        if (username != null &&
            password != null &&
            !username.equals("") &&
            !password.equals("")) {
            
            UserService userService = new UserService();
        
            if (userService.login(user.getUsername(), user.getPassword())) {
                request.setAttribute("user", user);
                request.getRequestDispatcher("/MainPageServlet").forward(request, response);
                return;
            }
            else
                request.setAttribute("message", "Invalid username or password.");
        }
        else
            request.setAttribute("message", "You must enter a username and a password!!!!!");
            
        request.setAttribute("username", username);
        request.setAttribute("password", password);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
