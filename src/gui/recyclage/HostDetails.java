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
import com.codename1.ui.Label;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Hiro
 */
public class HostDetails  extends BaseGui{
    
    private Host CurrentHost = new Host();
    
    public HostDetails(Host HostToOpen){
        super();
         Resources res ; 
            res = UIManager.initFirstTheme("/theme");   
             

                  

        CurrentHost = HostToOpen;
        MakeInterface(res);
        ShowForm();
    }
    
    private void MakeInterface( Resources res ){
        Label Name_Label = new Label(CurrentHost.getOwner());
        Label Places_Label =new Label(CurrentHost.getAvailablePlaces()+ "/" + CurrentHost.getTotalPlaces());
        MakeModifyButton();
         Button Delete_Button = new Button("Supprimer");
        Delete_Button.addActionListener((evt) -> {
            HostService.DeleteHost(CurrentHost.getID());
           MainForm.refreshTheme();
        });
        Container CurrentContainer = CreateContainer_Y(Name_Label, Places_Label, MakeModifyButton(),MakeMapsButton(res), Delete_Button);
        MainForm.add(CurrentContainer);
    
   
    }
    
    private Button MakeModifyButton(){
        Button ModifyButton = new Button("Modifier");
        ModifyButton.addActionListener((evt) -> {
            new HostModify(CurrentHost);
        System.out.println("<HostList::Modify HOST > Displaying host : "+ CurrentHost.getOwner());

        });
        
        return ModifyButton;
        
    }
      private Button MakeMapsButton(Resources res ){
        Button MapsButton = new Button("Afficher sur Maps");
        MapsButton.addActionListener((evt) -> {
            //Resources res = null ; //Resources.openLayered("/theme")
            new HostMapView(res, CurrentHost); 
        System.out.println("<HostList::MAPS  HOST > Displaying host : "+ CurrentHost.getOwner());

        });
        
        return MapsButton;
        
    }
    
}
