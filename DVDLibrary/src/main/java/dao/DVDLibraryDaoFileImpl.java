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

/**
 *
 * @author zshug
 */
public class DVDLibraryDaoFileImpl implements DVDLibraryDao {
    
    final static String filePath = "C:\\Users\\zshug\\Documents\\git_hub\\Projects\\Java\\classWork\\DVDLibrary\\data\\DVDLibraryData.txt";
        
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
        HashMap<String, DVD> map = new HashMap<String, DVD>();
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
                 
                map.put(title, fileDVD);
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
  
        return map;
        }
    
    
    @Override
    public DVD addDVD(ArrayList<String> array){
        DVD newDVDObject = new DVD();
        ArrayList<String> inputDVD = array;
        newDVDObject.setTitle(inputDVD.get(0));
        newDVDObject.setReleaseDate(inputDVD.get(1)); 
        newDVDObject.setRating(inputDVD.get(2));
        newDVDObject.setDirectorName(inputDVD.get(3));
        newDVDObject.setStudio(inputDVD.get(4));
        newDVDObject.setUserRating(inputDVD.get(5));
        return newDVDObject;
        
    }
    
    @Override
    public void deleteDVD(String title){
        
    }
    
    
    @Override
    public void search(){
        
    }
    @Override
    public void listInfo(String title){
        
    }
    
    @Override
    public void editInfo(String name){
        
    }
    
}
