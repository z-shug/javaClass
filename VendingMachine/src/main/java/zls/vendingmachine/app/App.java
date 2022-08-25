/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.vendingmachine.app;

import zls.vendingmachine.controller.VendingMachineController;
import zls.vendingmachine.dao.VendingMachineAuditDao;
import zls.vendingmachine.dao.VendingMachineAuditDaoFileImpl;
import zls.vendingmachine.dao.VendingMachineDao;
import zls.vendingmachine.dao.VendingMachineDaoFileImpl;
import zls.vendingmachine.service.InsufficientFundsException;
import zls.vendingmachine.service.VendingMachineServiceLayer;
import zls.vendingmachine.service.VendingMachineServiceLayerImpl;
import zls.vendingmachine.ui.UserIO;
import zls.vendingmachine.ui.UserIOConsoleImpl;
import zls.vendingmachine.ui.VendingMachineView;

/**
 *
 * @author zshug
 */
public class App {
    public static void main(String[] args) throws InsufficientFundsException {
        UserIO myIo = new UserIOConsoleImpl();
        VendingMachineView myView = new VendingMachineView(myIo);
        VendingMachineDao myDao= new VendingMachineDaoFileImpl();
        VendingMachineAuditDao myAuditDao = new VendingMachineAuditDaoFileImpl();
        VendingMachineServiceLayer myService = new VendingMachineServiceLayerImpl(myDao,myAuditDao);
        
        
        VendingMachineController controller = new VendingMachineController(myView, myService);
        
        controller.run();
    }
}
