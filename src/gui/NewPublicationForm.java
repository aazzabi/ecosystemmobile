/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import entities.CategoriePub;
import entities.PublicationForum;
import entities.Utilisateur;
import java.util.ArrayList;
import services.ForumService;

/**
 *
 * @author arafe
 */
public class NewPublicationForm extends BaseForm {
    Resources res;
    static PublicationForum publication ;
    
    private TextField libelleP;
    private TextField descriptionP;
    private Button btnConfirmer;
    Container cnt; 
    Form formaa;
    Label libLabel;
    Label descLabel;
    Label categLabel;
    ComboBox cbCategorie;
    private int idc;

    private ArrayList<CategoriePub> categs = new ArrayList<>();

    public NewPublicationForm(Resources res) {
       super("Ajouter une nouvelle publication", BoxLayout.y());
       this.res = res;
       Toolbar tb = new Toolbar(true);
       setToolbar(tb);
       getTitleArea().setUIID("Container");
       getContentPane().setScrollVisible(false);
       this.header("Ajouter une nouvelle publication");
       ForumService FrmService = new ForumService();
       Container cnt = new Container();
       
       categs = FrmService.getAllCategories();
       cbCategorie = new ComboBox<CategoriePub>();
       for (CategoriePub c : categs){
           cbCategorie.addItem(c.getLibelle());
       }
       cbCategorie.addActionListener((evt) -> {
            idc = categs.get(cbCategorie.getSelectedIndex()).getId();
            //  System.out.println(Ccateg.getSelectedItem().toString());
        });
       libLabel = new Label("Libelle");
       descLabel = new Label("Description");
       categLabel = new Label("Categorie");
       libelleP = new TextField();
       descriptionP = new TextField();
       
       btnConfirmer = new Button("Confirmer");
       cnt.add(libLabel);
       cnt.add(libelleP);
       
       cnt.add(descLabel);
       cnt.add(descriptionP);
       cnt.add(categLabel);
       cnt.add(cbCategorie);
       cnt.add(btnConfirmer);
       super.add(cnt);
       
       btnConfirmer.addActionListener(new ActionListener(){
           @Override
           public void actionPerformed(ActionEvent evt) {
               System.out.println("add");
               PublicationForum publi = new PublicationForum(libelleP.getText(), descriptionP.getText(),idc, Utilisateur.current_user.getId());
               FrmService.ajouterPublication(publi);
               formaa = new DetailsPublication(res, publi);
                formaa.show();
           }
           
       });
    }

    public Container getFa() {
        return cnt;
    }

    public void setFa(Container cnt) {
        this.cnt = cnt;
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
