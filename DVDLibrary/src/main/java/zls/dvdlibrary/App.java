/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.dvdlibrary;

import controller.DVDLibraryController;
import dao.DVDLibraryDaoFileImpl;
import ui.DVDLibraryView;

/**
 *
 * @author zshug
 */
public class App {
    public static void main(String[] args){
        DVDLibraryDaoFileImpl dao = new DVDLibraryDaoFileImpl();
        DVDLibraryView view = new DVDLibraryView();
        DVDLibraryController controller = new  DVDLibraryController(dao,view);
       // String[] maybe = view.addDVDMenu("test");
        //System.out.println(maybe);
        controller.start();
        
    }
}
