/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import controller.java.CookieCart;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "UpdateCartServlet", urlPatterns = {"/updatecart"})
public class UpdateCartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //value của delete là id của product cần xóa
        String delete_raw = request.getParameter("delete");
        CookieCart cc = new CookieCart();
        
        //lấy ra toàn bộ cookie rồi đẩy vào chuỗi
        Cookie[] arrC = request.getCookies();
        String txt = "";
        if (arrC != null) {
            for (Cookie c : arrC) {
                if (c.getName().equals("cart")) {
                    txt += c.getValue();
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }

        if (delete_raw == null) {
            String productId_raw = request.getParameter("productId");
            String quantity_raw = request.getParameter("quantity");

            int productId = Integer.parseInt(productId_raw);
            int quantity = Integer.parseInt(quantity_raw);
            //update số lượng của sản phẩm trong cookie
            String cart = cc.update(txt, productId, quantity);
            Cookie c = new Cookie("cart", cart);
            c.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(c);
        } else {
            int delete = Integer.parseInt(delete_raw);
            String cart = cc.delete(txt, delete);
            Cookie c = new Cookie("cart", cart);
            c.setMaxAge(60 * 60 * 24 * 365);
            response.addCookie(c);
        }

//        System.out.println(delete);
//        System.out.println("productId:" + productId);
//        System.out.println("quantity:" + quantity);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
