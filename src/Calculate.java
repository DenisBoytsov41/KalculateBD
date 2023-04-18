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
    }

    /*public int calculator() {
        char operator = '+';
        int operand1 = 0;
        int operand2 = 0;
        int i = 0;
        int n = 0;
        int result = 0;
        String op = "";
        expression +=" ";

        while (i < expression.length()) {
            char c = expression.charAt(i);

            int digit = Character.digit(c, base);

            if (digit != -1) {
                operand1 = operand1 * base + digit;

            }
            else
            {
                if (c!=' ') {
                    operator = c;
                    op +=operator;
                }
                n += 1;
                if (n==2) {
                    Operation operation = (Operation) operationMap.get(op);
                    operation.performOperation(operand1, operand2);
                    result = operation.getBitwiseResult();
                }
                operand2 = operand1;
                operand1 = 0;
            }
            i++;
        }

        return result;
    }*/
    public int calculator() throws InvalidExpressionException {
        Stack<Integer> values = new Stack<>();
        Stack<String> operators = new Stack<>();
        Matcher matcher = Pattern.compile("\\d+|\\(|\\)|[+\\-*/&|^<>]{1,2}").matcher(expression.replaceAll("\\s+", ""));
        while (matcher.find()) {
            String token = matcher.group();
            if (token.matches("\\d+")) {
                values.push(Integer.parseInt(token, base));
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
    private int applyOperator(String operator, int operand2, int operand1) throws InvalidExpressionException {
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
    /*public int calculator() {
        String[] tokens = expression.split("\\s+");
        int result = Integer.parseInt(tokens[0], base);

        for (int i = 1; i < tokens.length; i += 2) {
            String operator = tokens[i];
            int operand = Integer.parseInt(tokens[i + 1], base);

            Operation operation = (Operation) operationMap.get(operator);
            operation.performOperation(result, operand);
            result = operation.getBitwiseResult();
        }

        return result;
    }*/

    public boolean calculateBoolean(String expression, int base) {
        String[] tokens = expression.split("\\s+");
        boolean result = Boolean.parseBoolean(tokens[0]);
        for (int i = 1;i <tokens.length; i += 2) {
            String operator = tokens[i];
            boolean operand = Boolean.parseBoolean(tokens[i + 1]);
            Operation operation = (Operation) operationMap.get(operator);
            operation.performOperation(result ? 1 : 0, operand ? 1 : 0);
            result = operation.getBooleanResult();
        }

        return result;
    }

}