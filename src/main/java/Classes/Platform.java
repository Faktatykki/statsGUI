package Classes;

public class Platform implements Comparable<Platform> {

    private String name;
    private String type;
    private int quantity;
    private double revenue;

    public Platform(String name, String type) {
        this.name = name;
        this.type = type;
        this.quantity = 0;
        this.revenue = 0;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public double getRevenue() {
        return this.revenue;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    @Override
    public String toString() {
        return "Platform: " + this.name + "\n\n" +
                "Type: " + this.type + "\n\n" +
                "Total quantity: " + this.quantity + "\n\n" +
                "Total revenue (EUR): " + this.revenue + "\n\n";
    }

    @Override
    public boolean equals(Object object) {
        if(!(object instanceof Platform)) {
            return false;
        }

        Platform comparable = (Platform) object;

        if(!comparable.getName().equals(this.name)) {
            return false;
        }
        if(!comparable.getType().equals(this.type)) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        int hash = 1;

        hash = 31 * hash + this.name.hashCode();
        hash = 31 * hash + this.quantity;
        hash = 31 * hash + (int) this.revenue;

        return hash;
    }

    @Override
    public int compareTo(Platform plat) {
        return this.name.compareToIgnoreCase(plat.getName());
    }
}
