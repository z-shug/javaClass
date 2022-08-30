/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dao;

import java.util.HashMap;
import zls.flooringmastery.dto.Tax;

/**
 *
 * @author zshug
 */
public interface FlooringMasteryTaxDao {
    
    HashMap<String,Tax> getAllTax()throws FilePersistenceException;
    
    Tax getStateTax(String statAbb)throws FilePersistenceException;
}
