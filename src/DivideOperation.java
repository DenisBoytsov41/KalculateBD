class DivideOperation implements Operation {
    private int bitwiseResult;

    public DivideOperation() {
    }

    @Override
    public void performOperation(int operand1, int operand2) {
        bitwiseResult = operand1 / operand2;
    }

    @Override
    public int getBitwiseResult() {
        return bitwiseResult;
    }
}
