/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.flooringmastery.ui;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author zshug
 */
public class UserIOConsoleImpl implements UserIO{
    
    final private Scanner console = new Scanner(System.in);


    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

   
    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

   
    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                String stringValue = this.readString(msgPrompt);
                num = Integer.parseInt(stringValue); 
                invalidInput = false; 
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

 
    @Override
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    
    @Override
    public long readLong(String msgPrompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }


    @Override
    public long readLong(String msgPrompt, long min, long max) {
        long result;
        do {
            result = readLong(msgPrompt);
        } while (result < min || result > max);

        return result;
    }


    @Override
    public float readFloat(String msgPrompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    
    @Override
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }


    @Override
    public double readDouble(String msgPrompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }


    @Override
    public double readDouble(String msgPrompt, double min, double max) {
        double result;
        do {
            result = readDouble(msgPrompt);
        } while (result < min || result > max);
        return result;
    }
    
    @Override
    public BigDecimal readBigDecimal(String prompt){
        BigDecimal result = null;
        boolean hasErrors = false;
        do{
            String userInput = readString(prompt);
            try{
                if(Double.parseDouble(userInput) > 0 && Double.parseDouble(userInput)>= 100 ){
                    result = new BigDecimal(userInput).setScale( 2,RoundingMode.HALF_UP);
                    hasErrors = false;   
                    }else{
                    System.out.println("Please enter a positive integer that is atleast 100");
                    hasErrors = true;
                }
            }
            catch(NumberFormatException e) {
                hasErrors = true; 
                System.out.println(userInput + " Is not a valid integer"); 
            }   
        }while (hasErrors);
        //System.out.println(result);
        return result;   
    }
    
    @Override
    public String readDate(String prompt){
       boolean parsed = false;
        String userDate = null;
        while(false == parsed){
            try {
                userDate = readString(prompt);
                LocalDate parsedDate = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
                parsed = true;
            }catch (DateTimeParseException e) {
                //e.printStackTrace();
                System.out.println("Could not parse date entry. Please enter in MM/dd/yyyy format.");
                parsed = false;
            }
        }
       return userDate; 
    } 
    
    @Override
    public String readFutureDate(String prompt){
        boolean validDate = false;
        String futureDate = null;
        LocalDate localDate = LocalDate.now(ZoneId.systemDefault());
        while(false == validDate){
            String userDate = readDate(prompt);
            LocalDate parsedUserDate = LocalDate.parse(userDate, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
            if(parsedUserDate.isAfter(localDate)) {
                futureDate = userDate;
                validDate = true;
                
            } else {
                print("Please enter a date in the future");
                validDate = false;
            }      
        } 
        return futureDate; 
    }    
    
}
