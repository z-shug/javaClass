/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import static zls.vendingmachine.service.ChangeEnums.QUARTERS;

/**
 *
 * @author zshug
 */
public class Change {
    BigDecimal quarters ;
    BigDecimal dimes;
    BigDecimal nickles;
    BigDecimal pennies;
   
    
    public void calculateChange(BigDecimal changeDue){
       BigDecimal quarterValue = new BigDecimal("25");
       BigDecimal dimeValue = new BigDecimal("10");
       BigDecimal nickleValue = new BigDecimal("5");
       BigDecimal pennieValue = new BigDecimal("1"); 
       BigDecimal totalChange = changeDue.setScale(0,RoundingMode.HALF_DOWN);
       
       
       BigDecimal numberQuarters = totalChange.divide(quarterValue,0,RoundingMode.FLOOR);
       totalChange = totalChange.subtract((numberQuarters.multiply(quarterValue)));
       
       BigDecimal numberDimes =totalChange.divide(dimeValue,0,RoundingMode.FLOOR);
       totalChange = totalChange.subtract((numberDimes.multiply(dimeValue)));
       
       BigDecimal numberNickles = totalChange.divide(nickleValue,0,RoundingMode.FLOOR);
       totalChange = totalChange.subtract((numberNickles.multiply(numberNickles)));
       
       BigDecimal numberPennies = totalChange.divide(pennieValue,0,RoundingMode.FLOOR);
       this.quarters = numberQuarters;
       this.dimes = numberDimes;
       this.nickles = numberNickles;
       this.pennies = numberPennies; 
       
    }
    
    public void setQuarters(BigDecimal quarters){
        this.quarters = quarters;
    }
    
    public BigDecimal getQuarters(){
        return quarters;
    }
    
    public void setDimes(BigDecimal dimes){
        this.dimes = dimes;
    }
    
    public BigDecimal getDimes(){
        return dimes;
    }
    
    public void setNickles(BigDecimal nickles){
        this.nickles = nickles;
    }
    
    public BigDecimal getNickles(){
        return nickles;
    }
    
    public void setPennies(BigDecimal pennies){
        this.pennies = pennies;
    }
    
    public BigDecimal getPennies(){
        return pennies;
    }
}
