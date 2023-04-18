public class ModuloOperation implements Operation {
    private double bitwiseResult;

    public ModuloOperation() {
        bitwiseResult = 0;
    }

    public void performOperation(double operand1, double operand2) {
        bitwiseResult = operand1 % operand2;
    }

    public double getBitwiseResult() {
        return bitwiseResult;
    }
}