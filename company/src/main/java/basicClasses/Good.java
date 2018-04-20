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


}
