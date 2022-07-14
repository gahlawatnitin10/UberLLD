package strategy;

import model.Cab;
import model.Location;
import model.Rider;

import java.util.List;

public class cabMatchingStrategy {
    public Cab matchCabToRider(Rider rider, List<Cab> candidateCabs, Location fromPoint, Location toPoint) {
        if (candidateCabs.isEmpty()) {
            return null;
        }
        return candidateCabs.get(0);
    }
}
