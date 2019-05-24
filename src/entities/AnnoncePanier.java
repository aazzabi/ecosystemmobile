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
public class AnnoncePanier {
    private int id ;
    private String id_annonce;
    private String titre;
    private String description;
    private double prix;
    private String region ;
    private String photo;

    public AnnoncePanier() {
    }

    public AnnoncePanier( String id_annonce, String titre, String description, double prix, String region, String photo) {
        
        this.id_annonce = id_annonce;
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.region = region;
        this.photo = photo;
    }

    @Override
    public String toString() {
        return "AnnoncePanier{" + "id=" + id + ", id_annonce=" + id_annonce + ", titre=" + titre + ", description=" + description + ", prix=" + prix + ", region=" + region + ", photo=" + photo + '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(String id_annonce) {
        this.id_annonce = id_annonce;
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

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
    
    
    
}
