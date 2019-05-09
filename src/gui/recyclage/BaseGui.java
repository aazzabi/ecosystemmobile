/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui.recyclage;

import Utils.UtilityAction;
import Utils.UtilityGui;
import com.codename1.ui.Container;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.Component;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Toolbar;
/**
 *
 * @author Hiro
 */
public class BaseGui {

    public static Form MainForm;
    private static Toolbar MainToolbar;
    
    
    public BaseGui() {
        
        //Main
        CreateMainForm();
        
        //Toolbar
        CreateToolbar();
        
    }
    

    
    //Form
    private void CreateMainForm(){
        MainForm = new Form();
    }
    public static void ShowForm(){
        MainForm.show();
    }

    
    //Toolbar
    private void CreateToolbar(){
        MainToolbar = UtilityGui.CreateToolBar(MainForm);        

        AddToRightBar(FontImage.MATERIAL_HOME, 0, "", () -> {
            
        });
         
         AddToRightBar(FontImage.MATERIAL_HOME, 0, "", () -> {
            
        });
         
        AddToRightBar(FontImage.MATERIAL_ASSISTANT, 6, "Nos Missions", () -> {
            HostList HostList_Link= new HostList();
        });
          AddToRightBar(FontImage.MATERIAL_HOME, 0, "", () -> {
            
        });
          AddToRightBar(FontImage.MATERIAL_HOME, 0, "", () -> {
            
        });
             
         
    }
    private void AddToRightBar(char IconName, float IconSize, String Text, UtilityAction Action){
        
        FontImage CurrentIcon = FontImage.createMaterial(IconName, "TitleCommand", IconSize);

        MainToolbar.addCommandToRightBar(Text, CurrentIcon, (evt) -> {
            Action.ActionToMake();
        });
    }
    
    //Blocks (GridLayout)
    public void AddBlocks1x1(Component... Components){
        AddBlocks_Custom(1, 1, Components);
    } 
    public void AddBlocks3x1(Component... Components){
        AddBlocks_Custom(3, 1, Components);
    } 
    public void AddBlocks2x2(Component... Components){
        AddBlocks_Custom(2, 2, Components);
    } 
    public void AddBlocks3x3(Component... Components){
        AddBlocks_Custom(3, 3, Components);
    }
    public void AddBlocks_Custom(int x, int y, Component... Components){
        MainForm.setLayout(new GridLayout(x, y));
        
        for (Component TempComponent : Components)
            MainForm.add(TempComponent); 
    }
    
    //Containers
    public static Container CreateContainer_Y(Component... Components){

        //Containers
        Container CurrentContainer = new Container(new BoxLayout(BoxLayout.Y_AXIS));
        
        //Adding to the Containers
        for(Component TempComp : Components){
            CurrentContainer.add(TempComp);
        }
        
        return CurrentContainer;
    }
    public static Container CreateContainer_X(Component... Components){

        //Containers
        Container CurrentContainer = new Container(new BoxLayout(BoxLayout.X_AXIS));
        
        //Adding to the Containers
        for(Component TempComp : Components){
            CurrentContainer.add(TempComp);
        }
        
        return CurrentContainer;
    }
}
