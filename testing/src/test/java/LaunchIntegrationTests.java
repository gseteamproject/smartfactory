import jade.Boot;

public class LaunchIntegrationTests {

	public static void main(String[] p_args) {
		String[] parameters = new String[2];
		parameters[0] = "-gui";
		parameters[1] = "tester:test.common.testSuite.TestSuiteAgent(testers.xml)";
		Boot.main(parameters);
	}
}
