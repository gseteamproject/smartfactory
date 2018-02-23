package smartfactory.behaviours.resource;

import jade.core.behaviours.ParallelBehaviour;
import jade.core.behaviours.ThreadedBehaviourFactory;
import smartfactory.behaviours.resource.ServiceProvisioningResponderBehaviour;
import smartfactory.utility.AgentDataStore;

public class ExecutionBehaviour extends ParallelBehaviour {

	private ThreadedBehaviourFactory tbf = new ThreadedBehaviourFactory();

	private ExecutionStatusBehaviour status;
	private ExecutionDeadlineBehaviour deadline;

	public ExecutionBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour,
			AgentDataStore agentDataStore) {
		super(interactionBehaviour.getAgent(), WHEN_ALL);

		status = new ExecutionStatusBehaviour(interactionBehaviour, agentDataStore);
		deadline = new ExecutionDeadlineBehaviour(interactionBehaviour, agentDataStore);

		addSubBehaviour(tbf.wrap(new ExecutionStartBehaviour(interactionBehaviour, agentDataStore)));
		addSubBehaviour(new ExecutionStopBehaviour(this, interactionBehaviour, agentDataStore));
		addSubBehaviour(status);
		addSubBehaviour(deadline);
	}

	public void stop() {
		status.stop();
		deadline.stop();
	}

	private static final long serialVersionUID = -987827733291008766L;
}
