// ADD YOUR HEADER HERE

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Assignment9 {

    public static void main(String[] args) throws IOException {
        char choice = ' ';
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        //String test = "Thee Hitch-hiker's Guuuuide to the Gallaxxy";
        //System.out.println(removeDupl(test, test.length() - 2));
        while (choice != '5') {
            printMenu();

            try {
                choice = reader.readLine().trim().charAt(0);
            } catch (IOException e) {
                System.out.print("err");
            }
            switch (choice) {
                case '1': {
                    int[] integers = parseInts(reader);
                    System.out.println(
                            "The largest number in the array is: " + largestNum(integers, integers.length - 1));
                    break;
                }
                case '2': {
                    // Calculate the product of all prime numbers in an array of integers
                    int[] integers = parseInts(reader);
                    System.out.println("The product of all prime numbers in the array is: "
                            + productOfPrimes(integers, integers.length - 1));
                    break;
                }
                case '3': {
                    // Find the element with the largest sum of digits in an array of integers
                    int[] integers = parseInts(reader);
                    System.out.println("The largest sum of digits in the array is: " + largestDig(integers, integers.length - 1));
                    break;
                }
                case '4': {
                    // Remove adjacent duplicate characters in a String
                    System.out.println("Please enter String:");
                    String in = reader.readLine();
                    System.out.println("String after adjacent duplicate characters were removed: " + removeDupl(in, in.length() - 2));
                    break;
                }
                case '5': {
                    break;
                }
                default: {
                    System.out.println("Please choose a number between 1 and 5.");
                    break;
                }
            }
        }

    }

    // Utility method for printing the menu
    public static void printMenu() {
        System.out.print("\nWhat would you like to do?\n\n");
        System.out.print("1: Find the largest number in an array of integers\n");
        System.out.print("2: Calculate the product of all prime numbers in an array of integers\n");
        System.out.print("3: Find the element with the largest sum of digits in an array of integers\n");
        System.out.print("4: Remove adjacent duplicate characters in a String\n");
        System.out.print("5: Quit\n\n");
    }

    // utility method for parsing integers from standard input
    public static int[] parseInts(BufferedReader reader) {
        String line = "";
        ArrayList<Integer> container = new ArrayList<>();
        try {
            System.out.print("Please enter integers:\n");
            line = reader.readLine();
            int num = Integer.parseInt(line);

            while (num > 0) {
                container.add(num);
                line = reader.readLine();
                num = Integer.parseInt(line);
            }

        } catch (IOException ex) {
            System.out.println("IO Exception");
        }

        int[] result = new int[container.size()];
        for (int i = 0; i < container.size(); i++) {
            result[i] = container.get(i);
        }
        return result;
    }

    static int largestNum(int[] nums, int idx) {
        if (idx > 0) {
            return Math.max(nums[idx], largestNum(nums, idx - 1));
        }
        return nums[0];
    }

    static int productOfPrimes(int[] nums, int idx) {
        if (idx >= 0) {
            boolean prime = isPrime(nums[idx]);
            i = 2;
            if (prime) {
                return productOfPrimes(nums, idx - 1) * nums[idx];
            }
            return productOfPrimes(nums, idx - 1);
        }
        return 1;
    }

    static int i = 2;
    public static boolean isPrime(int n) {
        //0 and one are not prime
        if (n == 0 || n == 1) {
            return false;
        }

        if (n == i) {
            return true;
        }

        if (n % i == 0) {
            return false;
        }
        i++;
        return isPrime(n);
    }

    static int largestDig(int[] nums, int idx) {
        if (idx > 0) {
            return Math.max(sumOfDig(nums[idx]), largestDig(nums, idx - 1));
        }
        return sumOfDig(nums[0]);
    }

    static int sumOfDig(int num){
        if (num > 9) return sumOfDig(num / 10) + (num % 10);
        return num;
    }

    static String removeDupl(String str, int idx){
        if (idx >= 0){
            if(str.charAt(idx) == str.charAt(idx + 1)){ //if char and next char are same, recurse on string without that char else just change idx
                //System.out.println(str.substring(0, idx) + str.substring(idx+1, str.length()));
                return removeDupl(str.substring(0, idx) + str.substring(idx+1, str.length()), idx - 1);
            }
            return removeDupl(str, idx - 1);
        }
        return str;
    }
}
