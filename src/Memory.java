import java.util.HashMap;

public class Memory {
    private HashMap<String, Double> memoryMap;

    public Memory() {
        this.memoryMap = new HashMap<String, Double>();
    }

    public double getValue() {
        return memoryMap.getOrDefault("value", 0.0);
    }

    public void setValue(double value) {
        memoryMap.put("value", value);
    }

    public void addToMemory(double value) {
        double currentValue = memoryMap.getOrDefault("value", 0.0);
        memoryMap.put("value", currentValue + value);
    }

    public void subtractFromMemory(double value) {
        double currentValue = memoryMap.getOrDefault("value", 0.0);
        memoryMap.put("value", currentValue - value);
    }

    public void clearMemory() {
        memoryMap.clear();
    }
}
