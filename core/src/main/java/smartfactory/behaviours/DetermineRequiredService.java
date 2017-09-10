package smartfactory.behaviours;

import jade.core.behaviours.Behaviour;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class DetermineRequiredService extends ProductSubBehaviour {

	final static public int RequiredServiceIsDetermined = 0;

	public DetermineRequiredService(Behaviour behaviour) {
		super(behaviour);
	}

	@Override
	public void action() {
		ServiceDescription requiredService = new ServiceDescription();
		requiredService.setName(dataStoreAccessor.getProduct().getRequiredServiceName());
		dataStoreAccessor.setRequiredService(requiredService);
	}

	private static final long serialVersionUID = -2422289734697182917L;
}
