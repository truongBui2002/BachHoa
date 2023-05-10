/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller.customer;

import controller.java.CookieCart;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(name="CartServlet", urlPatterns={"/addItem"})
public class AddItemServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String productId_raw = request.getParameter("productId");
        String quantity_raw = request.getParameter("quantity");
        Cookie[] arrCk = request.getCookies();
        String txt = "";
        ProductDAO pd = new ProductDAO();
        CookieCart cc = new CookieCart();
        
        int productId = Integer.parseInt(productId_raw);
        int quantity = Integer.parseInt(quantity_raw);
        
        Product p = pd.getById(productId);
        if(arrCk!=null){
            for (Cookie c : arrCk) {
                if(c.getName().equals("cart")){
                    txt+= c.getValue();
                    c.setMaxAge(0);
                    response.addCookie(c);
                }
            }
        }
        //chưa có sản phẩm nào trong giỏ hàng
        if(txt.isEmpty()){
            txt = productId_raw + ":" + quantity_raw;
        }else{
            // đã có giản phẩm trong giỏ hàng
            txt = cc.add(txt, productId, quantity);
        }
        Cookie c= new Cookie("cart", txt);
        c.setMaxAge(60*60*24*365);//1 năm
        response.addCookie(c);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        
    }

}
