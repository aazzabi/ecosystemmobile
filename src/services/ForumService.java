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
import entities.CategoriePub;
import entities.CommentairePublication;
import entities.PublicationForum;
import entities.SignalisationCommentaire;
import entities.Utilisateur;
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
    ArrayList<CategoriePub> allCategories = new ArrayList<>();
    ArrayList<CommentairePublication> allComments = new ArrayList<>();
    String newDislikesString;
    String newLikesString;
    String photo = "profile.png";
    
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
                
                Map<String, Object> user = (Map<String, Object>) obj.get("publication_created_by");
                String username = user.get("username").toString();
                
                if (user.get("photo") == null){
                    a.setCreatedByPhoto("profile.png");
                } else {
                    a.setCreatedByPhoto(user.get("photo").toString());
                }
                a.setId( (int) id);
                a.setTitre(obj.get("titre").toString());
                a.setDescription(obj.get("description").toString());
                a.setEtat(obj.get("etat").toString());
                a.setNbrVues((int) Float.parseFloat(obj.get("nbr_vues").toString()));
                a.setCreatedByName(username);
                
                listPublications.add(a);

            }

        } catch (IOException ex) {
        }
        System.out.println(listPublications);
        return listPublications;
    }
    
    public ArrayList<PublicationForum> getAllPublication(){       
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
    
    public ArrayList<CategoriePub> listeCategorie(String json) throws ParseException {
        ArrayList<CategoriePub> allCategories = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String, Object> categorie = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> categories = (List<Map<String, Object>>) categorie.get("root");
            for (Map<String, Object> obj : categories) {
                CategoriePub c = new CategoriePub();

                float id = Float.parseFloat(obj.get("id").toString());
                c.setId( (int) id);
                c.setLibelle(obj.get("libelle").toString());
                c.setDescription(obj.get("description").toString());
                allCategories.add(c);
            }
        } catch (IOException ex){}
        System.out.println(allCategories);
        return allCategories;
    }
    
    public ArrayList<CategoriePub> getAllCategories(){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/forumApi/allCategories");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    ForumService ForumSer = new ForumService();
                    allCategories = ForumSer.listeCategorie(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   System.out.println("error");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return allCategories;
    }
    
    public ArrayList<CommentairePublication> listeComments(String json) throws ParseException {
        ArrayList<CommentairePublication> allComments = new ArrayList<>();
        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json
            Map<String, Object> categorie = j.parseJSON(new CharArrayReader(json.toCharArray()));
            List<Map<String, Object>> categories = (List<Map<String, Object>>) categorie.get("root");
            for (Map<String, Object> obj : categories) {
                CommentairePublication c = new CommentairePublication();

                float id = Float.parseFloat(obj.get("id").toString());
                
                Map<String, Object> user = (Map<String, Object>) obj.get("commented_by");
                String nom = user.get("username").toString();
                
                if (user.get("photo") == null){
                    c.setCreatedByPhoto("profile.png");
                } else {
                    c.setCreatedByPhoto(user.get("photo").toString());
                }
                c.setId( (int) id);
                c.setDescription(obj.get("description").toString());
                c.setCreatedByName(nom);
                c.setDislikes((int) Float.parseFloat(obj.get("dislikes").toString()));
                c.setLikes((int) Float.parseFloat(obj.get("likes").toString()));
                
                allComments.add(c);
            }
        } catch (IOException ex){}
        System.out.println(allComments);
        return allComments;
    }
    
    public ArrayList<CommentairePublication> getAllCommentsByPublication(int id){       
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/forumApi/allComments/"+id);  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    ForumService ForumSer = new ForumService();
                    allComments = ForumSer.listeComments(new String(con.getResponseData()));
                } catch (ParseException ex) {
                   System.out.println("error");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return allComments;
    }
    
    
    public void ajouterPublication(PublicationForum a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/forumApi/new/titre/"+a.getTitre()+"/description/"+a.getDescription()+"/user/"+a.getCreatedBy()+"/categorie/"+a.getCategorieId();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void ajouterCommentaire(CommentairePublication a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/forumApi/newComment/publication/"+a.getIdPublication()+"/content/"+a.getDescription()+"/user/"+a.getCreatedBy();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void like(CommentairePublication a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/forumApi/like/commentaire/"+a.getId();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(a.getId());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public void dislike(CommentairePublication a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/forumApi/dislike/commentaire/"+a.getId();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }

    public String getNewDislikes(CommentairePublication a) {
        ConnectionRequest con = new ConnectionRequest();
//        String Url = "http://localhost/ecosystemweb/web/app_dev.php/forumApi/getcommentaire/"+a.getId();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/forumApi/getdislikes/"+a.getId();
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ForumService ForumSer = new ForumService();
                newDislikesString = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return newDislikesString;
    }

    public String getNewLikes(CommentairePublication a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/forumApi/getlikes/"+a.getId();
        con.setUrl(Url);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ForumService ForumSer = new ForumService();
                newLikesString = new String(con.getResponseData());
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return newLikesString;
    }
    

    public void ajouterSignalisation(SignalisationCommentaire s) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/forumApi/signaler/commentaire/"+s.getCommentaire()+"/libelle/"+s.getCause()+"/by/"+s.getCommSignaleePar();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }


}
