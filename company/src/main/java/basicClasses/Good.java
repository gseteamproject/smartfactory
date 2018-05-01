package basicClasses;

/*Upper Class of Product, Stone and Paint
  It is made to use Products and Materials in the interface iStorage.
  Materials (Stone and Paint) and Products have a Price
 */

public abstract class Good {
    private int price;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public boolean equals(Good good) {
        if (good instanceof Stone) {
            return ((Stone) this).equals(good);
        } else if (good instanceof Paint) {
            return ((Paint) this).equals(good);
        } else if (good instanceof Product) {
            return ((Product) this).equals(good);
        }
        return false;
    }
}
