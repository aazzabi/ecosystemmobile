/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Reparateur;
import entities.Utilisateur;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author actar
 */
public class ServiceUser {

    public Utilisateur U;
    public static ArrayList<Reparateur> listreps = new ArrayList<>();

    public Utilisateur getUserEntity(String json) {
        Utilisateur u = null;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> obj = j.parseJSON(new CharArrayReader(json.toCharArray()));

            if (obj.size() == 0) {
                return null;
            }
            u = new Utilisateur();
            float id = Float.parseFloat(obj.get("id").toString());
            u.setId((int) id);

            u.setUsername(obj.get("username").toString());
            u.setRoles(obj.get("roles").toString());
//            u.setEmail(obj.get("email").toString());
//            u.setPhoto(obj.get("photo").toString());
//            u.setPrenom(obj.get("prenom").toString());
//            u.setNumtel(obj.get("numtel").toString());
//            u.setNom(obj.get("nom").toString());
            //u.setProfilepicture("" + obj.get("profilePicture")); Au cas ou
        } catch (IOException ex) {
        }
        return u;
    }

    public Utilisateur getUserData(int user_id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/userapi/getuserid/" + user_id);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                U = ser.getUserEntity(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return U;
    }

    public Utilisateur CheckLoginData(String username, String password) {
        ConnectionRequest con = new ConnectionRequest();

        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/userapi/checklogindata/username/" + username + "/password/" + password);

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ServiceUser ser = new ServiceUser();
                System.out.println(new String(con.getResponseData()));
                U = ser.getUserEntity(new String(con.getResponseData()));

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);

        return U;
    }

    public static ArrayList<Reparateur> getTtReps() {

        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/userapi/getttrep");
        

        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                 ServiceUser ser = new ServiceUser();
            
                try {

                    System.out.println(new String(con.getResponseData()));

                    listreps = ser.parseListTaskJsonReparateur(new String(con.getResponseData()));
                } catch (Exception ex) {

                }

            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listreps;
    }

    public  ArrayList<Reparateur> parseListTaskJsonReparateur(String json) throws ParseException {

        ArrayList<Reparateur> listreps = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");
            System.out.println("Liste "+list.size());
            for (Map<String, Object> obj : list) {
             
                Reparateur e = new Reparateur();

                 float id = Float.parseFloat(obj.get("id").toString());
                 
                e.setId((int) id);
                e.setUsername(obj.get("username").toString());
                System.out.println(e.getUsername());
                e.setNumerotel((int) Float.parseFloat(obj.get("numeroTel").toString())+1);
                e.setDescription(obj.get("description").toString());
                e.setHoraire(obj.get("horaire").toString());
                e.setType(obj.get("type").toString());
                e.setPhoto(obj.get("photo").toString());
                e.setSpecialite(obj.get("specialite").toString());
                e.setAdresse(obj.get("adresse").toString());
                listreps.add(e);

            }

        } catch (Exception ex) {
        }

        System.out.println("XAZE"+listreps.size());
        return listreps;

    }

}
