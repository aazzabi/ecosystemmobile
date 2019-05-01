/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.util.Resources;
import entities.Annonce;
import java.io.IOException;
import java.text.SimpleDateFormat;

/**
 *
 * @author anasc
 */
public class ShowAnnonceForm extends BaseForm {

    Container cnt;
    Resources res;
    Label lbl_text, lbl_desc, lbl_prix, lbl_region, lbl_date, lbl_likes, lbl_viwes;

    public ShowAnnonceForm(Resources res, Annonce a) throws IOException {
        super("Show Annonce", BoxLayout.y());
        this.res = res;
        cnt = new Container(BoxLayout.y());
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
        RadioButton all = RadioButton.createToggle("Show Annonce", barGroup);
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
        Image img = Image.createImage("file:/C:/wamp64/www/ecosystemweb/web/uploads/Annonce/photo/" + a.getPhoto()).fill(2000, 1500);
        ImageViewer v = new ImageViewer(img);
        v.setWidth(2000);
        v.setHeight(1500);
        Container cloum = new Container(BoxLayout.y());
        Container row1 = new Container(BoxLayout.x());
        Label titre = new Label("Titre Du Annonce :");
        lbl_text = new Label(a.getTitre());
        lbl_text.setIcon(FontImage.createMaterial(FontImage.MATERIAL_TEXT_FIELDS, lbl_text.getUnselectedStyle()));
        lbl_text.setUIID("CenterLabel");
        row1.add(titre);
        row1.add(lbl_text);
        Container row2 = new Container(BoxLayout.x());
        Label desc = new Label("Description Du Annonce :");
        desc.setUIID("CenterLabel");
        lbl_desc = new Label(a.getDescription());
        lbl_desc.setUIID("Label");
        row2.add(desc);
        row2.add(lbl_desc);
        Container row3 = new Container(BoxLayout.x());
        Label pr = new Label("Prix :");
        pr.setUIID("CenterLabel");
        lbl_prix = new Label(Double.toString(a.getPrix()));
        lbl_prix.setUIID("Label");
        row3.add(pr);
        row3.add(lbl_prix);
        Container row4 = new Container(BoxLayout.x());
        Label reg = new Label("Region :");
        reg.setUIID("Label");
        lbl_region = new Label(a.getRegion());
        lbl_region.setUIID("Label");
        row4.add(reg);
        row4.add(lbl_region);
        Container row5 = new Container(BoxLayout.x());
        SimpleDateFormat sm = new SimpleDateFormat("EEEE,dd,MMMM,yyyy");
        Label date = new Label("Cree le :");
        date.setUIID("CenterLabel");
        lbl_date = new Label(sm.format(a.getDate_creation()));
        lbl_date.setUIID("Label");
        row5.add(date);
        row5.add(lbl_date);
        Container row6 = new Container(BoxLayout.x());
        lbl_likes = new Label(Integer.toString(a.getLikes()));
        lbl_likes.setIcon(FontImage.createMaterial(FontImage.MATERIAL_THUMB_UP, lbl_likes.getUnselectedStyle()));
        lbl_likes.setUIID("Label");
        lbl_viwes = new Label(Integer.toString(a.getViews()));
        lbl_viwes.setIcon(FontImage.createMaterial(FontImage.MATERIAL_VISIBILITY, lbl_viwes.getUnselectedStyle()));
        lbl_viwes.setUIID("Label");
        row6.add(lbl_likes);
        row6.add(lbl_viwes);
        cloum.add(row1);
        cloum.add(row2);
        cloum.add(row3);
        cloum.add(row4);
        cloum.add(row5);
        cloum.add(row6);
        cnt.add(v);
        cnt.add(cloum);
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
