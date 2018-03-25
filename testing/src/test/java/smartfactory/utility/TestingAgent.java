package smartfactory.utility;

import jade.core.AID;
import jade.core.Agent;
import smartfactory.configuration.AgentConfiguration;
import test.common.TestException;
import test.common.TestUtility;

public class TestingAgent {
	private AID agentAID;

	private String agentName;

	private String agentClass;

	private Agent tester;

	public TestingAgent(Agent tester, String agentName, Class<?> agentClass) {
		this(tester, agentName, agentClass.getName());
	}

	public TestingAgent(Agent tester, String agentName, String agentClass) {
		this.tester = tester;
		this.agentName = agentName;
		this.agentClass = agentClass;
	}

	public void start() throws TestException {
		agentAID = TestUtility.createAgent(tester, agentName, agentClass,
				new AgentConfiguration().getAgentParameters());
	}

	public void stop() throws TestException {
		TestUtility.killAgent(tester, agentAID);
	}
}
