/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

/**
 *
 * @author zshug
 */
public interface UserIO {
    public void print(String message);

    public String readString(String prompt);

    public int readInt(String prompt);
    
}
