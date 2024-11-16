package lab3;

public class ConsonantCounter {

    public static int consonantCount(String s) {
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
    
    public static void main(String[] args) {
        
        System.out.println(consonantCount("abra cadabra")); 
        System.out.println(consonantCount("how many consonants?")); 
        System.out.println(consonantCount("This is Lab#3!"));
    }
}