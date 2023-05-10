/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.security;

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
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("security/register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String u = request.getParameter("userName").trim();
        String e = request.getParameter("email").trim();
        String p = request.getParameter("password").trim();
        if (u == "" || u.contains(" ") || e == "" || p == "") {
            request.setAttribute("errE", "No spaces and blanks");
            request.getRequestDispatcher("security/register.jsp").forward(request, response);
        } else {
            AccountDAO accD = new AccountDAO();
            Account acc1 = accD.getAccByUName(u);
            Account acc2 = accD.getAccByEmail(e);
            if (acc1 != null || acc2 != null) {
                request.setAttribute("err", "User name or email already exists!!!");
                request.getRequestDispatcher("security/register.jsp").forward(request, response);
            } else {
                session.setAttribute("drawUserName", u);
                session.setAttribute("drawEmail", e);
                session.setAttribute("drawPassword", p);
                request.getRequestDispatcher("security/authen.jsp").forward(request, response);
            }
        }
    }
}
