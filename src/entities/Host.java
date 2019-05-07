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
public class Host {
    
    //Variables
    private int ID;
    private String Owner;
    private Date DateStart;
    private Date DateEnd;
     private double geolat ; 
    private double geolong ; 

    public void setGeolat(double geolat) {
        this.geolat = geolat;
    }

    public void setGeolong(double geolong) {
        this.geolong = geolong;
    }
    private int TotalPlaces;
    private int AvailablePlaces;
    private String Localisation;
    private String Participants;
    private int OwnerID;
    private String Image ; 

    public String getImage() {
        return Image;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }


    //Constructors
    public Host() {
        this.ID = 0;
        this.Owner = "";
        this.DateStart = null;
        this.DateEnd = null;
        this.TotalPlaces = 0;
        this.AvailablePlaces = 0;
        this.Localisation = "";
        this.Participants = "";
    }
    public Host(String Owner, Date DateStart, Date DateEnd, int TotalPlaces, int AvailablePlaces, String Localisation, String People) {

        this.Owner = Owner;
        this.DateStart = DateStart;
        this.DateEnd = DateEnd;
        this.TotalPlaces = TotalPlaces;
        this.AvailablePlaces = AvailablePlaces;
        this.Localisation = Localisation;
        this.Participants = People;
    }
   

    
    //Setters
    
    public int getOwnerID() {
        return OwnerID;
    }
    public void setAvailablePlaces(int AvailablePlaces) {
        this.AvailablePlaces = AvailablePlaces;
    }
    public void setDateEnd(Date DateEnd) {
        this.DateEnd = DateEnd;
    }
    public void setDateStart(Date DateStart) {
        this.DateStart = DateStart;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setLocalisation(String Localisation) {
        this.Localisation = Localisation;
    }
    public void setParticipants(String Participants) {
        this.Participants = Participants;
    }
    public void setTotalPlaces(int TotalPlaces) {
        this.TotalPlaces = TotalPlaces;
    }
    public void setOwner(String Owner) {
        this.Owner = Owner;
    }
        
    
    // Getters
     public double getGeolat() {
        return geolat;
    }

    public double getGeolong() {
        return geolong;
    }
    public int getAvailablePlaces() {
        return AvailablePlaces;
    }
    public Date getDateEnd() {
        return DateEnd;
    }
    public Date getDateStart() {
        return DateStart;
    }
    public int getID() {
        return ID;
    }
    public String getLocalisation() {
        return Localisation;
    }
    public String getParticipants() {
        return Participants;
    }
    public int getTotalPlaces() {
        return TotalPlaces;
    }
    public String getOwner() {
        return Owner;
    }
    public void setOwnerID(int OwnerID) {
        this.OwnerID = OwnerID;
    }
    
}
