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
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import entities.Categorie_Evts;
import entities.Evenement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Rania
 */
public class CategorieEvtsService {
    
     public ArrayList<Categorie_Evts> listeCategories(String json) throws ParseException {

        ArrayList<Categorie_Evts> listCat = new ArrayList<>();

        try {
            JSONParser j = new JSONParser();// Instanciation d'un objet JSONParser permettant le parsing du résultat json

      
            Map<String, Object> cat = j.parseJSON(new CharArrayReader(json.toCharArray()));

         
            List<Map<String, Object>> cats = (List<Map<String, Object>>) cat.get("root");

            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : cats) {
                //Création des tâches et récupération de leurs données
               Categorie_Evts c = new Categorie_Evts();

                float id = Float.parseFloat(obj.get("id").toString());
                c.setId((int) id);
               c.setBut(obj.get("but").toString());
               c.setLibelle(obj.get("libelle").toString());
           
               
              //  System.out.println(c);

                listCat.add(c);

            }

        } catch (IOException ex) {
        }

      //  System.out.println(listCat);
        return listCat;

    }

    ArrayList<Categorie_Evts> listCats = new ArrayList<>();

    public ArrayList<Categorie_Evts> getList() {
        ConnectionRequest con = new ConnectionRequest();
        con.setUrl("http://localhost/ecosystemweb/web/app_dev.php/front/Categorieevent");
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    //ServiceTask ser = new ServiceTask();
                    listCats = (new CategorieEvtsService()).listeCategories(new String(con.getResponseData()));
                } catch (ParseException ex) {
                    System.out.println("error");
                }
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return listCats;
    }
}
