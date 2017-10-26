package smartfactory.models;

import smartfactory.services.Services;

public class Lego extends Machine {

	@Override
	public String[] getOperations() {
		return new String[] { Services.cleaning, Services.painting };
	}
}
