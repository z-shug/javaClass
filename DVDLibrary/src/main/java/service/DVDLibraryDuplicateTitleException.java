/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author zshug
 */
public class DVDLibraryDuplicateTitleException extends Exception{
    public DVDLibraryDuplicateTitleException(String message) {
        super(message);
    }
    
    public DVDLibraryDuplicateTitleException(String message,
            Throwable cause) {
        super(message, cause);
    }
}
