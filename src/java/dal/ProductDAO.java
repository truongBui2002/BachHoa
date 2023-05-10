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
import model.Product;

/**
 *
 * @author LENOVO
 */
public class ProductDAO extends DBContext {

    public List<Product> getAll() {
        List<Product> list = new ArrayList<>();
        String sql = "select*from Product";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setBrand(rs.getString("brand"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImg(rs.getString("img"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public Product getById(int productId) {
        Product p = null;
        String sql = "select*from Product where productId =?";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                p = new Product(rs.getInt("productId"),
                        rs.getString("productName"),
                        rs.getString("brand"),
                        rs.getString("description"),
                        rs.getDouble("price"),
                        rs.getInt("quantity"),
                        rs.getString("img"));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }

    public List<Product> getByName(String productName) {
        List<Product> list = new ArrayList<>();
        String sql = "select*from Product where productName like '%" + productName + "%'";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setBrand(rs.getString("brand"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImg(rs.getString("img"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Product> getByBrand(String brand) {
        List<Product> list = new ArrayList<>();
        String sql = "select*from Product where brand like '%" + brand + "%'";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setBrand(rs.getString("brand"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImg(rs.getString("img"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    // mảng chuyền vào có size tối thiểu = 1
    public List<Product> getByBrand(List<String> listSelecteds) {
        List<Product> list = new ArrayList<>();
        String sql = "select*from Product where brand = '" + listSelecteds.get(0) +"'";
        for (int i = 1; i < listSelecteds.size(); i++) {
            sql += " or brand = '" + listSelecteds.get(i) + "'";
        }
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setBrand(rs.getString("brand"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImg(rs.getString("img"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Product> getAllBrand() {
        List<Product> list = new ArrayList<>();
        String sql = "select brand from Product group by brand";

        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setBrand(rs.getString("brand"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    //id, name, brand giống key
    public List<Product> getByKey(String key) {
        List<Product> list = new ArrayList<>();
        String sql = "select*from Product where "
                + "productId like '%" + key + "%' or "
                + "productName like '%" + key + "%' or "
                + "brand like '%" + key + "%'";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setBrand(rs.getString("brand"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImg(rs.getString("img"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<Product> getByPrice(double from, double to){
        List<Product> list = new ArrayList<>();
        String sql = "select*from Product where price between ? and ?";
        
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setDouble(1, from);
            ps.setDouble(2, to);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Product p = new Product();
                p.setProductId(rs.getInt("productId"));
                p.setProductName(rs.getString("productName"));
                p.setBrand(rs.getString("brand"));
                p.setDescription(rs.getString("description"));
                p.setPrice(rs.getDouble("price"));
                p.setQuantity(rs.getInt("quantity"));
                p.setImg(rs.getString("img"));
                list.add(p);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
    
    public List<Product> getByPrice(List<Product> raw, double from, double to){
        List<Product> list = new ArrayList<>();
        for (Product p : raw) {
            if(p.getPrice()>=from&&p.getPrice()<=to){
                list.add(p);
            }
        }
        return list;
    }

    public void insert(Product p) {
        String sql = "INSERT INTO Product (productId, productName, brand, description, price, quantity, img) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, p.getProductId());
            ps.setString(2, p.getProductName());
            ps.setString(3, p.getBrand());
            ps.setString(4, p.getDescription());
            ps.setDouble(5, p.getPrice());
            ps.setInt(6, p.getQuantity());
            ps.setString(7, p.getImg());
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void update(Product p) {
        String sql = "update Product set "
                + "productName = ?, "
                + "brand = ?, "
                + "description =?, "
                + "price =?, "
                + "quantity =?, "
                + "img =? "
                + "where productId = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, p.getProductName());
            ps.setString(2, p.getBrand());
            ps.setString(3, p.getDescription());
            ps.setDouble(4, p.getPrice());
            ps.setInt(5, p.getQuantity());
            ps.setString(6, p.getImg());
            ps.setInt(7, p.getProductId());
            ps.executeUpdate();
        } catch (SQLException ex) {

        }
    }

    public void delete(int productId) {
        String sql = "delete Product where productId =?";
        try {
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, productId);
            ps.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}
