/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.RoundRectBorder;
import com.codename1.ui.util.Resources;
import entities.Annonce;
import entities.Evenement;
import entities.PublicationForum;
import java.io.IOException;
import java.util.ArrayList;
import services.ForumService;

/**
 *
 * @author arafe
 */
public class ForumForm extends BaseForm {

    Resources res;
//    Form f;
    // SpanLabel lb;
    static PublicationForum publication ;
    Label titre; 
    Label etat;
    Label description;
    Label nbr_vues;
    Label publisher;
    Container pubs;
    Container cnt ;
    private ArrayList<PublicationForum> publications = new ArrayList<>();

    public ForumForm(Resources res) {
       super("Forum wa nagham", BoxLayout.y());
       this.res = res;
       Toolbar tb = new Toolbar(true);
       setToolbar(tb);
       getTitleArea().setUIID("Container");
       setTitle("Newsfeed");
       getContentPane().setScrollVisible(false);
       super.addSideMenu(res);   
       ForumService FrmService = new ForumService();
       cnt = new Container();
       
       Button Btn = new Button(" + ");
       cnt.add(Btn);
       
       Container ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    publications = FrmService.getList2();
                    amount= publications.size();
                    
                }
                if (index + amount > publications.size()) {

                    amount = publications.size() - index;

                }
                if (amount <= 0) {
                    return null;
                }
                // System.out.println(amount);
                Component[] elments = new Component[amount];
                int i = 0;
                for (PublicationForum a : publications) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                        Container ls1 = new Container(BoxLayout.y());

                        Label lbl_name = new Label(a.getTitre());
                        Label lbl_desc = new Label(a.getDescription());
                        Label lbl_etat = new Label(a.getEtat().toString());
                        ls1.add(lbl_name);
                        ls1.add(lbl_desc);
                        ls1.add(lbl_etat);
                        element.add(ls);
                        ls.add(ls1);
                        Button b = new Button("Button");
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
//                                Dialog.show("Info", a.getTitre(), "ok", "");
                                new DetailsPublication(res);
                            }
                        });
                        element.setLeadComponent(b);
                        elments[i] = element;
                        i++;
                }
                return elments;
            }
        };
        ic.setScrollableY(false);
        
        cnt.add(ic);
        super.add(cnt);       
       
//       Button signIn = new Button("Sign In");
//       Container content = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//       content.add(signIn);
//       content.setScrollableY(true);
//       f.add(content);
//       f.show();
       
//       Container pubs = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//       ForumService service = new ForumService();
//
//       for (PublicationForum ev : service.getList2() ) 
//       {
//           Container pubContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
//           Container detailsContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
//           
//           titre= new Label();
//           publisher=new Label();
//           description=new Label();
//           etat = new Label();
////           nbr_vues = new Label();
//           
//           titre.setText(ev.getTitre());
////           publisher.setText("Par  :"+ev.getCreatedByName());
//           description.setText(ev.getDescription());
//           etat.setText(ev.getEtat());
////           nbr_vues.setText(String.valueOf(ev.getNbrVues()));
////            date.setText("Cree le " +ev.getDate().toString());
//           System.out.println("hellllll");
//           detailsContainer.add(titre);
//           detailsContainer.add(description);
//           detailsContainer.add(etat);
////           detailsContainer.add(publisher);
////           detailsContainer.add(nbr_vues);
//           
//           pubContainer.add(detailsContainer);
//           Button detailsPub = new Button(); 
//           detailsPub.addActionListener((e)->
//           {
//               publication = ev; 
//               new DetailsPublication(res);
//           });
//           pubContainer.setLeadComponent(detailsPub);
//           pubs.add(pubContainer);
//       }
//       
//        f.add(pubs);
//        f.show();
//        super.add(f);
//        super.add(pubs);
    }
//
//    public Form getF() {
//        return f;
//    }
//
//    public void setF(Form f) {
//        this.f = f;
//    }
//    
    
}