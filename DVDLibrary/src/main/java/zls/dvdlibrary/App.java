/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.dvdlibrary;

import controller.DVDLibraryController;
import dao.DVDLibraryDao;
import dao.DVDLibraryPersistenceException;
import dao.DVDLibraryDaoFileImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.DVDLibraryDataValidationException;
import service.DVDLibraryDuplicateTitleException;
import service.DVDLibraryServiceLayer;
import service.DVDLibraryServiceLayerImpl;
import ui.DVDLibraryView;
import ui.UserIO;
import ui.UserIOConsoleImpl;

/**
 *
 * @author zshug
 */
public class App {
    public static void main(String[] args) throws DVDLibraryPersistenceException, DVDLibraryDuplicateTitleException, DVDLibraryDataValidationException{
       /* UserIO myIO = new UserIOConsoleImpl();
        DVDLibraryView view = new DVDLibraryView(myIO);
        DVDLibraryDao dao = new DVDLibraryDaoFileImpl();
        DVDLibraryServiceLayer myService = new DVDLibraryServiceLayerImpl(dao);
        
        DVDLibraryController controller = new  DVDLibraryController(myService,view);
        controller.startMessage();
        controller.start();*/
       ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        DVDLibraryController controller = ctx.getBean("controller", DVDLibraryController.class);
        controller.start();
        
    }
}
