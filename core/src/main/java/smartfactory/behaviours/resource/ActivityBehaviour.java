package smartfactory.behaviours.resource;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import smartfactory.behaviours.resource.ServiceProvisioningResponderBehaviour;
import smartfactory.utility.AgentDataStore;

public class ActivityBehaviour extends ParallelBehaviour {

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	private StatusBehaviour status;
	private DeadlineBehaviour deadline;
	private StopBehaviour result;

	public ActivityBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, AgentDataStore dataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ALL);

		status = new StatusBehaviour(interactionBehaviour, dataStore);
		deadline = new DeadlineBehaviour(interactionBehaviour, dataStore);
		result = new StopBehaviour(this, interactionBehaviour, dataStore);

		addSubBehaviour(tbf.wrap(new WorkBehaviour(interactionBehaviour, dataStore)));
		addSubBehaviour(result);
		addSubBehaviour(status);
		addSubBehaviour(deadline);
	}

	public void stop() {
		status.stop();
		deadline.stop();
	}

	private static final long serialVersionUID = -987827733291008766L;
}
