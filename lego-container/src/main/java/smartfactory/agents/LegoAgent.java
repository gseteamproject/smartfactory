package smartfactory.agents;

import smartfactory.models.Lego;
import smartfactory.models.Resource;

public class LegoAgent extends ResourceAgent {

	@Override
	public Resource createResource() {
		return new Lego();
	}

	private static final long serialVersionUID = 8580835111885945247L;
}
