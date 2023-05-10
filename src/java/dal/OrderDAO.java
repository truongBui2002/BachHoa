/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Order;

/**
 *
 * @author LENOVO
 */
public class OrderDAO extends DBContext{
    public List<Order> getAll(){
        List<Order> list = new ArrayList<>();
        String sql = "select*from Order";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Order d = new Order();
                d.setOrderId(rs.getInt("orderId"));
                d.setCustomerId(rs.getInt("customerId"));
                d.setOrderDate(rs.getDate("orderDate"));
                d.setShipMode(rs.getString("shipMode"));
                list.add(d);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return  list;
    }
    
    public void delete(int orderId){
        String sql = "delete [Order] where orderId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, orderId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            
        }
    }
    public void insert(Order od){
        String sql = "INSERT INTO [Order] (customerId, orderDate, shipMode) "
                + "VALUES (?, ?, ?)";
       //format lại thành dạng chuỗi
        String orderDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(od.getOrderDate());
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, od.getCustomerId());
            ps.setString(2, orderDate);
            ps.setString(3, od.getShipMode());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    public int getNewestOrder(){
        String sql = "select Top(1) orderId from [Order] order by orderId desc";
        int orderId = 0 ;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                orderId = rs.getInt("orderId");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return orderId;
    }
    
    public int[] getOrderId(int customerId){
        String sql = "select orderId from [Order] where customerId = "+customerId;
        int[] arr = new int[1000];
        int i = 0;
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                arr[i] = rs.getInt("orderId");
                i++;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        return arr;
    }
    
//    public static void main(String[] args) {
//        OrderDAO od = new OrderDAO();
//        Date date = new Date();
//        Order o = new Order();
//        o.setCustomerId(16);
//        o.setOrderDate(date);
//        od.insert(o);
//        
//    }
    
}
