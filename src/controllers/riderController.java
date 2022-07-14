package controllers;

import database.RiderManager;
import database.TripManager;
import model.Location;
import model.Rider;
import model.Trip;

import java.util.List;

public class riderController {
    private RiderManager ridersManager;
    private TripManager tripsManager;

    public riderController(RiderManager ridersManager, TripManager tripsManager) {
        this.ridersManager = ridersManager;
        this.tripsManager = tripsManager;
    }

    public void fetch(String[] command){
        String riderId = command[2];
        if(command[1].equals("addRider")){
            String riderName = command[3];
            ridersManager.createRider(new Rider(riderId, riderName));
            System.out.println("rider added");
        }else if(command[1].equals("book")){
            Double sourceX = Double.parseDouble(command[3]);
            Double sourceY = Double.parseDouble(command[4]);
            Double destX = Double.parseDouble(command[5]);
            Double destY = Double.parseDouble(command[6]);
            tripsManager.createTrip(ridersManager.getRider(riderId), new Location(sourceX, sourceY), new Location(destX, destY));
            System.out.println("cab booked");
        }else if(command[1].equals("history")){
            List<Trip> trips = tripsManager.riderTripHistory(ridersManager.getRider(riderId));
            for(Trip trip : trips){
                System.out.println("From : "+trip.getFromPoint().getX()+" "+trip.getFromPoint().getY()+"  to "+trip.getToPoint().getX()+" "+trip.getToPoint().getY()+"  bill "+ trip.getPrice());
            }
        }else{
            System.out.println("Please enter valid command");
        }

    }

}
