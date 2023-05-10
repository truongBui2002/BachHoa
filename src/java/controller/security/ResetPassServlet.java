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

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ResetPassServlet", urlPatterns = {"/resetpass"})
public class ResetPassServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String newP = request.getParameter("newP");
        String reNewP = request.getParameter("reNewP");
        HttpSession session = request.getSession();
        if (newP != ""&&reNewP != "") {
            if (reNewP.equals(newP)) {
                String email = (String) session.getAttribute("fEmail");
                AccountDAO accD = new AccountDAO();
                accD.updatePass(email.trim(), newP);
                request.getRequestDispatcher("security/success.html").forward(request, response);
            } else {
                request.setAttribute("err", "Password doesn't match");
                request.getRequestDispatcher("security/repass.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errC", "No spaces and blanks");
            request.getRequestDispatcher("security/repass.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
