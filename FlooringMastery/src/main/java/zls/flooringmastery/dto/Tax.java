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
public class Tax {
    private String stateAbb;
    private String stateName;
    private BigDecimal taxRate;
    
    public Tax(String stateAbb){
        this.stateAbb = stateAbb;
    }
    
    public String getStateAbb(){
        return this.stateAbb;
    }
    
    public void setStateAbb(String stateAbb){
        this.stateAbb = stateAbb;
    }
    
    public BigDecimal getTaxRate(){
        return this.taxRate;
    }
    
    public void setTaxRate(BigDecimal taxRate){
        this.taxRate = taxRate;
    }
    
    public String getStateName(){
        return this.stateName;
    }
    
    public void setStateName(String stateName){
        this.stateName = stateName; 
    }
    
    
    
}
