import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int base = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression;
        double result;
        Memory memory = new Memory();

        while (true) {
            System.out.print("Введите выражение: ");
            expression = scanner.nextLine();

            if (expression.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы.");
                break;
            }

            if (expression.startsWith("m")) {
                String[] command = expression.split("\\s");
                if (command.length != 2) {
                    System.out.println("Неверный формат команды.");
                    continue;
                }

                if (command[0].equalsIgnoreCase("m+")) {
                    memory.addToMemory(Double.parseDouble(command[1]));
                    System.out.println("Результат сохранен в память.");
                } else if (command[0].equalsIgnoreCase("m-")) {
                    memory.subtractFromMemory(Double.parseDouble(command[1]));
                    System.out.println("Результат сохранен в память.");
                } else if (command[0].equalsIgnoreCase("mc")) {
                    memory.clearMemory();
                    System.out.println("Память очищена.");
                } else if (command[0].equalsIgnoreCase("mr")) {
                    expression = Double.toString(memory.getValue());
                }
            }

            try {
                Calculate calculator = new Calculate(base, expression);
                result = calculator.calculator();
                System.out.println("Результат: " + result);
                memory.setValue(result);
            } catch (InvalidExpressionException e) {
                System.out.println("Ошибка: " + e.getMessage());
            }

            System.out.print("Хотите продолжить выполнение программы? (Y/N): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("y")) {
                System.out.println("Выход из программы.");
                break;
            }
        }
    }
}