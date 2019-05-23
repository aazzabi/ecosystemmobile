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
import entities.Reparation;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author actar
 */
public class ReparationService {

    static ArrayList<Reparation> listReparation = new ArrayList<>();

    public static ArrayList<Reparation> getReparations(int id, int type) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/repapi/reparationUser/id/" + id + "/type/" + type);
        // System.out.println("http://localhost/ecosystemweb/web/app_dev.php/repapi/reparationUser/id/" + id + "/type/" + type);
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                ReparationService ser = new ReparationService();
                try {

                    listReparation = ser.parseListTaskJsonAnnonce(new String(con.getResponseData()));

                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listReparation;
    }

    public ArrayList<Reparation> parseListTaskJsonAnnonce(String json) throws ParseException {

        ArrayList<Reparation> listReparation = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();
            Map<String, Object> tasks = j.parseJSON(new CharArrayReader(json.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) tasks.get("root");

            for (Map<String, Object> obj : list) {

                Reparation e = new Reparation();
                Map<String, Object> time = (Map<String, Object>) obj.get("datePrisEnCharge");
                Map<String, Object> users = (Map<String, Object>) obj.get("utilisateur");
                Map<String, Object> reps = (Map<String, Object>) obj.get("reparateur");
                Date date=new Date((long) Double.parseDouble(time.get("timestamp").toString())*1000L);
                //Map<String, Object> photo = (Map<String, Object>) obj.get("utilisateur");
                float idu = Float.parseFloat(users.get("id").toString());
                String nomUser = users.get("prenom").toString();
                String nomRep = reps.get("prenom").toString();

                float id = Float.parseFloat(obj.get("id").toString());

                e.setId((int) id);
                e.setCommentaire(obj.get("commentaire").toString());
                
                e.setDateP(getDate(date));
                e.setStatut(obj.get("statut").toString());
                e.setUserId((int) idu);
                e.setNomUser(nomUser);
                e.setNomRep(nomRep);
                listReparation.add(e);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        /*
            A ce niveau on a pu récupérer une liste des tâches à partir
        de la base de données à travers un service web
        
         */
        System.out.println(listReparation);
        return listReparation;

    }
    
       public static void validerOffre(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/repapi/valider/id/" + id);
         con.setFailSilently(true);
        con.addArgument("id", Integer.toString(id));
         System.out.println("http://localhost/ecosystemweb/web/app_dev.php/repapi/valider/id/" + id);
            con.addExceptionListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("RIEN");
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
       
             public static void annulerRep(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/repapi/annulerep/id/" + id);
         con.setFailSilently(true);
        con.addArgument("id", Integer.toString(id));
         System.out.println("http://localhost/ecosystemweb/web/app_dev.php/repapi/annulerep/id/" + id);
            con.addExceptionListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("RIEN");
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
      public static void supprimerRep(int id) {
        ConnectionRequest con = new ConnectionRequest();
       
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/repapi/supprimerrep/id/" + id);
        con.setFailSilently(true);
        con.addArgument("id", Integer.toString(id));
         System.out.println("http://localhost/ecosystemweb/web/app_dev.php/repapi/supprimerrep/id/" + id);
            con.addExceptionListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("RIEN");
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
      
            public static void confirmerRep(int id) {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/repapi/confirmerrep/id/" + id);
        con.addArgument("id", Integer.toString(id));
         con.setFailSilently(true);
         System.out.println("http://localhost/ecosystemweb/web/app_dev.php/confirmerrep/valider/id/" + id);
            con.addExceptionListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println("RIEN");
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(con);
       
    }
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
            
      public static String getDate(Date date)
{
String dateString=new SimpleDateFormat("EEEE dd MMMM yyyy").format(date);
return dateString;

}
}
