/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import controller.java.CookieCart;
import dal.CustomerDAO;
import dal.OrderDAO;
import dal.OrderDetailsDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import model.Account;
import model.Customer;
import model.Order;
import model.OrderDetails;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "NewServlet", urlPatterns = {"/cart"})
public class CartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] arrC = request.getCookies();
        String txt = "";
        if (arrC != null) {
            for (Cookie c : arrC) {
                if (c.getName().equals("cart")) {
                    txt += c.getValue();
                }
            }
        }

        CookieCart cc = new CookieCart();
        List<Product> list = cc.getListcart(txt);

        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc != null) {
            String userName = acc.getUserName();
            CustomerDAO cu = new CustomerDAO();
            Customer c = cu.getByUserName(userName);
            request.setAttribute("cu", c);
        }

        request.setAttribute("listCart", list);

        request.getRequestDispatcher("customer/cart.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            response.sendRedirect("login");
        }

        String fullName = request.getParameter("fullName");
        String phone = request.getParameter("phone");
        String shipMode = request.getParameter("shipMode");
        String address = request.getParameter("address");
        //cập nhật lại thông tin người mua
        CustomerDAO cd = new CustomerDAO();
        Customer cu = cd.getByUserName(acc.getUserName());
        cu.setFullName(fullName);
        cu.setPhone(phone);
        cu.setAddress(address);
        cd.update(cu);

        //Thêm sản phẩm vừa mua vào database và xóa giỏ hàng
        Cookie[] arrC = request.getCookies();
        String txt = "";
        for (Cookie c : arrC) {
            if (c.getName().equals("cart")) {
                txt += c.getValue();
                c.setMaxAge(0);
                response.addCookie(c);
            }
        }
        if (txt.isEmpty()) {
            response.sendRedirect("homec");
        } else {
            CookieCart cc = new CookieCart();
            List<Product> listCart = cc.getListcart(txt);
            //Thêm vào bảng Order
            OrderDAO od = new OrderDAO();
            Order o = new Order();
            o.setCustomerId(cu.getCustomerId());
            o.setOrderDate(new Date());
            o.setShipMode(shipMode);
            od.insert(o);

            //Thêm vào bảng orderDetail
            OrderDetailsDAO oddd = new OrderDetailsDAO();
            int orderId = od.getNewestOrder();
            for (Product p : listCart) {
                OrderDetails odd = new OrderDetails();
                odd.setOrderId(orderId);
                odd.setProductId(p.getProductId());
                odd.setQuatity(p.getQuantity());
                oddd.insert(odd);
            }
        }
        response.sendRedirect("bought");
    }

}
