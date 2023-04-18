public class RightShiftOperation implements Operation{
    private double bitwiseResult;

    public RightShiftOperation() {
        bitwiseResult = 0;
    }

    @Override
    public void performOperation(double operand1, double operand2) {
        bitwiseResult = (int)operand1>>(int)operand2;
    }

    @Override
    public double getBitwiseResult() {
        return bitwiseResult;
    }
}
