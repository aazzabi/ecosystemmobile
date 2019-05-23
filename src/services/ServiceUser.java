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
import entities.Utilisateur;
import java.io.IOException;
import java.util.Map;

/**
 *
 * @author actar
 */
public class ServiceUser {

    public Utilisateur U;

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

            u.setNomPropriete(obj.get("nomPropriete").toString());
            u.setVille(obj.get("ville").toString());
            u.setRue(obj.get("rue").toString());
          u.setRoles(obj.get("roles").toString());
           u.setPrenom(obj.get("prenom").toString());
           u.setNumtel(obj.get("numtel").toString());
           u.setNom(obj.get("nom").toString());
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

}
