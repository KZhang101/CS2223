import java.util.ArrayList;
import java.util.List;
import java.lang.Math;

public class BRGC{

    public static class algoRhythimes{

        String[] nameList = new String[]{"Alexiev", "Barack", "Clarice", "Darlene", " Eduardo", "Freddie", "Giselle"};
        String[] ignoredBandList = new String[]{"Freddie", "Giselle"};
        String[] currentPlayers = new String[]{"", "", "","", "", "", ""};

        public algoRhythimes(){
            playing();
        };
        
        // Generates Gray codes recursively 
        private List<String> generateGrayCodes(int n){
            if(n == 1){
                List<String> temp = new ArrayList<String>();
                temp.add("0");
                temp.add("1");
                return temp;
            }
            else{
                List<String> combined = new ArrayList<String>();
                combined.addAll(generateGrayCodes(n-1));
                combined.addAll(generateGrayCodes(n-1).reversed());
                int size = combined.size();
                for(int i = 0; i<combined.size()/2; i++){
                    combined.set(i, "0"+combined.get(i));
                    combined.set(size-i-1, "1"+combined.get(size-i-1));
                }
                return combined;                
            }
        }

        /*
         * Prints name of memeber and whether if they join or fade
         */
        private void printBand(int member, boolean on){
            if(on){
                System.out.println(nameList[member] + " joins");
            }
            else{
                System.out.println(nameList[member] + " fades");
            }
        }

        /**
         * Returns bit with changed bit location 
         * @param str1 bit string 1 
         * @param str2 bit string 2
         */
        private int compareStrings(String str1, String str2){
            int num1 = Integer.parseInt(str1, 2);
            int num2 = Integer.parseInt(str2, 2);
            return num1^num2;
        }


        /**
         * Update current playing characters given bit location and if it turned off or on 
         * @param indexLocation
         * @param isPlaying
         */
        private void printCurrentPlayers(int indexLocation, boolean isPlaying){
            if(isPlaying){
                currentPlayers[indexLocation] = nameList[indexLocation];
            }
            else{
                currentPlayers[indexLocation] = "";
            }

            for(int i = 0; i < 7-1; i++){
                String str = currentPlayers[i];
                if(!str.equals("")) System.out.print(str + ", ");
            }
            System.out.print(currentPlayers[6] + " | ");
        }


        /**
         * Plays the band 
         */
        private void playing(){
            List<String> grayCodes = generateGrayCodes(nameList.length);
            String previousGrayCode = "0000000";
            int indexLocation = 0;
            System.out.println("Index  | Gray Code | Currently Playing | Action");
            System.out.println("0 | 0000000 | Silent | \n");
            for(int i = 1; i < grayCodes.size(); i++){
                System.out.print(i + " | ");
                String currentGrayCode = grayCodes.get(i);
                int bit = compareStrings(previousGrayCode, currentGrayCode);
                indexLocation = (int)(Math.log(bit) / Math.log(2));
                int strLength = currentGrayCode.length();
                System.out.print(" " + currentGrayCode + " |");
                boolean isPlaying = currentGrayCode.substring(strLength - indexLocation-1, strLength - indexLocation).equals("1");
                printCurrentPlayers(indexLocation, isPlaying);
                printBand(indexLocation, isPlaying);
                previousGrayCode = currentGrayCode;
                //System.out.println("\n");
            }
        }


    }

    public static void printList(List<String> list){
        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }

    public static void main(String[] args) {
        algoRhythimes band = new algoRhythimes();
    }

}