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
	ContainerConfiguration containerConfiguration_mock;
	AgentConfigurations agentConfigurations_mock;
	XMLFile configurationFile_mock;

	Configuration configuration;

	@Before
	public void startUp() {
		platformConfiguration_mock = context.mock(PlatformConfiguration.class);
		containerConfiguration_mock = context.mock(ContainerConfiguration.class);
		agentConfigurations_mock = context.mock(AgentConfigurations.class);
		configurationFile_mock = context.mock(XMLFile.class);

		configuration = new Configuration(platformConfiguration_mock, containerConfiguration_mock,
				agentConfigurations_mock, configurationFile_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getStartupParameters() {
		final List<String> platformParameters = new ArrayList<String>();
		platformParameters.add("-host:localhost");
		final List<String> containerParameters = new ArrayList<String>();
		platformParameters.add("-gui");
		final List<String> agentsParameters = new ArrayList<String>();
		agentsParameters.add("sniffer:jade.tools.sniffer.Sniffer(l*);");

		context.checking(new Expectations() {
			{
				oneOf(platformConfiguration_mock).getStartupParameters();
				will(returnValue(platformParameters));

				oneOf(containerConfiguration_mock).getStartupParameters();
				will(returnValue(containerParameters));

				oneOf(agentConfigurations_mock).getStartupParameters();
				will(returnValue(agentsParameters));
			}
		});

		String[] parameters = configuration.getStartupParameters();
		Assert.assertEquals(3, parameters.length);
	}

	@Test
	public void load() {
		Element rootElement_mock = context.mock(Element.class);
		Element platformConfigurationElement_mock = context.mock(Element.class, "e2");
		Element containerConfigurationElement_mock = context.mock(Element.class, "e3");
		Element agentConfigurationsElement_mock = context.mock(Element.class, "e4");

		context.checking(new Expectations() {
			{
				oneOf(configurationFile_mock).getRootElement();
				will(returnValue(rootElement_mock));

				oneOf(rootElement_mock).getChild("platform");
				will(returnValue(platformConfigurationElement_mock));

				oneOf(platformConfiguration_mock).load(platformConfigurationElement_mock);

				oneOf(rootElement_mock).getChild("container");
				will(returnValue(containerConfigurationElement_mock));

				oneOf(containerConfiguration_mock).load(containerConfigurationElement_mock);

				oneOf(rootElement_mock).getChild("agents");
				will(returnValue(agentConfigurationsElement_mock));

				oneOf(agentConfigurations_mock).load(agentConfigurationsElement_mock);
			}
		});

		configuration.load();
	}
}
