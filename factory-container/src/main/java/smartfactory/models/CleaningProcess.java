package smartfactory.models;

import smartfactory.services.Services;

public class CleaningProcess extends Process {

	public CleaningProcess() {
		super();
		operations.add(new ProcessOperation(Services.transport));
		operations.add(new ProcessOperation(Services.cleaning_worker));
	}
}
