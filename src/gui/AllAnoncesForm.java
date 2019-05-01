/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Command;
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
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import com.sun.webkit.Disposer;
import entities.Annonce;
import entities.Categorie_Annonce;
import entities.Utilisateur;
import entities.signalAnnonce;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import services.AnnonceService;
import services.signalAnnonceService;

/**
 *
 * @author anasc
 */
public class AllAnoncesForm extends BaseForm {

    Resources res;
    private ArrayList<Annonce> annonces = new ArrayList<>();
    Container cnt;
    ArrayList<Categorie_Annonce> listCat = new ArrayList<>();
    ArrayList<signalAnnonce> lisS = new ArrayList<>();

    public AllAnoncesForm(Resources res) {
        super("Annonces", BoxLayout.y());
        AnnonceService serviceAnnonce = new AnnonceService();
        cnt = new Container();
        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        getContentPane().setScrollVisible(false);
        getToolbar().addCommandToRightBar("Retour", FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_LEFT, getUnselectedStyle()), (evt) -> {
            new AccueilForm(res).show();

        });
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
        listCat = serviceAnnonce.getAllCategories();
        Button ajout = new Button(" + ");

        Button Tailler = new Button("");
        Tailler.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, Tailler.getUnselectedStyle()));
        Label c = new Label("aaaaaa");
        row.add(ajout);
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
                        Image img = Image.createImage("file:/C:/wamp64/www/ecosystemweb/web/uploads/Annonce/photo/" + a.getPhoto()).fill(500, 500);

                        ImageViewer v = new ImageViewer(img);
                        Container ls1 = new Container(BoxLayout.y());
                        Container buttons = new Container(BoxLayout.x());
                        Label lbl_name = new Label(a.getTitre());
                        Label lbl_desc = new Label(a.getDescription());
                        Label lbl_prix = new Label(a.getPrix().toString());
                        Button j = new Button("");
                        Button show = new Button("");
                        Button panier = new Button("");
                        Button signaler = new Button("");
                        j.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, j.getUnselectedStyle()));
                        show.setIcon(FontImage.createMaterial(FontImage.MATERIAL_MORE, show.getUnselectedStyle()));
                        panier.setIcon(FontImage.createMaterial(FontImage.MATERIAL_SHOPPING_CART, panier.getUnselectedStyle()));
                        signaler.setIcon(FontImage.createMaterial(FontImage.MATERIAL_WARNING, signaler.getUnselectedStyle()));
                        buttons.add(j);
                        buttons.add(show);
                        buttons.add(signaler);
                        buttons.add(panier);
                        ls.add(v);
                        ls1.add(lbl_name);
                        ls1.add(lbl_desc);
                        ls1.add(lbl_prix);
                        ls1.add(buttons);
                        element.add(ls);
                        ls.add(ls1);
                        show.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                try {
                                    new ShowAnnonceForm(res, a).show();
                                } catch (IOException ex) {
                                    ex.printStackTrace();
                                }
                            }
                        });
                        signaler.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
                                Dialog c = new Dialog("Signaller Annonce");
                                c.setLayout(BoxLayout.y());
                                RadioButton rb1 = new RadioButton("Violence");
                                RadioButton rb2 = new RadioButton("arnaque");
                                RadioButton rb3 = new RadioButton("Harcelement");
                                RadioButton rb4 = new RadioButton("Discour insitant à la haine");
                                new ButtonGroup(rb1, rb2, rb3, rb4);
                                Container x = new Container(BoxLayout.x());
                                Button ok = new Button("ok");
                                Button annButton = new Button("annuler");
                                x.add(ok).add(annButton);
                                c.add(rb1).add(rb2).add(rb3).add(rb4).add(x);
                                annButton.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {
                                        c.dispose();
                                    }
                                });
                                ok.addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent evt) {
                                        signalAnnonceService snp = new signalAnnonceService();
//                                        lisS = snp.getCountSignaAnnonces();
                                        String rb = "";
                                        if (rb1.isSelected() == true) {
                                            rb = rb1.getText();
                                        } else if (rb2.isSelected() == true) {
                                            rb = rb2.getText();
                                        } else if (rb3.isSelected() == true) {
                                            rb = rb3.getText();
                                        } else if (rb4.isSelected() == true) {
                                            rb = rb4.getText();
                                        }
                                        signalAnnonce sn = new signalAnnonce(a.getId(), Utilisateur.current_user.getId(), rb);
                                        System.out.println(sn.toString());
                                        snp.ajouterSigna(sn);
                                        verifSignal();
                                        c.dispose();
                                    }

                                    private void verifSignal() {
                                        signalAnnonceService snp = new signalAnnonceService();
                                        AnnonceService as = new AnnonceService();
                                        lisS = snp.getCountSignaAnnonces();
                                        System.out.println(lisS);
                                        for (signalAnnonce sn : lisS) {
                                            if (sn.getCounts() >= 10) {
                                                as.supprimerAnnonce(sn.getAnnonce_id());
                                                System.out.println("supprimer");
                                            }
                                        }
                                    }

                                });
                                c.showDialog();

                            }
                        });
                        j.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent evt) {
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
        ic.setScrollableY(
                false);
        cnt.add(ic);

        ajout.addActionListener(
                new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt
            ) {
                new AddAnnonceForm(res).show();
            }
        }
        );

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
