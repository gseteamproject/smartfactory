package smartfactory.configuration;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.utility.XMLFile;

public class ConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	PlatformConfiguration platformConfiguration_mock;
	AgentsConfiguration agentsConfiguration_mock;
	XMLFile configurationFile_mock;

	Configuration configuration;

	@Before
	public void startUp() {
		platformConfiguration_mock = context.mock(PlatformConfiguration.class);
		agentsConfiguration_mock = context.mock(AgentsConfiguration.class);
		configurationFile_mock = context.mock(XMLFile.class);

		configuration = new Configuration(platformConfiguration_mock, agentsConfiguration_mock, configurationFile_mock);
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

	@Test
	public void load() {
		Element rootElement_mock = context.mock(Element.class, "e1");
		Element platformConfigurationRootElement_mock = context.mock(Element.class, "e2");
		Element agentsConfigurationRootElement_mock = context.mock(Element.class, "e3");

		context.checking(new Expectations() {
			{
				oneOf(configurationFile_mock).getRootElement();
				will(returnValue(rootElement_mock));

				oneOf(rootElement_mock).getChild("jade-container");
				will(returnValue(platformConfigurationRootElement_mock));

				oneOf(platformConfiguration_mock).load(platformConfigurationRootElement_mock);

				oneOf(rootElement_mock).getChild("agents");
				will(returnValue(agentsConfigurationRootElement_mock));

				oneOf(agentsConfiguration_mock).load(agentsConfigurationRootElement_mock);
			}
		});

		configuration.load();
	}
}
