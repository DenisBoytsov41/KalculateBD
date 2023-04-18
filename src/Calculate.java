import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
    private int base;
    private String expression;
    private HashMap operationMap;

    public Calculate(int base, String expression) {
        this.base = base;
        this.expression = expression.replaceAll("\\s", "");
        operationMap = new HashMap<>();
        operationMap.put("+", new AddOperation());
        operationMap.put("-", new SubtractOperation());
        operationMap.put("*", new MultiplyOperation());
        operationMap.put("/", new DivideOperation());
        operationMap.put("&", new BitwiseAndOperation());
        operationMap.put("|", new BitwiseOROperation());
        operationMap.put("^", new BitwiseXOROperation());
        operationMap.put("&&", new LogicalANDOperation());
        operationMap.put("||", new LogicalOROperation());
        operationMap.put("<<", new LeftShiftOperation());
        operationMap.put(">>", new RightShiftOperation());
        operationMap.put("^^", new RightShiftOperation());
        operationMap.put("sqrt", new RightShiftOperation());
    }
    public double calculator() throws InvalidExpressionException {
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();
        Matcher matcher = Pattern.compile("-?[0-9A-Z" + (char)('A'+(base-11)) + "]+(\\.[0-9A-Z" + (char)('A'+(base-11)) + "]+)?|\\(|\\)|[+\\-*/&|^<>]{1,2}|&&|\\|\\|").matcher(expression.replaceAll("\\s+", ""));
        while (matcher.find()) {
            String token = matcher.group();
            if (token.matches("-?[0-9A-Z" + (char)('A'+(base-11)) + "]+")) {
                if (token.matches("-?\\d+")) {
                    values.push((double)Integer.parseInt(token, base));
                } else {
                    values.push(Double.parseDouble(token));
                }
            } else if (token.matches("[+\\-*/&|^<>]{1,2}")) {
                while (!operators.empty() && hasPrecedence(token, operators.peek())) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.push(token);
            } else if (token.equals("(")) {
                operators.push(token);
            } else if (token.equals(")")) {
                while (!operators.peek().equals("(")) {
                    values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
                }
                operators.pop();
            } else {
                throw new InvalidExpressionException("Недопустимый токен: " + token);
            }
        }

        while (!operators.empty()) {
            values.push(applyOperator(operators.pop(), values.pop(), values.pop()));
        }

        if (values.size() != 1 || !operators.empty()) {
            throw new InvalidExpressionException("Недопустимое выражение: " + expression);
        }

        return values.pop();
    }
    private double applyOperator(String operator, double operand2, double operand1) throws InvalidExpressionException {
        Operation operation = (Operation) operationMap.get(operator);
        if (operation == null) {
            throw new InvalidExpressionException("Недопустимый оператор: " + operator);
        }
        operation.performOperation(operand1, operand2);
        return operation.getBitwiseResult();
    }

    private boolean hasPrecedence(String operator1, String operator2) {
        if (operator2.equals("(") || operator2.equals(")")) {
            return false;
        }
        if ((operator1.equals("*") || operator1.equals("/")) && (operator2.equals("+") || operator2.equals("-"))) {
            return false;
        }
        if ((operator1.equals("&") || operator1.equals("|") || operator1.equals("^")) && (operator2.matches("[+\\-*/<>]{1,2}"))) {
            return false;
        }
        return true;
    }
}