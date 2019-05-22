/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import entities.Categorie_Evts;
import entities.Evenement;
import static gui.AffichageEvents.f;
import java.util.ArrayList;
import services.CategorieEvtsService;
import services.EvenementService;


/**
 *
 * @author Rania
 */
public class AffichageCatEvts extends Form{
    static  Form f;
   // SpanLabel lb;
     Label libelle ; 
    Label but ;
    static Categorie_Evts cat ;

    public AffichageCatEvts() {
        
         f = new Form("Choisissez une catégorie d'event");
    
         Container categories = new Container(new BoxLayout(BoxLayout.Y_AXIS));
     
        CategorieEvtsService service=new CategorieEvtsService();
       // lb.setText(e.getList2().toString());
       ArrayList<Categorie_Evts> a = service.getList();
   
        for (Categorie_Evts c : a )  
       {
            Container catContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
            Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
          
           libelle= new Label();
         //  but=new Label();
          
           libelle.setText(c.getLibelle());
           //but.setText(c.getBut());
     
           detailsContainer.add(libelle);
          // detailsContainer.add(but);
        
           
           catContainer.add(detailsContainer);
           
           Button events = new Button(); 
           events.addActionListener((e)->
           {
               
           cat = c; 
           new AffichageEvents();
           
           });
           catContainer.setLeadComponent(events);
           categories.add(catContainer);
       }
       
        f.add(categories);
        f.show();
    }
    
    
    
}
