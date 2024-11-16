package lab3;

import java.util.Scanner;

public class Lab3 {
	
	public static void TrangleChecker(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Gets Input from the user on the lengths of the 3 sides of the triangle and 
        // binds those outputs to the variables for sides 1, 2 and 3.
        System.out.println("Enter the lengths of the three sides of the triangle:");
        double side1 = scanner.nextDouble();
        double side2 = scanner.nextDouble();
        double side3 = scanner.nextDouble();

        // Checks if the sides actually form a valid triangle, and if they don't,
        // prints that it isn't valid
        if ((side1 + side2 > side3) && (side1 + side3 > side2) && (side2 + side3 > side1)) {
            // Checks the type of the verified valid triangle
            if (side1 == side2 && side2 == side3) {
            	
                System.out.println("The triangle is valid. It is an equilateral triangle.");
                
            } else if (side1 == side2 || side1 == side3 || side2 == side3) {
            	
                System.out.println("The triangle is valid. It is an isosceles triangle.");
                
            } else {
            	
                System.out.println("The triangle is valid. It is a scalene triangle.");   
            }
        } else {
        	
            System.out.println("The triangle is not valid.");
            
        }
        scanner.close();
    }
	
	public static int ConsonantCount(String s) {
        // Convert the inputed string to lower case
        s = s.toLowerCase();

        // Create count variable that is at 0
        int count = 0;

        // Loops through every character in the string and checks if the character is a consonant,
        // if it is it adds it to the count.
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c >= 'a' && c <= 'z' && "aeiouy".indexOf(c) == -1) {
                count++;
            }
        }
        return count;
    }
    
    public static void mainConsonantCount(String[] args) {
        
        System.out.println(ConsonantCount("abra cadabra")); 
        System.out.println(ConsonantCount("how many consonants?")); 
        System.out.println(ConsonantCount("This is Lab#3!"));
    }
    
    public static int FindMin(int[] arr) {
        // Create min 
        int min = arr[0];

        // Iterate through the array starting from the second element in the array
        for (int i = 1; i < arr.length; i++) {
            // Update min if the current element in the array is smaller
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        // Return the smallest number found
        return min;
    }
    
    public static void mainFindMin(String[] args) {
       
        int[] array1 = {16, 5, 3, 7, 22};
        int[] array2 = {-8, -2, 2, 7, 5};
        int[] array3 = {42, -15, 23, 12, 0};

        System.out.println(FindMin(array1));
        System.out.println(FindMin(array2)); 
        System.out.println(FindMin(array3)); 
    }
}
