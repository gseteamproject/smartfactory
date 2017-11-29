package smartfactory.dataStores;

import smartfactory.models.ServiceProvisioning;
import smartfactory.models.Product;

public class ProductDataStore extends BaseDataStore {

	public void setProduct(Product product) {
		put("product", product);
	}

	public Product getProduct() {
		return (Product) get("product");
	}

	public void setServiceProvisioning(ServiceProvisioning serviceProvisioning) {
		put("service-provisioning", serviceProvisioning);
	}

	public ServiceProvisioning getServiceProvisioning() {
		return (ServiceProvisioning) get("service-provisioning");
	}

	private static final long serialVersionUID = 6575511248639460129L;
}
