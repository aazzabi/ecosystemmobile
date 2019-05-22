/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Evenement;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.Categorie_Evts;
import entities.ParticiperEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Rania
 */
public class EvenementService {

    public ArrayList<Evenement> listeEvents(String json) throws ParseException {

        ArrayList<Evenement> listEvent = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

            /*
                On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches.
             */
            Map<String, Object> event = j.parseJSON(new CharArrayReader(json.toCharArray()));

            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
             */
            List<Map<String, Object>> events = (List<Map<String, Object>>) event.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : events) {
                //Création des tâches et récupération de leurs données
                Evenement a = new Evenement();

                float id = Float.parseFloat(obj.get("id").toString());
                a.setId((int) id);
                a.setTitre(obj.get("titre").toString());
                a.setDescription(obj.get("description").toString());
                a.setLieu(obj.get("lieu").toString());
                a.setCover(obj.get("cover").toString());
                 String datee=obj.get("date").toString();
                 /*  SimpleDateFormat format1 = new SimpleDateFormat("dd-MM-yyyy");
                 
                 Date date = format1.parse(datee);
                 a.setDate(date);*/
              
                a.setDate(new Date((((Double) ((Map<String, Object>) obj.get("date")).get("timestamp")).longValue() * 1000)));
                //a.setDate(obj.get("date"));
                //     a.setCategorie(obj.get("categorie").toString());
                 float nbVues = Float.parseFloat(obj.get("nbVues").toString());
                 
                 a.setNbVues((int) nbVues);
                System.out.println(a);

                listEvent.add(a);

            }

        } catch (IOException ex) {
        }

        System.out.println(listEvent);
        return listEvent;

    }

    ArrayList<Evenement> listEvents = new ArrayList<>();

    public ArrayList<Evenement> getList2() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/front/jmsevent");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //ServiceTask ser = new ServiceTask();
                    listEvents = (new EvenementService()).listeEvents(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("erroe");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEvents;
    }
   
    
     ArrayList<Evenement> listEventsCat = new ArrayList<>();

    public ArrayList<Evenement> getListByCat(Categorie_Evts cat) {
        ConnectionRequest con = new ConnectionRequest();
        System.out.println(cat.getId());
                
        
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/front/jmseventCat/cat/"+cat.getId() ;// création de l'URL 
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion
        
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                  
                    listEventsCat = (new EvenementService()).listeEvents(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error"); 
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listEventsCat;
    }
    
    public void Participer(ParticiperEvent join) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/front/joinMobile/user/"+join.getId_user()+"/event/" +join.getId_event() ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
     
    }
    
     boolean test;
    public boolean verifParticiper(ParticiperEvent join)
    {   test =false;
         ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/front/verifjoinMobile/user/"+join.getId_user()+"/event/" +join.getId_event() ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
           
            System.out.println(str);//Affichage de la réponse serveur sur la console
             if(str.equals("false"))
             test=false;
             else test =true;       
            
        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
       return test;
    }
    public void NoParticiper(ParticiperEvent nojoin) {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/front/nojoinMobile/user/"+nojoin.getId_user()+"/event/" +nojoin.getId_event() ;// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
    }
    
    
    public void nbVues (Evenement event)
    {
        ConnectionRequest con = new ConnectionRequest();// création d'une nouvelle demande de connexion
     
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/front/nbvuesevent/"+event.getId();// création de l'URL
        con.setUrl(Url);// Insertion de l'URL de notre demande de connexion

        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());//Récupération de la réponse du serveur
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);// Ajout de notre demande de connexion à la file d'attente du NetworkManager
        
    }


}
