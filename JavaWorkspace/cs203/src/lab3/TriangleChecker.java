package lab3;

import java.util.Scanner;

public class TriangleChecker {

    public static void main(String[] args) {
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
}

