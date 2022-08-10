/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DVD;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author zshug
 */
public interface DVDLibraryDao {
    public void saveToFile(HashMap<String,DVD> dvdMap);
    
    public HashMap<String,DVD> loadFromFile();
    
    public void addDVD(DVD dvd);
    
    public void deleteDVD(String title);
   
    public boolean search(String title);
    
    public DVD listInfo(String title);
    
    public void editInfo(String name, int number, String newValue);
    
    
    
    
}
