/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dto;

import java.math.BigDecimal;

/**
 *
 * @author zshug
 */
public class Order {
    
    private int orderNumber;
    private String orderDate;
    private String customerName; 
    private String state;
    private BigDecimal taxRate;
    private String productType;
    private BigDecimal area;
    private BigDecimal costSqFt;
    private BigDecimal laborPerSqFt;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal tax;
    private BigDecimal total;
    
    public Order(String orderDate, String customerName, String state, String productType, BigDecimal area){
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.state = state;
        this.productType = productType;
        this.area = area;
    }
    
    public Order(){
        
    }
    
    public Order(int orderNumber){
        this.orderNumber = orderNumber;
    }
    
    public int getOrderNumber(){
        return this.orderNumber;
    }
    
    public void setOrderNumber(int orderNumber){
        this.orderNumber = orderNumber;
    }
    
    public String getOrderDate(){
        return this.orderDate;
    }
    
    public void setOrderDate(String orderDate){
        this.orderDate = orderDate;
    }
    
    public String getCustomerName () {
        return this.customerName;
    }
    
    
    public void setCustomerName(String customerName){
        this.customerName = customerName;
    }
    
    public String getState(){
        return this.state;
    }
    
    public void setState (String state){
        this.state = state;
    }
    
    public BigDecimal getTaxRate(){
        return this.taxRate;
    }
    
    public void setTaxRate (BigDecimal taxRate){
        this.taxRate = taxRate;          
    }
    
    public String getProductType(){
        return this.productType;
    }
    
    public void setProductType(String productType){
        this.productType = productType;
    }
    
    public BigDecimal getArea(){
        return this.area;
    }
    
    public void setArea(BigDecimal area){
        this.area = area;
    }
    
    public BigDecimal getCostSqFt (){
        return this.costSqFt;
    }
    
    public void setCostSqFt(BigDecimal costSqFt){
        this.costSqFt = costSqFt;
    }
    
    public BigDecimal getlaborPerSqFt (){
        return this.laborPerSqFt;
    }
    
    public void setLaborPerSqFt (BigDecimal laborPerSqFt){
        this.laborPerSqFt = laborPerSqFt;
    }
    
    public BigDecimal getMaterialCost (){
       return this.materialCost; 
    }
    
    public void setMaterialCost(BigDecimal materialCost){
        this.materialCost = materialCost;
    }
    
    public BigDecimal getLaborCost (){
        return this.laborCost;
    }
    
    public void setLaborCost(BigDecimal laborCost){
        this.laborCost = laborCost;
    }
    
    public BigDecimal getTax (){
        return this.tax;
    }
    
    public void setTax(BigDecimal tax){
        this.tax = tax;
    }
    
    public BigDecimal getTotal (){
        return this.total;
    }
    
    public void setTotal (BigDecimal total){
        this.total = total;
    }
}
