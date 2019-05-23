/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import entities.Annonce;
import java.io.IOException;
import java.util.ArrayList;
import services.AnnonceService;
import services.PanierService;
import entities.AnnoncePanier;

/**
 *
 * @author anasc
 */
public class AllAnoncesForm extends BaseForm {

    Resources res;
    private ArrayList<Annonce> annonces = new ArrayList<>();
    private ArrayList<AnnoncePanier> annoncesp = new ArrayList<>();
    Container cnt;

    public AllAnoncesForm(Resources res) {
        super("Annonces", BoxLayout.y());
        AnnonceService serviceAnnonce = new AnnonceService();
        PanierService panierService = new PanierService();
        cnt = new Container();
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
       
        getContentPane().setScrollVisible(false);

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
        RadioButton all = RadioButton.createToggle("Annonces", barGroup);
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

      Container row = new Container(BoxLayout.x());
        Button ajout = new Button(" + ");

        Button Tailler = new Button("");
       Tailler.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                int x=1;
                                annoncesp=panierService.getAllAnnoncesP();
                                if(annoncesp.size()>0)
                                {
                                ShowPanier a=new ShowPanier(res);
                            a.show();
                                }
                                else
                                {
                                 Dialog.show("Information", "Votre Panier est Vide , Veuillez le remplir", "OK", "Cancel");
                                }

                            }
                        });
       
        
        Label y = new Label("                                                   ");
         Tailler.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, Tailler.getUnselectedStyle()));
         Label c = new Label("(0)");
         annoncesp=panierService.getAllAnnoncesP();
                              int sizePanier=annoncesp.size();
                              c.setText("("+sizePanier+")");
                              c.getStyle().setFgColor(0x220000);
        row.add(ajout);
        row.add(y);
        row.add(Tailler);
        row.add(c);
        cnt.add(row);
        
        Container ic = new InfiniteContainer() {
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    annonces = serviceAnnonce.getAllAnnonces();
                    amount = annonces.size();

                }
                if (index + amount > annonces.size()) {

                    amount = annonces.size() - index;

                }
                if (amount <= 0) {
                    return null;
                }
                // System.out.println(amount);
                Component[] elments = new Component[amount];
                int i = 0;
                for (Annonce a : annonces) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                    try {
                        Image img = Image.createImage("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + a.getPhoto()).fill(500, 500);

                        ImageViewer v = new ImageViewer(img);
                        Container ls1 = new Container(BoxLayout.y());
                        Container buttons = new Container(BoxLayout.x());
                        Label lbl_name = new Label(a.getTitre());
                        Label lbl_desc = new Label(a.getDescription());
                        Label lbl_prix = new Label(a.getPrix().toString());
                        Button j = new Button("");
                        Button show = new Button("");
                        Button panier = new Button("");
                        int id_annonce=a.getId();
                        
                        
                        
                        j.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, j.getUnselectedStyle()));
                        show.setIcon(FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY, j.getUnselectedStyle()));
                        panier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, j.getUnselectedStyle()));
                        buttons.add(j);
                        buttons.add(show);
                        buttons.add(panier);
                        ls.add(v);
                        ls1.add(lbl_name);
                        ls1.add(lbl_desc);
                        ls1.add(lbl_prix);
                        ls1.add(buttons);
                        element.add(ls);
                        ls.add(ls1);

                        panier.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                int x=1;
                                annoncesp=panierService.getAllAnnoncesP();
                                 for (AnnoncePanier ty : annoncesp) {
                                   if(ty.getId_annonce().equals(Integer.toString(a.getId()))) 
                                   {
                                   x=2;
                                   }
                                   else
                                   {
                                   x=1;
                                   }
                                 }
                                 if(x==1)
                                 {
                                 AnnoncePanier ap = new AnnoncePanier(Integer.toString(a.getId()),a.getTitre(),a.getDescription(),a.getPrix(),a.getRegion(),a.getPhoto());
                              panierService.ajouterAnnonce(ap);
                              System.out.println("C bn ajout panier ");
                              annoncesp=panierService.getAllAnnoncesP();
                              int sizePanier=annoncesp.size();
                              c.setText("("+sizePanier+")");
                              c.getStyle().setFgColor(0x220000);
                                 }
                                 else
                                 {
                                   Dialog.show("Erreur", "Article Déja Ajouté au Panier", "OK", "Cancel");
                                 }
                                 
                            }
                        });
                        
                        
                        elments[i] = element;
                        i++;
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // element.setLeadComponent(b);

                }
                return elments;
            }
        };
        ic.setScrollableY(false);
        cnt.add(ic);
        ajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                new AddAnnonce(res).show();
            }
        });
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
}
