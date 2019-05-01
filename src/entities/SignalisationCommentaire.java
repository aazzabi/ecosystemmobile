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
public class SignalisationCommentaire {
    private int id;
    private String publicationLibelle;
    private int commentaire;
    private String commentaireLibelle;
    private String commEcritPar;
    private int commSignaleePar;
    private String commSignaleeParName;
    private String cause;

    public SignalisationCommentaire(String cause,int commentaire,  int commSignaleePar) {
        this.commentaire = commentaire;
        this.commSignaleePar = commSignaleePar;
        this.cause = cause;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPublicationLibelle() {
        return publicationLibelle;
    }

    public void setPublicationLibelle(String publicationLibelle) {
        this.publicationLibelle = publicationLibelle;
    }

    public String getCommentaireLibelle() {
        return commentaireLibelle;
    }

    public void setCommentaireLibelle(String commentaireLibelle) {
        this.commentaireLibelle = commentaireLibelle;
    }

    public String getCommEcritPar() {
        return commEcritPar;
    }

    public void setCommEcritPar(String commEcritPar) {
        this.commEcritPar = commEcritPar;
    }

    public String getCommSignaleeParName() {
        return commSignaleeParName;
    }

    public void setCommSignaleeParName(String commSignaleePar) {
        this.commSignaleeParName = commSignaleePar;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getCommSignaleePar() {
        return commSignaleePar;
    }

    public void setCommSignaleePar(int commSignaleePar) {
        this.commSignaleePar = commSignaleePar;
    }

    public int getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(int publication) {
        this.commentaire = publication;
    }
    
    
    @Override
    public String toString() {
        return "SignalisationCommentaire{" + "id=" + id + ", publicationLibelle=" + publicationLibelle + ", commentaireLibelle=" + commentaireLibelle + ", commEcritPar=" + commEcritPar + ", commSignaleePar=" + commSignaleePar + ", cause=" + cause + '}';
    }
    
    
    
    
}
