package wgu.software1;

public class Part {
    private int id;
    private String name;
    private int inventory;
    private double price;

    public Part(int id, String name, int inventory, double price) {
        this.id = id;
        this.name = name;
        this.inventory = inventory;
        this.price = price;
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
}
