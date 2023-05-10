package controller.customer;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
import dal.AccountDAO;
import dal.CustomerDAO;
import dal.OrderDAO;
import dal.OrderDetailsDAO;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import model.Account;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(urlPatterns = {"/bought"})
public class OrdersPurchasedServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        HashMap<Integer, List<Product>> listOrd = new HashMap<>();
        HttpSession session = request.getSession();
        Account acc = (Account) session.getAttribute("account");
        String userName = acc.getUserName();
        CustomerDAO cd = new CustomerDAO();
        OrderDAO od = new OrderDAO();
        ProductDAO pd = new ProductDAO();

        //lấy ra mã khách hàng
        int customerId = cd.getByUserName(userName).getCustomerId();
        //lấy ra toàn bộ đơn của khách hàng
        int[] orderId = od.getOrderId(customerId);
        //lấy ra chi tiết từng đơn của khách hàng, trả về mã sản phẩm và số lượng mua
        if (orderId.length > 0) {
            OrderDetailsDAO oddd = new OrderDetailsDAO();
            listOrd = oddd.getProduct(orderId);
        }
        
        request.setAttribute("listCart", listOrd);
        request.getRequestDispatcher("customer/bought.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

}
