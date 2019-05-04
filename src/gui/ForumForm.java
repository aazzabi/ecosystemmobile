/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
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
    static PublicationForum publication ;
    Label titre; 
    Label etat;
    Label description;
    Label nbr_vues;
    Label publisher;
    Container pubs;
    Container cnt ;
    Container firstChild;
    private TextField rechercher;
    private Button btnRechercher;
    private Button btnAdd;
    
    private ArrayList<PublicationForum> publications = new ArrayList<>();

    public ForumForm(Resources res) {
       super("Forum wa nagham", BoxLayout.y());
       this.res = res;
       Toolbar tb = new Toolbar(true);
       setToolbar(tb);
       getTitleArea().setUIID("Container");
       getContentPane().setScrollVisible(false);
       this.header("forum w nagham");
       ForumService FrmService = new ForumService();
       cnt = new Container(BoxLayout.y());
//       
//       firstChild = new Container(BoxLayout.x());
//       
//       rechercher = new TextField();
//       btnRechercher = new Button("Chercher");
//       
//       firstChild.add(rechercher);
//       firstChild.add(btnRechercher);
//       
//              
        btnAdd = new Button("Ajouter une publication ");
        cnt.add(btnAdd);
       
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                (new NewPublicationForm(res)).show();
            }}
        );

       Container ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    publications = FrmService.getAllPublication();
                    amount= publications.size();
                }
                if (index + amount > publications.size()) {
                    amount = publications.size() - index;
                }
                if (amount <= 0) {
                    return null;
                }
                Component[] elments = new Component[amount];
                int i = 0;
                for (PublicationForum a : publications) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                    Container ls1 = new Container(BoxLayout.y());
                    Container ls2 = new Container(BoxLayout.y());
                    Container ls3 = new Container(BoxLayout.x());
                    Image img;
                    try {
                        img = Image.createImage("file:/C:/wamp/www/ecosystemweb/web/uploads/user/photo/" + a.getCreatedByPhoto()).fill(300, 300);
                        ImageViewer v = new ImageViewer(img);

                        Label lbl_name = new Label(a.getTitre());
                        Label lbl_desc = new Label(a.getDescription());
                        Label lbl_etat = new Label(a.getEtat().toString());
                        Label lbl_nbVues = new Label(String.valueOf(a.getNbrVues())+" Vues");
                        Label lbl_nbComm = new Label(String.valueOf(a.getNbrCommentaires())+" Commentaire");
                        ls1.add(v);
                        ls2.add(lbl_name);
                        ls2.add(lbl_desc);
                        ls3.add(lbl_etat);
                        ls3.add(lbl_nbVues);
                        ls3.add(lbl_nbComm);
                        ls2.add(ls3);
                        ls.add(ls1);
                        ls.add(ls2);
                        element.add(ls);
                        element.add(new Label("______________________________________________________________________"));
                        Button b = new Button("Button");
                        b.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                (new DetailsPublication(res, a)).show();
                            }
                        });
                        element.setLeadComponent(b);
                        elments[i] = element;
                        i++;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                }
                return elments;
            }
        };
        ic.setScrollableY(false);
        
//        cnt.add(firstChild);
        cnt.add(ic);
        super.add(cnt);       
    }
    
    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }
    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {
                updateArrowPosition(b, arrow);
            }
        });
    }
    public void header(String titre){
        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        super.addSideMenu(res);

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle(titre, barGroup);
        all.setUIID("SelectBar");
        
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(1, all),
                FlowLayout.encloseBottom(arrow)
        ));

        all.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(false);
            updateArrowPosition(all, arrow);
        });
        bindButtonSelection(all, arrow);
    }
    
}