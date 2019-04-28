/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Container;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import static gui.AffichageEvents.f;
import entities.Evenement;
import services.UserService;

/**
 *
 * @author Rania
 */
public class DetailsEvent extends Form {
    
    Label titre ; 
    Label date ;
    static Form f;
    ImageViewer img ; 
    Label User ;
    Label lieu;
    Label description;
    
    
     public DetailsEvent(){
        
        f= new Form("",new BoxLayout(BoxLayout.Y_AXIS));
      //  UserService serviceuser =new UserService();
       // l= new Label();
        titre = new Label();
        date = new Label ();
        lieu = new Label();
        description = new Label();
        
        SpanLabel description  = new SpanLabel(AffichageEvents.event.getDescription());
        description.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        img = new ImageViewer();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(f.getWidth()/3, f.getWidth()/3),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, AffichageEvents.event.getCover(), "http://localhost/ecosystemweb/web/uploads/evt/cover/"+AffichageEvents.event.getCover());
        img.setImage(urlImage);
        f.getToolbar().addCommandToLeftBar("Retour", null, e -> AffichageEvents.f.showBack());
       // l.setText(String.valueOf(TopicHome.topic.getId()));
       
        titre.setText("Découvrez l'évènement "+AffichageEvents.event.getTitre());
        titre.getAllStyles().setFgColor(ColorUtil.rgb(0,128,0));
        lieu.setText("Qui se tiendra à: "+AffichageEvents.event.getLieu());
      //  date.setText(sm.format(TopicHome.topic.getTopic_Date()));
        f.add(titre);
        f.add(date);
        f.add(lieu);
        f.add(img);
        f.add(description);
        
     
        f.show();
        
    }
    
}
