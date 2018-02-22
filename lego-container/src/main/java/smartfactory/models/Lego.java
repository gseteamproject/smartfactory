package smartfactory.models;

import smartfactory.services.Services;

public class Lego extends Resource {

	public Lego() {
		super();
		addOperation(new ResourceOperation(Services.painting));
	}
}
