/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.security;

import controller.java.RandomString;
import controller.java.SendEmail;
import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ForgotPassServlet", urlPatterns = {"/forgot"})
public class ForgotPassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String email = request.getParameter("fEmail");
        if (email != null) {
            HttpSession session = request.getSession();
            AccountDAO accD = new AccountDAO();
            Account acc = accD.getAccByEmail(email.trim());
            if (acc != null) {
                RandomString rd = new RandomString();
                SendEmail se = new SendEmail();
                String code = rd.getString(10);
                se.sendCode(email, code);

                session.setAttribute("fCode", code);
                session.setAttribute("status", true);
                session.setAttribute("fEmail", email);
                request.getRequestDispatcher("security/forgot.jsp").forward(request, response);
            } else {
                String errEmail ="";
                if(email.trim().equals("")){
                    errEmail = "Can't be blank";
                }else{
                    errEmail = "Email is not exist!!!";
                }
                request.setAttribute("errEmail", errEmail);
                request.getRequestDispatcher("security/forgot.jsp").forward(request, response);
            }
        } else {
            request.getRequestDispatcher("security/forgot.jsp").forward(request, response);
        }
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Code mà hệ thống tạo
        String code = (String) session.getAttribute("fCode");
        //Code do người dùng nhập
        String codeU = request.getParameter("fCode");
        if (!codeU.equals(code)) {
            request.setAttribute("errCode", "Verification code is wrong");
            request.getRequestDispatcher("security/forgot.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("security/repass.jsp").forward(request, response);
        }
    }

}
