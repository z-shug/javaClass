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
public class Inventory {
    private String productType;
    private BigDecimal costPerSqFt;
    private BigDecimal laborPerSqFt;
    
    
    public Inventory(String productType){
        this.productType = productType;
    }
    
    public String getProductType(){
        return this.productType;
    }
    
    public void setProductType(String productType){
        this.productType = productType;
    }
       
    public BigDecimal getCostPerSqFt (){
        return this.costPerSqFt;
    }
    
    public void setCostPerSqFt(BigDecimal costPerSqFt){
        this.costPerSqFt = costPerSqFt;
    }
    
    public BigDecimal getLaborPerSqFt(){
        return this.laborPerSqFt;
    }
    
    public void setLaborPerSqFt(BigDecimal laborPerSqFt){
        this.laborPerSqFt = laborPerSqFt;
    }
    
}
