/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Rania
 */
public class ParticiperEvent {
    int id_user;
    int id_event;

    public ParticiperEvent(int id_user, int id_event) {
        this.id_user = id_user;
        this.id_event = id_event;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public int getId_event() {
        return id_event;
    }

    public void setId_event(int id_event) {
        this.id_event = id_event;
    }

    @Override
    public String toString() {
        return "ParticiperEvent{" + "id_user=" + id_user + ", id_event=" + id_event + '}';
    }
    
}
