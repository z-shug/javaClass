/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import dto.DVD;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author zshug
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    final static String filePath = "C:\\Users\\zshug\\Documents\\git_hub\\Projects\\Java\\classWork\\DVDLibrary\\data\\DVDLibraryData.txt";
    //Create HashMap that you load into //collectionofDvds
    HashMap<String, DVD> dvdMap = new HashMap<String,DVD>();
    
    @Override
    public void saveToFile(HashMap<String,DVD> dvdMap){
       File file = new File(filePath); 
       BufferedWriter bf = null;
       
       try {
           bf = new BufferedWriter(new FileWriter(file));
           
           for (HashMap.Entry<String, DVD> entry : dvdMap.entrySet()){
           bf.write(entry.getKey() + ":" + entry.getValue());
           bf.newLine();    
            }
         bf.flush();
       }
       catch (IOException e) {
        e.printStackTrace();
        }
       finally {
           try {
               bf.close();
           }
           catch (Exception e){
               
           }
       }
       
    }
    
    @Override
    public HashMap<String,DVD> loadFromFile(){
        //HashMap<String, DVD> map = new HashMap<String, DVD>();
        BufferedReader br = null;
        try {
  
            File file = new File(filePath);
  
            br = new BufferedReader(new FileReader(file));
  
            String line = null;
  
            while ((line = br.readLine()) != null) {
  
                DVD fileDVD = new DVD();
                
                String[] parts = line.split(":");
                String[] dvdInfo = parts[1].split(", ");
  
                // first part is name, second is number
                String title = parts[0].trim();
                fileDVD.setTitle(dvdInfo[0].substring(10));
                fileDVD.setReleaseDate(dvdInfo[1].substring(12));
                fileDVD.setRating(dvdInfo[2].substring(7));
                fileDVD.setDirectorName(dvdInfo[3].substring(13));
                fileDVD.setStudio(dvdInfo[4].substring(7));
                fileDVD.setUserRating(dvdInfo[5].substring(11));
                 
                dvdMap.put(title, fileDVD);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
  
            // Always close the BufferedReader
            if (br != null) {
                try {
                    br.close();
                }
                catch (Exception e) {
                };
            }
        }
  
        return dvdMap;
        }
    
    
    @Override
    public void addDVD(DVD dvd){       
        loadFromFile();
        dvdMap.put(dvd.getTitle(), dvd);
        saveToFile(dvdMap);      
    }
    
    @Override
    public void deleteDVD(String title){
        loadFromFile();
        dvdMap.remove(title);
        saveToFile(dvdMap);
    }
    
    
    @Override
    public boolean search(String title){
        loadFromFile();
        return dvdMap.containsKey(title);
        
    }
    @Override
    public DVD listInfo(String title){
        loadFromFile();
        DVD dvdObject = dvdMap.get(title);
        return dvdObject;
    }
    
    @Override
    public void editInfo(String title, int number, String newValue){
        loadFromFile();
        DVD dvdObj = dvdMap.get(title);
        switch (number){
            case 1:
                dvdObj.setReleaseDate(newValue);
                break;
            case 2: 
                dvdObj.setRating(newValue);
                break;
            case 3: 
                dvdObj.setDirectorName(newValue);
                break;
            case 4 :
                dvdObj.setStudio(newValue);
                break;
            case 5 :
                dvdObj.setUserRating(newValue);
                break;                         
        }  
        saveToFile(dvdMap);
    }
    
}
