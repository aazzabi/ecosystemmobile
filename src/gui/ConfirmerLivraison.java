/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.components.FloatingHint;
import com.codename1.ui.util.Resources;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.InfiniteContainer;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
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
import entities.AnnoncePanier;
import entities.Commande;
import entities.Livraison;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;

import services.CommandeService;
import services.PanierService;
import com.codename1.payment.Product;
import com.codename1.payment.Purchase;
import com.codename1.payment.Receipt;
import com.codename1.payment.PurchaseCallback;
import com.codename1.payment.ReceiptStore;
import com.codename1.payment.RestoreCallback;
import java.util.*;
import services.TwilioSMS;

import com.codename1.components.FloatingHint;
import com.codename1.components.ImageViewer;
import com.codename1.db.Database;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import entities.Categorie_Annonce;
import entities.Utilisateur;
import java.io.IOException;
import services.AnnonceService;
import services.ServiceUser;

/**
 *
 * @author Aziz
 */
public class ConfirmerLivraison extends BaseForm{
    Resources res;
 Container cnt;
TextField code;
    public ConfirmerLivraison(Resources res,int id) {
   super("Annonces", BoxLayout.y());
        this.res = res;
        AnnonceService serviceAnnonce = new AnnonceService();
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
        RadioButton all = RadioButton.createToggle("Ajouter Annonce", barGroup);
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

       
        Container element = new Container(BoxLayout.y());
        Label ao=new Label("Entrer le Code de Livraison");
        code = new TextField("", "Code Livraison", 4, TextField.PASSWORD);
     
        Button confirmer = new Button("Confirmer");
       
      
           element.add(ao);
            element.add(code);
           element.add(confirmer);
            cnt.add(element);
            
            confirmer.addActionListener(new ActionListener() {
       @Override
       public void actionPerformed(ActionEvent evt) {
           CommandeService commandeService = new CommandeService();
                ArrayList<Livraison> l = new ArrayList<>();
                l=commandeService.getAllLivraisons();
                  Livraison lp=new Livraison();
                 for (Livraison a : l) {
                 if(a.getId()==id)
                 {
              
                lp=a;
              
                 }
                 }
              
                 if(lp.getCode_livraison().equals(code.getText()))
                 {
                    // commandeService.chnagerEtat(Utilisateur.current_user.getId(),id);
                   Dialog.show("Information", "Confirmation de livraison effectué aves succés", "OK", "Cancel");
                   commandeService.changerEtatLiv(id);
                   EspaceLivreur a=new EspaceLivreur(res);
                            a.show();
                 }
                 else
                 {
                   Dialog.show("Erreur", "Code Incorrect", "OK", "Cancel");
                 }
                
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
