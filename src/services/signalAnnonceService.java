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
import entities.Annonce;
import entities.Categorie_Annonce;
import entities.signalAnnonce;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anasc
 */
public class signalAnnonceService {

    ArrayList<signalAnnonce> listSigna = new ArrayList<>();

    public void ajouterSigna(signalAnnonce a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/annonceApi/snAnnonce?idAnnonce=" + a.getAnnonce_id() + "&user=" + a.getUser_id() + "&description=" + a.getDescription();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<signalAnnonce> parseListSignalJsonAnnonce(String json) throws ParseException {

        ArrayList<signalAnnonce> ListS = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> lists = (List<Map<String, Object>>) tasks.get("root");
            System.out.println(ListS);
            for (Map<String, Object> obj : lists) {

                signalAnnonce e = new signalAnnonce();

                float id = Float.parseFloat(obj.get("id").toString());
                int countss = Integer.parseInt(obj.get("counts").toString());
                int idAnn = Integer.parseInt(obj.get("annonce_id").toString());
                int iduset = Integer.parseInt(obj.get("user_id").toString());
                e.setId((int) id);
                e.setAnnonce_id(idAnn);
                e.setCounts(countss);
                e.setUser_id(iduset);
                e.setDescription(obj.get("description").toString());
                System.out.println(e);
                ListS.add(e);

            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        System.out.println(ListS);
        return ListS;

    }

    public ArrayList<signalAnnonce> getCountSignaAnnonces() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/annonceApi/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                signalAnnonceService ser = new signalAnnonceService();
                try {
                    listSigna = ser.parseListSignalJsonAnnonce(new String(con.getResponseData()));
                    System.out.println(listSigna);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listSigna;
    }
}
