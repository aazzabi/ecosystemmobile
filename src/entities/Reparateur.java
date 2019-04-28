/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author arafe
 */
public class Reparateur extends Utilisateur {
 
    private String adresse ;
    private int numerofix;
    private int numerotel;
    private String specialite;
    private String horaire;
    private String type;
    private String description;

    public Reparateur(String username, String usernameCanonical, String email, String emailCanonical, String password, String nom, String prenom, String nomPropriete) {
        super(username, usernameCanonical, email, emailCanonical, password, nom, prenom, nomPropriete);
    }

    public Reparateur() {
    }

    public Reparateur(String username, String usernameCanonical, String email, String emailCanonical, String password, String nom, String prenom, String nomPropriete, String adresse, int numerofix, int numerotel, String specialite, String horaire, String type, String description) {
        super(username, usernameCanonical, email, emailCanonical, password, nom, prenom, nomPropriete);
        this.adresse = adresse;
        this.numerofix = numerofix;
        this.numerotel = numerotel;
        this.specialite = specialite;
        this.horaire = horaire;
        this.type = type;
        this.description = description;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getNumerofix() {
        return numerofix;
    }

    public void setNumerofix(int numerofix) {
        this.numerofix = numerofix;
    }

    public int getNumerotel() {
        return numerotel;
    }

    public void setNumerotel(int numerotel) {
        this.numerotel = numerotel;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getHoraire() {
        return horaire;
    }

    public void setHoraire(String horaire) {
        this.horaire = horaire;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        super.toString();
        return "Reparateur{" + "adresse=" + adresse + ", numerofix=" + numerofix + ", numerotel=" + numerotel + ", specialite=" + specialite + ", horaire=" + horaire + ", type=" + type + ", description=" + description + '}';
    }
    
    
  
}
