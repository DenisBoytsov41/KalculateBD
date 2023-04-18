import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static int base = 10;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String expression;
        Memory memory = new Memory();
        ChoosingANumberSystem(scanner);

        while (true) {
            System.out.print("Введите выражение: ");
            System.out.println("Доступные операции:\n\"+\",\t" + "\"-\",\t" + "\"*\",\t" + "\"/\",\t" + "\"&\",\t" +
                    "" + "\"|\",\n" +"\"^\",\t" + "\"&&\",\t" + "\"||\",\t" + "\"<<\",\t" + "\">>\",\t" + "\"pow\",\n" +
                    "\"sqrt\"\t"+ "\"m+\"\t"+ "\"m-\"\t"+ "\"mr\"\t"+ "\"mc\"\t");
            expression = scanner.nextLine();

            if (expression.equalsIgnoreCase("exit")) {
                System.out.println("Выход из программы.");
                break;
            }
            if (expression.startsWith("m")) {
                String[] command = expression.split("\\s");
                if (command.length != 2 && !command[0].equalsIgnoreCase("mr")) {
                    System.out.println("Неверный формат команды.");
                    continue;
                }
                expression = "";
                for (int i =1;i<command.length;i++)
                {
                    expression +=command[i];
                }

                if (command[0].equalsIgnoreCase("m+")) {
                    memory.addToMemory(mainOperation(expression,memory));
                    System.out.println("Результат сохранен в память.");
                } else if (command[0].equalsIgnoreCase("m-")) {
                    memory.subtractFromMemory(mainOperation(expression,memory));
                    System.out.println("Результат сохранен в память.");
                } else if (command[0].equalsIgnoreCase("mc")) {
                    memory.clearMemory();
                    System.out.println("Память очищена.");
                } else if (command[0].equalsIgnoreCase("mr")) {
                    expression = Double.toString(memory.getValue());
                    System.out.println(expression);
                }
            }
            else
                mainOperation(expression,memory);

            System.out.print("Хотите продолжить выполнение программы? (Y/N): ");
            String answer = scanner.nextLine();
            if (!answer.equalsIgnoreCase("y")) {
                System.out.print("Хотите поменять СИС? (Y/N): ");
                String answer2 = scanner.nextLine();
                if (!answer2.equalsIgnoreCase("y")) {
                    System.out.println("Выход из программы.");
                    break;
                }
                else ChoosingANumberSystem(scanner);
            }
        }
    }
    private static double mainOperation(String expression, Memory memory)
    {
        double result = 0;
        try {
            Calculate calc = new Calculate(base,expression,memory);
            result = calc.calculator();
            int vrem = (int)result;
            if (base!=10)
                System.out.printf("Результат в ведённой СИС: %s (%d)%n", Integer.toString(vrem, base), base);
            System.out.printf("Результат в 10-ой СИС: %s (%d)%n", result, 10);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InvalidExpressionException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
        return result;
    }
    private static void ChoosingANumberSystem(Scanner scanner)
    {
        int baseChoice = 0;
        System.out.println("Введите систему счисления (от 2 до 16):");
        try {
            baseChoice = scanner.nextInt();
        }
        catch (InputMismatchException e)
        {
            System.out.println(e.getMessage());
        }
        scanner.nextLine();

        if (baseChoice>=2 && baseChoice<=16)
            base = baseChoice;
        else
        {
            System.out.println("Неправильна система счисления");
        }
    }
}