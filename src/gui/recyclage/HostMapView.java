package gui.recyclage;


/**
 *
 * @author Weepey
 */
 
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 

import entities.Host;
import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Sof
 */
public class HostMapView extends BaseGui{
    
    public HostMapView(Resources res,Host h)
    {
    
	 super();
 
    
        //super.addSideMenu(res);
        
        Container cncn = new Container();
       ImageViewer im = new ImageViewer();

        /*Image placeholder1 = Image.createImage(300, 300, 0xbfc9d2);
        EncodedImage encImage = EncodedImage.createFromImage(placeholder1, true);

        im.setImage(URLImage.createToStorage(encImage, "High" + h.getImage(), h.getImage(), URLImage.RESIZE_SCALE_TO_FILL));

        cncn.add(im);*/
        Image img = res.getImage("details.png");
       img =  img.scaled(1000,150 );
        ImageViewer image= new ImageViewer(img);

     Button imgg = new Button(image.getImage());
        Label InfoMission= new Label("");
        Label nom = new Label("Name : "+h.getOwner());
        Label NbEtoiles = new Label("Nbr of mats : "+h.getTotalPlaces());
        Label capacity = new Label("City : "+h.getLocalisation());
        Label city = new Label("End Date : "+h.getDateEnd());
        InfoMission.setUIID("SideCommand");
        Button Voir = new Button("Show Location");
        Voir.setUIID("Button");
       
        
        cncn.add(BoxLayout.encloseY(
                imgg,
                 BorderLayout.center(InfoMission),nom,NbEtoiles,capacity,city,BorderLayout.center(Voir)
         ));
        
       cncn.setUIID(""); //InputContainerBackgroundSofien
            AddBlocks1x1(cncn);
        ShowForm();
          Voir.addActionListener(e->{
        GoogleMapsTestApp x = new GoogleMapsTestApp();
       h.setGeolat(34.791366);
       h.setGeolong(9.572108);
       System.out.println("latitude = " + h.getGeolat() + "long = " + h.getGeolong() );
        x.start(h.getGeolat(),h.getGeolong(),res,h);
        });
        }
    }