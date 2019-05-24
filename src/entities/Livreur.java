/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Aziz
 */
public class Livreur extends Utilisateur{
     private int id;
    private String zone;
    private String disponibilite;
    private int nbr_livraison;
    private int note;
    
    public Livreur()
    {
    
    }
    
    public Livreur(String username, String usernameCanonical, String email, String emailCanonical, String password, String nom, String prenom, String nomPropriete) {
        super(username, usernameCanonical, email, emailCanonical, password, nom, prenom, nomPropriete);
    }
    public Livreur(String username, String usernameCanonical, String email, String emailCanonical, String password, String nom, String prenom, String nomPropriete,String zone,String disponibilite,int nbr_livraison,int note) {
        super(username, usernameCanonical, email, emailCanonical, password, nom, prenom, nomPropriete);
        this.zone = zone;
        this.disponibilite = disponibilite;
        this.nbr_livraison = nbr_livraison;
        this.note = note;
       
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getZone() {
        return zone;
    }

    public void setZone(String zone) {
        this.zone = zone;
    }

    public String getDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(String disponibilite) {
        this.disponibilite = disponibilite;
    }

    public int getNbr_livraison() {
        return nbr_livraison;
    }

    public void setNbr_livraison(int nbr_livraison) {
        this.nbr_livraison = nbr_livraison;
    }

    public int getNote() {
        return note;
    }

    public void setNote(int note) {
        this.note = note;
    }

    public static Utilisateur getCurrent_user() {
        return current_user;
    }

    public static void setCurrent_user(Utilisateur current_user) {
        Utilisateur.current_user = current_user;
    }
    
    
}
