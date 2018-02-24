package smartfactory.agents;

import smartfactory.models.Resource;
import smartfactory.models.Warehouse;

public class WarehouseAgent extends ResourceAgent {

	@Override
	public Resource createResource() {
		return new Warehouse();
	}

	private static final long serialVersionUID = -5919274387044865830L;
}
