public class InvalidExpressionException extends Throwable {
    public InvalidExpressionException(String s) {
        System.out.println("Ну так же нельзя:  " + s);
    }
}
