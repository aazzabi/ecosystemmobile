/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;



/**
 *
 * @author Rania
 */
public class Categorie_Evts  {
   
    private int id;
    private String libelle;
    private String but;

    public Categorie_Evts(String libelle, String but) {
       
        this.libelle = libelle;
        this.but =but;
    }
    
   public Categorie_Evts(int id, String libelle, String but) {
   this.id = id;
   this.libelle =libelle;
   this.but = but;
    }

    public Categorie_Evts(int id, String libelle) {
        this.id = id;
        this.libelle = libelle;
    }

    public Categorie_Evts(String libelle) {
        this.libelle = libelle;
    }
   
   

    public Categorie_Evts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public final String getLibelle() {
        return libelle;  
    }


    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getBut() {
        return but;
    }
    

    public void setBut(String but) {
        this.but = but;
    }

    @Override
    public String toString() {
        return "Categorie_Evts{" + "id=" + id + ", libelle=" + libelle + ", but=" + but + '}';
    }

    

    
}
