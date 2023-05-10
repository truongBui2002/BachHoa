/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.security;

import dal.AccountDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "LoginServlet", urlPatterns = "/login" )
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("security/login.jsp").forward(request, response);
    } 
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        //Lấy tài khoản(email) , mật khẩu và biến trạng thái ghi nhớ pass từ form login
        String u = request.getParameter("userName");
        String p = request.getParameter("password");
        String r = request.getParameter("remember");
        
        //Tạo cookie để ghi nhớ tài khoản, mật khẩu và trạng thái remember
        Cookie cu = new Cookie("userName", u);
        Cookie cp = new Cookie("password", p);
        Cookie cr = new Cookie("remember", r);
        
        //Người dùng chọn remember => r = on
        if(r!=null){
            cu.setMaxAge(60*60*24*365);// 1 năm
            cp.setMaxAge(60*60*24*365);
            cr.setMaxAge(60*60*24*365);
        }else{
            cu.setMaxAge(0); // xóa cookie
            cp.setMaxAge(0);
            cr.setMaxAge(0);
        }

        //Lưu vào brower(trình duyệt)
        response.addCookie(cu);
        response.addCookie(cp);
        response.addCookie(cr);
        
        AccountDAO accD = new AccountDAO();
        if(!accD.isExist(u, p)){
            request.setAttribute("err", "User name or password invalid!!");
            request.getRequestDispatcher("security/login.jsp").forward(request, response);
        }else{
            Account acc = accD.getAccByKey(u);
            session.setAttribute("account", acc);
            //kiểm tra xem người đăng nhập là admin hay customer
            if(accD.isAdmin(u)){
                session.setAttribute("role", "admin");
                response.sendRedirect("homea");
            }else{
                session.setAttribute("role", "customer");
                response.sendRedirect("homec");
            }
        }
    }

}
