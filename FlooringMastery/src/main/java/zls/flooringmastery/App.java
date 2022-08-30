/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zls.flooringmastery.controller.FlooringMasteryController;
import zls.flooringmastery.dao.FilePersistenceException;
import zls.flooringmastery.ui.InvalidDateException;


/**
 *
 * @author zshug
 */
public class App {
    
    public static void main(String[] args) throws FilePersistenceException, InvalidDateException{
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        FlooringMasteryController controller = ctx.getBean("controller", FlooringMasteryController.class);
        controller.start();
        
        
    }
    
}
