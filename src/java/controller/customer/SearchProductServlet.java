/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.customer;

import dal.ProductDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "SearchProductServlet", urlPatterns = {"/searchc"})
public class SearchProductServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nameP = request.getParameter("nameP");
        ProductDAO pd = new ProductDAO();
        List<Product> listp = pd.getByName(nameP);
        List<Product> listb = pd.getAllBrand();
        request.setAttribute("listp", listp);
        request.setAttribute("listb", listb);
        request.getRequestDispatcher("customer/homec.jsp").forward(request, response);

    }
}
