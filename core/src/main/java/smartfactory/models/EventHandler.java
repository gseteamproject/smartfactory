package smartfactory.models;

import jade.lang.acl.ACLMessage;

public interface EventHandler {

	public void callback(ACLMessage message);
}
