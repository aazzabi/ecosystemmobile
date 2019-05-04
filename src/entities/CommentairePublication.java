/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author arafe
 */
public class CommentairePublication {
    private int id;
    private int createdBy;
    private String description;
    private String createdByPhoto;
    private String createdByName;
    private int idPublication;
    private String publicationName;
    private String photo;
    private Date createdAt;
    private int nbSignalisation;
    private int likes;
    private int dislikes; 

    public CommentairePublication() {
        this.createdBy = Session.getCurrentSession();
        this.nbSignalisation = 0;
        this.likes = 0;
        this.dislikes = 0;
    }
    public CommentairePublication(String description, int idPublication, int createdBy) {
        this.description = description;
        this.idPublication = idPublication;
        this.createdBy = createdBy;
    }

    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public int getNbSignalisation() {
        return nbSignalisation;
    }

    public void setNbSignalisation(int nbrSignalisation) {
        this.nbSignalisation = nbrSignalisation;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getDislikes() {
        return dislikes;
    }

    public void setDislikes(int dislikes) {
        this.dislikes = dislikes;
    }

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }

    public String getPublicationName() {
        return publicationName;
    }

    public void setPublicationName(String idPublicationName) {
        this.publicationName = idPublicationName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public String getCreatedByPhoto() {
        return createdByPhoto;
    }

    public void setCreatedByPhoto(String createdByPhoto) {
        this.createdByPhoto = createdByPhoto;
    }
    
    @Override
    public String toString() {
        return "CommentairePublication{" + "id=" + id + ", createdBy=" + createdBy + ", description=" + description + ", createdByName=" + createdByName + ", idPublication=" + idPublication + ", publicationName=" + publicationName + ", createdAt=" + createdAt + ", nbrSignalisation=" + nbSignalisation + ", likes=" + likes + ", dislikes=" + dislikes + '}';
    }
}
