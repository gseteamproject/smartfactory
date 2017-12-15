package smartfactory.behaviours.machine;

import jade.core.behaviours.SimpleBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.MachineDataStore;
import smartfactory.interactors.machine.Work;

public class WorkBehaviour extends SimpleBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Work interactor;

	public WorkBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, MachineDataStore dataStore) {
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Work(dataStore);
	}

	@Override
	public void action() {
		interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));

		// TODO : message must have interaction-id
		// ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		// msg.addReceiver(getAgent().getAID());
		// msg.setConversationId("activity-completed");
		// getAgent().send(msg);
	}

	@Override
	public boolean done() {
		return interactor.done();
	}

	private static final long serialVersionUID = -3500469822678572098L;
}
