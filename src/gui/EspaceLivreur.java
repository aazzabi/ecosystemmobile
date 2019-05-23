/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import com.codename1.ui.util.Resources;
import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
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
import services.ServiceUser;
/**
 *
 * @author Aziz
 */
public class EspaceLivreur extends BaseForm{
    Resources res;
 Container cnt;
 ArrayList<Livraison> listliv = new ArrayList<>();

    public EspaceLivreur(Resources res) {
        
        super("Espace Livreur", BoxLayout.y());
         CommandeService commandeService = new CommandeService();
         ServiceUser userService = new ServiceUser();
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
        RadioButton all = RadioButton.createToggle("Annonces", barGroup);
        all.setUIID("SelectBar");
//System.out.println("yeeeah sexxy lady");
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
      
      Button Back=new Button();
      Label bien=new Label("Vos Livraisons : ");
      bien.getStyle().setFgColor(0x220000);
      row.add(Back);
      Back.setIcon(FontImage.createMaterial(FontImage.MATERIAL_REPLAY, j.getUnselectedStyle()));
      row.add(bien);
      Back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
              AccueilForm a=new AccueilForm(res);
                            a.show();
            }
        });
        
      
      
         Container ic = new InfiniteContainer() {
            double prix_total=0;
            
            @Override
            public Component[] fetchComponents(int index, int amount) {
                if (index == 0) {
                    listliv=commandeService.getLivLivreur(Utilisateur.current_user.getId());
                    amount = listliv.size();

                }
                if (index + amount > listliv.size()) {

                    amount = listliv.size() - index;

                }
                if (amount <= 0) {
                    return null;
                }
                
                Component[] elments = new Component[amount];
                int i = 0;
                int i2=1;
                for (Livraison a : listliv) {
                    int id_livraison=a.getId();
                    //System.out.println("yeeeah sexxy lady boucle");
                    Container element = new Container(BoxLayout.y());
                    Container ls = new Container(BoxLayout.x());
                   
                    try { 
                        Image img = Image.createImage("file:/C:/wamp/www/ecosystemweb/web/uploads/Annonce/photo/livraison.png").fill(300, 300);
                        Button j = new Button("");
                        ImageViewer v = new ImageViewer(img);
                        Container ls1 = new Container(BoxLayout.y());
                        Container buttons = new Container(BoxLayout.x());
                       
                        Utilisateur p=userService.getUserData(a.getId_utilisateur());
                        Label id_com = new Label(Integer.toString(a.getId_commande()));
                  
                       System.out.println(a.getId_utilisateur());
                   String tt=p.getNom()+" "+p.getPrenom();
                   Label nom=new Label("Nom & Prénom Client :");
                   Label id_cmdd=new Label("Id Commande :");
                   Label etatliv=new Label("Etat Livraison :");
                     Label tel=new Label("Téléphone Client :");
                     Label adresse2=new Label("Adresse Client :");
                      Label Date=new Label("Date :");
                     
                       String foufa =a.getDate_livraison().toString();
                           String f1=foufa.substring(0,10);
                        String f3=foufa.substring(30,34);
                        Label date = new Label(f1+" "+f3);
                        
                        Label nomprenom_u = new Label(tt);
                         Label tel_user = new Label(p.getNumtel());
                         
                        Label etat = new Label(a.getEtat_livraison());
                        Label adresse = new Label(a.getAdresse_livraison());
                        
                        Button valider=new Button("Valider Livraison");
                        id_com.getStyle().setFgColor(0x220000);
                        nomprenom_u.getStyle().setFgColor(0x220000);
                       tel_user.getStyle().setFgColor(0x220000);
                         etat.getStyle().setFgColor(0x220000);
                          adresse.getStyle().setFgColor(0x220000);
                           Date.getStyle().setFgColor(0xE54040);
                            
                            date.getStyle().setFgColor(0x220000);
                            adresse2.getStyle().setFgColor(0xE54040);
                              nom.getStyle().setFgColor(0xE54040);
                                tel.getStyle().setFgColor(0xE54040);
 id_cmdd.getStyle().setFgColor(0xE54040);
  etatliv.getStyle().setFgColor(0xE54040);
  
                        Label Article=new Label(""+i2);
                         Article.getStyle().setFgColor(0x220000);
                          ls.add(Article);
                          
                        ls.add(v);
                         ls1.add(id_cmdd);
                        ls1.add(id_com);
                        ls1.add(nom);
                        ls1.add(nomprenom_u);
                        ls1.add(tel);
                        ls1.add(tel_user);
                        ls1.add(adresse2);
                         ls1.add(adresse);
                         ls1.add(etatliv);
                        ls1.add(etat);
                        ls1.add(Date);
                        ls1.add(date);
                       
                        ls1.add(buttons);
                        buttons.add(valider);
                        
                        element.add(ls);
                        ls.add(ls1);

                        elments[i] = element;
                        i++;
                        i2++;
                               valider.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                ConfirmerLivraison a=new ConfirmerLivraison(res,id_livraison);
                            a.show();
                            }
                        });
                               
 
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    
                    
                    
                    
                }
                
              
                return elments;
                
                
            }
           
    
            
        };
        ic.setScrollableY(false);
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
 
 
}
