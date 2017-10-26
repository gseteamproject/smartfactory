package smartfactory.behaviours;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jade.core.Agent;
import jade.core.behaviours.OneShotBehaviour;
import jade.wrapper.StaleProxyException;

public class LaunchAgentBehaviour extends OneShotBehaviour {

	private String launchingAgentName;
	private String launchingAgentClass;

	public LaunchAgentBehaviour(Agent agent, String launchingAgentName, String launchingAgentClass) {
		super(agent);
		this.launchingAgentClass = launchingAgentClass;
		this.launchingAgentName = launchingAgentName;
	}

	@Override
	public void action() {
		try {
			myAgent.getContainerController().createNewAgent(launchingAgentName, launchingAgentClass, null).start();
		} catch (StaleProxyException e) {
			logger.error("", e);
		}
	}

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final long serialVersionUID = -3738136280724031758L;
}
