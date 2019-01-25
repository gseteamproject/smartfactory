package communication;

import jade.core.ServiceHelper;

public interface WebServerHelper extends ServiceHelper {

	public void sendMessageToClient(MessageObject msgObj);
}
