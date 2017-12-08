package smartfactory.models;

public class Factory extends Production {

	public Factory() {
		super();
		addOperation(new Operation("block-production"));
	}
}
