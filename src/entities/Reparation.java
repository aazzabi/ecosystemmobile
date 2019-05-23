/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author actar
 */
public class Reparation {
    
    
    int id;
    String commentaire;
    String dateP;
    int userId;
    int repId;
    String nomRep;
    String nomUser;
    String statut;

    public Reparation(int id, String commentaire, String dateP, int userId, int repId, String nomRep, String nomUser, String statut) {
        this.id = id;
        this.commentaire = commentaire;
        this.dateP = dateP;
        this.userId = userId;
        this.repId = repId;
        this.nomRep = nomRep;
        this.nomUser = nomUser;
        this.statut = statut;
    }

    public Reparation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public String getDateP() {
        return dateP;
    }

    public void setDateP(String dateP) {
        this.dateP = dateP;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRepId() {
        return repId;
    }

    public void setRepId(int repId) {
        this.repId = repId;
    }

    public String getNomRep() {
        return nomRep;
    }

    public void setNomRep(String nomRep) {
        this.nomRep = nomRep;
    }

    public String getNomUser() {
        return nomUser;
    }

    public void setNomUser(String nomUser) {
        this.nomUser = nomUser;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    
    
    
    
    
    
    
    
    
    
    
}
