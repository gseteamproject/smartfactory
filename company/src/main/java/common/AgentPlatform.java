package common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import communication.MessageObject;
import communication.ServerServiceHelper;
import communication.ServerService;
import jade.core.Agent;
import jade.core.ServiceException;
import jade.domain.DFService;
import jade.domain.FIPAException;
import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class AgentPlatform {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private Agent agent;

	public AgentPlatform(Agent agent) {
		this.agent = agent;
	}

	public void sendMessageToWebClient(MessageObject msgObj) {
		try {
			((ServerServiceHelper) agent.getHelper(ServerService.NAME)).sendMessageToClient(msgObj);
		} catch (ServiceException e) {
			logger.error("", e);
		}
	}

	public void registerAgentServices(DFAgentDescription dfd) {
		try {
			DFService.register(agent, dfd);
		} catch (FIPAException exception) {
			logger.error("register failed", exception);
		}
	}

	public void deregisterAgentServices() {
		try {
			DFService.deregister(agent);
		} catch (FIPAException exception) {
			logger.error("deregister failed", exception);
		}
	}
}
