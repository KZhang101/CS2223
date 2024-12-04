import java.util.*;


public class hasing {

    private String originalTxt;
    private ArrayList<String> parsedStr = new ArrayList<String>();
    private String[] hashTable;
    private int[] hashVaules;  
    private int hashSize;
    private int hashConstant;
    private int nonEmptyCount;


    
    public hasing(String txt, int hashSize, int hashConstant){
        this.hashSize = hashSize;
        this.hashConstant = hashConstant;
        this.hashTable = new String[this.hashSize];
        this.hashVaules = new int[this.hashSize];
        for(int i = 0; i < hashSize; i++){
            this.hashTable[i] = "-1";
        }

        parseString(txt);
        addHash();
        viewHash();

        question3A();
        question3B();
        question3C();
        question3D();
        question3E();

        // searchHash("EDGAR");
        // searchHash("ALLAN");
        // searchHash("POE");
    }



    public void parseString(String txt){
        String words = txt.replaceAll("[^a-zA-Z0-9 ' -]", " ").replaceAll("\\s+", " ").trim();
        //words = words.replaceAll("\\?", " ").trim();
        //words = words.replaceAll("?", " ").trim();
        while(words.indexOf(" ") != -1){
            int index = words.indexOf(" ");
            String str = words.substring(0, index);
            parsedStr.add(str);
            //System.out.println(str);
            words = words.substring(index+1);
        }
        parsedStr.add(words);
    }

    public void addHash(){
        int len = parsedStr.size();
        for(int i = 0; i < len; i++){
            String word = parsedStr.get(i);
            int key  = calcHashValue(word);
            int originalVal = key;
            while(!this.hashTable[key].equals("-1") && !this.hashTable[key].equals(word)){
                if(key >= (this.hashSize-1)) {
                    key = 0;
                }
                else{
                    key++;
                }
            }
            this.hashTable[key] = word;
            this.hashVaules[key] = originalVal; 
            //System.out.println("Hash Address: "+ key + " | Word: "+ this.hashTable[key] +" | Value: " + originalVal + "| I: " + i);
        }
    }



    public int calcHashValue(String txt){
        int hashValue = 0;
        int length = txt.length();
        for(int i = 0; i < length; i++){
            hashValue = (hashValue * this.hashConstant +  (int)txt.charAt(i)) % this.hashSize;
        }
        return hashValue; 
    }

    public void question3A(){
        for(int i = 0; i < this.hashTable.length; i++){
            this.nonEmptyCount += !this.hashTable[i].equals("-1") ? 1: 0;
        }
        System.out.println("Non-empty addreses count: " + this.nonEmptyCount + " | Load Factor: " + (this.nonEmptyCount / (this.hashSize * 1.0)));
    }

    public void question3B(){
        int maxCount = 0;
        int currentCount = 0;
        int end = 0;
        for(int i = 0; i < this.hashTable.length; i++){
            if(this.hashTable[i].equals("-1")){
                currentCount++;
            }
            else{
                if(maxCount < currentCount){
                    maxCount = currentCount;
                    end = i-1;
                } 
                currentCount = 0;
            }
        }
        System.out.println("Longest empty run is " + maxCount+ " starting from " + (end-maxCount+1) + " and ending at " + end);
    }

    public void question3C(){
        int maxCount = 0;
        int currentCount = 0;
        int end = 0;
        int start = 0;
        boolean fullLoop = false;
        boolean maxWrap = false;

        for(int i = 0; i < this.hashTable.length; i++){
            if(!this.hashTable[i].equals("-1")){
                currentCount++;
                if(i == this.hashTable.length-1){
                    i = -1;
                    fullLoop = true;
                } 
            }
            else{
                if(maxCount < currentCount){
                    maxWrap = fullLoop;
                    maxCount = currentCount;
                    end = i-1;
                    start = end-maxCount+1;
                    //System.out.println(end);
                } 
                currentCount = 0;
                if(maxWrap){
                    end = i;
                    start = this.hashTable.length - (maxCount - i); 
                }
                if(fullLoop) break;
            }
        }
        System.out.println("Longest non-empty run is " + maxCount + " starting from " + start + " and ending at " + end);
    }

    public void question3D(){
        int mostPopularHash = 0;
        int maxCount = 0;
        int currentCount = 0;

        for(int i = 0; i < this.hashTable.length; i++){
            currentCount = 0;
            for(int j = 0; j < this.hashTable.length; j++){
                if(this.hashVaules[i] == 0) break;
                if(this.hashVaules[i] == this.hashVaules[j]){
                    currentCount++;
                }
            }
            if(maxCount < currentCount){
                maxCount = currentCount;
                mostPopularHash = i;
            }
        }
        System.out.println("Hash value " + mostPopularHash + " occurs " + maxCount + " times");
    }

    public void question3E(){
        String mostDriftHash = "";
        int max = 0;
        int diff = 0;
        int location = 0;
        for(int i = 0; i < this.hashTable.length; i++){
            if(hashTable[i].equals("-1")) continue;
            diff = (i - this.hashVaules[i]);
            if(diff < 0) diff = diff + 1000;
            if(max < diff){
                max = diff;
                mostDriftHash = hashTable[i];
                location = i;
            }
        }
        System.out.println(mostDriftHash + " drifted more than any word, " + max + " places from the hash address " + 
                            (location - max) + " all the way to position " + location);
    }

    public void viewHash(){
        for(int i = 0; i < this.hashTable.length; i++){
            int hashValue = this.hashVaules[i];
            if(hashValue != 0 ){
                System.out.println("Address: " + i + "| Hashed Word: " + this.hashTable[i] + " | Hash Value of Word: " + hashValue);
            }
            else{
                System.out.println("Address: " + i + "| Hashed Word: " + this.hashTable[i] + " | Hash Value of Word: " + "n/a");
            }
        }
    }

    public int searchHash(String word){
        int key = calcHashValue(word);
        System.out.println("Actual Hash Value: " + key);
        while(!this.hashTable[key].equals("-1") && !this.hashTable[key].equals(word)){
            if(key >= (this.hashSize-1)) {
                key = 0;
            }
            else{
                key++;
            }
        }
        System.out.println("Stored Address: " + key);
        return key;
    }





         
}
