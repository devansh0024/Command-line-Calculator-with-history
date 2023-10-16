import java.util.ArrayList;
import java.util.Scanner;

public class HumanReadableCalculator {
    // Store calculation history
    private static ArrayList<String> history = new ArrayList<>();

    // Main method to run the calculator
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Keep prompting the user for input until 'q' is entered
        while (true) {
            System.out.print("Enter an expression (or 'q' to quit): ");
            String input = scanner.nextLine();

            if (input.equalsIgnoreCase("q")) {
                break;
            }

            try {
                // Evaluate the expression and display the result
                double result = evaluateExpression(input);
                System.out.println("Result: " + result);

                // Add the expression and result to history
                history.add(input + " = " + result);
            } catch (Exception e) {
                // Handle any errors that occur during evaluation
                System.out.println("Error: " + e.getMessage());
            }
        }

        // Display calculation history
        System.out.println("\nCalculation History:");
        for (String entry : history) {
            System.out.println(entry);
        }

        scanner.close(); // Close the scanner when done
    }

    // Method to evaluate a mathematical expression
    public static double evaluateExpression(String expression) throws Exception {
        char operator = findOperator(expression);
        String[] operands = expression.split("\\" + operator);

        double operand1 = Double.parseDouble(operands[0]);
        double operand2 = Double.parseDouble(operands[1]);

        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {
                    throw new Exception("Division by zero is not allowed");
                }
            default:
                throw new Exception("Invalid operator: " + operator);
        }
    }

    // Helper method to find the operator in an expression
    private static char findOperator(String expression) throws Exception {
        for (char c : expression.toCharArray()) {
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                return c;
            }
        }
        throw new Exception("No operator found in the expression");
    }
}