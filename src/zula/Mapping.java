package zula;

public class Mapping {
    int FindNearestCab(Location[] locs, int Source){
        
        int startD = 0;
        for(Location l : locs){
            if(l.distFromOrigin != -1){
                if(l.id == (Source)){
                    startD = l.distFromOrigin;
                }
            }
        }
        //System.out.println("Start = " + startD);
        
        int minId = -1;
        int minD = Integer.MAX_VALUE;
        for(Location l : locs){
            if(l.distFromOrigin != -1){
                if(l.countCabs() > 0){
                    if(Math.abs(l.distFromOrigin - startD) < minD){
                        minD = Math.abs(l.distFromOrigin - startD);
                        minId = l.id;
                    }
                }
            }
        }
        
        if(minD == Integer.MAX_VALUE){
            return -1;
        }
        return minId;
    }
}
