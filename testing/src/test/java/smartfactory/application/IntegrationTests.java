package smartfactory.application;

import jade.Boot;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import test.common.Test;

public class IntegrationTests {

	public static final long TIMEOUT = 60 * 1000;

	public static void main(String[] args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "sta:test.common.testSuite.TestSuiteAgent(testers.xml)";
		Boot.main(parameters);
	}

	public static void launchDedicatedTester(Class<?> testerClass, String[] args) {
		try {
			Runtime rt = Runtime.instance();
			rt.setCloseVM(true);

			Profile profile = new ProfileImpl(null, Test.DEFAULT_PORT, null);
			profile.setParameter(Profile.GUI, "true");

			AgentContainer mc = rt.createMainContainer(profile);

			AgentController tester = mc.createNewAgent("tester", testerClass.getName(), args);
			tester.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
