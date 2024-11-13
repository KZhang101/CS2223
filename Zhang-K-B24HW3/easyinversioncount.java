import java.util.ArrayList;
import java.util.Scanner;

public class easyinversioncount {

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
        System.out.println("Inversion Count: " + inversions);
    }

    public static void bubbleSort(Integer[] uList){

        boolean swap = true;
        int swapCount = 0;
        for(int i = 0; i < uList.length; i++){
            swap = false;
            for(int k = 0; k < uList.length-1-i; k++){
                if(uList[k] > uList[k+1]){
                    Integer temp = uList[k];
                    uList[k] = uList[k+1];
                    uList[k+1] = temp;
                    swap = true;
                    swapCount++;
                }
            }
            if(!swap) break;
        }
        printList(uList);
        System.out.println("Inversion Count for bubble: " + swapCount);
    }

    public static void main(String[] args) {
        Integer[] input = getList();

        inversionCheck(input);
        bubbleSort(input);
    }
}
