package Homework;


public class Main {

    public static void main(String[] args) {
        // Example 1: Bad practice - unused variable (removed)

        // Example 2: Performance issue - redundant method call in loop
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 1000; i++) {
            output.append(i).append("\n");
        }
        System.out.print(output.toString());

        // Example 3: PMD - Simple rule violation (using an instance variable without initialization)
        User user = new User();
        user.setName("New name");
        System.out.println(user.getName());
    }

    // Example of a method with a bug (FindBugs will flag this)
    public static void divide(int a, int b) {
        if (b != 0) {
            System.out.println(a / b);
        } else {
            System.out.println("Division by zero is not allowed.");
        }
    }
}
