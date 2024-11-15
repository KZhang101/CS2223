import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class fastinversioncount {
    public static int counter = 0;

    /**
     *  Ask the user to input a list of nums, separated by commas. 
     * @return
     */
    public static Integer[] getList(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("type you list as 1,2,3,4,5 where each number is seperated by a comma.");
        String input = scanner.nextLine().replace(" ", "");
        ArrayList<Integer> list = new ArrayList<>();
        while(input.indexOf(",") != -1){
            int index = input.indexOf(",");
            int num =  Integer.parseInt(input.substring(0, index));
            list.add(num);
            input = input.substring(index + 1, input.length());
        }
        list.add( Integer.parseInt(input.substring(0, input.length())));
        Integer[] arr = new Integer[list.size()];
        arr =  list.toArray(arr);
        return arr;
    }

    public static void printList(Integer[] list){
        for(int i = 0; i < list.length-1; i++){
            System.out.print(list[i] + ",");
        }
        System.out.println(list[list.length-1]);
    }

    public static void inversionCheck(Integer[] list){
        int inversions = 0;
        for(int i = 0; i < list.length; i++){
            for(int k = 0; k < list.length; k++){
                if(i < k && list[i] > list[k] && k != i){
                    inversions++;
                }
            }
        }
        System.out.println("Inversion Count from brute force checker (Not merge sort): " + inversions);
    }


    public static void sortList(Integer[] currentArray, Integer[] leftHalf, Integer[] rightHalf){
        int leftIndex = 0, rightIndex = 0, arrayIndex = 0;
        int leftLength = leftHalf.length;
        int rightLength = rightHalf.length;

        while (leftIndex < leftLength && rightIndex < rightLength) {
            if (leftHalf[leftIndex] <= rightHalf[rightIndex]) { // <= is needed because having the same value is not an inversion
                currentArray[arrayIndex] = leftHalf[leftIndex];
                leftIndex++;
            } else {
                currentArray[arrayIndex] = rightHalf[rightIndex];
                rightIndex++;
                counter += leftLength - leftIndex; //This shows the number of left items greater than the right
            }
            arrayIndex++;
        }

        while (leftIndex < leftLength) {
            currentArray[arrayIndex] = leftHalf[leftIndex];
            leftIndex++;
            arrayIndex++;
        }

        while (rightIndex < rightLength) {
            currentArray[arrayIndex] = rightHalf[rightIndex];
            rightIndex++;
            arrayIndex++;
        }

    }
   
    public static void mergeSort(Integer[] array) {
        if (array == null) {
            return;
        }
        if (array.length > 1) {
            int midPoint = array.length / 2;

            Integer[] leftHalf = new Integer[midPoint];
            for (int i = 0; i < midPoint; i++) leftHalf[i] = array[i];
            

            Integer[] rightHalf = new Integer[array.length - midPoint];
            for (int i = midPoint; i < array.length; i++) rightHalf[i - midPoint] = array[i];
            

            // keep splitting until 1 length
            mergeSort(leftHalf);
            mergeSort(rightHalf);

            // Merge the sorted halves
            sortList(array, leftHalf, rightHalf);
        }
        
    }

    public static void main(String[] args) {
        Integer[] input = getList();
        inversionCheck(input);
        mergeSort(input);
        System.out.println("Sorted list for merge sort");
        printList(input);
        System.out.println("Inversion counted from merge sort: " + counter);
    }
}
