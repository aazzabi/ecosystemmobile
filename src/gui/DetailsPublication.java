/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
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
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.CategoriePub;
import entities.CommentairePublication;
import entities.Evenement;
import entities.PublicationForum;
import entities.SignalisationCommentaire;
import entities.Utilisateur;
import java.util.ArrayList;
import services.ForumService;
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
    Container cnt, cnt1, cnt2, cnt3 , cnt4, cntSignalisation,commCont;
    Label description;
    Resources res;
    ComboBox<String> cbSignalisation;
    Button confirmeSign, btnAddComment;
    String cause;
    TextField comm;
    private ArrayList<CommentairePublication> commentaires = new ArrayList<>();

    
public DetailsPublication(Resources res, PublicationForum p) {
    super("Publication", BoxLayout.y());
    this.res = res;
    Toolbar tb = new Toolbar(true);
    setToolbar(tb);
    getTitleArea().setUIID("Container");
    getContentPane().setScrollVisible(false);
    //       super.addSideMenu(res);  
    this.header("Publication");
    ForumService FrmService = new ForumService();
    cnt = new Container(BoxLayout.y());
    cnt1 = new Container(BoxLayout.x());
    cnt2 = new Container(BoxLayout.y());
    cnt3 = new Container(BoxLayout.y());
    cnt4 = new Container(BoxLayout.x());
    Form f = new Form();

    f= new Form("",new BoxLayout(BoxLayout.Y_AXIS));
    titre = new Label(p.getTitre());
    etat = new Label(p.getEtat());
    nbr_vues = new Label(String.valueOf(p.getNbrVues())+ " Vue(s)");
    description = new Label(p.getDescription());
    publisher = new Label(p.getCreatedByName());
    
    publisher.setTextPosition(cnt3.RIGHT);
    etat.getAllStyles().setFgColor(0xff000);
    etat.getAllStyles().set3DTextNorth(true);
    titre.getAllStyles().set3DTextNorth(true);
    titre.getAllStyles().setUnderline(true);
    
    Style boxStyle = cnt2.getUnselectedStyle();
    boxStyle.setBgColor(0xeeeeee);
    boxStyle.setMargin(2, 1, 1, 1);
    boxStyle.setPadding(1, 1, 1, 1);
    
    setSameWidth(titre,nbr_vues,etat);
    cnt1.add(titre);
    cnt1.add(nbr_vues);
    cnt2.add(etat);
    cnt3.add(description);
    cnt4.add(publisher);

//    cnt.getAllStyles().setBorder(border, false);
    cnt.add(cnt1);
    cnt.add(cnt2);
    cnt.add(cnt3);
    cnt.add(cnt4);
    
    cnt.add(new Label("=============================================================="));
//    cnt.refreshTheme();
    Container ic = new InfiniteContainer() {
         @Override
         public Component[] fetchComponents(int index, int amount) {
             if (index == 0) {
                 commentaires = FrmService.getAllCommentsByPublication(p.getId());
                 amount= commentaires.size();

             }
             if (index + amount > commentaires.size()) {

                 amount = commentaires.size() - index;

             }
             if (amount <= 0) {
                 return null;
             }
             // System.out.println(amount);
             Component[] elments = new Component[amount];
             int i = 0;
             for (CommentairePublication a : commentaires) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                    Container ls1 = new Container(BoxLayout.y());

                    Label lbl_desc = new Label(a.getDescription());
                    Label lbl_publisher = new Label(a.getCreatedByName());
                    Label lbl_dislike = new Label(String.valueOf(a.getDislikes())+" Dislikes");
                    Label lbl_like = new Label(String.valueOf(a.getLikes())+" Likes\n \n \n");
                    ls1.add(lbl_desc);
                    ls1.add(lbl_publisher);
//                    ls1.add(lbl_like);
//                    ls1.add(lbl_dislike);
                    element.add(ls);
                    ls.add(ls1);
                    Button signaler = new Button(" Signaler");
                    signaler.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REPORT_PROBLEM, signaler.getUnselectedStyle()));
                    signaler.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            Dialog dlg = new Dialog("Choisissez la cause");
                            cntSignalisation= new Container(BoxLayout.y());
                            cbSignalisation = new ComboBox<String>();
                            cbSignalisation.addItem("");
                            cbSignalisation.addItem("Violence");
                            cbSignalisation.addItem("arnaque");
                            cbSignalisation.addItem("Harcelement");
                            cbSignalisation.addItem("Haine");
                            cbSignalisation.addActionListener((evta) -> {
                                cause = cbSignalisation.getSelectedItem();
                            });
                            confirmeSign = new Button("Signaler");
                            
                            cntSignalisation.add(cbSignalisation);
                            cntSignalisation.add(confirmeSign);
                            dlg.add(cntSignalisation);
                            confirmeSign.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    SignalisationCommentaire s = new SignalisationCommentaire(cause, a.getId(), Utilisateur.current_user.getId() );
                                    System.out.println(cause);
                                    FrmService.ajouterSignalisation(s);
                                    dlg.dispose();
                                }
                            });
                            int h = Display.getInstance().getDisplayHeight();
                            dlg.setDisposeWhenPointerOutOfBounds(true);
                            dlg.show(h /8 * 3,  h /8 * 3,0, 0);
                        }
                    });
                    Button like = new Button("");
                    Button dislike = new Button("");
                    like.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, like.getUnselectedStyle()));
                    dislike.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_DOWN, dislike.getUnselectedStyle()));
                    Container c1 = new Container(BoxLayout.x()) ;
                    Container c2 = new Container(BoxLayout.x()) ;
                    c1.add(like);
                    c1.add(lbl_like);
                    c1.add(dislike);
                    c1.add(lbl_dislike);
                    ls1.add(c1);
                    ls1.add(c2);
                    ls1.add(signaler);
                    like.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            (new ForumService()).like(a);
                            lbl_like.setText((new ForumService()).getNewLikes(a)+" Likes");
                        }
                    });
                    dislike.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent evt) {
                            (new ForumService()).dislike(a);
                            lbl_dislike.setText((new ForumService()).getNewDislikes(a)+" Dislikes");
                        }
                    });
                    ls1.add(new Label("--------------------------------------------------------------------------"));
//                    element.setLeadComponent(b);
                    elments[i] = element;
                    i++;
            }
            return elments;
        }
    };
    ic.setScrollableY(false);
    cnt.add(ic);
    commCont = new Container(BoxLayout.y());
    comm = new TextField();
    btnAddComment = new Button("Ajouter");
    setSameWidth(comm,btnAddComment);
    commCont.add(comm);
    commCont.add(btnAddComment);
    btnAddComment.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent evt) {
            CommentairePublication cpub = new CommentairePublication(comm.getText(), p.getId(), Utilisateur.current_user.getId());
            System.out.println(cpub.getIdPublication());
            System.out.println(cpub.getDescription());
            System.out.println(cpub.getCreatedBy());
            (new ForumService()).ajouterCommentaire(cpub);
        }
    });
    cnt.add(commCont);


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
