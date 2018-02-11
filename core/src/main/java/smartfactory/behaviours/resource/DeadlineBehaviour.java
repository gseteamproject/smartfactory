package smartfactory.behaviours.resource;

import jade.core.behaviours.WakerBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.dataStores.ResourceDataStore;
import smartfactory.interactors.resource.Deadline;
import smartfactory.models.Resource;

public class DeadlineBehaviour extends WakerBehaviour {

	ServiceProvisioningResponderBehaviour interactionBehaviour;

	Deadline interactor;

	public DeadlineBehaviour(ServiceProvisioningResponderBehaviour interactionBehaviour, ResourceDataStore dataStore) {
		super(interactionBehaviour.getAgent(), Resource.DURATION_LIMIT * 1000);
		this.interactionBehaviour = interactionBehaviour;
		this.interactor = new Deadline(dataStore);
	}

	@Override
	protected void onWake() {
		// TODO : move setResult to separate behaviour
		interactionBehaviour.setResult(interactor.execute(interactionBehaviour.getRequest()));

		// TODO : message must have interaction-id
		ACLMessage msg = new ACLMessage(ACLMessage.INFORM);
		msg.addReceiver(getAgent().getAID());
		msg.setConversationId("activity-completed");
		getAgent().send(msg);
	}

	private static final long serialVersionUID = 9050743659839198854L;
}
