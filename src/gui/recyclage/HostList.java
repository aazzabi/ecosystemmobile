/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.recyclage;

import com.codename1.components.ImageViewer;
import entities.Host;
import services.HostService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
/**
 *
 * @author Hiro
 */
public class HostList extends BaseGui {
    
    
    public HostList(){
        super();
                Resources res ; 
            res = UIManager.initFirstTheme("/theme");   
             
       Image img = res.getImage("logoeco.png");


        DisplayAllHosts(res);
 		 
          
        MakeAddHostButton();
        
        
        ShowForm();
    }
    
    private void DisplayAllHosts( Resources res){
        
        
                 Image img2 = res.getImage("liste.png");
       img2 =  img2.scaled(1000,150 );
        ImageViewer image= new ImageViewer(img2);

     Button imgg = new Button(image.getImage());
     
     
        for (Host CurrentHost : HostService.GetAllHosts()){
           
            Label borderNom = new Label("NOM : ");
            Label borderdispo = new Label("PLACE DISPO : ");
            Label bordergeo = new Label("LOCALISATION : ");

            Label Name_Label = new Label(CurrentHost.getOwner());
            Label Places_Label = new Label(CurrentHost.getAvailablePlaces() + "/" + CurrentHost.getTotalPlaces());
            Label Location_Label = new Label(CurrentHost.getLocalisation());
            
            //Listeners
            Name_Label.addPointerReleasedListener((evt) -> {
                System.out.println("<HostList::DisplayAllHosts> Displaying host : "+ CurrentHost.getOwner());
                new HostDetails(CurrentHost);
            });
            Container ContainerTemp= CreateContainer_Y( borderNom,Name_Label,borderdispo, Places_Label, bordergeo,Location_Label);
            ContainerTemp.setUIID("Button");

            AddBlocks2x2(ContainerTemp);
        }
    }
    private void MakeAddHostButton(){
        Button CurrentButton = new Button("Créer une Mission!");
        CurrentButton.addActionListener((evt) -> {
            new HostAdd();
        });
        AddBlocks2x2(CurrentButton);
    }

     
}
