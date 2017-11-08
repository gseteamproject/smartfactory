package smartfactory.behaviours.machine;

import jade.core.behaviours.OneShotBehaviour;
import jade.lang.acl.ACLMessage;
import smartfactory.models.Machine;

public class Decision extends OneShotBehaviour {

	@Override
	public void action() {
		ActivityResponder parent = (ActivityResponder) getParent();
		ACLMessage request = (ACLMessage) getDataStore().get(parent.REQUEST_KEY);

		Machine machine = (Machine) getDataStore().get("machine");
		ACLMessage response = request.createReply();
		if (machine.willExecute("operation-xxx")) {
			response.setPerformative(ACLMessage.AGREE);

		} else {
			response.setPerformative(ACLMessage.REFUSE);
		}

		getDataStore().put(parent.RESPONSE_KEY, response);
	}

	private static final long serialVersionUID = -7477532730880615646L;
}
