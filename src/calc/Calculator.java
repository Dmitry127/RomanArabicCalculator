package calc;

import java.util.Scanner;
import static calc.RomanArabicConverter.*;

public class Calculator {

    void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.trim().equalsIgnoreCase("exit")) {
                System.out.println("Bye.");
                break;
            }
            Expression exp = new Expression(input);
            if (!isInRange(exp.getFirstNumber()) || !isInRange(exp.getSecondNumber())) {
                throw new InvalidInputException("Only numbers from 1 to 10");
            }
            try {
                switch (exp.getExpressionState()) {
                    case ARABIC -> System.out.println(calculate(exp));
                    case ROMAN -> System.out.println(arabicToRoman(calculate(exp)));
                }
            } catch (ArithmeticException e) {
                System.out.println("Division by 0!");
            }
        }
    }

    private int calculate(Expression exp) {
        return switch (exp.getOperation()) {
            case "+" -> exp.getFirstNumber() + exp.getSecondNumber();
            case "-" -> exp.getFirstNumber() - exp.getSecondNumber();
            case "*" -> exp.getFirstNumber() * exp.getSecondNumber();
            case "/" -> exp.getFirstNumber() / exp.getSecondNumber();
            default -> throw new IllegalArgumentException();
        };
    }

    private boolean isInRange(int number) {
        return number > 0 && number <= 10;
    }
}
