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
public class LigneCommande {
    private int id;
    private int id_commande;
    private int id_annonce;
    private int id_utilisateur;
    private double prix_annonce;

    public LigneCommande(int id, int id_commande, int id_annonce, int id_utilisateur, double prix_annonce) {
        this.id = id;
        this.id_commande = id_commande;
        this.id_annonce = id_annonce;
        this.id_utilisateur = id_utilisateur;
        this.prix_annonce = prix_annonce;
    }

    public LigneCommande() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_commande() {
        return id_commande;
    }

    public void setId_commande(int id_commande) {
        this.id_commande = id_commande;
    }

    public int getId_annonce() {
        return id_annonce;
    }

    public void setId_annonce(int id_annonce) {
        this.id_annonce = id_annonce;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public double getPrix_annonce() {
        return prix_annonce;
    }

    public void setPrix_annonce(double prix_annonce) {
        this.prix_annonce = prix_annonce;
    }

    @Override
    public String toString() {
        return "LigneCommande{" + "id=" + id + ", id_commande=" + id_commande + ", id_annonce=" + id_annonce + ", id_utilisateur=" + id_utilisateur + ", prix_annonce=" + prix_annonce + '}';
    }
    
    
    
}
