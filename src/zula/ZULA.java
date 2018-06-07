package zula;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Scanner;

/**
 *
 * @author Administrator
 */
public class ZULA {

    /**
     * @param args the command line arguments
     */
    
    static Location[] locations;
    static Customer[] customers;
    
    public static void main(String[] args) throws IOException {
        
        locations = new Location[8];
        
        locations[0] = new Location(0, 0);
        locations[1] = new Location(1, 4);
        locations[2] = new Location(2, 7);
        locations[3] = new Location(3, 9);
        locations[4] = new Location(4, 15);
        locations[5] = new Location(5, 18);
        locations[6] = new Location(6, 20);
        locations[7] = new Location(7, 23);
        
        
        customers = new Customer[3];
        
        customers[0] = new Customer(0, "Karthi", 0, "user");
        customers[1] = new Customer(1, "Admin", 1, "pass");
        
        locations[0].addCab(new Cab("aaa", "111", 43, "M", 1));
        locations[1].addCab(new Cab("bbb", "222", 31, "M", 2));
        locations[2].addCab(new Cab("ccc", "333", 27, "F", 3));
        locations[3].addCab(new Cab("ddd", "444", 32, "F", 4));
        //locations[3].addCab(new Cab("ddd", "444", 32, "F", 4));

        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int CID = -1;
        
        
        while(true){
            System.out.println("Customer Login");
            String op = br.readLine();
            while(op.equals("Login")){
                System.out.println("Enter Login Username");
                String pop = br.readLine();
                for(int x = 0; x < customers.length - 1; x++){
                    if(customers[x].customerName.equals(pop)){
                        
                        while(true){
                            System.out.println("Enter Password : ");
                            pop = br.readLine();
                            //System.out.println(pop + " " + customers[x].password);
                            if(pop.equals(customers[x].password)){
                                CID = customers[x].customerID;
                                System.out.println("Logged in as " +customers[x].customerName);
                                break;
                            }
                        }
                        if(CID != -1){
                            break;
                        }
                    }
                }
                if(CID == -1){
                    System.out.println("Customer not found ! Try again !");
                }
                else{
                    break;
                }   
            }
            while(!op.equals("Login")){
                System.out.println("Enter new Customer name");
                String pop = br.readLine();
                System.out.println("Enter new Customer password");
                String popp = br.readLine();
                CID = 2;
                customers[2] = new Customer(2, pop, 0, popp);
                break;
            }
            break;
        }
        
        int S = -1;
        int D = -2;
        
        String opt = "";
        
        while(!opt.equals("Quit")){
            for(Location l : locations){
                if(l.distFromOrigin != -1){
                    System.out.print("Location " + l.id + " at " + l.distFromOrigin + " : ");
                    l.printCabs();    
                }
                
            }
            
            if(customers[CID].isAdmin()){
                System.out.println("Enter Administration Commands");
            }
            else{
                System.out.println("Enter Source and Destination");
            }
            
            System.out.print(customers[CID].customerName + "> ");
            
        
            opt = br.readLine();
            
            if(customers[CID].isAdmin()){
                
            
            if(opt.split(" ")[0].equals("CabDetails")){
                int cabID = Integer.parseInt(opt.split(" ")[1]);
                printRides(cabID);
            }
            else if(opt.split(" ")[0].equals("CustomerDetails")){
                customers[CID].printRides();
            }
            else if(opt.split(" ")[0].equals("Book")){
                S = Integer.parseInt(opt.split(" ")[1]);
                D = Integer.parseInt(opt.split(" ")[2]);
                
                boolean sC = false;
                boolean dC = false;
                
                for(Location l : locations){
                    if(l.distFromOrigin != -1){
                        if(l.id == S){
                            sC = true;
                        }
                        
                        if(l.id == D){
                            dC = true;
                        }
                    }
                }
        
                int cabID = -1;
                
                if(!(sC && dC)){
                    System.out.println("Invalid Source or Destination !");
                }
                else{
                    
                    cabID = createRide(S, D, CID);
                
                }
                
                if(cabID == -1){
                    System.out.println("Booking cancelled !");
                }
            }
            else if(opt.split(" ")[0].equals("Admin")){
                    if(opt.split(" ")[1].equals("Cab")){
                        if(opt.split(" ")[2].equals("Remove")){
                            int tID = Integer.parseInt(opt.split(" ")[3]);
                            removeCab(tID);
                        }
                        else if(opt.split(" ")[2].equals("Add")){
                            addCab();
                        }
                        else if(opt.split(" ")[2].equals("Report")){
                            printCabReport();
                        }
                    }else if(opt.split(" ")[1].equals("Location")){
                        if(opt.split(" ")[2].equals("Remove")){
                            int tID = Integer.parseInt(opt.split(" ")[3]);
                            locations[tID].distFromOrigin = - 1;
                        }
                        else if(opt.split(" ")[2].equals("Add")){
                            addLocation();
                        }
                    }
            }
            
            }
            else{
                
                if(opt.equals("Quit")){
                    System.exit(0);
                }
                S = Integer.parseInt(opt.split(" ")[0]);
                D = Integer.parseInt(opt.split(" ")[1]);
                
                boolean sC = false;
                boolean dC = false;
                
                for(Location l : locations){
                    if(l.distFromOrigin != -1){
                        if(l.id == S){
                            sC = true;
                        }
                        
                        if(l.id == D){
                            dC = true;
                        }
                    }
                }
        
                int cabID = -1;
                
                if(!(sC && dC)){
                    System.out.println("Invalid Source or Destination !");
                }
                else{
                    
                    cabID = createRide(S, D, CID);
                
                }
                
                if(cabID == -1){
                    System.out.println("Booking cancelled !");
                }
            }
        
            
            
        }
        
        
    }
    
    static int createRide(int Source, int Destination, int cID) throws IOException{
        Mapping m = new Mapping();
        int nearestCabLocation = m.FindNearestCab(locations, Source);
        
        Cab onRide = locations[nearestCabLocation].removeLastCab();
        
        
        
        Ride r = new Ride(Source, Destination, cID, onRide.id);
        
        r.calculateFare(locations);
        
        if(confirmRide(Source, Destination, onRide.id, r.fare)){
            onRide.rest = 2;
        
            onRide.addRide(r);
        
            locations[Destination].addCab(onRide);

            updateRest();
        
            customers[cID].addRide(r);
        
            return onRide.id;
        }
        else{
            locations[nearestCabLocation].addCab(onRide);
            return -1;
        }
        
        
        
    }
    
    static boolean confirmRide(int Source, int Destination, int cabID, int fare) throws IOException{
        System.out.println("Ride from " +Source + " to " +Destination + ".\nEstimated fare " +fare+ " with cab ID " +cabID+ ".\nDo you want to confirm the booking ?");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        String op = br.readLine();
        
        if(op.equals("yes") || op.equals("Yes") || op.equals("y") || op.contains("y")){
            return true;
        }
        return false;
    }
    
    static void printRides(int cabID){
        
        for(Location l : locations){
            for(Cab c : l.cabs){
                if(c.id == cabID){
                    System.out.println("Cab Driver Name : " + c.driverName);
                    c.printRides();
                }
            }
        }
    }
    
    static void updateRest(){
        for(Location l : locations){
            if(l.distFromOrigin != -1){
                
            
                for(Cab c : l.cabs){
                    if(c.rest > 0){
                      c.rest--;
                   }
                }
            
            }
        }
    }
    
    static void removeCab(int cabID){
        for(int i = 0; i < locations.length; i++){
            if(locations[i].cabs.contains(new Cab(" ", " ", 0, " ", cabID))){
                locations[i].cabs.remove(new Cab(" ", " ", 0, " ", cabID));
            }
        }
    }
    
    static void addCab(){
        
        System.out.println();
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter Cab Driver Name : ");
        String dN = sc.nextLine();
        System.out.println("Enter Cab Driver Password : ");
        String dP = sc.nextLine();
        System.out.println("Enter Cab Driver Age : ");
        int dA = Integer.parseInt(sc.nextLine());
        System.out.println("Enter Cab Gender : ");
        String dG = sc.nextLine();
        System.out.println("Enter Cab ID : ");
        int dID = Integer.parseInt(sc.nextLine());
       
        int cLoc = -1;
        
        while(!isValidLocation(cLoc)){
            System.out.println("Enter Cab Location : ");
            cLoc = Integer.parseInt(sc.nextLine());
        }
        
        int locID = 0;
        for(Location l : locations){
            if(!(l.distFromOrigin == -1)){
                if(l.id == cLoc){
                    locID = l.id;
                }
            }
            
        }
        locations[locID].addCab(new Cab(dN, dP, dA, dG, dID));
        
    }
    
    static void addLocation(){
        Scanner sc = new Scanner(System.in);
        
        int locID = -1;
        while(! isValidLocation(locID)){
            System.out.println("Enter Location ID : ");
            locID = sc.nextInt();
            System.out.println("Enter Distance from Origin : ");
            int dist = sc.nextInt();
            Location temp[] = new Location[locations.length + 1];
            
            System.arraycopy(locations, 0, temp, 0, locations.length);
            
            temp[temp.length - 1] = new Location(locID, dist);
            
            locations = temp;
            
        }
    }
    
    static boolean isValidLocation(int loc){
        for(Location l : locations){
            if(l.distFromOrigin != -1 && l.id == loc){
                return true;
            }
        }
        return false;
    }
    
    static void printCabReport(){
        double totalFare = 0;
        double totalComm = 0;
        for(Location l : locations){
            
            if(l.distFromOrigin != -1 && l.cabs.size() > 0){
                
                for(Cab c : l.cabs){
                        c.printRides();
                        totalComm += c.totalC;
                }
            }
        }
        
        System.out.println("Total ZULA Commission : " + totalComm);
        
    }
    
    static void printCustomerReport(){
        for(Customer c : customers){
            c.printRides();
        }
    }
    
}
