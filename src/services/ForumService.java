/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import entities.Evenement;
import com.codename1.io.CharArrayReader; 
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;
import entities.PublicationForum;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author arafe
 */
public class ForumService {
    ArrayList<PublicationForum> listPublications = new ArrayList<>();

    public ArrayList<PublicationForum> listePublication(String json) throws ParseException {
        ArrayList<PublicationForum> listPublications = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            /* On doit convertir notre réponse texte en CharArray à fin de
            permettre au JSONParser de la lire et la manipuler d'ou vient 
            l'utilité de new CharArrayReader(json.toCharArray())
            
            La méthode parse json retourne une MAP<String,Object> ou String est 
            la clé principale de notre résultat.
            Dans notre cas la clé principale n'est pas définie cela ne veux pas
            dire qu'elle est manquante mais plutôt gardée à la valeur par defaut
            qui est root.
            En fait c'est la clé de l'objet qui englobe la totalité des objets 
                    c'est la clé définissant le tableau de tâches. */
            Map<String, Object> publication = j.parseJSON(new CharArrayReader(json.toCharArray()));
            /* Ici on récupère l'objet contenant notre liste dans une liste 
            d'objets json List<MAP<String,Object>> ou chaque Map est une tâche                
            */
            List<Map<String, Object>> publications = (List<Map<String, Object>>) publication.get("root");
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : publications) {
                //Création des tâches et récupération de leurs données
                PublicationForum a = new PublicationForum();

                float id = Float.parseFloat(obj.get("id").toString());

                a.setId( (int) id);
                a.setTitre(obj.get("titre").toString());
                a.setDescription(obj.get("description").toString());
                a.setEtat(obj.get("etat").toString());
//                a.setNbrVues(Integer.valueOf(obj.get("nbr_vues").toString()));                
//                a.setCreatedByName(obj.get("publication_created_by").toString());
                System.out.println(a);
                
                listPublications.add(a);

            }

        } catch (IOException ex) {
        }
        System.out.println(listPublications);
        return listPublications;
    }
    
    public ArrayList<PublicationForum> getList2(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/forumApi/");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    ForumService ForumSer = new ForumService();
                    listPublications = ForumSer.listePublication(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   System.out.println("error");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPublications;
    }
    
    public ArrayList<PublicationForum> getAllPublication() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/annonceApi/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ForumService ser = new ForumService();
                try {
                    listPublications = ser.listePublication(new String(con.getResponseData()));
                    System.out.println(listPublications);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listPublications;
    }

}
