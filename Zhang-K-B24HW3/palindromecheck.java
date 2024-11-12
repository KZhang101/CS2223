import java.util.Scanner;

import javax.swing.plaf.TreeUI;


public class palindromecheck {

    public static String replaceString(String ori, int index, char replace){
        return ori.substring(0, index) + replace + ori.substring(index+1);
    }

    public static void palindromeCheck(){
        Scanner scanner = new Scanner(System.in);
        while(true){
            System.out.print("Enter String: ");
            String original = scanner.nextLine();
            String input = original.toLowerCase().replace(" ", "").replaceAll("\\p{Punct}","");;
            String newString = input; 
            int length = input.length();
            for(int i = 0; i < length; i++){
                char temp = input.charAt(length - i - 1);
                newString = replaceString(newString, i, temp);
            }
            boolean isPalindrome = newString.equals(input);
            if(isPalindrome){
                System.out.println(original + " is a palindrome.");
            }
            else{
                System.out.println(original + " is not a palindrome.");
            }
        }
    }

    public static void main(String[] args) {
        palindromeCheck();
    }
}
