/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.crudp;

import controller.java.CheckInput;
import dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/updatep"})
public class UpdateProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productId = request.getParameter("productId");
        ProductDAO pd = new ProductDAO();
        Product p = pd.getById(Integer.parseInt(productId));
        session.setAttribute("p", p);
        request.getRequestDispatcher("admin/updatep.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String productId_raw = request.getParameter("productId");
        String productName = request.getParameter("productName");
        String brand = request.getParameter("brand");
        String description = request.getParameter("description");
        String price_raw = request.getParameter("price");
        String quantity_raw = request.getParameter("quantity");
        String img = request.getParameter("img");

        CheckInput in = new CheckInput();
        //Kiểm tra xem nhập vào có đúng là số dương hay không
        if (!(in.isPosDouble(price_raw) && in.isPosInt(quantity_raw))) {
            request.setAttribute("err", "Error format!!");
            request.getRequestDispatcher("admin/updatep.jsp").forward(request, response);
        } else {
            session.removeAttribute("p");
            int productId = Integer.parseInt(productId_raw);
            ProductDAO pd = new ProductDAO();
            double price = Double.parseDouble(price_raw);
            int quantity = Integer.parseInt(quantity_raw);
            pd.update(new Product(productId, productName, brand, description, price, quantity, img));
            response.sendRedirect("listpa");

        }

    }
}
