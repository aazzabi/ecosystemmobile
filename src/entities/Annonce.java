/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


import java.util.Date;
import java.time.LocalDate;
import java.util.Calendar;


/**
 *
 * @author anasc
 */
public class Annonce  {
    
    private int id;
    
    private String titre;
    
    private String description;
    
    private Calendar date_creation;
    
    private Calendar date_update;;
    
    private Double prix;
    
    private String region;
    
    private String etat ;
    
    private String photo;
    
    private Calendar photo_updated_at;
    
    private int likes;
    
    private int  views;
    
    private int categorie_id;
    
    private int user_id;
    
    private String lib ;
    
    private String nomPrenom;
    public Annonce() {
    }

    public Annonce(int id, String titre, String description, Calendar date_creation, Calendar date_update, Double prix, String region, String etat, String photo, Calendar photo_updated_at, int likes, int views, int categorie_id, int user_id) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.date_creation = date_creation;
        this.date_update = date_update;
        this.prix = prix;
        this.region = region;
        this.etat = etat;
        this.photo = photo;
        this.photo_updated_at = photo_updated_at;
        this.likes = likes;
        this.views = views;
        this.categorie_id = categorie_id;
        this.user_id = user_id;
    }

    public Annonce(String titre, String description,Double prix, String region, String photo, int categorie_id, int user_id) {
        this.titre = titre;
        this.description = description;
        this.date_creation = Calendar.getInstance();
        this.date_update = Calendar.getInstance();
        this.prix = prix;
        this.region = region;
        this.etat = "Disponible";
        this.photo = photo;
        this.photo_updated_at =Calendar.getInstance();
        this.likes = 0;
        this.views = 0;
        this.categorie_id = categorie_id;
        this.user_id = user_id;
    }
    

    public Annonce(String titre, String description, Double prix, String region, String photo, int categorie_id) {
        this.titre = titre;
        this.description = description;
        this.prix = prix;
        this.region = region;
        this.photo = photo;
        this.categorie_id = categorie_id;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public Calendar getDate_creation() {
        return date_creation;
    }

    public void setDate_creation(Calendar date_creation) {
        this.date_creation = date_creation;
    }

    public Calendar getDate_update() {
        return date_update;
    }

    public void setDate_update(Calendar date_update) {
        this.date_update = date_update;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public Calendar getPhoto_updated_at() {
        return photo_updated_at;
    }

    public void setPhoto_updated_at(Calendar photo_updated_at) {
        this.photo_updated_at = photo_updated_at;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public int getCategorie_id() {
        return categorie_id;
    }

    public void setCategorie_id(int categorie_id) {
        this.categorie_id = categorie_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getLib() {
        return lib;
    }

    public void setLib(String lib) {
        this.lib = lib;
    }

    public String getNomPrenom() {
        return nomPrenom;
    }

    public void setNomPrenom(String nomPrenom) {
        this.nomPrenom = nomPrenom;
    }

    @Override
    public String toString() {
        return "Annonce{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", date_creation=" + date_creation + ", date_update=" + date_update + ", prix=" + prix + ", region=" + region + ", etat=" + etat + ", photo=" + photo + ", photo_updated_at=" + photo_updated_at + ", likes=" + likes + ", views=" + views + ", categorie_id=" + categorie_id + ", user_id=" + user_id + ", lib=" + lib + ", nomPrenom=" + nomPrenom + '}';
    }


    

  
    
    
}
