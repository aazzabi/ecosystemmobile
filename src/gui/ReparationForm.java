/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.components.InteractionDialog;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
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
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.AnnounceRep;
import entities.Reparateur;
import entities.Reparation;
import entities.Utilisateur;
import java.io.IOException;
import java.util.ArrayList;
import services.AnnonceRepService;
import services.ReparationService;
import services.ServiceUser;

/**
 *
 * @author actar
 */
public class ReparationForm extends BaseForm {

    Resources res;
    Container mainContainer = new Container();
    int v = 0;
    private static String i;

    private ArrayList<AnnounceRep> annonces = new ArrayList<>();
    private ArrayList<Reparateur> reparateurs = new ArrayList<>();
    private ArrayList<Reparation> reparations = new ArrayList<>();

    public ReparationForm(Resources res) {
        super("Reparation", BoxLayout.y());

        this.res = res;
        Toolbar tb = new Toolbar(true);
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Reparation");
        getContentPane().setScrollVisible(false);
        Style s = UIManager.getInstance().getComponentStyle("TitleCommand");
        FontImage icon = FontImage.createMaterial(FontImage.MATERIAL_ADD, s);

        super.addSideMenu(res);
        tb.addCommandToRightBar("Ajout", icon, e -> {

            InteractionDialog dlg = new InteractionDialog("Ajout d'une nouvelle annonce de réparation ");
            dlg.setLayout(new BorderLayout());

//                                            dlg.add(BorderLayout.NORTH, new Label("Créer une nouvelle annonce !"));
//                                            Container champs = new Container(BoxLayout.x());
            TextField titre = new TextField("", "Titre de l'annonce");
            TextField desc = new TextField("", "Description");
            Button btn = new Button("Capture Image");
            TextField path = new TextField("");

            ComboBox<String> categorie = new ComboBox("Téléphone", "Electroménager", "Meuble");

//                                           
//                                           
//                                
            dlg.add(BorderLayout.CENTER, BoxLayout.encloseY(titre, desc, categorie, btn));

            Button OK = new Button("Confirmer");
            Button close = new Button("Fermer");

            btn.addActionListener(x -> {
                ScaleImageLabel imageForm = new ScaleImageLabel();

                i = Capture.capturePhoto(Display.getInstance().getDisplayWidth(), -1);
                if (i != null) {
                    Image im;
                    try {
                        im = Image.createImage(i).scaledSmallerRatio(1000, 500);
                        imageForm.setIcon(im);
                        refreshTheme();
                        System.out.println("path :" + i);
                        path.setText(i);
                        ;
                    } catch (IOException ex) {
                        System.out.println("Could not load image!");
                    }
                }
            });

            OK.addActionListener((ee) -> {
                AnnounceRep ann = new AnnounceRep();
                ann.setCategorie(categorie.getModel().getItemAt(categorie.getModel().getSelectedIndex()));
                System.out.println("Categorie " + categorie.getModel().getItemAt(categorie.getModel().getSelectedIndex()));
                ann.setTitre(titre.getText().toString());
                ann.setDescription(desc.getText().toString());
                ann.setUserId(Utilisateur.current_user.getId());
                ann.setUrlPhoto(path.getText());
                AnnonceRepService.ajouterAnnonce(ann);
                dlg.dispose();

            });
            close.addActionListener((ee) -> {
                dlg.dispose();
            });
            Container btnsgroups = new Container(BoxLayout.x());
            btnsgroups.add(OK);
            btnsgroups.add(close);
            dlg.addComponent(BorderLayout.SOUTH, btnsgroups);
            Dimension pre = dlg.getContentPane().getPreferredSize();
            dlg.showPopupDialog(getComponentAt(getComponentCount() - 1));

        });

        Tabs swipe = new Tabs();

        Label espace1 = new Label();
        Label espace2 = new Label();
        Label espace3 = new Label();

        newTab(swipe, res.getImage("imageRep2.png"), espace1, "Liste des annonces de réparation", "Nombre d'annonce de réparation : " + AnnonceRepService.getAllAnnonces().size());
        newTab(swipe, res.getImage("partenariat.jpg"), espace2, " Nombre de réparateur pro ", "Nombre de réparateurs :" + ServiceUser.getTtReps().size());
        newTab(swipe, res.getImage("ap-environnement-projet.jpg"), espace3, " Mes réparations en cours ", "");
        //******************************************************************************************************************//

        swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

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
        //******************************************************************************************************************//

        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int i = 0; i < rbs.length; i++) {
            rbs[i] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[i].setPressedIcon(selectedWalkthru);
            rbs[i].setUIID("Label");
            radioContainer.add(rbs[i]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, espace1, espace2, espace3);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton annonce = RadioButton.createToggle("Annonce de réparation", barGroup);
        annonce.setName("annonce");
        annonce.setUIID("SelectBar");
        RadioButton listRep = RadioButton.createToggle("List des réparateurs", barGroup);
        listRep.setName("listRep");
        listRep.setUIID("SelectBar");
        RadioButton repEnCours = RadioButton.createToggle("Mes réparations en cours", barGroup);
        repEnCours.setName("repEnCours");
        repEnCours.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, annonce, listRep, repEnCours),
                FlowLayout.encloseBottom(arrow)
        ));

        annonce.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(annonce, arrow);
        });
        bindButtonSelection(annonce, arrow);
        bindButtonSelection(listRep, arrow);
        bindButtonSelection(repEnCours, arrow);

        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

    }
    //******************************************************************************************************************//

    private void newTab(Tabs swipe, Image img, Label espace, String TitreTab, String text) {

        Label titre = new Label(TitreTab);
        titre.setTextPosition(RIGHT);

        ScaleImageLabel image = new ScaleImageLabel(img);
        image.setUIID("Container");
        image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        Label overlay = new Label(" ", "ImageOverlay");

        Container cnt;
        cnt = LayeredLayout.encloseIn(image, overlay, BorderLayout.south(
                BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                        FlowLayout.encloseIn(titre),
                        espace
                )
        ));

        swipe.addTab("", cnt);
    }

    private void updateArrowPosition(Button b, Label arrow) {
        arrow.getUnselectedStyle().setMargin(LEFT, b.getX() + b.getWidth() / 2 - arrow.getWidth() / 2);
        arrow.getParent().repaint();

    }

    private void bindButtonSelection(Button b, Label arrow) {
        b.addActionListener(e -> {
            if (b.isSelected()) {

                updateArrowPosition(b, arrow);
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                //////////////////////////////////////////////////////ANNONCES ////////////////////////////////////////////////////////////////////////////////                               
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                               
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
                if (b.getName().equals("annonce")) {
                    System.out.println("Annonces");
                    mainContainer.removeAll();
                    Container ic = new InfiniteContainer() {
                        @Override
                        public Component[] fetchComponents(int index, int amount) {
                            if (index == 0) {
                                annonces = AnnonceRepService.getAllAnnonces();
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
                            for (AnnounceRep a : annonces) {
                                Container element = new Container(BoxLayout.y());
                                Container ls = new Container(BoxLayout.x());
                                try {
                                    System.out.println("file:/C:/wamp64/www/ecosystemweb/web/uploads/annoncerep/photos/" + a.getUrlPhoto());
                                    Image img = Image.createImage("file:/C:/wamp64/www/ecosystemweb/web/uploads/annoncerep/photos/" + a.getUrlPhoto()).fill(500, 500);

                                    //lS containter for photo / nomRep
                                    //LS1 container for other information
                                    ImageViewer v = new ImageViewer(img);
                                    Container ls1 = new Container(BoxLayout.y());
                                    Label lbl_titre = new Label("Titre : " + a.getTitre());
                                    lbl_titre.setUIID("Rep_Titre");
                                    Label lbl_desc1 = new Label("Description");
                                    Label lbl_NomUser1 = new Label("Demandeur");
                                    lbl_desc1.setUIID("Rep_Titre");

                                    Label lbl_desc = new Label(a.getDescription());
                                    lbl_desc.setUIID("CenterLabel");
                                    Label lbl_cat = new Label("Catégorie : " + a.getCategorie());
                                    lbl_cat.setUIID("rep_champ");
                                    Label lbl_prix = new Label("Dernier prix " + Float.toString(a.getPrix()) + " Proposé par :" + a.getNomRep());
                                    lbl_prix.setUIID("rep_champ");
                                    Label lbl_NomUser = new Label("Demandeur : " + a.getNomUser());
                                    lbl_NomUser.setUIID("Rep_Titre");
                                    ls.add(v);
                                    ls1.add(lbl_titre);
                                    ls1.add(lbl_cat);
                                    ls1.add(lbl_desc1);
                                    ls1.add(lbl_desc);

                                    ls1.add(lbl_NomUser);
                                    ls1.add(lbl_prix);
                                    element.add(ls);
                                    ls.add(ls1);
                                    Button b = new Button("Button");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                    if (!(a.getUserId() == Utilisateur.current_user.getId())) {
                                        if (Utilisateur.current_user.getRoles().contains("ROLE_REPARATEUR")) {

                                            b.addActionListener(new ActionListener() {
                                                @Override
                                                public void actionPerformed(ActionEvent evt) {
                                                    InteractionDialog dlg = new InteractionDialog("Détails d'une annonce de réparation ");
                                                    dlg.setLayout(new BorderLayout());

                                                    dlg.add(BorderLayout.NORTH, new Label("Proposez une offre"));
                                                    TextField inputPrice = new TextField("", "Proposez votre prix", 8, TextField.NUMERIC);

                                                    dlg.add(BorderLayout.CENTER, inputPrice);

                                                    Button close = new Button("OK");
                                                    close.addActionListener((ee) -> {
                                                        if (inputPrice.getText().equals("")) {
                                                            dlg.dispose();
                                                        } else {
                                                            System.out.println("Prix :" + inputPrice.getText());
                                                            if (Integer.parseInt(inputPrice.getText()) <= a.getPrix()) {
                                                                Dialog.show("Veuillez proposer un prix supérieur", a.getTitre(), "ok", "");
                                                            }
                                                            AnnonceRepService.updatePrix(a.getId(), Utilisateur.current_user.getId(), inputPrice.getText());
                                                            dlg.dispose();
                                                        }

                                                    });
                                                    dlg.addComponent(BorderLayout.SOUTH, close);
                                                    Dimension pre = dlg.getContentPane().getPreferredSize();
                                                    dlg.showPopupDialog(getComponentAt(getComponentCount() - 1));

                                                }
                                            });
                                            /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                        }
                                    } else if (a.getPrix() != 0) {
                                        b.addActionListener(new ActionListener() {
                                            @Override
                                            public void actionPerformed(ActionEvent evt) {
//                                                      
                                                InteractionDialog dlg = new InteractionDialog("Validation d'une offre  ");
                                                dlg.setLayout(new BorderLayout());

                                                dlg.add(BorderLayout.NORTH, new Label("Voulez vous valider La meilleur  offre ?"));
                                                Label offre = new Label("Offre :" + a.getPrix() + "Proposé par :" + a.getNomRep());

                                                dlg.add(BorderLayout.CENTER, offre);

                                                Button Ok = new Button("Confirmer");
                                                Button close = new Button("Annuler");
                                                Ok.addActionListener((ee) -> {
                                                    ReparationService.validerOffre(a.getId());
//                                        
                                                    dlg.dispose();
                                                });
                                                close.addActionListener((ee) -> {
                                                    dlg.dispose();
                                                });
                                                Container btnsgroups = new Container(BoxLayout.x());
                                                btnsgroups.add(Ok);
                                                btnsgroups.add(close);
                                                dlg.addComponent(BorderLayout.SOUTH, btnsgroups);

                                                Dimension pre = dlg.getContentPane().getPreferredSize();
                                                dlg.showPopupDialog(getComponentAt(getComponentCount() - 1));

                                            }
                                        });

                                    }

                                    element.setLeadComponent(b);
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
                    mainContainer.add(ic);
                    ic.setScrollableY(false);
                    if (v == 0) {
                        add(mainContainer);
                        v = 1;
                    }

                }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                //////////////////////////////////////////////////////Liste réparateur ////////////////////////////////////////////////////////////////////////////////                               
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                               
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////// 
                if (b.getName().equals("listRep")) {

                    mainContainer.removeAll();
                    System.out.println("Liste des réparateurs");
                    reparateurs = ServiceUser.getTtReps();
                    System.out.println("IC " + reparateurs.size());
                    Container ic = new InfiniteContainer() {
                        @Override
                        public Component[] fetchComponents(int index, int amount) {
                            if (index == 0) {

                                amount = reparateurs.size();
                                System.out.println(amount);

                            }
                            if (index + amount > reparateurs.size()) {

                                amount = reparateurs.size() - index;

                            }
                            if (amount <= 0) {
                                return null;
                            }
                            // System.out.println(amount);

                            Component[] elments = new Component[amount];
                            int i = 0;
                            for (Reparateur a : reparateurs) {
                                Container element = new Container(BoxLayout.y());
                                Container ls = new Container(BoxLayout.x());
                                try {
                                    System.out.println("file:/C:/wamp64/www/ecosystemweb/web/uploads/user/photo/" + a.getPhoto());
                                    Image img = Image.createImage("file:/C:/wamp64/www/ecosystemweb/web/uploads/user/photo/" + a.getPhoto()).fill(500, 500);

                                    //lS containter for photo / nomRep
                                    //LS1 container for other information
                                    ImageViewer v = new ImageViewer(img);
                                    Container ls1 = new Container(BoxLayout.y());
                                    Label lbl_nom = new Label(a.getUsername());
                                    lbl_nom.setUIID("Rep_Titre");
                                    Label lbl_desc = new Label(a.getDescription());
                                    lbl_desc.setUIID("CenterLabel");
                                    Label lbl_desc1 = new Label("Description");
                                    lbl_desc1.setUIID("Rep_Titre");

                                    Label lbl_adresse = new Label(a.getAdresse());
                                    lbl_adresse.setUIID("CenterLabel");

                                    Label lbl_adresse1 = new Label("Adresse");
                                    Label lbl_numTel1 = new Label("Numéro téléphone");

                                    lbl_numTel1.setUIID("Rep_Titre");
                                    Label lbl_spec = new Label("Specialité : " + a.getSpecialite());
                                    lbl_spec.setUIID("rep_champ");
                                    lbl_adresse1.setUIID("Rep_Titre");
                                    Label lbl_numTel = new Label(Integer.toString(a.getNumerotel()));
                                    lbl_numTel.setUIID("CenterLabel");

                                    ls.add(v);

                                    ls1.add(lbl_nom);
                                    ls1.add(lbl_spec);
                                    ls1.add(lbl_desc1);
                                    ls1.add(lbl_desc);
                                    ls1.add(lbl_adresse1);
                                    ls1.add(lbl_adresse);
                                    ls1.add(lbl_numTel1);
                                    ls1.add(lbl_numTel);

                                    element.add(ls);
                                    ls.add(ls1);
                                    Button b = new Button("Button");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                                    b.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {

                                        }
                                    });
                                    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                                    element.setLeadComponent(b);
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

                    super.revalidateWithAnimationSafety();

                    mainContainer.add(ic);
                    super.refreshTheme();

                }
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                
                //////////////////////////////////////////////////////REPARATION EN COURS ////////////////////////////////////////////////////////////////////////////////                               
                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                               
//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////                

                if (b.getName().equals("repEnCours")) {
                    System.out.println("Reparation en cours  en cours");
                    mainContainer.removeAll();
                    int UserID;
                    String TypeAccount;
                    UserID = Utilisateur.current_user.getId();
                    TypeAccount = Utilisateur.current_user.getRoles();

                    if (TypeAccount.contains("ROLE_REPARATEUR")) {
                        reparations = ReparationService.getReparations(UserID, 2);
                    } else {
                        reparations = ReparationService.getReparations(UserID, 1);
                    }

                    System.out.println("Size réparations " + reparations.size());
                    Container ic = new InfiniteContainer() {
                        @Override
                        public Component[] fetchComponents(int index, int amount) {
                            if (index == 0) {

                                amount = reparations.size();
                                System.out.println(amount);

                            }
                            if (index + amount > reparations.size()) {

                                amount = reparations.size() - index;

                            }
                            if (amount <= 0) {
                                return null;
                            }
                            // System.out.println(amount);

                            Component[] elments = new Component[amount];
                            int i = 0;
                            for (Reparation a : reparations) {
                                Container element = new Container(BoxLayout.y());
                                Container ls = new Container(BoxLayout.x());

                                Container ls1 = new Container(BoxLayout.y());
                                int inter = i + 1;
                                Label lbl_Titre = new Label("Réparation Numéro  : " + inter);
                                lbl_Titre.setUIID("Rep_Titre");
                                Label lbl_commentaire = new Label("Commentaire  : " + a.getCommentaire());
                                lbl_commentaire.setUIID("rep_champ");
                                Label lbl_DatePrisEncharge = new Label("Date pris En charge  : " + a.getDateP());
                                lbl_DatePrisEncharge.setUIID("rep_champ");
                                Label lbl_Statut = new Label("Statut  : " + a.getStatut());
                                //lbl_Statut.setUIID("rep_champ");
                                lbl_Statut.setUIID("statut");
                                Label lbl_nomUser = new Label("Demandeur:" + a.getNomUser());
                                lbl_nomUser.setUIID("rep_champ");
                                Label lbl_nomRep = new Label("Réparateur en charge : " + a.getNomRep());
                                lbl_nomRep.setUIID("rep_champ");
                                ls1.add(lbl_Titre);
                                ls1.add(lbl_nomUser);
                                ls1.add(lbl_nomRep);
                                ls1.add(lbl_commentaire);
                                ls1.add(lbl_DatePrisEncharge);
                                ls1.add(lbl_Statut);

                                element.add(ls);
                                ls.add(ls1);
                                Button b = new Button("Button");
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (TypeAccount.contains("ROLE_REPARATEUR") && a.getStatut().equals("En cours")) {
                                    b.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            InteractionDialog dlg = new InteractionDialog("une action est requise ");
                                            dlg.setLayout(new BorderLayout());

                                            dlg.add(BorderLayout.NORTH, new Label("Choissisez une action"));
                                            ComboBox<String> action = new ComboBox("Réparation fini", "Annuler la réparation");

                                            dlg.add(BorderLayout.CENTER, action);

                                            Button OK = new Button("Confirmer");
                                            Button close = new Button("Fermer");
                                            OK.addActionListener((ee) -> {
                                                if (action.getModel().getSelectedIndex() == 1) {
                                                    ReparationService.annulerRep(a.getId());
                                                } else {

                                                    ReparationService.confirmerRep(a.getId());

                                                }

                                                dlg.dispose();
                                            });
                                            close.addActionListener((ee) -> {
                                                dlg.dispose();
                                            });
                                            Container btnsgroups = new Container(BoxLayout.x());
                                            btnsgroups.add(OK);
                                            btnsgroups.add(close);
                                            dlg.addComponent(BorderLayout.SOUTH, btnsgroups);
                                            Dimension pre = dlg.getContentPane().getPreferredSize();
                                            dlg.showPopupDialog(getComponentAt(getComponentCount() - 1));

                                        }
                                    });
                                } else if (a.getStatut().equals("En cours")) {
                                    b.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            InteractionDialog dlg = new InteractionDialog("une action est requise ");
                                            dlg.setLayout(new BorderLayout());

                                            dlg.add(BorderLayout.NORTH, new Label("Choissisez une action"));
                                            ComboBox<String> action = new ComboBox("Annuler la réparation");

                                            dlg.add(BorderLayout.CENTER, action);

                                            Button OK = new Button("Confirmer");
                                            Button close = new Button("Fermer");
                                            OK.addActionListener((ee) -> {

                                            });
                                            close.addActionListener((ee) -> {
                                                dlg.dispose();
                                            });
                                            Container btnsgroups = new Container(BoxLayout.x());
                                            btnsgroups.add(OK);
                                            btnsgroups.add(close);
                                            dlg.addComponent(BorderLayout.SOUTH, btnsgroups);
                                            Dimension pre = dlg.getContentPane().getPreferredSize();
                                            dlg.showPopupDialog(getComponentAt(getComponentCount() - 1));

                                        }
                                    });
                                } else {
                                    b.addActionListener(new ActionListener() {
                                        @Override
                                        public void actionPerformed(ActionEvent evt) {
                                            InteractionDialog dlg = new InteractionDialog("Supprimer la réparation ?");
                                            dlg.setLayout(new BorderLayout());

                                            dlg.add(BorderLayout.NORTH, new Label("Voulez vous supprimer la réparation ?"));

                                            Button OK = new Button("Confirmer");
                                            Button close = new Button("Fermer");
                                            OK.addActionListener((ee) -> {
                                                ReparationService.supprimerRep(a.getId());
                                                dlg.dispose();;
                                                refreshTheme();
                                            });
                                            close.addActionListener((ee) -> {
                                                dlg.dispose();
                                            });
                                            Container btnsgroups = new Container(BoxLayout.x());
                                            btnsgroups.add(OK);
                                            btnsgroups.add(close);
                                            dlg.addComponent(BorderLayout.SOUTH, btnsgroups);
                                            Dimension pre = dlg.getContentPane().getPreferredSize();
                                            dlg.showPopupDialog(getComponentAt(getComponentCount() - 1));

                                        }
                                    });
                                }
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                                element.setLeadComponent(b);
                                elments[i] = element;
                                i++;

                                // element.setLeadComponent(b);
                            }
                            return elments;
                        }
                    };

                    ic.setScrollableY(false);

                    super.revalidateWithAnimationSafety();

                    mainContainer.add(ic);
                    super.refreshTheme();

                }
            }
        });
    }

}
