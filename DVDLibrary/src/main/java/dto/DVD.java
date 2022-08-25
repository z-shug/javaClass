/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author zshug
 */
public class DVD {
    
    private String title;
    private LocalDate releaseDate;
    private String rating;
    private String directorName;
    private String studio;
    private String userRating;

    public DVD(){
       
    }
    
    public DVD(String dvdTitle){
        this.title = dvdTitle;
    }
    
    public String getTitle(){
        return title;
    }
    
    public void setTitle(String title){
        this.title = title;
    }
    
    public LocalDate getReleaseDate(){
        return releaseDate;
    }
    
    public void setReleaseDate(LocalDate releaseDate){
        this.releaseDate = releaseDate;
    }
    
    public String getRating(){
        return rating;
    }
    
    public void setRating(String rating){
        this.rating = rating;
    }
    
    public String getDirectorName(){
        return directorName;
    }
    
    public void setDirectorName(String directorName){
        this.directorName = directorName;
    }
    
    public String getStudio(){
        return studio;
    }
    
    public void setStudio(String studio){
        this.studio = studio;
    }
    
    public String getUserRating(){
        return userRating;
    }
    
    public void setUserRating(String userRating){
        this.userRating = userRating;
    }

    @Override
    public String toString() {
        return "DVD{" + "title=" + title + ", releaseDate=" + releaseDate + ", rating=" + rating + ", directorName=" + directorName + ", studio=" + studio + ", userRating=" + userRating + '}';
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.title);
        hash = 43 * hash + Objects.hashCode(this.releaseDate);
        hash = 43 * hash + Objects.hashCode(this.rating);
        hash = 43 * hash + Objects.hashCode(this.directorName);
        hash = 43 * hash + Objects.hashCode(this.studio);
        hash = 43 * hash + Objects.hashCode(this.userRating);
        return hash;
    }
    
        @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final DVD other = (DVD) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.releaseDate, other.releaseDate)) {
            return false;
        }
        if (!Objects.equals(this.rating, other.rating)) {
            return false;
        }
        if (!Objects.equals(this.directorName, other.directorName)) {
            return false;
        }
        if (!Objects.equals(this.studio, other.studio)) {
            return false;
        }
        if (!Objects.equals(this.userRating, other.userRating)) {
            return false;
        }
        return true;
    }
    
    
    
}
