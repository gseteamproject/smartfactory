package smartfactory.models;

import smartfactory.services.Services;

public class OrderProcess extends Process {

	public OrderProcess() {
		super();
		operations.add(new ProcessOperation(Services.block_production));
	}
}
