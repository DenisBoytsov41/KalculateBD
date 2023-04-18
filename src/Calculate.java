import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculate {
    private int base;
    private String expression;
    private HashMap operationMap;
    private double savedResult;
    private Memory memory;

    public Calculate(int base, String expression, Memory memory) {
        this.base = base;
        this.expression = expression.replaceAll("\\s", "");
        operationMap = new HashMap<>();
        this.memory = memory;
        addAllOperations();
    }
    public double calculator() throws InvalidExpressionException {
        Stack<Double> values = new Stack<>();
        Stack<String> operators = new Stack<>();
        Matcher matcher = Pattern.compile("-?[0-9A-F" + (char)('A'+(base-11)) + "]+(\\.[0-9A-F" + (char)('A'+(base-11)) + "]+)?|\\(|\\)|[+\\-*/&|^<>%]{1,2}|&&|\\|\\||sqrt|pow").matcher(expression.replaceAll("\\s+", ""));
        while (matcher.find()) {
            String token = matcher.group();
            if (token.matches("-?[0-9A-F" + (char)('A'+(base-11)) + "]+(\\.[0-9A-F" + (char)('A'+(base-11)) + "]+)?")) {
                values.push((double)Integer.parseInt(token, base));
                if ((double)Integer.parseInt(token, base)<0)
                    operators.push("+");
            } else if (token.matches("[+\\-*/&|^<>%]{1,2}|&&|\\|\\||sqrt|pow"))
            {
                while (!operators.empty() && hasPrecedence(token, operators.peek()))
                {
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
            if (!operators.peek().equals("sqrt") && !(values.peek()<0&&values.size()<=1)){
                double result = applyOperator(operators.pop(), values.pop(), values.pop());
                savedResult = result;
                values.push(result);
            }
            else {
                double result = applyOperator(operators.pop(), 0, values.pop());
                savedResult = result;
                values.push(result);
            }
        }

        if (values.size() != 1 || !operators.empty()) {
            throw new InvalidExpressionException("Недопустимое выражение: " + expression);
        }
        double result = values.pop();
        savedResult = result;
        return result;
    }
    private double applyOperator(String operator, double operand2, double operand1) throws InvalidExpressionException {
        Operation operation = (Operation) operationMap.get(operator);
        if (operation == null) {
            throw new InvalidExpressionException("Недопустимый оператор: " + operator);
        }
        double result = operation.performOperation(operand1, operand2);
        savedResult = result;
        return operation.getBitwiseResult();
    }

    private boolean hasPrecedence(String operator1, String operator2) {
        if (operator2.equals("(") || operator2.equals(")")) {
            return false;
        }
        if ((operator1.equals("*") || operator1.equals("/")) && (operator2.equals("+")|| operator2.equals("-") || operator2.equals("sqrt")||  operator2.equals("pow"))) {
            return false;
        }
        if ((operator1.equals("&") || operator1.equals("|") || operator1.equals("^")) && (operator2.matches("[+\\-*/<>]{1,2}") || operator2.equals("sqrt") || operator2.equals("pow"))) {
            return false;
        }
        if ((operator1.equals("sqrt") || operator1.equals("pow")) && (operator2.matches("[+\\-*/<>^&|]{1,2}"))) {
            return false;
        }
        if ((operator1.equals("+") || operator1.equals("-")) && (operator2.equals("pow"))) {
            return false;
        }
        return true;
    }
    private void addAllOperations()
    {
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
        operationMap.put("pow", new ExponentiationOperation());
        operationMap.put("sqrt", new RootExtraction());
        operationMap.put("%", new ModuloOperation());
    }
}