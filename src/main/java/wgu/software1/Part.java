package wgu.software1;

public class Part {
    private int id;
    private String name;
    private int inventory;
    private double price;

    private int min;

    private int max;

    private String machineID;

    private boolean machineType;

    public Part(int id, String name, int inventory, double price, int min, int max, String machineID, boolean machineType) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.price = price;
        this.min = min;
        this.max = max;
        this.machineID = machineID;
        this.machineType = machineType;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getInventory() {
        return inventory;
    }

    public double getPrice() {
        return price;
    }

    public int getMin() { return min;}

    public int getMax() {
        return max;
    }

    public String getMachineID() {
        return machineID;
    }

    public boolean isMachineType() {
        return machineType;
    }
}
