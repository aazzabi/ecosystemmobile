/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.time.LocalDate;

/**
 *
 * @author arafe
 */
public class PublicationForum {
    private int id;
    private String titre;
    private String description;
    private String etat;
    private String categorie;
    private int createdBy;
    private String createdByName;
    private Calendar createdAt;
    private int nbrVues;
    private int nbrCommentaires;
    private ArrayList<CommentairePublication> commentaires;

    public PublicationForum() {  
        this.etat = "publié";
        this.createdBy = Session.getCurrentSession();
        this.createdAt = Calendar.getInstance();
        this.nbrVues = 0;
        this.commentaires = new ArrayList();
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public Calendar getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Calendar createdAt) {
        this.createdAt = createdAt;
    }

    public int getNbrVues() {
        return nbrVues;
    }

    public void setNbrVues(int nbrVues) {
        this.nbrVues = nbrVues;
    }

    public ArrayList<CommentairePublication> getCommentaires() {
        return commentaires;
    }

    public void setCommentaires(ArrayList<CommentairePublication> commentaires) {
        this.commentaires = commentaires;
    }

    public int getNbrCommentaires() {
        return nbrCommentaires;
    }

    public void setNbrCommentaires(int nbrCommentaires) {
        this.nbrCommentaires = nbrCommentaires;
    }    

    public String getCreatedByName() {
        return createdByName;
    }

    public void setCreatedByName(String createdByName) {
        this.createdByName = createdByName;
    }
    
    @Override
    public String toString() {
        return "PublicationForum{" + "id=" + id + ", titre=" + titre + ", description=" + description + ", etat=" + etat + ", categorie=" + categorie + ", createdBy=" + createdBy + ", createdAt=" + createdAt + ", nbrVues=" + nbrVues + ", commentaires=" + commentaires + '}';
    }
    
}
