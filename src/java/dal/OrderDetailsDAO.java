/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.OrderDetails;
import model.Product;

/**
 *
 * @author LENOVO
 */
public class OrderDetailsDAO extends DBContext{
    public void insert(OrderDetails od){
        String sql = "INSERT INTO [OrderDetails] (orderId, productId, quantity) "
                + "VALUES (?, ?, ?)";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, od.getOrderId());
            ps.setInt(2, od.getProductId());
            ps.setInt(3, od.getQuatity());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    public HashMap<Integer, List<Product>> getProduct(int[] arrOd){
        ProductDAO pd = new ProductDAO();
        String sqlOd = "select productId, quantity from OrderDetails where orderId = ";
        //Mỗi một mã orderId lưu trữ một list các sản phẩm 
        HashMap<Integer, List<Product>> listOrd = new HashMap<>();
        for (int i = 0; i < arrOd.length; i++) {
            try {
                PreparedStatement ps = connection.prepareStatement(sqlOd + arrOd[i]);
                ResultSet rs = ps.executeQuery();
                listOrd.put(arrOd[i], new ArrayList());
                while(rs.next()){
                    int productId =rs.getInt("productId");
                    int quantity = rs.getInt("quantity");
                    Product p = pd.getById(productId);
                    p.setQuantity(quantity);
                    listOrd.get(arrOd[i]).add(p);
                    
                }
            } catch (SQLException ex) {
               ex.printStackTrace();
            }
        }
        return listOrd;
        
    }
    
//    public int[] getProductId(int orderId){
//        String sql = "select productId, quantity from OrderDetails where orderId = "+orderId;
//        int[] arr = new int[1000];
//        
//    }
}
