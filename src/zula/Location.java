/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package zula;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;


public class Location {
    int id;
    int distFromOrigin;
    
    LinkedList<Cab> cabs;
    
    Location(int id, int distFromOrigin){
        this.id = id;
        this.distFromOrigin = distFromOrigin;
        cabs = new LinkedList<Cab>();
    }
    
    void addCab(Cab cabToAdd){
        cabs.add(cabToAdd);
    }
    
    void removeCab(Cab cabToRemove){
        cabs.remove(cabToRemove);
    }
    
    void removeCab(int cabID){
        cabs.remove(new Cab(" ", " ", 0, " ", cabID));
    }
    
    
    void printCabs(){
        
        for(int i = 0; i < cabs.size(); i++){
            System.out.print("(" + cabs.get(i).id + ") ");
        }
        System.out.println();
    }
    
    int countCabs(){
        Collections.sort(cabs, new Comparator<Cab>(){

            @Override
            public int compare(Cab o1, Cab o2) {
                if( o1.rest - o2.rest == 0 ){
                    return o1.rides.size() - o2.rides.size();
                }
                else{
                    return o1.rest - o2.rest;
                }
            }
            
        });
        
        int cabsAvail = 0;
        for(Cab c : cabs){
            if(c.rest <= 0){
                cabsAvail++;
            }
        }
        return cabsAvail;
    }
    
    Cab removeLastCab(){
        Collections.sort(cabs, new Comparator<Cab>(){

            @Override
            public int compare(Cab o1, Cab o2) {
                if( o1.rest - o2.rest == 0 ){
                    return o1.rides.size() - o2.rides.size();
                }
                else{
                    return o1.rest - o2.rest;
                }
            }
            
        });
        
        printCabs();
        
        Cab x = cabs.get(0);
        cabs.remove(cabs.get(0));
        return x;
    }
}
