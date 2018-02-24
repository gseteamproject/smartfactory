package smartfactory.models;

import smartfactory.services.Services;

public class Warehouse extends Resource {

	public Warehouse() {
		super();
		addOperation(new ResourceOperation(Services.store));
		addOperation(new ResourceOperation(Services.recognition));
		addOperation(new ResourceOperation(Services.packing));
	}
}
