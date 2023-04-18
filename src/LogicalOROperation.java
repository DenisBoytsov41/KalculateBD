public class LogicalOROperation implements Operation{
    private int bitwiseResult;

    public LogicalOROperation() {
        bitwiseResult = 0;
    }

    @Override
    public void performOperation(int operand1, int operand2) {
        bitwiseResult = (operand1!=0) || (operand2!=0) ? 1:0;
    }

    @Override
    public int getBitwiseResult() {
        return bitwiseResult;
    }
}
