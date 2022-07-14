package database;

import exceptions.NoCabsAvailableException;
import exceptions.TripNotFoundException;
import model.Cab;
import model.Location;
import model.Rider;
import model.Trip;
import strategy.cabMatchingStrategy;
import strategy.pricingStrategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TripManager {


    public static final Double MAX_ALLOWED_TRIP_MATCHING_DISTANCE = 15.0;
    private Map<String, List<Trip>> riderTrips = new HashMap<>();
    private Map<String, List<Trip>> driverTrips = new HashMap<>();

    private CabManager cabsManager;
    private RiderManager ridersManager;
    private strategy.cabMatchingStrategy cabMatchingStrategy;
    private strategy.pricingStrategy pricingStrategy;

    public TripManager(CabManager cabsManager, RiderManager ridersManager, cabMatchingStrategy cabMatchingStrategy, pricingStrategy pricingStrategy) {
        this.cabsManager = cabsManager;
        this.ridersManager = ridersManager;
        this.cabMatchingStrategy = cabMatchingStrategy;
        this.pricingStrategy = pricingStrategy;
    }

    public void createTrip(Rider rider, Location fromPoint, Location toPoint) {
        List<Cab> closeByCabs = cabsManager.getCabs(fromPoint, MAX_ALLOWED_TRIP_MATCHING_DISTANCE);
        List<Cab> closeByAvailableCabs = new ArrayList<>();

        for(Cab cab : closeByCabs){
            if(cab.getCurrentTrip()==null){
                closeByAvailableCabs.add(cab);
            }
        }

        Cab selectedCab = cabMatchingStrategy.matchCabToRider(rider, closeByAvailableCabs, fromPoint, toPoint);
        if (selectedCab == null) {
            throw new NoCabsAvailableException();
        }

        final Double price = pricingStrategy.findPrice(fromPoint, toPoint);
        final Trip newTrip = new Trip(rider, selectedCab, price, fromPoint, toPoint);
        if (!riderTrips.containsKey(rider.getId())) {
            riderTrips.put(rider.getId(), new ArrayList<>());
        }
        riderTrips.get(rider.getId()).add(newTrip);

        if (!driverTrips.containsKey(selectedCab.getId())) {
            driverTrips.put(selectedCab.getId(), new ArrayList<>());
        }
        driverTrips.get(selectedCab.getId()).add(newTrip);

        selectedCab.setCurrentTrip(newTrip);
    }

    public List<Trip> riderTripHistory(Rider rider) {
        return riderTrips.get(rider.getId());
    }

    public List<Trip> cabTripHistory(Cab cab) {
        return driverTrips.get(cab.getId());
    }

    public void endTrip(Cab cab) {
        if (cab.getCurrentTrip() == null) {
            throw new TripNotFoundException();
        }

        cab.getCurrentTrip().endTrip();
        cab.setCurrentTrip(null);
    }
}
