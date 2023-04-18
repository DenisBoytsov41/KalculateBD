public class RootExtraction implements Operation{
    private double bitwiseResult;

    public RootExtraction() {
        bitwiseResult = 0;
    }

    @Override
    public void performOperation(double operand1, double operand2) {
        bitwiseResult  = Math.pow(operand1,1/operand2);
    }

    @Override
    public double getBitwiseResult() {
        return bitwiseResult;
    }
}
