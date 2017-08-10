package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	PlatformConfiguration platformConfiguration_mock;
	AgentsConfiguration agentsConfiguration_mock;

	Configuration configuration;

	@Before
	public void startUp() {
		platformConfiguration_mock = context.mock(PlatformConfiguration.class);
		agentsConfiguration_mock = context.mock(AgentsConfiguration.class);

		configuration = new Configuration(platformConfiguration_mock, agentsConfiguration_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getStartupParameters() {
		List<String> platformParameters = new ArrayList<String>();
		platformParameters.add("-gui");
		List<String> agentsParameters = new ArrayList<String>();
		agentsParameters.add("sniffer:jade.tools.sniffer.Sniffer(l*);");

		context.checking(new Expectations() {
			{
				oneOf(platformConfiguration_mock).getStartupParameters();
				will(returnValue(platformParameters));

				oneOf(agentsConfiguration_mock).getStartupParameters();
				will(returnValue(agentsParameters));
			}
		});

		String[] parameters = configuration.getStartupParameters();
		Assert.assertEquals(2, parameters.length);
	}
}
