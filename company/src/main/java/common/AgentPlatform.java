package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import communication.MessageObject;
import communication.WebServerHelper;
import communication.WebServerService;
import jade.core.Agent;
import jade.core.ServiceException;

public class AgentPlatform {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Agent agent;

	public AgentPlatform(Agent agent) {
		this.agent = agent;
	}

	public void sendMessageToWebClient(MessageObject msgObj) {
		try {
			((WebServerHelper) agent.getHelper(WebServerService.NAME)).sendMessageToClient(msgObj);
		} catch (ServiceException e) {
			logger.error("", e);
		}
	}
}
