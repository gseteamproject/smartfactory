package smartfactory.behaviours.machine;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import smartfactory.behaviours.machine.ServiceProvisioningResponderBehaviour;
import smartfactory.dataStores.MachineDataStore;

public class ActivityBehaviour extends ParallelBehaviour {

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	public ActivityBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, MachineDataStore dataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ANY);
		addSubBehaviour(tbf.wrap(new WorkBehaviour(interactionBehaviour, dataStore)));
		addSubBehaviour(new StatusBehaviour(interactionBehaviour, dataStore));
		addSubBehaviour(new DeadlineBehaviour(interactionBehaviour, dataStore));
	}

	private static final long serialVersionUID = -987827733291008766L;
}
