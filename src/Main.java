import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    private static  int base = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        System.out.println("Доступные операции:\n\"+\",\t" + "\"-\",\t" + "\"*\",\t" + "\"/\",\t" + "\"&\",\t" + "\"|\",\n" +
                "\"^\",\t" + "\"&&\",\t" + "\"||\",\t" + "\"<<\",\t" + "\">>\",\t" + "\"pow\",\t" + "\"sqrt\"");
        String expression = scanner.nextLine();
        int baseChoice = 0;

        System.out.println("Введите систему счисления (от 2 до 16):");
        try {
            baseChoice = scanner.nextInt();
        }
        catch (InputMismatchException e)
        {
        }
        scanner.nextLine();

        if (baseChoice>=2 && baseChoice<=16)
            base = baseChoice;
        else
        {
            System.out.println("Неправильна система счисления");
        }

        try {
            Calculate calc = new Calculate(base,expression);
            double result = calc.calculator();
            int vrem = (int)result;
            if (base!=10)
                System.out.printf("Результат в ведённой СИС: %s (%d)%n", Integer.toString(vrem, base), base);
            System.out.printf("Результат в 10-ой СИС: %s (%d)%n", result, 10);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InvalidExpressionException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}