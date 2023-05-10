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
import java.util.Arrays;
import java.util.List;
import model.Product;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "HomeCustomerServlet", urlPatterns = {"/homec"})
public class HomeCustomerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        List<Product> listp = pd.getAll();
        List<Product> listb = pd.getAllBrand();

        request.setAttribute("listp", listp);
        request.setAttribute("listb", listb);
        request.getRequestDispatcher("customer/homec.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProductDAO pd = new ProductDAO();
        String delete = request.getParameter("delete");
        String price = request.getParameter("price");
        List<Product> listb = pd.getAllBrand();
        List<Product> listp = null;
        List<String> listSelecteds = null;
        String[] selecteds;
//        System.out.println("price:"+price);
        if (delete == null) {
            if (price == null) {
                //không có giá, chỉ có thường hiệu
                if (request.getParameterValues("brand") != null) {
                    selecteds = request.getParameterValues("brand");
                    listSelecteds = Arrays.asList(selecteds);
                    listp = pd.getByBrand(listSelecteds);
                } //Không giá, không brand
                else {
//                    System.out.println("giữa");
                    listp = pd.getAll();
                }
//                System.out.println("trên");
            } //price có giá trị trong khoảng 
            //1: 0 - 50000
            //2: 50.000đ-100.000đ
            //3: 100.000-200.000đ
            //4: 200.000-400.000
            else {
                //vừa có khoảng giá, vừa có thương hiệu
                //đổi price sang số
//                System.out.println("ở đây");
                int pri = Integer.parseInt(price);
                if (request.getParameterValues("brand") != null) {
                    //Lấy ra toàn bộ thương hiệu đã chọn
                    selecteds = request.getParameterValues("brand");
                    //chuyển đổi từ array[] sang dạng list
                    listSelecteds = Arrays.asList(selecteds);
                    System.out.println("size"+ listSelecteds.size());
                    //lấy ra brand theo list đã select
                    List<Product> raw = pd.getByBrand(listSelecteds);
                    //duyệt list raw lấy ra những khoảng có giá thỏa mãn
                    switch (pri) {
                        case 1:
                            listp = pd.getByPrice(raw, 0, 50000);
                            break;
                        case 2:
                            listp = pd.getByPrice(raw, 50000, 100000);
                            break;
                        case 3:
                            listp = pd.getByPrice(raw, 100000, 200000);
                            break;
                        case 4:
                            listp = pd.getByPrice(raw, 200000, 400000);
                            break;
                    }
//                    System.out.println("duoi:" + price);

                } //chỉ có giá, không có thường hiệu
                else {
                    switch (pri) {
                        case 1:
                            listp = pd.getByPrice(0, 50000);
                            break;
                        case 2:
                            listp = pd.getByPrice(50000, 100000);
                            break;
                        case 3:
                            listp = pd.getByPrice(100000, 200000);
                            break;
                        case 4:
                            listp = pd.getByPrice(200000, 400000);
                            break;
                    }
                }
            }
        } //Bấm delete thì xóa tất cả, gọi hết product
        else {
            price = null;
            listp = pd.getAll();
        }
//        System.out.println("Bình thường");
//        price = "1";
        //mức giá đã chọn
        request.setAttribute("price", price);
        //list product
        request.setAttribute("listp", listp);
        //list brand
        request.setAttribute("listb", listb);
        //list brand đã chọn
        request.setAttribute("listSelecteds", listSelecteds);
        request.getRequestDispatcher("customer/homec.jsp").forward(request, response);
    }

}
