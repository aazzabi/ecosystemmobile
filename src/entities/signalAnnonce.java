/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author anasc
 */
public class signalAnnonce {
    private int id;
    private int annonce_id;
    private int user_id;
    private String description;
    private int counts;
    
    
     public signalAnnonce() {
    }
    public signalAnnonce(int id, int annonce_id, int user_id, String description) {
        this.id = id;
        this.annonce_id = annonce_id;
        this.user_id = user_id;
        this.description = description;
    }

    public signalAnnonce(int id, int annonce_id, int user_id, String description, int counts) {
        this.id = id;
        this.annonce_id = annonce_id;
        this.user_id = user_id;
        this.description = description;
        this.counts = counts;
    }
    
    public signalAnnonce(int annonce_id, int counts) {
        this.annonce_id = annonce_id;
        this.counts = counts;
    }
    
    public signalAnnonce(int annonce_id, int user_id, String description) {
        this.annonce_id = annonce_id;
        this.user_id = user_id;
        this.description = description;
    }
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAnnonce_id() {
        return annonce_id;
    }

    public void setAnnonce_id(int annonce_id) {
        this.annonce_id = annonce_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCounts() {
        return counts;
    }

    public void setCounts(int counts) {
        this.counts = counts;
    }

    @Override
    public String toString() {
        return "signalAnnonce{" + "id=" + id + ", annonce_id=" + annonce_id + ", user_id=" + user_id + ", description=" + description + ", counts=" + counts + '}';
    }
    

    
    
    
}
