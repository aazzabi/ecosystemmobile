/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.db.Database;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import entities.Utilisateur;
import static gui.recyclage.BaseGui.MainForm;
import gui.recyclage.HomeGui;
import java.io.IOException;

/**
 *
 * @author actar
 */
public class BaseForm extends Form {

    public BaseForm() {
    }

    public BaseForm(Layout contentPaneLayout) {
        super(contentPaneLayout);
    }

    public BaseForm(String title, Layout contentPaneLayout) {
        super(title, contentPaneLayout);
    }

    public Component createLineSeparator() {
        Label separator = new Label("", "WhiteSeparator");
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    public Component createLineSeparator(int color) {
        Label separator = new Label("", "WhiteSeparator");
        separator.getUnselectedStyle().setBgColor(color);
        separator.getUnselectedStyle().setBgTransparency(255);
        separator.setShowEvenIfBlank(true);
        return separator;
    }

    //Blocks (GridLayout)
    public void AddBlocks1x1(Component... Components){
        AddBlocks_Custom(1, 1, Components);
    } 
    public void AddBlocks3x1(Component... Components){
        AddBlocks_Custom(3, 1, Components);
    } 
    public void AddBlocks2x2(Component... Components){
        AddBlocks_Custom(2, 2, Components);
    } 
    public void AddBlocks3x3(Component... Components){
        AddBlocks_Custom(3, 3, Components);
    }
    public void AddBlocks_Custom(int x, int y, Component... Components){
        MainForm.setLayout(new GridLayout(x, y));
        
        for (Component TempComponent : Components)
            MainForm.add(TempComponent); 
    }
    
    
    
    //sidemenu
    protected void addSideMenu(Resources res) {
        Toolbar tb = getToolbar();
        Image img = res.getImage("logoeco.png");
        if (img.getHeight() > Display.getInstance().getDisplayHeight() / 2) {
            img = img.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        Image placeholder = Image.createImage(45, 45, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder, false);
        ScaleImageLabel sl = new ScaleImageLabel(img);
        sl.setUIID("BottomPad");
        sl.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Image userImage;
        if (Utilisateur.current_user.getPhoto() == null || Utilisateur.current_user.getPhoto().equals("null")) {
            userImage = res.getImage("default_profile_picture.png");
            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    sl,
                    FlowLayout.encloseCenterBottom(
                            new Label(userImage.scaled(200, 200)))
            ));
        } else {
            tb.addComponentToSideMenu(LayeredLayout.encloseIn(
                    sl,
                    FlowLayout.encloseCenterBottom(
                            new Label(URLImage.createToStorage(encImage, "User" + Utilisateur.current_user.getPhoto(), Utilisateur.current_user.getPhoto(), URLImage.RESIZE_SCALE_TO_FILL).scaled(200, 200), "PictureWhiteBackgrond"))
            ));
        }
        tb.addMaterialCommandToSideMenu("Forum", FontImage.MATERIAL_VOICEMAIL, e -> new ForumForm(res).show());
        tb.addMaterialCommandToSideMenu("Reparation", FontImage.MATERIAL_TOLL, e -> new ReparationForm(res).show());
        tb.addMaterialCommandToSideMenu("Annonce", FontImage.MATERIAL_ADD_A_PHOTO, e -> new AllAnoncesForm(res).show());
        tb.addMaterialCommandToSideMenu("Recyclage", FontImage.MATERIAL_ADD_A_PHOTO, e -> new HomeGui().ShowForm());
        tb.addMaterialCommandToSideMenu("Events", FontImage.MATERIAL_ADD_A_PHOTO, e -> new AffichageCatEvts(res).show());
        tb.addMaterialCommandToSideMenu("Commandes", FontImage.MATERIAL_STYLE, e -> new CommandeForm(res).show());
        tb.addMaterialCommandToSideMenu("Livraisons", FontImage.MATERIAL_TIME_TO_LEAVE, e -> new LivraisonForm(res).show());
      if(Utilisateur.current_user.getRoles().contains("ROLE_LIVREUR"))
        {
             tb.addMaterialCommandToSideMenu("Espace Livreur", FontImage.MATERIAL_WORK, e -> new EspaceLivreur(res).show());
        }

       
        
      
      
     
     
     
     
     
     
        

        tb.addMaterialCommandToSideMenu("Logout", FontImage.MATERIAL_EXIT_TO_APP, e -> {
            try {
                Database db = Database.openOrCreate("EcoSystem.db");
                db.execute("delete from appstates");
                Utilisateur.current_user = null;
                new SignInForm(res).show();
            } catch (IOException ex) {
            }
        });
    }
    
}
