package applicationDriver;

import controllers.cabController;
import controllers.riderController;
import database.CabManager;
import database.RiderManager;
import database.TripManager;
import strategy.cabMatchingStrategy;
import strategy.pricingStrategy;

import java.util.Scanner;

public class codeDriver {
    private cabController CabController;
    private riderController RiderController;

    void initialize() {
        CabManager cabsManager = new CabManager();
        RiderManager ridersManager = new RiderManager();

        cabMatchingStrategy cabMatchingStrategy = new cabMatchingStrategy();
        pricingStrategy pricingStrategy = new pricingStrategy();

        TripManager tripManager = new TripManager(cabsManager, ridersManager, cabMatchingStrategy, pricingStrategy);

        CabController = new cabController(cabsManager, tripManager);
        RiderController = new riderController(ridersManager, tripManager);
    }

    public void run(){
        initialize();
        Scanner scn = new Scanner(System.in);
        boolean runMode = true;
        while(runMode){
            String command = scn.nextLine();
            String[] commands = command.split(" ");

            if(commands[0].equals("rider")){
                RiderController.fetch(commands);
            }else if(commands[0].equals("cab")){
                CabController.fetch(commands);
            }else if(commands[0].equals("exit")){
                runMode = false;
            }else{
                System.out.println("please enter valid command");
            }
        }
    }

}
