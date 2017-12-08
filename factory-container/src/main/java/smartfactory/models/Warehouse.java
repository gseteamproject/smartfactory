package smartfactory.models;

import smartfactory.services.Services;

public class Warehouse extends Machine {

	public Warehouse() {
		super();
		addOperation(new Operation(Services.store));
		addOperation(new Operation(Services.recognition));
		addOperation(new Operation(Services.packing));
	}
}
