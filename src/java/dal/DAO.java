/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author LENOVO
 */
public class DAO extends DBContext {

    //Top 10 khách hành tiêu nhiều tiền nhất(khách hàng tiềm năng)
    public List<Object> potentialCustomers() {
        List<Object> list = new ArrayList<>();
        String sql = "select TOP(10) c.userName, sum(p.price*(1-od.discount))[Total] "
                + "from Customer c, Product p, [Order] o, OrderDetails od\n"
                + "where c.customerId = o.customerId and o.orderId = od.orderId "
                + "and p.productId = od.productId\n"
                + "group by c.userName \n"
                + "order by Total desc";
        String[] userName = new String[10];
        double[] total = new double[10];
        int i = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                userName[i] = rs.getString("userName");
                total[i] = rs.getDouble("Total");
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        list.add(userName);
        list.add(total);
        return list;
    }

    //Những sản phẩm bán chạy
    public List<Object> topSell() {
        List<Object> list = new ArrayList<>();
        String sql = "select TOP(10) p.productId, ISNULL(sum(od.quantity), 0)[quantity] from  Product p full join  OrderDetails od\n"
                + "on  p.productId = od.productId \n"
                + "group by p.productId, p.productName\n"
                + "order by [quantity] desc";
        String[] productId = new String[10];
        double[] quatity = new double[10];
        int i = 0;

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productId[i] = "ID: " + rs.getInt("productId");
                quatity[i] = rs.getInt("quantity");
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        list.add(productId);
        list.add(quatity);
        return list;
    }

    //Khách hàng ít mua 
    public List<Object> cusRarelyShop() {
        List<Object> list = new ArrayList<>();
        String sql = "with draw as (select TOP(10) c.userName, "
                + "COALESCE(sum(p.price*(1-od.discount)), 0)[Total] "
                + "from Customer c full join  [Order] o on c.customerId = o.customerId\n"
                + "full join OrderDetails od  on  o.orderId = od.orderId "
                + "full join Product p on  p.productId = od.productId\n"
                + "where c.userName is not null\n"
                + "group by c.userName\n"
                + "order by Total asc)\n"
                + "select*from draw order by Total desc";
        String[] userName = new String[10];
        double[] total = new double[10];
        int i = 0;
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                userName[i] = rs.getString("userName");
                total[i] = rs.getDouble("Total");
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        list.add(userName);
        list.add(total);
        return list;
    }

    //Những sản phẩm ít lượt mua
    public List<Object> fewerPurchases() {
        List<Object> list = new ArrayList<>();
        String sql = "with draw as (select TOP(10) p.productId, p.productName,  "
                + "ISNULL(sum(od.quantity), 0)[quantity] "
                + "from  Product p full join  OrderDetails od\n"
                + "on  p.productId = od.productId \n"
                + "group by p.productId, p.productName\n"
                + "order by [quantity] asc)\n"
                + "select*from draw order by quantity desc";
        String[] productId = new String[10];
        double[] quatity = new double[10];
        int i = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                productId[i] = "ID: " + rs.getInt("productId");
                quatity[i] = rs.getInt("quantity");
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        list.add(productId);
        list.add(quatity);

        return list;
    }
}
