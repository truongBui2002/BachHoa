/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.security;

import controller.java.RandomString;
import controller.java.SendEmail;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="ResendCodeServlet", urlPatterns={"/resend"})
public class ResendCodeServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       HttpSession session = request.getSession();
        RandomString rs = new RandomString();
        SendEmail se = new SendEmail();
        String code = rs.getString(10);
        String toEmail = (String) session.getAttribute("fEmail");
        se.sendCode(toEmail, code);
        session.setAttribute("fCode", code);
        System.out.println(code);
        request.getRequestDispatcher("security/forgot.jsp").forward(request, response);
    } 

//    @Override
//    protected void doPost(HttpServletRequest request, HttpServletResponse response)
//    throws ServletException, IOException {
//        
//    }

}
