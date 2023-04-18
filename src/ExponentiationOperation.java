public class ExponentiationOperation implements Operation{
    private double bitwiseResult;

    public ExponentiationOperation() {
        bitwiseResult = 0;
    }

    @Override
    public double performOperation(double operand1, double operand2) {
        bitwiseResult  = Math.pow(operand1,operand2);
        return operand1;
    }

    @Override
    public double getBitwiseResult() {
        return bitwiseResult;
    }
}
