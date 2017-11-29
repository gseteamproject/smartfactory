package smartfactory.models;

import smartfactory.services.Services;

public class Lego extends Machine {

	public Lego() {
		super();
		operations.add(new Operation(Services.cleaning));
		operations.add(new Operation(Services.painting));
	}
}
