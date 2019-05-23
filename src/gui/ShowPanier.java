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
import com.codename1.ui.Font;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import entities.Annonce;
import entities.AnnoncePanier;
import entities.Commande;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import services.AnnonceService;
import services.CommandeService;
import services.PanierService;

/**
 *
 * @author Aziz
 */
public class ShowPanier extends BaseForm{
private ArrayList<AnnoncePanier> annoncesp = new ArrayList<>();
    Container cnt;
     Resources res;
     int k2;
     double prixtot;

    public ShowPanier(Resources res) {
        super("Panier", BoxLayout.y());
      
        PanierService panierService = new PanierService();
          CommandeService commandeService = new CommandeService();
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
        g.setColor(0x29a02d);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0x29a02d);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        super.addSideMenu(res);

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton all = RadioButton.createToggle("Articles Panier", barGroup);
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
        
Button j = new Button("");
      Container row = new Container(BoxLayout.x());
      Label pt=new Label("                                     ");
      Button Back=new Button();
      Back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REPLAY, j.getUnselectedStyle()));
      row.add(Back);
      Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              AllAnoncesForm a=new AllAnoncesForm(res);
                            a.show();
            }
        });
       row.add(pt);
       
        cnt.add(row);
      
        Container ic = new InfiniteContainer() {
              int k=0;
            double prix_total=0;
            
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    annoncesp=panierService.getAllAnnoncesP();
                    amount = annoncesp.size();

                }
                if (index + amount > annoncesp.size()) {

                    amount = annoncesp.size() - index;

                }
                if (amount <= 0) {
                    return null;
                }
                
                Component[] elments = new Component[amount];
                int i = 0;
                int i2=1;
                for (AnnoncePanier a : annoncesp) {
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                    try {
                        k=commandeService.verifLigne(a.getId_annonce());
                        k2=k;
                        
                        Image img = Image.createImage("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/" + a.getPhoto()).fill(300, 300);
Button j = new Button("");
                        ImageViewer v = new ImageViewer(img);
                        Container ls1 = new Container(BoxLayout.y());
                        Container buttons = new Container(BoxLayout.x());
                          Label lbl_name1 = new Label("Nom :");
 Label lbl_desc1 = new Label("Description :");
  Label lbl_prix1 = new Label("Prix :");
                        Label lbl_name = new Label(a.getTitre());
                        Label lbl_desc = new Label(a.getDescription());
                        Label lbl_prix = new Label(Double.toString(a.getPrix()));
                        Button delete=new Button();
                        delete.setIcon(FontImage.createMaterial(FontImage.MATERIAL_DELETE, j.getUnselectedStyle()));
                       prix_total=prix_total+a.getPrix();
                        lbl_name.getStyle().setFgColor(0x220000);
                        lbl_desc.getStyle().setFgColor(0x220000);
                        lbl_prix.getStyle().setFgColor(0x220000);
                       
                        lbl_name1.getStyle().setFgColor(0xE54040);
                        lbl_desc1.getStyle().setFgColor(0xE54040);
                        lbl_prix1.getStyle().setFgColor(0xE54040);
                        
                        Label Article=new Label("Article "+i2);
                         Article.getStyle().setFgColor(0x220000);
                         //Article.getStyle().setFont(Font.STYLE_ITALIC);
                        ls.add(Article);
                        ls.add(v);
                        ls1.add(lbl_name1);
                        ls1.add(lbl_name);
                        ls1.add(lbl_desc1);
                        ls1.add(lbl_desc);
                        ls1.add(lbl_prix1);
                        ls1.add(lbl_prix);
                        
                        ls1.add(buttons);
                        buttons.add(delete);
                        element.add(ls);
                        ls.add(ls1);

                        elments[i] = element;
                        i++;
                        i2++;
                        
                        delete.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                panierService.supprimerAnnonce(a.getId());
                                Dialog.show("Information", "Article Supprimé du Panier", "OK", "Cancel");
                             //ls.setHidden(true);
                             ShowPanier r=new ShowPanier(res);
                            r.show();

                            }
                        });
                        
                      
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    // element.setLeadComponent(b);

                }
                pt.setText("                                             "+"Prix Total : "+Double.toString(prix_total)+" DT");
                pt.getStyle().setFgColor(0xFF5C5C);
                prixtot=prix_total;
                return elments;
                
                
            }
           
           
             
            
        };
        ic.setScrollableY(false);
        
        cnt.add(ic);
        Container CMD = new Container(BoxLayout.x());
      Button cmd = new Button("Vider Panier");
      
     
      
          Button cmd1 = new Button("Passer Commande");
        CMD.add(cmd);
        CMD.add(cmd1);
        cnt.add(CMD);
        
        cmd1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                System.out.println("k : "+k2);
                if(k2>=1)
                {
                 Dialog.show("Erreur","Commande Déja Existante", "OK", "Cancel"); 
                }
                else
                {
                 Commande c=new Commande("En cours",Utilisateur.current_user.getId(),prixtot);
               panierService.ajouterCommande(c);
                panierService.viderPanier();
                CommandeForm a=new CommandeForm(res);
                            a.show();
                            Dialog.show("Information", "Votre Commande vient d'être confirmé ", "OK", "Cancel");  
                
                }
               
            }
        });
        
        
        
        
         cmd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                panierService.viderPanier();
                cmd.setHidden(true);
                ic.setHidden(true);
                row.setHidden(true);
                CMD.setHidden(true);
                Container PanierVide = new Container(BoxLayout.x());
                Label txt= new Label("Votre Panier est Vide");
                pt.getStyle().setFgColor(0x220000);
                PanierVide.add(txt);
                cnt.add(PanierVide);
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
