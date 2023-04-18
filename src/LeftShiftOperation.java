public class LeftShiftOperation implements Operation{
    private double bitwiseResult;

    public LeftShiftOperation() {
        bitwiseResult = 0;
    }

    @Override
    public double performOperation(double operand1, double operand2) {
        bitwiseResult = (int)operand1<<(int)operand2;
        return operand1;
    }

    @Override
    public double getBitwiseResult() {
        return bitwiseResult;
    }
}
