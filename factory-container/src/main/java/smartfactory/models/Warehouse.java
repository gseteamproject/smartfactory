package smartfactory.models;

import smartfactory.services.Services;

public class Warehouse extends Machine {

	public Warehouse() {
		super();
		operations.add(new Operation(Services.store));
		operations.add(new Operation(Services.recognition));
		operations.add(new Operation(Services.packing));
	}
}
