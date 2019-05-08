/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.recyclage;

import com.codename1.capture.Capture;
import com.codename1.components.ImageViewer;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.rest.Response;
import com.codename1.io.rest.Rest;
import entities.Host;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.GridLayout;
import java.util.Map;
import com.codename1.ui.Dialog;
import com.codename1.ui.util.ImageIO;
import com.codename1.util.Base64;
import com.wefeel.QRMaker.QRMaker;
import entities.Utilisateur;
import static gui.recyclage.BaseGui.CreateContainer_Y;
import java.io.IOException;
import java.io.OutputStream;
/**
 *
 * @author Hiro
 */
public class HostParticipate extends BaseGui{
    
    private Host CurrentHost = new Host();
    
    public HostParticipate(Host HostToOpen){
        super();
        
        CurrentHost = HostToOpen;
        
        MakeInterface();
        
        ShowForm();
    }
    
    private void MakeInterface(){
      	 /*String accountSID = "ACeff1a322ac15e1b89c8851cdab1e2766";
String authToken = "9b8c6660c2962fa3dfa62fee7884f5b7";
String fromPhone = "+18577632054";
		Response<Map> result = Rest.post("https://api.twilio.com/2010-04-01/Accounts/" + accountSID + "/Messages.json").
        queryParam("To", "+21654559260").
        queryParam("From", fromPhone).
        queryParam("Body", "Vous avez participer avec succés à la mission nommee :   "+CurrentHost.getOwner()+" ! ! ! ").
        header("Authorization", "Basic " + Base64.encodeNoNewline((accountSID + ":" + authToken).getBytes())).
        getAsJsonMap();
		*/
 Button btnMoveCamera = new Button("Retour à la Liste");
        btnMoveCamera.addActionListener(e->{
            new HostList() ; 
            
        });
        
        
         Button saveButton = new Button("Sauvegarder le QRCODE");
      
Image img = QRMaker.QRCode(String.valueOf(CurrentHost.getOwner()+Utilisateur.current_user.getId()*2341));
ImageViewer image= new ImageViewer(img);

Dialog.show("Success", "Vous avez participer avec succés à la mission nommee :   "+CurrentHost.getOwner()+" ! ", "OK", null);
Dialog d = new Dialog("QR Code");
 Image placeholder = Image.createImage(45, 45);
		

        int height = Display.getInstance().convertToPixels(25f);
        int width = Display.getInstance().convertToPixels(25f);
        Button imgg = new Button(image.getImage().fill(width, height));
        
          saveButton.addActionListener(e->{
          saveImage(img); 
        });
          
          
          
          
d.add(GridLayout.encloseIn(2, imgg,btnMoveCamera));
d.add(GridLayout.encloseIn(1, saveButton));

d.show();
 
    }


 private Button saveImage(Image img){

 String filePath = Capture.capturePhoto();
        if (filePath != null) {
            try {
                String pathToBeStored = FileSystemStorage.getInstance().getAppHomePath() + System.currentTimeMillis() +  ".jpg" ; 
                
              Image imgSaved = Image.createImage(filePath);
                try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(pathToBeStored )) {
                    ImageIO.getImageIO().save(img, os, ImageIO.FORMAT_JPEG, 0.9f);
                }
            }
            catch (IOException e) {
            }
        }
        return null;
 }







//TextFields
      /*  TextField Name_TF = new TextField();
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
        
        MainForm.add(CreateContainer_Y(TITRE,Name_Label, Name_Field, Date_Label, CreateContainer_X(DateStart_Picker, DateEnd_Picker), Location_Label, Location_Field, Places_Label,Places_TF,Participant_Label,Participants_TF,  Validate_Button));
        
        */
    }
    
