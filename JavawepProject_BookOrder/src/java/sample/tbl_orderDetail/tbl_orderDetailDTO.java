/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_orderDetail;

import java.io.Serializable;

/**
 *
 * @author DEll
 */
public class tbl_orderDetailDTO implements Serializable{
    String productName;
    int quantity;
    float price;
    float total;
    String orderId;

    public tbl_orderDetailDTO() {
    }

    public tbl_orderDetailDTO(String productName, int quantity, float price, float total, String orderId) {
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.orderId = orderId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    
}
