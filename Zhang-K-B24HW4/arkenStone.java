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
        
        
        public BillboTheLegend(){
            beginTheHunt();
        }



        public boolean isValidIndex(int i, int j){
            return (i >= 0 && i < greatHall.length) && (j >= 0 && j < greatHall.length); 
        } 
        
        
        public void beginTheHunt(){
            int hallSize = greatHall.length;
            int[][] valueMap = new int[8][8];
            int[] currentPosition = new int[2];

            for(int startingColumn = 0; startingColumn < hallSize; startingColumn++){ // go through all possible starting locations  
            }
        }







    }

    
    
    public static void main(String[] args) {
        
    }
}
