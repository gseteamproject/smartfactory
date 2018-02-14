package smartfactory.models;

import smartfactory.services.Services;

public class BlockProcess extends Process {

	public BlockProcess() {
		super();
		operations.add(new ProcessOperation(Services.store));
		operations.add(new ProcessOperation(Services.recognition));
		operations.add(new ProcessOperation(Services.cleaning));
		operations.add(new ProcessOperation(Services.painting));
		operations.add(new ProcessOperation(Services.packing));
	}
}
