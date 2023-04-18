class AddOperation implements Operation {
    private int bitwiseResult;

    public AddOperation() {
        bitwiseResult = 0;
    }

    @Override
    public void performOperation(int operand1, int operand2) {
        bitwiseResult = operand1 + operand2;
    }

    @Override
    public int getBitwiseResult() {
        return bitwiseResult;
    }
}