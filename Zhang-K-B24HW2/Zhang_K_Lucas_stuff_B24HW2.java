import java.time.LocalTime;
import java.time.Duration;

public class Zhang_K_Lucas_stuff_B24HW2 {
    
    public static void main(String[] args) {
        //calcLucasTime(45);
        calcMyNumTime(20);
    }    

    /**
     * Function to calculate the Lucas Number recursively 
     * @param n
     * @return int lucas number 
     */
    public static int lucasNumber(int n){
        if(n==0) return 2;
        if(n==1) return 1;
        return lucasNumber(n-1) + lucasNumber(n-2);
    }


    /**
     * Function to calculate the Lucas Number recursively 
     * @param n
     * @return int lucas number 
     */
    public static int myNumber(int n){
        if(n==0) return 10;
        if(n==1) return 29;
        return lucasNumber(n-1) + lucasNumber(n-2);
    }

    /**
     * Used to calculate the time taken for each Lucas Number; 
     * @param n
     */
    public static void calcLucasTime (int n){
        float previousDuration = 0; 
        float previousLucasNum = 0;
        for(int i = 0; i<= n; i++){
            LocalTime startingTime = LocalTime.now();
            long lucasNum = lucasNumber(i);
            float duration = (float)Duration.between(startingTime, LocalTime.now()).toMillis();
            float timeRatio, lucasRatio = 0;
            if(previousDuration == 0){
                timeRatio = 0;
            }
            else{
                timeRatio = duration/previousDuration;
            }
            lucasRatio = lucasNum/previousLucasNum;
            previousLucasNum = lucasNum;
            previousDuration = duration;
            System.out.println("n: " + i +"| Lucas Number: "+ lucasNum + "| Ratio: "+ lucasRatio + "| Time taken: " + duration + "ms" + "|Ratio: " + timeRatio);
        }
    }

    public static void calcMyNumTime (int n){
        float previousDuration = 0; 
        float previousLucasNum = 0;
        for(int i = 0; i<= n; i++){
            LocalTime startingTime = LocalTime.now();
            long lucasNum = myNumber(i);
            float duration = (float)Duration.between(startingTime, LocalTime.now()).toMillis();
            float timeRatio, lucasRatio = 0;
            if(previousDuration == 0){
                timeRatio = 0;
            }
            else{
                timeRatio = duration/previousDuration;
            }
            lucasRatio = lucasNum/previousLucasNum;
            previousLucasNum = lucasNum;
            previousDuration = duration;
            System.out.println("n: " + i +"| My Number: "+ lucasNum + "| Ratio: "+ lucasRatio + "| Time taken: " + duration + "ms" + "|Ratio: " + timeRatio);
        }
    }



}
