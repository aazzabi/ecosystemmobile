/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.events.ActionListener;
import entities.Annonce;
import entities.AnnounceRep;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author actar
 */
public class AnnonceRepService {

    static ArrayList<AnnounceRep> listAnnonce = new ArrayList<>();

    public static ArrayList<AnnounceRep> getAllAnnonces() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/repapi/all");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceRepService ser = new AnnonceRepService();
                try {
                    listAnnonce = ser.parseListTaskJsonAnnonce(new String(con.getResponseData()));
                    System.out.println(listAnnonce);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }

    public ArrayList<AnnounceRep> parseListTaskJsonAnnonce(String json) throws ParseException {

        ArrayList<AnnounceRep> listAnnonce = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                AnnounceRep e = new AnnounceRep();
                String nomRep;

                Map<String, Object> users = (Map<String, Object>) obj.get("utilisateur");
                Map<String, Object> reps = (Map<String, Object>) obj.get("reparateur");
                Map<String, Object> photo = (Map<String, Object>) obj.get("utilisateur");
                float idu = Float.parseFloat(users.get("id").toString());
                String nomUser = users.get("prenom").toString();
              

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                e.setTitre(obj.get("titre").toString());
                e.setCategorie(obj.get("categorie").toString());
                e.setEtat(obj.get("etat").toString());
                e.setDescription(obj.get("description").toString());
                e.setUrlPhoto(obj.get("photo").toString());
                e.setUserId((int) idu);
                e.setNomUser(nomUser);

                if (reps != null) {
                    float idrep = Float.parseFloat(reps.get("id").toString());
                    nomRep = reps.get("prenom").toString(); 
                    e.setRepId((int) idrep);
                    e.setNomRep(nomRep);
                    e.setPrix(Float.parseFloat(obj.get("lastprix").toString()));
                } else {
                    e.setRepId(0);
                    e.setNomRep("");
                }

                System.out.println(e);
                listAnnonce.add(e);
            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        System.out.println(listAnnonce);
        return listAnnonce;

    }
    public static  void  updatePrix(int id,int rep_id,String prix) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/repapi/updatePrix/id/"+id+"/prix/"+prix+"/repid/"+rep_id);
;
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
           
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
     
    }
    
    
    
    
    public static void ajouterAnnonce(AnnounceRep e) {
        String url = "http://localhost/ecosystemweb/web/app_dev.php/repapi/ajoutann";
        MultipartRequest req = new MultipartRequest();
        try {
            req.setUrl(url);
            req.setPost(true);
            req.addArgument("titre", e.getTitre());
            req.addArgument("description", e.getDescription());
            req.addArgument("categorie", e.getCategorie());
            req.addArgument("UserId", String.valueOf(e.getUserId()));
   
            if (!e.getUrlPhoto().equals("")) {
                req.addData("photoannonce", e.getUrlPhoto(), "image/jpeg");
                req.setFilename("photoannonce", e.getTitre()+ ".jpg");
            }

            req.addResponseListener((response) -> {
                byte[] data = (byte[]) response.getMetaData();
                String s = new String(data);
                // System.out.println("data : " + s);
            });

            NetworkManager.getInstance().addToQueueAndWait(req);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
}
