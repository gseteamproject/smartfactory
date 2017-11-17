package smartfactory.behaviours.machine;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import smartfactory.behaviours.machine.ServiceProvisioningResponderBehaviour;
import smartfactory.dataStores.MachineDataStore;

public class ActivityBehaviour extends ParallelBehaviour {

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	public ActivityBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, MachineDataStore machineDataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ANY);
		addSubBehaviour(tbf.wrap(new WorkBehaviour(interactionBehaviour, machineDataStore)));
		addSubBehaviour(new StatusBehaviour(interactionBehaviour, machineDataStore));
		addSubBehaviour(new DeadlineBehaviour(interactionBehaviour, machineDataStore));
	}

	private static final long serialVersionUID = -987827733291008766L;
}
