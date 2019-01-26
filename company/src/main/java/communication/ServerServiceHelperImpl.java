package communication;

import jade.core.Agent;

public class ServerServiceHelperImpl implements ServerServiceHelper {

	private Server server;

	public ServerServiceHelperImpl(Server server) {
		this.server = server;
	}

	@Override
	public void init(Agent a) {
	}

	@Override
	public void sendMessageToClient(MessageObject msgObj) {
		server.sendMessageToClient(msgObj);
	}
}
