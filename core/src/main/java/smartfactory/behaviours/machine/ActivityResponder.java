package smartfactory.behaviours.machine;

import jade.core.Agent;
import jade.domain.FIPANames;
import jade.proto.AchieveREResponder;

public class ActivityResponder extends AchieveREResponder {

	public ActivityResponder(Agent a) {
		super(a, AchieveREResponder.createMessageTemplate(FIPANames.InteractionProtocol.FIPA_REQUEST));

		registerHandleRequest(new Decision());
		registerPrepareResultNotification(new Activity(a));
	}

	private static final long serialVersionUID = -5604378107490850297L;
}
