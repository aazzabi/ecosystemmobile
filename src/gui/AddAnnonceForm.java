/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.File;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import entities.Annonce;
import entities.Categorie_Annonce;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import services.AnnonceService;

/**
 *
 * @author anasc
 */
public class AddAnnonceForm extends BaseForm {

    Resources res;
    File file;
    Container cnt;
    TextField tnom, tprix;
    TextField tdesc;
    ComboBox Cregion, Ccateg;
    Button btnajout, btncapture, back;
    Image img, img1;
    ImageViewer vu1, vu2;
    private int idc;
    private String reg;
    private String path, absolutePathPhotoAnnonce;
    ArrayList<Categorie_Annonce> listCat = new ArrayList<>();
    ArrayList<String> Region = new ArrayList<>();

    public AddAnnonceForm(Resources res) {

        super("Annonces", BoxLayout.y());
        this.res = res;
        AnnonceService serviceAnnonce = new AnnonceService();
        cnt = new Container();
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getToolbar().addCommandToRightBar("Retour", null, (evt) -> {
            new AllAnoncesForm(res).show();

        });
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

        AnnonceService annonceS = new AnnonceService();
        listCat = annonceS.getAllCategories();
        Region = annonceS.getRegion();
        Ccateg = new ComboBox<Categorie_Annonce>();
        Cregion = new ComboBox<String>();
        for (Categorie_Annonce l : listCat) {
            Ccateg.addItem(l.getLibelle());
        }
        for (String l : Region) {
            Cregion.addItem(l);
        }
        Container element = new Container(BoxLayout.y());
        tnom = new TextField("", "Titre");
        tdesc = new TextField("", "Description", 500, TextArea.ANY);
        tprix = new TextField("", "Prix");
        btnajout = new Button("ajouter");
        btncapture = new Button("Capture");
        try {
            img = Image.createImage("file:/C:/Users/anasc/Downloads/Abstract-new-arrival-composition-with-flat-design/379874-PCNF9W-951.jpg").fill(300, 300);
            vu1 = new ImageViewer(img);
            element.add(vu1);
            element.add(tnom);
            element.add(tdesc);
            element.add(tprix);
            element.add(btncapture);
            element.add(Cregion);
            element.add(Ccateg);
            element.add(btnajout);
            cnt.add(element);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        btncapture.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Display.getInstance().openGallery(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent ev) {
                        if (ev != null && ev.getSource() != null) {
                            String filePath = (String) ev.getSource();
                            int fileNameIndex = filePath.lastIndexOf("/") + 1;
                            path = filePath.substring(fileNameIndex);

                            System.out.println(path);
                            Image imgg;
                            try {
                                imgg = Image.createImage(filePath);
                                if (imgg != null) {

                                    file = new File(filePath);
                                    absolutePathPhotoAnnonce = filePath;
                                    System.err.println(file);

                                }
                            } catch (IOException ex) {
                            }
                        }
                    }
                }, Display.GALLERY_IMAGE);
            }
        });
        Ccateg.addActionListener((evt) -> {

            idc = listCat.get(Ccateg.getSelectedIndex()).getId();
            //  System.out.println(Ccateg.getSelectedItem().toString());
        });
        Cregion.addActionListener((evt) -> {
            reg = Cregion.getSelectedItem().toString();
            // System.out.println(Cregion.getSelectedItem());
        });
        btnajout.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
//                if (tnom.getText().equals("") || tdesc.getText().equals("") || tprix.getText().equals("")) {
                        
                    
//                } else {
//                    Annonce a = new Annonce(tnom.getText(), tdesc.getText(), Double.parseDouble(tprix.getText()), reg, "Capture.PNG", idc, Utilisateur.current_user.getId());
//                    annonceS.ajouterAnnonce(a);
//                    new AllAnoncesForm(res).show();
//                }
                //copyImages.deplacerVers(, absolutePathPhotoAnnonce, "C:\\ecosystemjava\\src\\res\\Annonce\\photo\\");
                // copyImages.deplacerVers(path, absolutePathPhotoAnnonce, "C:\\wamp\\www\\ecosystemweb\\web\\uploads\\Annonce\\photo\\");

            }
        });
        add(cnt);
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
