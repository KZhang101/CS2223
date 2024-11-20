public class GaussJordanElim{

    public static class GJE{
        

        public GJE(float[][] m, float[] b){
            performGJE(m, b);
        };

        private float[][] copyM(float[][] m, float[][] newM){
            int len = m.length;
            for(int i = 0; i < len; i++){
                for(int k = 0; k<len; k++){
                    newM[i][k] = m[i][k];
                    
                }
            }

            return newM;
        }

        private void swapRow(float[] a, float[] b){
            for(int i = 0; i < a.length; i++){
                float temp = a[i];
                a[i] = b[i];
                b[i] = temp;
            }
        }



        private void performGJE(float[][] m, float[] b){
            int mLen = m.length;
            float[][] sysM = new float[mLen][mLen+1];
            sysM = copyM(m, sysM);

            for(int i = 0; i < mLen; i++){
                sysM[i][mLen] = b[i]; // last column
            }

            for(int i = 0; i < mLen; i++){ // for each row
                int pivotRow = i;
                for(int j = i + 1; j < mLen; j++){
                    if(sysM[j][i] > sysM[pivotRow][i]) pivotRow = j;
                }
                swapRow(sysM[i], sysM[pivotRow]);


                float pivot = sysM[i][i]; // pivot value
                for(int j = i; j < mLen + 1; j++){ // for each colum in pivot row
                    sysM[i][j] = sysM[i][j]/ pivot; // divide row by pivot (makes pivot point 1)
                }

                for(int k = i+1; k < mLen; k++){ // for next row after pivot
                    float factor = sysM[k][i];
                    for (int j = i; j < mLen + 1; j++){ // for each column starting at pivot
                        sysM[k][j] = sysM[k][j] - factor * sysM[i][j]; // substract lower row by pivot row
                    }
                }
            }
            //printM(sysM);
            sysM[mLen-1][mLen] = Math.round(sysM[mLen-1][mLen]);
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
            
        }

        private void printM(float[][] m){
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

        float[] b
            = {2047, 3, 12, 48, 684, 1536, 5, 50, 1952, 4083, 459};


        GJE elim = new GJE(m, b);

        
        
    }


}