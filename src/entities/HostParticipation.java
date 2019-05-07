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
public class HostParticipation {
    private int UserID;
    private int HostID;
    private Date ParticipationDate;
    private int Active;

    
    
    public HostParticipation() {
    UserID = 0;
    HostID = 0;
    ParticipationDate = null;
    Active = 0 ;    
    }

    public HostParticipation(int UserID, int HostID, Date ParticipationDate, int Active) {
        this.UserID = UserID;
        this.HostID = HostID;
        this.ParticipationDate = ParticipationDate;
        this.Active = Active;
    }
    
    
    public int getUserID() {
        return UserID;
    }
    public void setUserID(int UserID) {
        this.UserID = UserID;
    }
    public int getHostID() {
        return HostID;
    }
    public void setHostID(int HostID) {
        this.HostID = HostID;
    }
    public Date getParticipationDate() {
        return ParticipationDate;
    }
    public void setParticipationDate(Date ParticipationDate) {
        this.ParticipationDate = ParticipationDate;
    }
    public int getActive() {
        return Active;
    }
    public void setActive(int Active) {
        this.Active = Active;
    }
}
