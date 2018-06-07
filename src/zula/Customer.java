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
public class Customer {
    int customerID;
    String customerName;
    LinkedList<Ride> rides;
    int userCode = 0;
    String password;
        void addRide(Ride rideToAdd){
            rides.add(rideToAdd);
        }
        
        Customer(int cID, String cName, int uC, String pass){
            customerID = cID;
            customerName = cName;
            rides = new LinkedList<Ride>();
            userCode = uC;
            password = pass;
        }
        
        void printRides(){
            int tR = 0;
            int tF = 0;
            System.out.println("Customer ID : " + customerID + "\nCustomer Name : " + customerName);
            System.out.println("Src. || Dest. || Cab ID || Fare");
            for(Ride r : rides){
                tR++;
                tF += r.fare;
                System.out.println(r.Source + " || " + r.Destination + " || " + r.driverID + " || " + r.fare);
            }
            System.out.println("Total Rides : " +tR);
            System.out.println("Total Fare : " +tF);
        }
        
        public boolean isAdmin(){
            if(userCode == 1){
                return true;
            }
            return false;
        }
}
