package smartfactory.behaviours.machine;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import smartfactory.behaviours.machine.ServiceProvisioningResponderBehaviour;
import smartfactory.dataStores.MachineDataStore;

public class ActivityBehaviour extends ParallelBehaviour {

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	private StatusBehaviour status;
	private DeadlineBehaviour deadline;

	public ActivityBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, MachineDataStore dataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ALL);

		status = new StatusBehaviour(interactionBehaviour, dataStore);
		deadline = new DeadlineBehaviour(interactionBehaviour, dataStore);

		addSubBehaviour(tbf.wrap(new WorkBehaviour(interactionBehaviour, dataStore)));
		addSubBehaviour(new StopBehaviour(this));
		addSubBehaviour(status);
		addSubBehaviour(deadline);
	}

	public void stop() {
		status.stop();
		deadline.stop();
	}

	private static final long serialVersionUID = -987827733291008766L;
}
