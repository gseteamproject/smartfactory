package smartfactory.models;

public class Factory extends Production {

	public Factory() {
		super();
		operations.add(new Operation("block-production"));
	}
}
