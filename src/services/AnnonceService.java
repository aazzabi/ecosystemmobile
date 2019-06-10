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
import entities.Categorie_Annonce;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
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
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/annonceApi/new";
        MultipartRequest req = new MultipartRequest();
        try {
            req.setUrl(Url);
            req.setPost(true);
            req.addArgument("titre", a.getTitre());
            req.addArgument("description", a.getDescription());
            req.addArgument("prix", a.getPrix().toString());
            req.addArgument("photo", a.getPhoto());
            req.addArgument("categorie", String.valueOf(a.getCategorie_id()));
            req.addArgument("user", String.valueOf(a.getUser_id()));
            req.addArgument("region", a.getRegion());
   
            if (!a.getPhoto().equals("")) {
                req.addData("photoannonce", a.getPhoto(), "image/jpeg");
                req.setFilename("photoannonce", a.getTitre()+ ".jpg");
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

    public void UpdateLikesAnnonce(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/annonceApi/likes/" + id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void supprimerAnnonce(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/annonceApi/delete/" + id;
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
                String st = obj.get("dateCreation").toString();
                System.out.println(st);
                float idc = Float.parseFloat(category.get("id").toString());
                float idu = Float.parseFloat(users.get("id").toString());
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setTitre(obj.get("titre").toString());
                e.setDescription(obj.get("description").toString());
                e.setPrix(Double.parseDouble(obj.get("prix").toString()));
                e.setRegion(obj.get("region").toString());
                e.setDate_creation(new Date((((Double) ((Map<String, Object>) obj.get("dateCreation")).get("timestamp")).longValue() * 1000)));
                e.setCategorie_id((int) idc);
                e.setUser_id((int) idu);
                e.setEtat(obj.get("etat").toString());
                e.setPhoto(obj.get("photo").toString());
                e.setViews((int) Float.parseFloat(obj.get("views").toString()));
                e.setLikes((int) Float.parseFloat(obj.get("likes").toString()));
                e.setNote(Float.parseFloat(obj.get("note").toString()));
                //System.out.println(e);
                listAnnonce.add(e);

            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        // System.out.println(listAnnonce);
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
                //System.out.println(e);

                ListCat.add(e);

            }

        } catch (IOException ex) {
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        //System.out.println(ListCat);
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
                    // System.out.println(listAnnonce);
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

    public ArrayList<Annonce> getAnnoncesByCategorie(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/annonceApi/categorie/" + id);
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

    public void UpdateViwesAnnonce(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/annonceApi/viwes/"+id;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
    
     public void UpdateNoteAnnonce(int id,float note) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/annonceApi/notes/"+id+"/"+note;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public ArrayList<String> getRegion() {
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
        return reg;
    }
    
    public ArrayList<Annonce> tirerAnnonces(String tr) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/annonceApi/trier/" + tr);
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
    
    public ArrayList<Annonce> recherchAnnonces(String key) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/annonceApi/recherche?keyWord=" + key);
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
       
}
