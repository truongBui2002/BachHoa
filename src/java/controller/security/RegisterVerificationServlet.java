/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.security;

import controller.java.RandomString;
import controller.java.SendEmail;
import dal.AccountDAO;
import dal.CustomerDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Customer;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="RegisterVerificationServlet", urlPatterns={"/verification"})
public class RegisterVerificationServlet extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        RandomString rs = new RandomString();
        SendEmail se = new SendEmail();
        String code = rs.getString(10);
        
        HttpSession session = request.getSession();
        String toEmail = (String) session.getAttribute("drawEmail");
        se.sendCode(toEmail, code);
        session.setAttribute("code", code);
        request.getRequestDispatcher("security/authen.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // codeU : code người dùng nhập
        String codeU = request.getParameter("code");
        HttpSession session = request.getSession();
        // code: code do hệ thông tạo ra
        String code = (String) session.getAttribute("code");
        if(!codeU.equals(code)){
            request.setAttribute("err", "Verification code is wrong");
            request.getRequestDispatcher("security/authen.jsp").forward(request, response);
        }else{
            session.removeAttribute("code");
            String userName = (String) session.getAttribute("drawUserName");
            String email = (String) session.getAttribute("drawEmail");
            String password = (String) session.getAttribute("drawPassword");
            // Mặc đinh người đăng kí là khách hàng
            String role = "customer";
            //thêm tài khoản vừa đăng kí vào bảng Account
            Account acc = new Account(userName, password, email, role);
            AccountDAO accD = new AccountDAO();
            accD.insert(acc);
            
            //thêm tài khoản vừa đăng kí vào bảng customer
            Customer cus = new Customer();
            cus.setUserName(userName);
            CustomerDAO cd = new CustomerDAO();
            cd.insert(cus);
            
            //xóa thông tin rác
            session.removeAttribute("drawUserName");
            session.removeAttribute("drawEmail");
            session.removeAttribute("drawPassword");
            
            request.getRequestDispatcher("security/success.html").forward(request, response);
        }
    }
}
