/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zula;

import java.util.LinkedList;

/**
 *
 * @author Administrator
 */
public class Cab {
    String driverName;
    String driverPassword;
    int driverAge;
    String driverGender;
    int id;
    
    int rest = 0;
    
    double totalC = 0;
    
    LinkedList<Ride> rides;
    
    Cab(String dN, String dP, int dA, String dG, int id){
        this.id = id;
        driverName = dN;
        driverPassword = dP;
        driverAge = dA;
        driverGender = dG;
        rides = new LinkedList<Ride>();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Cab other = (Cab) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }
    
    void addRide(Ride rideToAdd){
        rides.add(rideToAdd);
    }
    
    void printRides(){
        
        int tR = 0;
        double tF = 0, tC = 0;
        System.out.println("Cab ID : " + this.id);
        System.out.println("Src. || Dest. || Fare || Cust. ID || Commission");
        for(Ride r : rides){
            tC += r.commission;
            tF += r.fare;
            tR++;
            System.out.println(r.Source + " || " + r.Destination + " || " + r.fare + " || " + r.customerID + " || " + r.commission);
        }
        System.out.println("Total Rides : " +tR);
        System.out.println("Total Fare : " +tF);
        System.out.println("Total Commission : " +tC);
        
        totalC += tC;
    }
    
}
