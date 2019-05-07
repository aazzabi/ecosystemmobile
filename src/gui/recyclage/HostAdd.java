/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.recyclage;

import entities.Host;
import services.HostService;
import com.codename1.ui.Button;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author Hiro
 */
public class HostAdd extends BaseGui{

    
    public HostAdd() {
        super();
        
        DisplayAddForm();
        
        ShowForm();
    }
    
    private void DisplayAddForm(){
        
        //TextFields
        TextField Name_TF = new TextField();
        TextField Places_TF = new TextField();
        TextField Location_TF = new TextField();
       TextField Participants_TF = new TextField();

        Picker DateStart_Picker = new Picker();
        Picker DateEnd_Picker = new Picker();
        
        
        //Labels
        Label Name_Label = new Label("Nom de la mission");
        Label Places_Label = new Label("Nombre de Matériaux Objectifs");
        Label DateStart_Label = new Label("Date Début");
        Label DateEnd_Label = new Label("Date Fin");
        Label Location_Label = new Label("Localisation");
        Label Participant_Label = new Label("Nombres Participants");
        Label TITRE = new Label("AJOUT D'UNE NOUVELLE MISSION");
 
        
        //Buttons
        Button Validate_Button = new Button("Créer Mission !");
        Validate_Button.addActionListener((evt) -> {
            
            Host CurrentHost = new Host();
            
            CurrentHost.setOwner(Name_TF.getText());
            CurrentHost.setAvailablePlaces(Integer.valueOf(Places_TF.getText()));
            CurrentHost.setTotalPlaces(Integer.valueOf(Places_TF.getText()));
            CurrentHost.setLocalisation(Location_TF.getText());
             CurrentHost.setParticipants(Participants_TF.getText());
            
            CurrentHost.setDateStart(DateStart_Picker.getDate());
            CurrentHost.setDateEnd(DateEnd_Picker.getDate());
            
            System.out.println(DateStart_Picker.getDate());
            HostService.AddHost(CurrentHost);
        });
        
        AddBlocks_Custom(10,1, TITRE, Name_Label, Name_TF, Places_Label, Places_TF, Location_Label, Location_TF,Participant_Label,Participants_TF, DateStart_Label, DateStart_Picker, DateEnd_Label, DateEnd_Picker, Validate_Button);
        
    }
}
