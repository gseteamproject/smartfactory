package smartfactory.models;

import smartfactory.services.Services;

public class CleaningStation extends Resource {

	public CleaningStation() {
		super();
		addOperation(new ResourceOperation(Services.cleaning));
	}
}
