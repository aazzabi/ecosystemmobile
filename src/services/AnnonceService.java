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
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anasc
 */
public class AnnonceService {
    ArrayList<Annonce> listAnnonce = new ArrayList<>();
    ArrayList<Categorie_Annonce> listCat = new ArrayList<>();
    
     public void ajouterAnnonce(Annonce a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/annonceApi/new?titre="+ a.getTitre()+"&description="+a.getDescription()+"&prix="+ a.getPrix().toString() + "&photo=" + a.getPhoto() + "&categorie=" + a.getCategorie_id() + "&user=" + a.getUser_id() + "&region=" + a.getRegion();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
      public ArrayList<Annonce> parseListTaskJsonAnnonce(String json) throws ParseException {

        ArrayList<Annonce> listAnnonce = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Annonce e = new Annonce();
                Map<String, Object> category = (Map<String, Object>) obj.get("categorie");
                Map<String, Object> users = (Map<String, Object>) obj.get("user");
                float idc = Float.parseFloat(category.get("id").toString());
                float idu = Float.parseFloat(users.get("id").toString());
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setTitre(obj.get("titre").toString());
                e.setDescription(obj.get("description").toString());
                e.setPrix(Double.parseDouble(obj.get("prix").toString()));
                e.setRegion(obj.get("region").toString());
                e.setCategorie_id((int) idc);
                e.setUser_id((int) idu);
                e.setEtat(obj.get("etat").toString());
                e.setPhoto(obj.get("photo").toString());
                e.setViews((int) Float.parseFloat(obj.get("views").toString()));
                e.setLikes((int) Float.parseFloat(obj.get("likes").toString()));

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
      
       public ArrayList<Categorie_Annonce> parseListTaskJsonCat(String json) throws ParseException {

        ArrayList<Categorie_Annonce> ListCat = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Categorie_Annonce e = new Categorie_Annonce();
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setLibelle(obj.get("libelle").toString());
                System.out.println(e);

                ListCat.add(e);

            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        System.out.println(ListCat);
        return ListCat;

    }
       
       public ArrayList<Annonce> getAllAnnonces() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/annonceApi/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
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
       
       public ArrayList<Categorie_Annonce> getAllCategories() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/annonceApi/categories");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
                try {
                    listCat = ser.parseListTaskJsonCat(new String(con.getResponseData()));
                    //  System.out.println(listCat);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCat;
    }
       
        public ArrayList<Annonce> getAnnonceById(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/annonceApi/annonce/" + id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                AnnonceService ser = new AnnonceService();
                try {
                    listAnnonce = ser.parseListTaskJsonAnnonce(new String(con.getResponseData()));
                   //System.out.println(listTasks);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnonce;
    }
        public ArrayList<String> getRegion()
        {
            ArrayList<String> reg = new ArrayList<>();
            reg.add("Tunis");
            reg.add("Ariana");
            reg.add("Manouba");
            reg.add("Ben Arous");
            reg.add("Bizerte");
            reg.add("Béja");
            reg.add("Jendouba");
            reg.add("Siliana");
            reg.add("Kasserine");
            reg.add("Sidi Bouzid");
            reg.add("Gafsa");
            reg.add("Tozeur");
            reg.add("Kébili");
            reg.add("Tataouine");
            reg.add("Médenine");
            reg.add("Gabès");
            reg.add("Sfax");
            reg.add("Kairouan");
            reg.add("Mahdia");
            reg.add("Monastir");
            reg.add("Zaghouan");
            reg.add("Nabeul");
            return  reg;
        }
}
