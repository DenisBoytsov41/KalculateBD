class MultiplyOperation implements Operation {
    private double bitwiseResult;

    public MultiplyOperation() {
        bitwiseResult = 1;
    }

    @Override
    public double performOperation(double operand1, double operand2) {
        bitwiseResult = operand1 * operand2;
        return operand1;
    }

    @Override
    public double getBitwiseResult() {
        return bitwiseResult;
    }
}
