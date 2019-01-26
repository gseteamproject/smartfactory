package communication;

import jade.core.ServiceHelper;

public interface ServerServiceHelper extends ServiceHelper {

	public void sendMessageToClient(MessageObject msgObj);
}
