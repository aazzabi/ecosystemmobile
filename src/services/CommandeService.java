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
import entities.AnnoncePanier;
import entities.Commande;
import entities.LigneCommande;
import entities.Livraison;
import entities.Utilisateur;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.text.SimpleDateFormat;  
import java.util.Date;  
 
/**
 *
 * @author Aziz
 */
public class CommandeService {
    ArrayList<Commande> listcmd = new ArrayList<>();
    ArrayList<Livraison> listcmd2 = new ArrayList<>();
    ArrayList<LigneCommande> liste_ligne = new ArrayList<>();
     public ArrayList<Commande> parseListTaskJsonCmd(String json) throws ParseException {

        ArrayList<Commande> listcmd = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Commande e = new Commande();
                
                
                float id = Float.parseFloat(obj.get("id").toString());
                float id_user = Float.parseFloat(obj.get("idUtilisateur").toString());
                e.setId((int) id);
                e.setId_user((int) id_user);
               // String d=obj.get("dateEmission").toString();
                Map< String, Object> dateCreated = (Map< String, Object>) obj.get("dateEmission");
                    String dat = dateCreated.get("timestamp").toString();
                    float ts = Float.parseFloat(dat);
                    Date d = new Date((long) ts * 1000);
                   
                e.setDate_emission(d);
                
                 e.setEtat_commande(obj.get("etatCommande").toString());
                  e.setPrixTotal(Double.parseDouble(obj.get("prixTotal").toString()));
                 
                   
               
                System.out.println(e);

                listcmd.add(e);

            }

        } catch (IOException ex) {
        }

      
        System.out.println(listcmd);
        return listcmd;

    }
     
      public ArrayList<Commande> getAllCommandes() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/panierApi/getcmd");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                try {
                    listcmd = ser.parseListTaskJsonCmd(new String(con.getResponseData()));
                    System.out.println(listcmd);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcmd;
    }
      
       public void ajouterLivraison(int id_c,int id_u,String adresse,String ville,Utilisateur u,String code_livraison) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/panierApi/livraison?id_cmd="+id_c+"&id_u="+id_u+"&adresse="+adresse+"&ville="+ville+"&current_user"+u+"&code_livraison="+code_livraison;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
       
       public ArrayList<Livraison> parseListTaskJsonLiv(String json) throws ParseException {

        ArrayList<Livraison> listcmd2 = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Livraison e = new Livraison();
                float id = Float.parseFloat(obj.get("id").toString());
                float id_c = Float.parseFloat(obj.get("idCommande").toString());
                float id_liv = Float.parseFloat(obj.get("idLivreur").toString());
                 float id_u = Float.parseFloat(obj.get("idUtilisateur").toString());
                 
                  Map< String, Object> dateCreated = (Map< String, Object>) obj.get("dateLivraison");
                    String dat = dateCreated.get("timestamp").toString();
                    float ts = Float.parseFloat(dat);
                    Date d = new Date((long) ts * 1000);
                e.setDate_livraison(d);
                e.setId((int) id);
                e.setAdresse_livraison(obj.get("adresseComplete").toString());
                 e.setEtat_livraison(obj.get("etatLivraison").toString());
                  e.setCode_livraison(obj.get("codeLivraison").toString());
                  e.setId_commande((int) id_c);
                  e.setId_utilisateur((int) id_u);
                     e.setId_livreur((int) id_liv);
                    
             
                System.out.println(e);

                listcmd2.add(e);

            }

        } catch (IOException ex) {
        }

      
        System.out.println(listcmd2);
        return listcmd2;

    }
     
       
       public ArrayList<Livraison> getAllLivraisons() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/panierApi/getliv");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                try {
                    listcmd2 = ser.parseListTaskJsonLiv(new String(con.getResponseData()));
                    System.out.println(listcmd);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcmd2;
    }
       
       public int verifLivraison(int id)
       {
       
       ArrayList<Livraison> liste_test = new ArrayList<>();
       int x=0;
       liste_test=this.getAllLivraisons();
       for (Livraison a : liste_test) {
           System.out.println("id cmd :"+a.getId_commande());
       if(a.getId_commande()==id)
       {
           x=1;
       }
       else
       {
       return x=0;
       }
       
       }
       System.out.println("x :"+x);
       return x;
       }
       
       public ArrayList<LigneCommande> parseListTaskJsonLignes(String json) throws ParseException {

        ArrayList<LigneCommande> liste_ligne = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                LigneCommande e = new LigneCommande();
                
                
                float id = Float.parseFloat(obj.get("id").toString());
                float id_c = Float.parseFloat(obj.get("idCommande").toString());
                float id_user = Float.parseFloat(obj.get("idUtilisateur").toString());
                 float id_a = Float.parseFloat(obj.get("idAnnonce").toString());
                 
                  
                e.setId((int) id);
                e.setId_annonce((int) id_a);
                e.setId_commande((int) id_c);
                e.setId_utilisateur((int) id_user);
                e.setPrix_annonce(Double.parseDouble(obj.get("prixAnnonce").toString()));
     
                liste_ligne.add(e);

            }

        } catch (IOException ex) {
        }

      
        return liste_ligne;

    }
       
        public ArrayList<LigneCommande> getAllLignes() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/panierApi/getligne");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                try {
                    liste_ligne = ser.parseListTaskJsonLignes(new String(con.getResponseData()));
                    System.out.println(listcmd);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return liste_ligne;
    }
        
         public int verifLigne(String id)
       {
       
       ArrayList<LigneCommande> liste_test = new ArrayList<>();
       int x=0;
       liste_test=this.getAllLignes();
       for (LigneCommande a : liste_test) {
           String d=Integer.toString(a.getId_annonce());
       if(d.equals(id))
       {
           x=1;
       }
       else
       {
       return x=2;
       }
       
       }
       return x;
       }
      
          public ArrayList<Commande> getCmdClient(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/panierApi/getcmdclient?id_user="+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                try {
                    listcmd = ser.parseListTaskJsonCmd(new String(con.getResponseData()));
                    System.out.println(listcmd);
                    
                    
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcmd;
    }
           public ArrayList<Livraison> getLivClient(int id) {
        ConnectionRequest con = new ConnectionRequest();
   con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/panierApi/getlivclient?id_user="+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                try {
                    listcmd2 = ser.parseListTaskJsonLiv(new String(con.getResponseData()));
                    
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcmd2;
    }
           
           public ArrayList<Livraison> getLivLivreur(int id) {
        ConnectionRequest con = new ConnectionRequest();
   con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/panierApi/getlivlivreur?id_livreur="+id);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                CommandeService ser = new CommandeService();
                try {
                    listcmd2 = ser.parseListTaskJsonLiv(new String(con.getResponseData()));
                    
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listcmd2;
    }
           
        public void chnagerEtat(int id_liv,int id_livraison) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/panierApi/changeretat?id_livreur="+id_liv+"&id_livraison"+id_livraison;
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }   
        
        public void deleteCMD(int a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/panierApi/deletecmd?id="+a+"";
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
        
         public void changerEtatCmd(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url =("http://localhost/ecosystemweb/web/app_dev.php/panierApi/livencours?id_c="+id);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println("Probléme vider panier ");
           System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
         
         public void changerEtatLiv(int id) {
        ConnectionRequest con = new ConnectionRequest();
        String Url =("http://localhost/ecosystemweb/web/app_dev.php/panierApi/livlivre?id_liv="+id);
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println("Probléme vider panier ");
           System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
          
        
        
     
}
