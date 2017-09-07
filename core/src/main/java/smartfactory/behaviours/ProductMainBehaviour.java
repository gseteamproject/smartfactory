package smartfactory.behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import smartfactory.models.Product;

public class ProductMainBehaviour extends FSMBehaviour {

	public ProductMainBehaviour(Agent agent) {
		super(agent);

		registerFirstState(new DetermineRequiredService(myAgent, getDataStore()), "determineRequiredState");
	}

	public void setProduct(Product product) {
		getDataStore().put("product", product);
	}

	private static final long serialVersionUID = -7091209844136813253L;
}
