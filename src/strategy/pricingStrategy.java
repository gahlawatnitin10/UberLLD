package strategy;

import model.Location;

public class pricingStrategy {


    public Double findPrice(Location fromPoint, Location toPoint) {
        Double bill = 50.0;
        Double distance = fromPoint.distance(toPoint);
        if(distance>=2){
            distance-=2;
            bill+=20;
            if(distance>=8){
                distance-=8;
                bill+=56;
                bill+=(distance)*5;
            }else{
                bill+=(8-distance)*7;
            }
        }else{
            bill+=(2-distance)*10;
        }
        return bill;
    }
}
