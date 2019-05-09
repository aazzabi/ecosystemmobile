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
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Hiro
 */
public class HostModify extends BaseGui{
    
    private Host CurrentHost = new Host();
    
    public HostModify(Host HostToOpen){
        super();
        
        CurrentHost = HostToOpen;
         Resources res ; 
           res = UIManager.initFirstTheme("/theme");   
        MakeInterface(res);
        
        ShowForm();
    }
    
    private void MakeInterface(Resources res){
         //TextFields
          Image img = res.getImage("modification.png");
       img =  img.scaled(1000,120 );
        ImageViewer image= new ImageViewer(img);

     Button imgg = new Button(image.getImage());

        TextField Name_TF = new TextField();
        TextField Places_TF = new TextField();
        TextField Location_TF = new TextField();
       TextField Participants_TF = new TextField();

        Picker DateStart_Picker = new Picker();
        Picker DateEnd_Picker = new Picker();
        
        
        //Labels
        Label Name_Label = new Label("Nom de la mission");
        Label Places_Label = new Label("Nombre de Matériaux Objectifs");
        Label Date_Label = new Label("Date Début ET fin");
        Label Location_Label = new Label("Localisation");
        Label Participant_Label = new Label("Nombres Participants");
        Label TITRE = new Label("MODIFICATION DE LA MISSION");
       
        TextField Name_Field = new TextField();
        TextField Location_Field = new TextField();
        
        
        Button Validate_Button = new Button("Valider");
        Validate_Button.addActionListener((evt) -> {
            CurrentHost.setOwner(Name_Field.getText());
            CurrentHost.setLocalisation(Location_Field.getText());
            CurrentHost.setDateStart(DateStart_Picker.getDate());
            CurrentHost.setDateEnd(DateEnd_Picker.getDate());
            HostService.ModifyHost(CurrentHost.getID(), CurrentHost);
        });
        Button Delete_Button = new Button("Supprimer");
        Delete_Button.addActionListener((evt) -> {
            HostService.DeleteHost(CurrentHost.getID());
        });
        
        MainForm.add(CreateContainer_Y(imgg,Name_Label, Name_Field, Date_Label, CreateContainer_X(DateStart_Picker, DateEnd_Picker), Location_Label, Location_Field, Places_Label,Places_TF,Participant_Label,Participants_TF,  Validate_Button));
        
        
    }
    
}
