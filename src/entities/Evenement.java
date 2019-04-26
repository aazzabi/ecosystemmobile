/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
//import java.sql.Date;
/**
 *
 * @author Rania
 */
public class Evenement {
    
    private int id;
    private int id_categorie;
    private Utilisateur createdBy;
    private String lieu;
    private Date date;
    private Categorie_Evts categorie;
    private String titre;
    private String description;
    private String cover;
    private Date coverUpdatedAt;

    public List<Utilisateur> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Utilisateur> participants) {
        this.participants = participants;
    }
    private int nbvues;
     
    private List<Utilisateur>participants=new ArrayList();
    
    public Evenement() {
    }
    
    

    public Evenement(int id, Utilisateur createdBy, String lieu, Date date, Categorie_Evts categorie, String titre, String description) {
        this.id = id;
        this.createdBy = createdBy;
        this.lieu = lieu;
        this.date = date;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
    }
    
     public Evenement( String lieu, int id_categorie,Date date, String titre, String description) {
      
        
        this.lieu = lieu;
        this.id_categorie = id_categorie;
        this.titre = titre;
        this.description = description;
        this.date=date;
    }
      public Evenement( String lieu, int id_categorie, String titre, String description,Date date) {
      
        
        this.lieu = lieu;
        this.id_categorie = id_categorie;
        this.titre = titre;
        this.description = description;
        this.date=date;
    }

    public int getId_categorie() {
        return id_categorie;
    }

    public void setId_categorie(int id_categorie) {
        this.id_categorie = id_categorie;
    }
    

    public Evenement(int id, Utilisateur createdBy, String lieu,Date date, Categorie_Evts categorie, String titre, String description, String cover, Date coverUpdatedAt, int nbvues) {
        this.id = id;
        this.createdBy = createdBy;
        this.lieu = lieu;
        this.date = date;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.cover = cover;
        this.coverUpdatedAt = coverUpdatedAt;
        this.nbvues = nbvues;
    }

    public Evenement(int id, String lieu, Date date, Categorie_Evts categorie, String titre, String description, String cover, Date coverUpdatedAt, int nbvues) {
        this.id = id;
        this.lieu = lieu;
        this.date = date;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.cover = cover;
        this.coverUpdatedAt = coverUpdatedAt;
        this.nbvues = nbvues;
    }

    public Evenement(int id, String lieu, Categorie_Evts categorie, String titre, String description, String cover, int nbvues) {
        this.id = id;
        this.lieu = lieu;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.cover = cover;
        this.nbvues = nbvues;
    }

    public Evenement(int id, String lieu, int id_categorie, String titre, String description, Date date,String cover) {
        this.id = id;
        this.lieu = lieu;
        this.date = date;
        this.id_categorie = id_categorie;
        this.titre = titre;
        this.description = description;
        this.cover=cover;
    }
    

    public Evenement(int id, Utilisateur createdBy, String lieu,Date date,Categorie_Evts categorie, String titre, String description, String cover, int nbvues) {
        this.id = id;
        this.createdBy = createdBy;
        this.lieu = lieu;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.cover = cover;
        this.nbvues = nbvues;
        this.date=date;
    }
     public Evenement( Utilisateur createdBy, String lieu,Date date,Categorie_Evts categorie, String titre, String description, String cover, int nbvues) {
       // this.id = id;
        this.createdBy = createdBy;
        this.lieu = lieu;
        this.categorie = categorie;
        this.titre = titre;
        this.description = description;
        this.cover = cover;
        this.nbvues = nbvues;
        this.date=date;
    }
   

    public Evenement(String lieu, int id_categorie, String titre, String description, Date date, String cover) {
        this.lieu = lieu;
        this.date = date;
        this.id_categorie = id_categorie;
        this.titre = titre;
        this.description = description;
        this.cover = cover;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Utilisateur getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Utilisateur createdBy) {
        this.createdBy = createdBy;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Categorie_Evts getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie_Evts categorie) {
        this.categorie = categorie;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public Date getCoverUpdatedAt() {
        return coverUpdatedAt;
    }

    public void setCoverUpdatedAt(Date coverUpdatedAt) {
        this.coverUpdatedAt = coverUpdatedAt;
    }

    public int getNbvues() {
        return nbvues;
    }

    public void setNbvues(int nbvues) {
        this.nbvues = nbvues;
    }

    @Override
    public String toString() {
        return "Evenement{" + "id=" + id + ", createdBy=" + createdBy + ", lieu=" + lieu + ", categorie=" + categorie + ", titre=" + titre + ", description=" + description + ", cover=" + cover + ", nbvues=" + nbvues + '}';
    }

 

   

 

    

    
    
    
    
    
    
    
    
}
