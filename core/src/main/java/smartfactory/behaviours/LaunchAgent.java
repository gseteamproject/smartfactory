package smartfactory.behaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.StaleProxyException;

public class LaunchAgent extends OneShotBehaviour {

	private String agentName;
	private String agentClass;

	public LaunchAgent(Agent owner, String agentName, String agentClass) {
		super(owner);
		this.agentClass = agentClass;
		this.agentName = agentName;
	}

	@Override
	public void action() {
		try {
			myAgent.getContainerController().createNewAgent(agentName, agentClass, null).start();
		} catch (StaleProxyException e) {
			logger.error("", e);
		}
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -3738136280724031758L;
}
