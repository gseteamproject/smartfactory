package smartfactory.models;

import smartfactory.services.Services;

public class PaintingStation extends Resource {

	public PaintingStation() {
		super();
		addOperation(new ResourceOperation(Services.painting));
	}
}
