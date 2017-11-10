package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.lang.acl.ACLMessage;
import jade.proto.AchieveREResponder;
import smartfactory.dataStores.MachineDataStore;

public class ActivityResponder extends AchieveREResponder {

	public ACLMessage getRequest() {
		return (ACLMessage) getDataStore().get(REQUEST_KEY);
	}

	public void setResponse(ACLMessage response) {
		getDataStore().put(RESPONSE_KEY, response);
	}

	public void setResult(ACLMessage result) {
		getDataStore().put(RESULT_NOTIFICATION_KEY, result);
	}

	public ActivityResponder(Agent a, MachineDataStore machineDataStore) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST));

		registerHandleRequest(new Decision(this, machineDataStore));
		registerPrepareResultNotification(new Activity(this, machineDataStore));
	}

	private static final long serialVersionUID = -5604378107490850297L;
}
