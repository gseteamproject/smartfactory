package smartfactory.models;

public class Factory extends Production {
	
	@Override
	public String[] getOperations() {
		return new String[] { "block-production" };
	}
}
