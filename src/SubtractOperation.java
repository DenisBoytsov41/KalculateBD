class SubtractOperation implements Operation {
    private double bitwiseResult;

    public SubtractOperation() {
        bitwiseResult = 0;
    }

    @Override
    public void performOperation(double operand1, double operand2) {
        bitwiseResult = operand1 - operand2;
    }

    @Override
    public double getBitwiseResult() {
        return bitwiseResult;
    }
}