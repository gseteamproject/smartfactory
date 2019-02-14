package basicClasses;

import java.util.ArrayList;
import java.util.List;

/* This Class contains only finished Products and cannot contain Material
   It is used by Selling.
 */

public class ProductStorage implements iStorage {

	private List<Product> products;

	public ProductStorage() {
		products = new ArrayList<>();
	}

	public List<Product> getProducts() {
		return products;
	}

	// Gives back the amount of Products of a specific Type
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

	// TODO Catch adding and removing other types of Goods (e.g. Paint)
	@Override
	public boolean add(Good good) {
		return products.add((Product) good);
	}

	@Override
	public boolean addAll(List<Good> list) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Good good) {
		return false;
	}

	@Override
	public boolean remove(Product product) {
		for (Product productExists : products) {
			if (productExists.equals(product)) {
				return products.remove(productExists);
			}
		}
		return false;
	}

	@Override
	public boolean removeAll(List<Good> list) {
		// TODO Auto-generated method stub
		return false;
	}

	public void clear() {
		products.clear();
	}
}
