public class LogicalOROperation implements Operation{
    private double bitwiseResult;

    public LogicalOROperation() {
        bitwiseResult = 0;
    }

    @Override
    public void performOperation(double operand1, double operand2) {
        bitwiseResult = (operand1!=0) || (operand2!=0) ? 1:0;
    }

    @Override
    public double getBitwiseResult() {
        return bitwiseResult;
    }
}
