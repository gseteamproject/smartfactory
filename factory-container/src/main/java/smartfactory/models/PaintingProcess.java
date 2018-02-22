package smartfactory.models;

import smartfactory.services.Services;

public class PaintingProcess extends Process {

	public PaintingProcess() {
		super();
		operations.add(new ProcessOperation(Services.transport));
		operations.add(new ProcessOperation(Services.painting_worker));
	}
}
