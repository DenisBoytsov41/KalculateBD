public class RootExtraction implements Operation{
    private double bitwiseResult;

    public RootExtraction() {
        bitwiseResult = 0;
    }

    @Override
    public double performOperation(double operand1, double operand2) {
        bitwiseResult  = Math.sqrt(operand1);
        return operand1;
    }

    @Override
    public double getBitwiseResult() {
        return bitwiseResult;
    }
}
