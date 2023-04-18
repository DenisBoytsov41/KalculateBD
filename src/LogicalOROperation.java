public class LogicalOROperation implements Operation{
    private int bitwiseResult;
    private boolean booleanResult;

    public LogicalOROperation() {
        bitwiseResult = 0;
        booleanResult = true;
    }

    @Override
    public void performOperation(int operand1, int operand2) {
        bitwiseResult = (operand1!=0) || (operand2!=0) ? 1:0;
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
