package basicClasses;

public class Product extends Good {
    private Stone stone;
	private Paint paint;

    /*
     * Products are made of 2 Materials: Paint and Stone It can be sold from
     * Selling-Agent to Sales-Market. So it has a Price.
     */

    public Product(Stone stone, Paint paint) {
        this.paint = paint;
        this.stone = stone;
    }

    public Product(double size, String color) {
        this.paint = new Paint(color);
        this.stone = new Stone(size);
    }

    // material for paint
    public Product(String color) {
        this.paint.setColor(color);
    }

    // material for raw stone
    public Product(double size) {
        this.stone.setSize(size);
    }

    public String getColor() {
        return this.paint.getColor();
    }

    public void setColor(String color) {
        this.paint.setColor(color);
    }

	public double getSize() {
        return this.stone.getSize();
    }

    public void setSize(double size) {
        this.stone.setSize(size);
    }
    
    public Stone getStone() {
		return stone;
	}

	public Paint getPaint() {
		return paint;
	}

    @Override
    public boolean equals(Good o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Product product = (Product) o;

        if (this.stone.getSize() == product.stone.getSize() && product.paint.getColor().equals(this.paint.getColor()))
            return true;

        return false;
    }
}