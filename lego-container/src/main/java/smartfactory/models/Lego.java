package smartfactory.models;

import smartfactory.services.Services;

public class Lego extends Machine {

	public Lego() {
		super();
		addOperation(new Operation(Services.cleaning));
		addOperation(new Operation(Services.painting));
	}
}
