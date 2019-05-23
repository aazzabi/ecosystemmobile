/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import entities.Host;
import gui.recyclage.HostDetails;
import gui.recyclage.HostList;
import Utils.Utility;
import com.codename1.l10n.DateFormat;
import com.codename1.l10n.SimpleDateFormat;
import entities.HostRating;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Hiro
 */
public class HostService {
    
    private static Host HostTemp = new Host();
    
    
    public static List<Host> GetAllHosts(){
        
        List<Host> HostList = new ArrayList<>();
        
        Utility.MakeConnection("http://127.0.0.1:8000/host/mobile/afficheAllHost", () -> {
            
            //Initialize Lists
            List<Map<String, Object>> PreHostList =  Utility.MakeListFromJSON();
            
            //Parse the List and Map
            for (Map<String, Object> PreHost: PreHostList){
                
                //Add to the List
                HostList.add(MapToHost(PreHost));
            
            }
        });
        
        return HostList;
    }
      public static List<HostRating> GetAllRatingHosts(){
        
        List<HostRating> HostList = new ArrayList<>();
        
        Utility.MakeConnection("http://127.0.0.1:8000/host/mobile/afficheAllHostRating", () -> {
            
            //Initialize Lists
            List<Map<String, Object>> PreHostList =  Utility.MakeListFromJSON();
            
            //Parse the List and Map
            for (Map<String, Object> PreHost: PreHostList){
                
                //Add to the List
                HostList.add(MapToRating(PreHost));
            
            }
        });
        
        return HostList;
    }
      
    public static Host GetHost(int ID){
        
        Utility.MakeConnection("http://127.0.0.1:8000/host/mobile/DisplayHost/"+ String.valueOf(ID), () -> {
            
        //Initialize Lists
        List<Map<String, Object>> PreHostList =  Utility.MakeListFromJSON();

        //Parse the List and Map
        HostTemp = MapToHost(PreHostList.get(0));
    
        });
       return HostTemp;
    }
    public static void DeleteHost(int ID){
        Utility.MakeConnection("http://127.0.0.1:8000/host/mobile/DeleteHost/"+ String.valueOf(ID),() -> {
            new HostList();
        });
        System.out.println("<HostService::DELETE HOST  !!!! > Done with the execution of the URL Query, Redirecting to the List");
    }
    public static void ModifyHost(int HostIDToChange, Host H){
        /** Date Works START */
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        /** Date Works END */
            
        Utility.MakeConnection("http://127.0.0.1:8000/host/mobile/ModifyHost/" + HostIDToChange + "/" +
                H.getOwner()+"/"+ format.format(H.getDateStart()) +"/"+ format.format(H.getDateEnd()) +"/"+ H.getLocalisation()+"/" , () -> {
                    new HostDetails(H); 
                });
        System.out.println("<HostService::Modify> Done with the execution of the URL Query, Redirecting to the Details of the Host");
    }
    public static void AddHost(Host H){
        /** Date Works START */
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        /** Date Works END */
        
        
        Utility.MakeConnection("http://127.0.0.1:8000/host/mobile/addHost/" + 
                H.getOwner()+"/"+ H.getTotalPlaces() +"/"+ format.format(H.getDateStart()) +"/"+ format.format(H.getDateEnd()) +"/"+ H.getLocalisation() +"/"+ H.getOwnerID() +"/"+ H.getParticipants() , () -> {
                    System.out.println("<HostService::AddHost> Host URL Query has been executed");
                    new HostDetails(H);
                            System.out.println("<HostService::AddHost> Done with the execution of the URL Query, Redirecting to the List");

        });
    }

    
    public static Host MapToHost(Map<String, Object> PreHost){
        
        //Host Instance
        Host CurrentHost = new Host();
                
        //ID
        float TempID  = Float.parseFloat(PreHost.get("id").toString());
        CurrentHost.setID((int) TempID);

        //Name
        CurrentHost.setOwner(PreHost.get("owner").toString());

        //Places 
        CurrentHost.setTotalPlaces((int)Float.parseFloat(PreHost.get("totalplaces").toString())); 
        CurrentHost.setAvailablePlaces((int)Float.parseFloat(PreHost.get("availableplaces").toString())); 

        //Localisation
        CurrentHost.setLocalisation(PreHost.get("localisation").toString());

        //DateStart
//        CurrentHost.set
        
        //LINK
        CurrentHost.setParticipants(PreHost.get("participants").toString());
        
        return CurrentHost;
    }
    
    
    public static HostRating MapToRating(Map<String, Object> PreHost){
        
        //Host Instance
        HostRating CurrentHost = new HostRating();
                
        //ID
        float TempID  = Float.parseFloat(PreHost.get("id").toString());
        CurrentHost.setID((int) TempID);

       
        CurrentHost.setHostID((int)Float.parseFloat(PreHost.get("hostid").toString())); 
        CurrentHost.setComment(PreHost.get("comment").toString());
 
       
        return CurrentHost;
    }

}
