/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.URLImage;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.mycompany.myapp.MyApplication;
import entities.Categorie_Evts;
import entities.Evenement;
import entities.ParticiperEvent;
import static gui.DetailsEvent.f;
import java.util.ArrayList;
import services.EvenementService;

/**
 *
 * @author Rania
 */
public class AffichageEvents extends BaseForm{
    
   static  Form f;
   // SpanLabel lb;
     Label titre ; 
    Label date ;
    static Evenement event ;
    ImageViewer img ; 
    Label User ;
    Label lieu;
    Label description;
    Label nbVues;
    Resources res;
   
  
    public AffichageEvents() {
        
        f = new Form("Voici tous les évènements");
          SimpleDateFormat sm = new SimpleDateFormat("dd/MM/yyyy");
         Container events = new Container(new BoxLayout(BoxLayout.Y_AXIS));
       // lb = new SpanLabel("");
       // f.add(lb);
        EvenementService service=new EvenementService();
       // lb.setText(e.getList2().toString());
     ArrayList<Evenement> a = service.getListByCat(AffichageCatEvts.cat) ;
        System.out.println(AffichageCatEvts.cat);
        for (Evenement ev : a) 
       {
            Container eventContainer = new Container (new BoxLayout(BoxLayout.X_AXIS));
            Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            img = new ImageViewer();
            EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(f.getWidth()/3, f.getWidth()/3),false);
            URLImage urlImage = URLImage.createToStorage(placeholder, ev.getCover(), "http://localhost/ecosystemweb/web/uploads/evt/cover/"+ev.getCover());
            img.setImage(urlImage);
            eventContainer.add(img);
           titre= new Label();
           lieu=new Label();
           description=new Label();
           date = new Label();
            nbVues = new Label();
           titre.setText(ev.getTitre());
           lieu.setText("à :"+ev.getLieu());
          // description.setText(ev.getDescription());
           date.setText("Cree le " + sm.format(ev.getDate()));
           nbVues.setText(ev.getNbVues()+"");
           nbVues.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REMOVE_RED_EYE, nbVues.getUnselectedStyle()));
           /* f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageCatEvts(res);
            AffichageCatEvts.f.showBack() ;});*/
           
           
            
           
           detailsContainer.add(titre);
           detailsContainer.add(lieu);
           detailsContainer.add(description);
           detailsContainer.add(date);
           detailsContainer.add(nbVues);
           
           eventContainer.add(detailsContainer);
           Button detailsEvent = new Button(); 
           detailsEvent.addActionListener((e)->
           {
               
           event = ev; 
           new EvenementService().nbVues(event);
           
           new DetailsEvent();
           
           });
           eventContainer.setLeadComponent(detailsEvent);
           events.add(eventContainer);
       }
       
        f.add(events);
        f.show();
    }
    
    }

  
