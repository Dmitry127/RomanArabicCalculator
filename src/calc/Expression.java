package calc;

import static calc.RomanArabicConverter.*;

public class Expression {
    private final int firstNumber;
    private final String operation;
    private final int secondNumber;
    private final NumbersState expressionState;

    public Expression(String expression) {
        if (isValidExpression(expression)) {
            String[] expressionMembers = expression.split("[+*/-]");
            this.expressionState = setExpressionState(expression);
            this.firstNumber = setIntValue(expressionMembers[0].trim());
            this.secondNumber = setIntValue(expressionMembers[1].trim());
            this.operation = getOperationFromExpression(expression);
        } else {
            throw new InvalidInputException("Invalid expression");
        }
    }

    private boolean isValidExpression(String expression) {
        String[] expressionMembers = expression.split("[+*/-]");
        if (expressionMembers.length != 2) {
            throw new InvalidInputException("Expression must be in Number + Number format");
        } else if (getOperationFromExpression(expression).equals("")) {
            throw new InvalidInputException("Invalid operator");
        } else {
            return true;
        }
    }

    private NumbersState setExpressionState(String expression) {
        String[] expressionMembers = expression.split("[+*/-]");
        if (isValidRoman(expressionMembers[0].trim()) && isValidRoman(expressionMembers[1].trim())) {
            return NumbersState.ROMAN;
        } else if (isValidArabic(expressionMembers[0].trim()) && isValidArabic(expressionMembers[1].trim())) {
            return NumbersState.ARABIC;
        } else {
            throw new InvalidInputException("Numbers should both be arabic or roman");
        }
    }

    private String getOperationFromExpression(String expression) {
        return expression.replaceAll("[^-+/*]", "");
    }

    private int setIntValue(String number) {
        if (expressionState == NumbersState.ROMAN) {
            return romanToArabic(number);
        } else {
            return Integer.parseInt(number);
        }
    }

    public int getFirstNumber() {
        return firstNumber;
    }

    public int getSecondNumber() {
        return secondNumber;
    }

    public String getOperation() {
        return operation;
    }

    public NumbersState getExpressionState() {
        return expressionState;
    }
}
