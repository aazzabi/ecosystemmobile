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
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Aziz
 */
public class PanierService {
    ArrayList<AnnoncePanier> listAnnoncep = new ArrayList<>();
     public void ajouterAnnonce(AnnoncePanier a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/panierApi/new?id_annonce="+ a.getId_annonce()+"&titre="+a.getTitre()+"&description="+a.getDescription()+"&prix="+ a.getPrix() + "&region=" + a.getRegion() +"&photo=" + a.getPhoto();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
     public ArrayList<AnnoncePanier> parseListTaskJsonAnnoncePanier(String json) throws ParseException {

        ArrayList<AnnoncePanier> listAnnoncep = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                AnnoncePanier e = new AnnoncePanier();
                
                
                float id = Float.parseFloat(obj.get("id").toString());
                e.setId((int) id);
                e.setId_annonce(obj.get("idAnnonce").toString());
                e.setTitre(obj.get("titre").toString());
                e.setDescription(obj.get("description").toString());
                e.setPrix(Double.parseDouble(obj.get("prix").toString()));
                e.setRegion(obj.get("region").toString());
                e.setPhoto(obj.get("photo").toString());
               
                System.out.println(e);

                listAnnoncep.add(e);

            }

        } catch (IOException ex) {
        }

      
        System.out.println(listAnnoncep);
        return listAnnoncep;

    }
     
     public ArrayList<AnnoncePanier> getAllAnnoncesP() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/panierApi/");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                PanierService ser = new PanierService();
                try {
                    listAnnoncep = ser.parseListTaskJsonAnnoncePanier(new String(con.getResponseData()));
                    System.out.println(listAnnoncep);
                } catch (ParseException ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listAnnoncep;
    }
     
      public void viderPanier() {
        ConnectionRequest con = new ConnectionRequest();
        String Url =("http://localhost/ecosystemweb/web/app_dev.php/panierApi/vider");
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            System.out.println("Probléme vider panier ");
           System.out.println(str);
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
      public void supprimerAnnonce(int a) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/panierApi/delete_a?id="+a+"";
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
      
      public void ajouterCommande(Commande c) {
        ConnectionRequest con = new ConnectionRequest();
        String Url = "http://localhost/ecosystemweb/web/app_dev.php/panierApi/newcmd?etat_commande="+c.getEtat_commande()+"&id_utilisateur="+c.getId_user()+"&prix_total="+c.getPrixTotal();
        con.setUrl(Url);
        con.addResponseListener((e) -> {
            String str = new String(con.getResponseData());
            //System.out.println(str);//Affichage de la réponse serveur sur la console

        });
        NetworkManager.getInstance().addToQueueAndWait(con);
    }
     
}
