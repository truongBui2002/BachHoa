/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.customer;

import dal.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name="ChangePassServlet", urlPatterns={"/change"})
public class ChangePassServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("customer/changepass.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String oldp = request.getParameter("oldp");
        String newp = request.getParameter("newp");
        HttpSession sesion = request.getSession();
        Account acc = (Account) sesion.getAttribute("account");
        if(oldp.equals(acc.getPassWord())){
            AccountDAO accD = new AccountDAO();
            accD.updatePass(acc.getEmail(), newp);
        }
        response.sendRedirect("account");
    }

}
