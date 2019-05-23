/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.recyclage;

import com.codename1.components.ImageViewer;
import com.codename1.ui.Button;
import com.codename1.ui.Image;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;

/**
 *
 * @author Hiro
 */
public class HomeGui extends BaseGui{
    public HomeGui(){
        super();
             Resources res ; 
           res = UIManager.initFirstTheme("/theme");  
           Image img = res.getImage("mainmenu.png");
          img =  img.scaled(1100,2100 );

        ImageViewer image= new ImageViewer(img);

     Button imgg = new Button(image.getImage());
 MainForm.add(imgg);
        ShowForm();
    }
}
