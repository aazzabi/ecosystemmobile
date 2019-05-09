/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.recyclage;

import com.codename1.components.ImageViewer;
import com.codename1.components.ScaleImageLabel;
import com.codename1.notifications.LocalNotification;
import entities.Host;
import services.HostService;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import entities.HostRating;
import entities.Utilisateur;
import java.io.IOException;

/**
 *
 * @author Hiro
 */
public class HostDetails extends BaseGui {

    private Host CurrentHost = new Host();

    public HostDetails(Host HostToOpen) {
        super();
        Resources res;
        res = UIManager.initFirstTheme("/theme");
        //  res  = Resources.openLayered("/theme") ; 

        CurrentHost = HostToOpen;
                            System.out.println(CurrentHost);

        MakeInterface(res);
        ShowForm();
    }

    private void MakeInterface(Resources res) {

        //MAIN IMAGE
        Image img = res.getImage("details.png");
        img = img.scaled(1000, 150);
        ImageViewer image = new ImageViewer(img);

        Button imgg = new Button(image.getImage());

        Label Name_Label = new Label(CurrentHost.getOwner());
        Label Places_Label = new Label(CurrentHost.getAvailablePlaces() + "/" + CurrentHost.getTotalPlaces());
        MakeModifyButton();
           Button Delete_Button = new Button("Supprimer");
            Delete_Button.addActionListener((evt) -> {
                            System.out.println("current id user : " + Utilisateur.current_user.getId() + "host id : " + CurrentHost.getOwnerID());

                
                 if (CurrentHost.getOwnerID() == Utilisateur.current_user.getId()) {
             HostService.DeleteHost(CurrentHost.getID());
              MainForm.refreshTheme();
        } else {
           Dialog.show("Failure", "Vous ne pouvez pas supprimer une mission qui n'est pas la votre ! ", "OK", null);

        }
               
               
            });
       
 Container CurrentContainer = CreateContainer_Y(imgg, Name_Label, Places_Label, MakeModifyButton(), MakeMapsButton(res), MakeParticipateButton(),Delete_Button);
            MainForm.add(CurrentContainer);
        ////LES COMMENTAIRES BACLKEND 
        for (HostRating Rating : HostService.GetAllRatingHosts()) {
            if (CurrentHost.getID() == Rating.getHostID()) {

                Label comm_Label = new Label(Rating.getComment());
                Container ContainerTemp = CreateContainer_Y(comm_Label);
                MainForm.add(ContainerTemp);

            }
        }

        //Commentaires end !!!
    }

    private Button MakeModifyButton() {
        Button ModifyButton = new Button("Modifier");
        ModifyButton.addActionListener((evt) -> {
            new HostModify(CurrentHost);
            System.out.println("<HostList::Modify HOST > Displaying host : " + CurrentHost.getOwner());

        });

        return ModifyButton;

    }

    private Button MakeParticipateButton() {
        Button ParticipateButton = new Button("Participer ! ");
        ParticipateButton.addActionListener((evt) -> {
            new HostParticipate(CurrentHost);
            System.out.println("<HostList::Participate  HOST > Displaying host : " + CurrentHost.getOwner());

        });

        return ParticipateButton;

    }

    private Button MakeMapsButton(Resources res) {
        Button MapsButton = new Button("Afficher sur Maps");
        MapsButton.addActionListener((evt) -> {
            //Resources res = null ; //Resources.openLayered("/theme")
            new HostMapView(res, CurrentHost);
            System.out.println("<HostList::MAPS  HOST > Displaying host : " + CurrentHost.getOwner());

        });

        return MapsButton;

    }

}
