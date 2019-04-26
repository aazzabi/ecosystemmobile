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
public class CategoriePub {
    private int id;
    private String libelle;
    private String description;
    private String domaine;
    private int nbPublication;

    public CategoriePub(int id, String libelle, String description, String domaine) {
        this.id = id;
        this.libelle = libelle;
        this.description = description;
        this.domaine = domaine;
    }

    public CategoriePub(String libelle, String description, String domaine) {
        this.libelle = libelle;
        this.description = description;
        this.domaine = domaine;
    }

    public CategoriePub() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public int getNbPublication() {
        return nbPublication;
    }

    public void setNbPublication(int nbPublication) {
        this.nbPublication = nbPublication;
    }

    @Override
    public String toString() {
        return "CategoriePub{" + "id=" + id + ", libelle=" + libelle + ", description=" + description + ", domaine=" + domaine + ", nbPublication=" + nbPublication + '}';
    }
}
