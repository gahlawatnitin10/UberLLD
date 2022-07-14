package database;

import exceptions.CabAlreadyExistsException;
import exceptions.CabNotFoundException;
import model.Cab;
import model.Location;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CabManager {
    Map<String, Cab> cabs = new HashMap<>();

    public void createCab(Cab newCab) {
        if (cabs.containsKey(newCab.getId())) {
            throw new CabAlreadyExistsException();
        }

        cabs.put(newCab.getId(), newCab);
    }

    public Cab getCab(String cabId) {
        if (!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        return cabs.get(cabId);
    }

    public void updateCabLocation(String cabId, Location newLocation) {
        if (!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        cabs.get(cabId).setCurrentLocation(newLocation);
    }

    public void updateCabAvailability (String cabId,Boolean newAvailability) {
        if (!cabs.containsKey(cabId)) {
            throw new CabNotFoundException();
        }
        cabs.get(cabId).setIsAvailable(newAvailability);
    }

    public List<Cab> getCabs(Location fromPoint, Double distance) {
        List<Cab> result = new ArrayList<>();
        for (Cab cab : cabs.values()) {
            if (cab.getIsAvailable() && cab.getCurrentLocation().distance(fromPoint) <= distance) {
                result.add(cab);
            }
        }
        return result;
    }
}
