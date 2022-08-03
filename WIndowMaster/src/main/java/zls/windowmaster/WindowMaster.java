/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zls.windowmaster;

import java.util.Scanner;

/**
 *
 * @author zshug
 */
public class WindowMaster {
    public static void main(String[] args) {
        float height;
        float width;
        
        String stringHeight;
        String stringWidth;
        
        float area;
        float perimeter;
        float cost;
       
        Scanner myScanner = new Scanner(System.in);
        
        System.out.println("Please enter window height;");
        stringHeight = myScanner.nextLine();
        System.out.println("Please enter window width;");
        stringWidth = myScanner.nextLine();
        
        height = Float.parseFloat(stringHeight);
        width = Float.parseFloat(stringWidth); 
        
        area = height * width;
        perimeter = 2 * ( height + width);
        cost = (area * 3.50f)+ (perimeter * 2.25f);
        
        System.out.println("Window height = " + stringHeight);
        System.out.println("Window width = " + stringWidth);
        System.out.println("Window area = " + area);
        System.out.println("Window perimeter = " + perimeter);
        System.out.println("Total Cost =  " + cost);
    }     
}
