package database;

import exceptions.RiderAlreadyExistsException;
import exceptions.RiderNotFoundException;
import model.Rider;

import java.util.HashMap;
import java.util.Map;

public class RiderManager {
    Map<String, Rider> riders = new HashMap<>();

    public void createRider(Rider newRider) {
        if (riders.containsKey(newRider.getId())) {
            throw new RiderAlreadyExistsException();
        }

        riders.put(newRider.getId(), newRider);
    }

    public Rider getRider(String riderId) {
        if (!riders.containsKey(riderId)) {
            throw new RiderNotFoundException();
        }
        return riders.get(riderId);
    }
}
