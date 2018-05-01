package basicClasses;

/* Basic Material bought from the Procurement Market

 */

public class Stone extends Good {
    private double size;

    public Stone(double size) {
        this.size = size;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public boolean equals(Good stone) {
        if (stone instanceof Stone) {
            if (this.getSize() == ((Stone) stone).getSize() && this.getPrice() == ((Stone) stone).getPrice()) {
                return true;
            }
        }
        return false;
    }
}
