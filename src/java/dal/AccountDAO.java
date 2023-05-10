/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Account;

/**
 *
 * @author LENOVO
 */
public class AccountDAO extends DBContext {

    public Account getAccByUName(String userName) {
        Account acc = null;
        String sql = "select*from Account where userName = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc = new Account(rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        return acc;
    }

    public Account getAccByEmail(String email) {
        Account acc = null;
        String sql = "select*from Account where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                acc = new Account(rs.getString("userName"),
                        rs.getString("password"),
                        rs.getString("email"),
                        rs.getString("role"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return acc;
    }
    public Account getAccByKey(String key){
        Account accU = getAccByUName(key);
        Account accE = getAccByEmail(key);
        if(accU!=null) return accU;
        else if(accE!=null) return accE;
        else return null;
    }
    //authentication
    //key : username or email
    public boolean isExist(String key, String pass){
        Account acc1 = getAccByUName(key);
        Account acc2 = getAccByEmail(key);
        if(acc1!=null||acc2!=null){
            Account acc = getAccByKey(key);
            if(acc.getPassWord().equals(pass)) return true;
        }
        return false;
    }
    //authorization
    public boolean isAdmin(String key){
        String sql = "select*from Account where userName=? or email=?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, key);
            ps.setString(2, key);
            ResultSet rs = ps.executeQuery();
            rs.next();
            String role = rs.getString("role");
            if(role.equals("admin")) return true;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void insert(Account acc){
        String sql = "INSERT INTO Account(userName, passWord, email, role) "
                + "VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, acc.getUserName());
            ps.setString(2, acc.getPassWord());
            ps.setString(3, acc.getEmail());
            ps.setString(4, acc.getRole());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
    
    public void updatePass(String email, String pass){
        String sql = "update Account set password = ? where email = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, pass);
            ps.setString(2, email);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }
    
}
