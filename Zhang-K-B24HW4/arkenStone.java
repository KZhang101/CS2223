import java.util.ArrayList;

public class arkenStone {
    public static class BillboTheLegend{
        private static final int[][] greatHall
        = {{21, 95, 20, 82, 66, 52, 89, 35},
        {74, 40, 37, 79, 23, 14, 5, 78},
        {63, 16, 4, 31, 25, 17, 59, 32},
        {15, 92, 70, 13, 48, 77, 11, 91},
        {12, 67, 88, 22, 64, 47, 71, 56},
        {7, 30, 51, 65, 27, 94, 97, 83},
        {93, 53, 24, 46, 86, 1, 41, 10},
        {84, 99, 68, 75, 98, 44, 33, 96}};   
        
        private static final int hallLen = greatHall.length;
        
        
        public BillboTheLegend(){
            beginTheHunt();
        }


        /**
         * 
         * @param row reference row
         * @param col reference col
         * @param retIdx index offset of largest value
         * @param valueMap map of values
         * @return gets the largest adjecent int from previous row
         */
        private int largestOfThree(int row, int col, boolean retIdx, int[][] valueMap){
            int max = -1; // not possible to get less than -1
            int idx = -1;
            
            for(int i = -1; i < 2; i++){
                try{
                    if(max < valueMap[row+1][col + i] ){
                        max = valueMap[row+1][col + i];
                        idx = i;
                    }
                }
                catch(Exception e){
                    continue; 
                }
            }
            int ret = retIdx ? idx: max;
            return ret;
        }


        /**
         * Finds the largest element index in the first row
         * @param valueMap
         * @return  largest element index in the first row
         */
        private int arkenStoneFinder(int[][] valueMap){
            int max = -1;
            int idx = -1;
            for(int i = 0; i < hallLen; i++){ 
                if(max < valueMap[0][i]){
                    max = valueMap[0][i];
                    idx = i;                
                }                
            }
            System.out.println("Max gem count: " + max + " wait wahh\n");
            return idx + 1;
        }

        /**
         * print Path of bill
         * @param start starting index of the last row
         * @param valueMap
         */
        private void pathFinder(int start,int[][] valueMap){
            String[][] path = new String[hallLen][hallLen];
            for(int i = 0; i < hallLen; i++) for(int j = 0; j < hallLen; j++) path[i][j] = "O";
            path[0][start] = "E";

            int col = start;
            for(int row = 0; row < hallLen-1; row++){ // backtrack to start
                int offset = largestOfThree(row, col, true, valueMap); // update col to largest below
                col = offset + col; // return index
                path[row+1][col] = row+1 == hallLen - 1 ? "S": "X";
            }
            printM(path);
        }
        
        public void beginTheHunt(){
            int[][] valueMap = new int[8][8];

            // Fills bottom row 
            for(int i = 0; i < hallLen; i++){
                valueMap[hallLen - 1][i] = greatHall[hallLen - 1][i];
            }

            // Iterate through each element 
            for(int row = hallLen-2; row >= 0; row--){
                for(int col = 0; col < hallLen; col++){ 
                    valueMap[row][col] = largestOfThree(row, col, false, valueMap) + greatHall[row][col];
                }
            }
            int arkenVault = arkenStoneFinder(valueMap);

            System.out.println("Max gem collect at each row and column");
            printM(valueMap);
            System.out.println("\nThe Arken Stone is hidden in vault: " + arkenVault);
            System.out.println("\nPath taken (S: start | X: Path | E: End): ");
            pathFinder(arkenVault - 1, valueMap);
        }

        private void printM(int[][] m){
            for(int i = 0; i < m.length; i++){
                System.out.print("[");
                for(int k = 0; k<m[i].length-1; k++){
                    System.out.print(m[i][k] + ",");
                }
                System.out.print(m[i][m[i].length-1] + "]");
                System.out.println();
            }
        }

        private void printM(String[][] m){
            for(int i = 0; i < m.length; i++){
                System.out.print("[");
                for(int k = 0; k<m[i].length-1; k++){
                    System.out.print(m[i][k] + ",");
                }
                System.out.print(m[i][m[i].length-1] + "]");
                System.out.println();
            }
        }

    }

    
    
    public static void main(String[] args) {
        BillboTheLegend bill = new BillboTheLegend();
    }
}
