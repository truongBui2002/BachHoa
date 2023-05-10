/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Customer;

/**
 *
 * @author LENOVO
 */
public class CustomerDAO extends DBContext {

    public void insert(Customer cus) {
        String sql = "INSERT INTO Customer(userName, fullName, address, phone) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cus.getUserName());
            ps.setString(2, cus.getFullName());
            ps.setString(3, cus.getAddress());
            ps.setString(4, cus.getPhone());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public Customer getByUserName(String userName) {
        Customer cu = null;
        String sql = "select*from Customer where userName = ?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cu = new Customer(rs.getInt("customerId"),
                        rs.getString("userName"),
                        rs.getString("fullName"),
                        rs.getString("address"),
                        rs.getString("phone"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return cu;
    }

    public void update(Customer cu) {
        String sql = "update Customer set fullName = ?, "
                + "address = ?, phone = ? "
                + "where userName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, cu.getFullName());
            ps.setString(2, cu.getAddress());
            ps.setString(3, cu.getPhone());
            ps.setString(4, cu.getUserName());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
}
