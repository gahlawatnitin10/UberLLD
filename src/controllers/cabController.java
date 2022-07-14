package controllers;

import database.CabManager;
import database.TripManager;
import model.Cab;
import model.Location;
import model.Rider;
import model.Trip;

import java.util.List;

public class cabController {

    private CabManager cabsManager;
    private TripManager tripsManager;

    public cabController(CabManager cabsManager, TripManager tripsManager) {
        this.cabsManager = cabsManager;
        this.tripsManager = tripsManager;
    }

    public void fetch(String[] command){
        String cabId = command[2];
        if(command[1].equals("addCab")){
            String cabDriverName = command[3];
            cabsManager.createCab(new Cab(cabId, cabDriverName));
            System.out.println("cab added");
        }else if(command[1].equals("updateLocation")){
            Double newX = Double.parseDouble(command[3]);
            Double newY = Double.parseDouble(command[4]);
            cabsManager.updateCabLocation(cabId, new Location(newX, newY));
            System.out.println("updated location");
        }else if(command[1].equals("updateAvailability")){
            boolean newAvailability = true;
            if(command[3].equals("false")){
                newAvailability = false;
            }
            cabsManager.updateCabAvailability(cabId, newAvailability);
            System.out.println("updated availability");
        }else if(command[1].equals("history")){
            List<Trip> trips = tripsManager.cabTripHistory(cabsManager.getCab(cabId));
            for(Trip trip : trips){
                System.out.println("From : "+trip.getFromPoint().getX()+" "+trip.getFromPoint().getY()+"  to "+trip.getToPoint().getX()+" "+trip.getToPoint().getY()+"  bill "+ trip.getPrice());
            }
        }else if(command[1].equals("endTrip")){
            tripsManager.endTrip(cabsManager.getCab(cabId));
            System.out.println("trip ended");
        }else{
            System.out.println("Please enter valid command");
        }

    }

}
