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
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import entities.Evenement;
import services.ServiceUser;

/**
 *
 * @author Rania
 */
public class DetailsPublication extends BaseForm {
    
    Label titre ; 
    Label date ;
    static Form f;
    Label nbr_vues;
    Label publisher;
    Label etat;
    Label description;
    Resources res;

    
     public DetailsPublication(Resources res) {
       super("Forum wa nagham", BoxLayout.y());
       this.res = res;
       Toolbar tb = new Toolbar(true);
       setToolbar(tb);
       getTitleArea().setUIID("Container");
       setTitle("Newsfeed");
       getContentPane().setScrollVisible(false);
       super.addSideMenu(res);   

       
       f= new Form("",new BoxLayout(BoxLayout.Y_AXIS));
      //  UserService serviceuser =new UserService();
       // l= new Label();
        titre = new Label();
        nbr_vues = new Label ();
        etat = new Label();
        description = new Label();
        publisher = new Label();
        
        SpanLabel description  = new SpanLabel(ForumForm.publication.getDescription());
        description.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
//        f.getToolbar().addCommandToLeftBar("Retour", null, e -> ForumForm.f.showBack());
       
        etat.setText("etat : "+ForumForm.publication.getEtat());
        
        f.add(titre);
        f.add(publisher);
        f.add(etat);
        f.add(nbr_vues);
        f.add(description);
        f.show();
        
    }
    
}
