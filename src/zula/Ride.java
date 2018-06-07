/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zula;

/**
 *
 * @author Administrator
 */
public class Ride {
    int Source;
    int Destination;
    int customerID;
    int driverID;
    int fare;
    
    double commission;
    
    Ride(int S, int D, int cID, int dID){
        Source = S;
        Destination = D;
        customerID = cID;
        driverID = dID;
        //calculateFare();
    }
    
    void calculateFare(Location[] locs){
        int sD = 0;
        int eD = 0;
        
        for(Location l : locs){
            if(l.id == Source){
                sD = l.distFromOrigin;
            }
            
            if(l.id == Destination){
                eD = l.distFromOrigin;
            }
            
            this.fare = Math.abs(sD - eD) * 10;
            this.commission = (float)this.fare * 0.3f;
        }
    }
}
