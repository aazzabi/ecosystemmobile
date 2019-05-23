/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Hiro
 */
public class HostRating {
    private int ID;
    private int HostID;
    private int OwnerID;
    private String Comment;
    private int Rank;
    private Date RatingDate;



    public void setID(int ID){
        this.ID = ID;
    }
    public void setHostID(int HostID) {
        this.HostID = HostID;
    }
    public void setComment(String Comment) {
        this.Comment = Comment;
    }
    public void setRank(int Rank) {
        this.Rank = Rank;
    }
    public void setRatingDate(Date RatingDate) {
        this.RatingDate = RatingDate;
    }
    public void setOwnerID(int OwnerID) {
        this.OwnerID = OwnerID;
    }
    
    public int getHostID() {
        return HostID;
    }
    public int getID() {
        return ID;
    }
    public int getOwnerID() {
        return OwnerID;
    }
    public String getComment() {
        return Comment;
    }
    public int getRank() {
        return Rank;
    }
    public Date getRatingDate() {
        return RatingDate;
    }
}
