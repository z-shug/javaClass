/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.service;

import java.math.BigDecimal;
import java.util.HashMap;
import zls.vendingmachine.dao.VendingMachineAuditDao;
import zls.vendingmachine.dao.VendingMachineDao;
import zls.vendingmachine.dao.VendingMachineDaoFileImpl;
import zls.vendingmachine.dao.VendingMachinePersistenceException;
import zls.vendingmachine.dto.Inventory;
import zls.vendingmachine.ui.ItemsEnum;

/**
 *
 * @author zshug
 */
public class VendingMachineServiceLayerImpl implements VendingMachineServiceLayer{
    VendingMachineDao dao; 
    private VendingMachineAuditDao auditDao;
    public VendingMachineServiceLayerImpl( VendingMachineDao dao, VendingMachineAuditDao auditDao){
        this.auditDao = auditDao;
        this.dao = dao ;
    }
    
    @Override
    public void createInventory()throws VendingMachinePersistenceException{
        dao.createInventory();
    } 
    
    @Override
    public void loadInventory()throws VendingMachinePersistenceException{
        dao.loadStartInventory();
    }
    
    @Override
    public boolean checkFunds(String userSelection, BigDecimal money)throws VendingMachinePersistenceException {
       return dao.checkFunds(userSelection, money);
    }

   

    @Override
    public Change returnChange(BigDecimal itemCost, BigDecimal userMoney) {
        BigDecimal changeDue = userMoney.subtract(itemCost);
        BigDecimal changeInPennies = changeDue.scaleByPowerOfTen(2);
        Change newChange = new Change();
        newChange.calculateChange(changeInPennies);
        return newChange;
    }

    @Override
    public BigDecimal makePurchase(String item) throws VendingMachinePersistenceException{
        auditDao.writeAuditEntry("Item " + item + " WAS PURCHASED" );
        return dao.makePurchase(item);
    }
    
    @Override
    public HashMap<String,Inventory> getItemInventory()throws VendingMachinePersistenceException{   
       return dao.getItemInventory(); 
    }
    
}
