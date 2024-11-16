package lab3;

public class FindMin {

    public static int findMin(int[] arr) {
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
    
    public static void main(String[] args) {
       
        int[] array1 = {16, 5, 3, 7, 22};
        int[] array2 = {-8, -2, 2, 7, 5};
        int[] array3 = {42, -15, 23, 12, 0};

        System.out.println(findMin(array1));
        System.out.println(findMin(array2)); 
        System.out.println(findMin(array3)); 
    }
}
