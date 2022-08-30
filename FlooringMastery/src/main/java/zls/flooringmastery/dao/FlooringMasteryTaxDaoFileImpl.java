/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.dao;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import zls.flooringmastery.dto.Tax;
import zls.flooringmastery.dto.Tax;

/**
 *
 * @author zshug
 */
public class FlooringMasteryTaxDaoFileImpl implements FlooringMasteryTaxDao {

    private final String TAX_FILE = "Taxes.txt";
    private HashMap<String, Tax> taxes = new HashMap<>();
    public final String DELIMITER = ",";
    
    private Tax unmarshallTax(String taxAsText){
        String[] taxTokens = taxAsText.split(DELIMITER);
        String stateAbb = taxTokens[0];
        Tax taxFromFile = new Tax(stateAbb);
        taxFromFile.setStateName(taxTokens[1]) ;
        taxFromFile.setTaxRate(new BigDecimal(taxTokens[2]).setScale(2,RoundingMode.HALF_UP));
        
        return taxFromFile;
    }
    
    private void loadTax() throws FilePersistenceException {
        Scanner scanner;
        String taxFile = TAX_FILE;
        try {
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(taxFile)));
        } catch (FileNotFoundException e) {
            throw new FilePersistenceException(
                    "-_- Could not load tax file data into memory.", e);
        }
        String currentLine;
        Tax currentTax;
        scanner.nextLine();
        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentTax = unmarshallTax(currentLine);
            taxes.put(currentTax.getStateAbb(), currentTax);
        }

        scanner.close();
    }
    
    @Override
    public HashMap<String,Tax> getAllTax() throws FilePersistenceException{
        loadTax();
        return taxes;
    }

    @Override
    public Tax getStateTax(String stateAbb)throws FilePersistenceException {
        loadTax();
        return taxes.get(stateAbb);
    }
    
}
