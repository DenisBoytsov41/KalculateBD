import java.util.Scanner;

public class Main {

    private static  int base = 10;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите выражение:");
        String expression = scanner.nextLine();

        System.out.println("Введите систему счисления (2, 8, 10, 16):");
        int baseChoice = scanner.nextInt();
        scanner.nextLine();

        switch (baseChoice) {
            case 2:
                base = 2;
                break;
            case 8:
                base = 8;
                break;
            case 10:
                base = 10;
                break;
            case 16:
                base = 16;
                break;
            default:
                System.out.println("Неправильна система счисления");
        }

        try {
            Calculate calc = new Calculate(base,expression);
            int result = calc.calculator();
            System.out.printf("Результат: %s (%d)%n", Integer.toString(result, base), base);
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        } catch (InvalidExpressionException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}