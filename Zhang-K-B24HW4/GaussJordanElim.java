public class GaussJordanElim{

    public static class GJE{
        

        public GJE(float[][] m, float[] b){
            performGJE(m, b);
        };

        /**
         * Copies a matrix to a larger matrix
         * @param m
         * @param newM
         * @returnm m -> newM 
         */
        private float[][] copyM(float[][] m, float[][] newM){
            int len = m.length;
            for(int i = 0; i < len; i++){
                for(int k = 0; k<len; k++){
                    newM[i][k] = m[i][k];
                }
            }
            return newM;
        }


        private int checkSolutions(float[][] matrix) {
            boolean redundant = false;
            boolean inconsistent = false;

            for (int i = 0; i < matrix.length; i++) {
                boolean rowZeros = true;
                for (int j = 0; j < matrix[0].length - 1; j++) { 
                    if (Math.abs(matrix[i][j]) > 0) { // checks if there is a possible redundant or inconsitent solutions
                        rowZeros = false;
                        break;
                    }
                }

                if (rowZeros) {
                    if (Math.abs(matrix[i][matrix[0].length - 1]) > 0) { // 0 = 0 means redundant, else inconsistent
                        // Row is inconsistent
                        inconsistent = true;
                    } else {
                        // Row is redundant
                        redundant = true;
                    }
                }
            }

            if (inconsistent) {  // Throw inconsistent notice first
                System.out.println("The system is inconsistent and has no solution.");
                return 0;
            }

            if (redundant) {
                System.out.println("The system has redundant equations, leading to infinite solutions.");
            } else {
                System.out.println("The system has a unique solution.");
            }
            return 1;
        }

        private void performGJE(float[][] m, float[] b){
            int mLen = m.length;
            float[][] sysM = new float[mLen][mLen+1];
            sysM = copyM(m, sysM);

            // put vector b into last colum of new matrix
            for(int i = 0; i < mLen; i++){
                sysM[i][mLen] = b[i]; // last column
            }
            
            for(int col = 0; col < mLen; col++){ 
                int pivotRow = col; 
                for (int row = col + 1; row < mLen; row++) { //check every row to see if the pivot value
                    if (Math.abs(sysM[row][col]) > Math.abs(sysM[pivotRow][col])) {
                        pivotRow = row;
                    }
                }

                // Swap to row if new pivot col found 
                if (pivotRow != col) { 
                    float[] temp = sysM[pivotRow];
                    sysM[pivotRow] = sysM[col];
                    sysM[col] = temp;
                }
                
                
                float pivot = sysM[col][col]; // pivot value
                if(pivot == 0) continue;
                for(int j = col; j < mLen + 1; j++){ // for each colum in pivot row
                    sysM[col][j] = sysM[col][j]/ pivot; // divide row by pivot (makes pivot point 1)
                }
                
                for(int k = col+1; k < mLen; k++){ // for next row after pivot
                    float factor = sysM[k][col];
                    for (int j = col; j < mLen + 1; j++){ // for each column starting at pivot
                        sysM[k][j] = sysM[k][j] - factor * sysM[col][j]; // substract lower row by pivot row
                    }
                }
            }

            for(int i = mLen - 1; i >= 0; i--){ // start at last column to left column
                for(int k = i-1; k >= 0; k--){ // start at second to last row to first row
                    float factor = sysM[k][i]; 
                    //System.out.println(factor);
                    for(int j = i; j < mLen+1; j++){ // we want this entire column zero to the pivot
                        float value = sysM[k][j] - factor * sysM[i][j]; 
                        sysM[k][j] = value;
                    }
                }
            }
            for(int i = 0; i < mLen; i++) sysM[i][mLen] =  Math.round(sysM[i][mLen]); // round
            printM(sysM);
            checkSolutions(sysM);
            
        }

        private void printM(float[][] m){
            System.out.println();
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

        float[][] m
            = {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0},
            {0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1},
            {1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0 },
            {0, 1, 0, 0, 1, 1, 0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0, 1, 0, 1, 1, 1, 1},
            {11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1},
            {11, -10, 9, -8, 7, -6, 5, -4, 3, -2, 1}};



        float [][] testBAD = {
        {1, 1, 1},
        {1, 1, 2}, 
        {2, 2, 3}};

        float [] b2 = {6, 9, 15};

        float[] b
            = {2047, 3, 12, 48, 384, 1536, 5, 50, 1952, 4083, 459};


        GJE elim = new GJE(testBAD, b2);

    }


}