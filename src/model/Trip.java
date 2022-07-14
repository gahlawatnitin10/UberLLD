package model;


enum TripStatus {
    IN_PROGRESS,
    FINISHED
}

public class Trip {
    private Rider rider;
    private Cab cab;
    private TripStatus status;
    private Double price;
    private Location fromPoint;
    private Location toPoint;

    public Trip(Rider rider, Cab cab, Double price, Location fromPoint, Location toPoint) {
        this.rider = rider;
        this.cab = cab;
        this.price = price;
        this.fromPoint = fromPoint;
        this.toPoint = toPoint;
        this.status = TripStatus.IN_PROGRESS;
    }

    public void endTrip() {
        this.status = TripStatus.FINISHED;
    }

    public Rider getRider() {
        return rider;
    }

    public void setRider(Rider rider) {
        this.rider = rider;
    }

    public Cab getCab() {
        return cab;
    }

    public void setCab(Cab cab) {
        this.cab = cab;
    }

    public TripStatus getStatus() {
        return status;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Location getFromPoint() {
        return fromPoint;
    }

    public void setFromPoint(Location fromPoint) {
        this.fromPoint = fromPoint;
    }

    public Location getToPoint() {
        return toPoint;
    }

    public void setToPoint(Location toPoint) {
        this.toPoint = toPoint;
    }
}
