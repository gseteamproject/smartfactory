package smartfactory.models;

import smartfactory.services.Services;

public class Warehouse extends Machine {

	@Override
	public String[] getOperations() {
		return new String[] { Services.store, Services.recognition, Services.packing };
	}
}
