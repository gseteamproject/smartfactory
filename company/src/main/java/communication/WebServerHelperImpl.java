package communication;

import jade.core.Agent;

public class WebServerHelperImpl implements WebServerHelper {

	@Override
	public void init(Agent a) {
		// TODO Auto-generated method stub
	}

	@Override
	public void sendMessageToClient(MessageObject msgObj) {
		// TODO remove singleton
		Communication.server.sendMessageToClient(msgObj);
	}
}
