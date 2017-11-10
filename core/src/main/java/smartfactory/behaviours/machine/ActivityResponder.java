package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.proto.AchieveREResponder;
import smartfactory.dataStores.MachineDataStore;

public class ActivityResponder extends AchieveREResponder {

	public ActivityResponder(Agent a, MachineDataStore machineDataStore) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST));

		registerHandleRequest(new Decision(machineDataStore));
		registerPrepareResultNotification(new Activity(a, machineDataStore));
	}

	private static final long serialVersionUID = -5604378107490850297L;
}
