/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import javafx.scene.image.Image;


/**
 *
 * @author actar
 */
public class AnnounceRep {

    int id;
    String titre;
    String description;
    String urlPhoto;
    Image image;
    String categorie;
    int userId;
    int repId;
    String nomRep;
    String nomUser;
    String etat;
    String datePub;
    String dateModif;
    float prix;

    public AnnounceRep(int id, String titre, String description, String urlPhoto, String categorie, int userId, int repId, String etat, String datePub, float prix) {
        this.id = id;
        this.titre = titre;
        this.description = description;
        this.urlPhoto = urlPhoto;
        this.categorie = categorie;
        this.userId = userId;
        this.repId = repId;
        this.etat = etat;
        this.datePub = datePub;
        this.prix = prix;
    }

    public AnnounceRep() {

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

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
//        try {
//            BufferedImage bf = ImageIO.read(new File("C:\\wamp\\www\\ecosystemweb\\web\\uploads\\annoncerep\\photos\\" + urlPhoto));
//            BufferedImage bf1 = Scalr.resize(bf, Scalr.Method.SPEED, Scalr.Mode.FIT_TO_WIDTH,
//                    250, 250, Scalr.OP_ANTIALIAS);
//            image = SwingFXUtils.toFXImage(bf1, null);
//        } catch (IOException ex) {
//            // NO PHOTO A AJOUTER
//            System.out.println("entities.AnnounceRep.setUrlPhoto()");;
//        }
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public String getDatePub() {
        return datePub;
    }

    public void setDatePub(String datePub) {
        this.datePub = datePub;
    }

    public String getDateModif() {
        return dateModif;
    }

    public void setDateModif(String dateModif) {
        this.dateModif = dateModif;
    }

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
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

}
