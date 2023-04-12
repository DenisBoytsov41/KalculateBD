import java.util.Scanner;
public class Calculate {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Выберите систему исчисления (2, 8, 10, 16): ");
        int base = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Введите выражение: ");
        String expression = scanner.nextLine().replaceAll("\\s", "");

        int result = 0;
        char operator = '+';
        int operand = 0;
        int i = 0;

        while (i < expression.length()) {
            char c = expression.charAt(i);

            int digit = Character.digit(c, base);

            if (digit != -1) {
                operand = operand * base + digit;
            }

            if (digit == -1 || i == expression.length() - 1) {
                switch (operator) {
                    case '+':
                        result += operand;
                        break;
                    case '-':
                        result -= operand;
                        break;
                    case '*':
                        result= operand;
                        break;
                    case '/':
                        result /= operand;

                }

                operand = 0;
                operator = c;
            }

            i++;
        }

        System.out.println("Результат: " + Integer.toString(result, base));
    }
}

