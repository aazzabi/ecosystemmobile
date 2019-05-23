/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.codename1.charts.util.ColorUtil;
import com.codename1.components.ImageViewer;
import com.codename1.components.ShareButton;
import com.codename1.components.SpanLabel;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Font;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.URLImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.myapp.MyApplication;
import static gui.AffichageEvents.f;
import entities.Evenement;
import entities.ParticiperEvent;
import entities.Session;
import entities.Utilisateur;
import static gui.AffichageEvents.f;
import services.EvenementService;
import services.UserService;

/**
 *
 * @author Rania
 */
public class DetailsEvent extends BaseForm {
    
    Label titre ; 
    Label date ;
    static Form f;
    ImageViewer img ; 
    Label User ;
    Label lieu;
    Label description;
    Label txtp;
    Label txtnop;
    ImageViewer participerimg ;
    ImageViewer noparticiperimg;
    ShareButton sb;
    Button participer;
    Button noparticiper;
    Label participants;
    
     public DetailsEvent(){
       
        SimpleDateFormat sm = new SimpleDateFormat("EEE, MMM d, yyyy");
        f= new Form("",new BoxLayout(BoxLayout.Y_AXIS));
      //  UserService serviceuser =new UserService();
       // l= new Label();
        titre = new Label();
        date = new Label ();
        lieu = new Label();
        description = new Label();
       participants= new Label();
        participerimg = new ImageViewer();
         noparticiperimg = new ImageViewer();
          participer=new Button("Participer");
          noparticiper=new Button("Ne Plus participer");
       
       txtp=new Label();
       txtnop=new Label();
       txtp.setVisible(false);
        txtnop.setVisible(false);
        participer.setVisible(false);
        noparticiper.setVisible(false);
        
        EvenementService es=new EvenementService();
        ParticiperEvent join=new ParticiperEvent(Utilisateur.current_user.getId(),AffichageEvents.event.getId()); 
        
        if(es.verifParticiper(join)==false)
            
        {
        participer.setVisible(true);
        participerimg.setVisible(true);
        
        noparticiper.setVisible(false);
        noparticiperimg.setVisible(false);
        }
        else
        {
        participer.setVisible(false);
         participerimg.setVisible(false);
         
        noparticiper.setVisible(true);
        noparticiperimg.setVisible(true);
        }
       
       titre.setText("Découvrez l'évènement "+AffichageEvents.event.getTitre());
        titre.getAllStyles().setFgColor(ColorUtil.rgb(0,128,0));
        lieu.setText("Qui se tiendra à : "+AffichageEvents.event.getLieu());
        date.setText("Le : " + sm.format(AffichageEvents.event.getDate()));
       // participants.setText("les participants sont :"+AffichageEvents.event.getParticipants());
        txtp.setText("participer!");
        txtnop.setText("Ne plus participer");
        
        //**********************
        ShareButton sb=new ShareButton();
        sb.setText("Invitez vos amis");
        sb.setTextToShare("Bonjour, je t’invite à\n" +
"télécharger et à installer l’application Ecosystem. C’est un truc de ouf pour l'écologie");
        //**********************
        
        
        SpanLabel description  = new SpanLabel(AffichageEvents.event.getDescription());
        description.getTextAllStyles().setFont(Font.createSystemFont(Font.FACE_SYSTEM, Font.STYLE_PLAIN, Font.SIZE_SMALL));
        img = new ImageViewer();
        EncodedImage placeholder = EncodedImage.createFromImage(Image.createImage(f.getWidth()/1,f.getHeight()/3),false);
        URLImage urlImage = URLImage.createToStorage(placeholder, AffichageEvents.event.getCover(), "http://localhost/ecosystemweb/web/uploads/evt/cover/"+AffichageEvents.event.getCover());
        img.setOpaque(false);
        img.setImage(urlImage);
/*        f.getToolbar().addCommandToLeftBar("Retour", null, e ->{
            new AffichageEvents();
            AffichageEvents.f.showBack() ;});*/
       
        
        
      //  date.setText(sm.format(AffichageEvents.event.getDate()));
     
     
            EncodedImage placeholder1 = EncodedImage.createFromImage(Image.createImage(f.getWidth()/5, f.getWidth()/5),false);
            URLImage urlImage1 = URLImage.createToStorage(placeholder1,"participer", "http://localhost/ecosystemweb/web/uploads/evt/cover/nop.png");
            participerimg.setImage(urlImage1);
            
            participer.addActionListener(e->{ 
                
                (new EvenementService()).Participer(new ParticiperEvent(Utilisateur.current_user.getId(),AffichageEvents.event.getId()));
                participer.setVisible(false);
                participerimg.setVisible(false);
                noparticiper.setVisible(true);
                noparticiperimg.setVisible(true);
                 txtnop.setVisible(true);
                  Dialog.show("Nouvelle participation!", "Vous venez de participer a l'évènement "+AffichageEvents.event.getTitre(), "Ok", "Annuler");
                
            });
            
        
        
            EncodedImage placeholder2 = EncodedImage.createFromImage(Image.createImage(f.getWidth()/5, f.getWidth()/5),false);
            URLImage urlImage2 = URLImage.createToStorage(placeholder2,"noparticiper", "http://localhost/ecosystemweb/web/uploads/evt/cover/p.png");
            noparticiperimg.setImage(urlImage2);
            
            noparticiper.addActionListener(e->{
                
                (new EvenementService()).NoParticiper(new ParticiperEvent(Utilisateur.current_user.getId(),AffichageEvents.event.getId()));
                noparticiper.setVisible(false);
                noparticiperimg.setVisible(false);
                participer.setVisible(true);
                participerimg.setVisible(true);
                Dialog.show("Annulation de participation", "Etes-vous sur de ne plus vouloir y participer?", "Oui", "Annuler");
                 txtp.setVisible(true);
                
            });
       
        f.add(participer);
        f.add(noparticiper);
        f.add(participerimg);
        f.add(noparticiperimg);
        f.add(titre);
        f.add(date);
        f.add(lieu);
        f.add(img);
        f.add(description);
        f.add(participants);
        f.add(sb);        
     
        f.show();
        
    }
    
}
