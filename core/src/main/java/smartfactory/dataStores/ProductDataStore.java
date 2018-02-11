package smartfactory.dataStores;

import smartfactory.models.Product;

public class ProductDataStore extends BaseDataStore {

	public void setProduct(Product product) {
		put("product", product);
	}

	public Product getProduct() {
		return (Product) get("product");
	}

	private static final long serialVersionUID = 6575511248639460129L;
}
