package smartfactory.agents;

import jade.core.Profile;
import jade.core.ProfileImpl;
import test.common.TestGroup;
import test.common.TesterAgent;

import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import test.common.Test;

public class BaseAgentTester extends TesterAgent {

	private static final long serialVersionUID = 2890828714489116537L;

	@Override
	protected TestGroup getTestGroup() {
		return new TestGroup("smartfactory/agents/BaseAgentTestsList.xml");
	}
	
	public static void main(String[] args) {
		try {
			// Get a hold on JADE runtime
			Runtime rt = Runtime.instance();

			// Exit the JVM when there are no more containers around
			rt.setCloseVM(true);

			Profile pMain = new ProfileImpl(null, Test.DEFAULT_PORT, null);

			AgentContainer mc = rt.createMainContainer(pMain);

			AgentController rma = mc.createNewAgent("rma", "jade.tools.rma.rma", new Object[0]);
			rma.start();

			AgentController tester = mc.createNewAgent("tester1", "smartfactory.agents.BaseAgentTester", args);
			tester.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
