class MultiplyOperation implements Operation {
    private int bitwiseResult;
    private boolean booleanResult;

    public MultiplyOperation() {
        bitwiseResult = 1;
        booleanResult = true;
    }

    @Override
    public void performOperation(int operand1, int operand2) {
        bitwiseResult = operand1 * operand2;
        booleanResult = booleanResult && (operand2 != 0);
    }

    @Override
    public int getBitwiseResult() {
        return bitwiseResult;
    }

    @Override
    public boolean getBooleanResult() {
        return booleanResult;
    }
}