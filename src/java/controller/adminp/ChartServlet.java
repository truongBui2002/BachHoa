/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller.adminp;

import com.fasterxml.jackson.databind.ObjectMapper;
import dal.DAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.lang.String;
import java.util.List;

/**
 *
 * @author LENOVO
 */
@WebServlet(name = "ChartServlet", urlPatterns = {"/chart"})
public class ChartServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        String index = request.getParameter("index");
//        System.out.println("Index is:" + index);
        List<Object> list = null;
        String[] labels;
        double[] values;
        String type = null;
        DAO d = new DAO();
        //check xem người dùng chọn biểu đồ mấy
        int i = Integer.parseInt(index);
        switch (i) {
            case 1:
                list = d.potentialCustomers();
                type = "Consumption";
                break;
            case 2:
                list = d.topSell();
                type = "Quantity";
                break;

            case 3:
                list = d.cusRarelyShop();
                type = "Consumption";
                break;
            case 4:
                list = d.fewerPurchases();
                type = "Quantity";
                break;
        }
        labels = (String[]) list.get(0);
        values = (double[]) list.get(1);
        //Gửi dữ liệu đi ở dạng JSON
        ObjectMapper objMapper = new ObjectMapper();
        String jLabels = objMapper.writeValueAsString(labels);
        String jValues = objMapper.writeValueAsString(values);
        response.getWriter().write(jLabels + "/" + jValues + "/" + type);
    }

}
