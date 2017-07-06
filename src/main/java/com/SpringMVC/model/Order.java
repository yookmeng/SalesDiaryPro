package com.SpringMVC.model;

public class Order {
    private int orderid;
    private int projectid;
    private String projectname;
    private int productid;
    private String productname;
    private int quantity;
    private float price;
    private int discount;
    private float amount;

    public Order() {
    }
 
    public Order(int orderid, int projectid, String projectname, 
    		int productid, String productname, int quantity, 
    		float price, int discount, float amount) {
        this.orderid = orderid;
        this.projectid = projectid;
        this.projectname = projectname;
        this.productid = productid;
        this.productname = productname;
        this.quantity = quantity;
        this.price = price;
        this.discount = discount;
        this.amount = amount;
    }
 
    public int getorderid() {
        return orderid;
    }  
    public void setorderid(int orderid) {
        this.orderid = orderid;
    }

    public int getprojectid() {
        return projectid;
    }  
    public void setprojectid(int projectid) {
        this.projectid = projectid;
    }

    public String getprojectname() {
        return projectname;
    }  
    public void setprojectname(String projectname) {
        this.projectname = projectname;
    }

    public int getproductid() {
        return productid;
    }  
    public void setproductid(int productid) {
        this.productid = productid;
    }

    public String getproductname() {
        return productname;
    }  
    public void setproductname(String productname) {
        this.productname = productname;
    }

    public int getquantity() {
        return quantity;
    }  
    public void setquantity(int quantity) {
        this.quantity = quantity;
    }

    public float getprice() {
        return price;
    }  
    public void setprice(float price) {
        this.price = price;
    }

    public int getdiscount() {
        return discount;
    }  
    public void setdiscount(int discount) {
        this.discount = discount;
    }

    public float getamount() {
        return amount;
    }  
    public void setamount(float amount) {
        this.amount = amount;
    }
}