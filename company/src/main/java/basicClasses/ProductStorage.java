package basicClasses;

import java.util.ArrayList;

/* This Class contains only finished Products and cannot contain Material
   It is used by Selling.
 */

public class ProductStorage implements iStorage {

	private ArrayList<Product> products;


	public ProductStorage() {
		products = new ArrayList<>();
	}



//Gives back the amount of Products of a specific Type
	public int getAmountOfProduct(Product product) {
		int count = 0;
		for (int a = 0; a < this.products.size(); a++) {
			try {
				if (this.products.get(a).equals(product)) {
					count++;
				}
			} catch (java.lang.NullPointerException e) {
				return count;
			}

		}
		return count;
	}


	//TODO Catch adding and removing other types of Goods (e.g. Paint)
	public boolean add(Good good) {
		return products.add((Product) good);
	}

	@Override
	public boolean remove(Good good) {
		return products.remove((Product) good);
	}
}
