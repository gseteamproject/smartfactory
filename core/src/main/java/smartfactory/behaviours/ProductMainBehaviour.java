package smartfactory.behaviours;

import jade.core.Agent;
import jade.core.behaviours.FSMBehaviour;
import smartfactory.models.Product;

public class ProductMainBehaviour extends FSMBehaviour {

	private Product product;

	public ProductMainBehaviour(Agent agent, Product product) {
		super(agent);
		this.product = product;
	}

	private static final long serialVersionUID = -7091209844136813253L;
}
