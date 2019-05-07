/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.recyclage;

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

     
        DisplayAllHosts();
 		 
          
        MakeAddHostButton();
        
        
        ShowForm();
    }
    
    private void DisplayAllHosts(){
        
        
        
        for (Host CurrentHost : HostService.GetAllHosts()){
            
            //HostData to labels
            Label Name_Label = new Label(CurrentHost.getOwner());
            Label Places_Label = new Label(CurrentHost.getAvailablePlaces() + "/" + CurrentHost.getTotalPlaces());
            Label Location_Label = new Label(CurrentHost.getLocalisation());
            Label border = new Label("--------------------------");
           
            //Listeners
            Name_Label.addPointerReleasedListener((evt) -> {
                System.out.println("<HostList::DisplayAllHosts> Displaying host : "+ CurrentHost.getOwner());
                new HostDetails(CurrentHost);
            });
            Container ContainerTemp= CreateContainer_Y(Name_Label, Places_Label, Location_Label, border);
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
