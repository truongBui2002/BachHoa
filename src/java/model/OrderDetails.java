/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author LENOVO
 */
public class OrderDetails {
    private int orderId;
    private int productId;
    private int quatity;
    private double discount;

    public OrderDetails() {
    }

    public OrderDetails(int orderId, int productId, int quatity, double discount) {
        this.orderId = orderId;
        this.productId = productId;
        this.quatity = quatity;
        this.discount = discount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getQuatity() {
        return quatity;
    }

    public void setQuatity(int quatity) {
        this.quatity = quatity;
    }

    public double getPrice() {
        return discount;
    }

    public void setPrice(double price) {
        this.discount = price;
    }
    
    
}
