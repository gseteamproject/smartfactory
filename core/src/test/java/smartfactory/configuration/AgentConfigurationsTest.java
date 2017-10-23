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

public class AgentConfigurationsTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentConfigurations agentConfigurations;

	@Before
	public void setUp() {
		agentConfigurations = new AgentConfigurations();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void getStartupParameters() {
		final String agent1StartupParameters = "listener:tutorial_2.ListenerAgent";
		final String agent2StartupParameters = "sniffer:jade.tools.sniffer.Sniffer(l*)";
		AgentConfiguration agent1Configuration_mock = context.mock(AgentConfiguration.class, "agent1");
		AgentConfiguration agent2Configuration_mock = context.mock(AgentConfiguration.class, "agent2");

		agentConfigurations.agentConfigurations.add(agent1Configuration_mock);
		agentConfigurations.agentConfigurations.add(agent2Configuration_mock);

		context.checking(new Expectations() {
			{
				oneOf(agent1Configuration_mock).getStartupParameters();
				will(returnValue(agent1StartupParameters));

				oneOf(agent2Configuration_mock).getStartupParameters();
				will(returnValue(agent2StartupParameters));
			}
		});

		List<String> parameters = agentConfigurations.getStartupParameters();
		Assert.assertEquals(agent1StartupParameters + ";" + agent2StartupParameters + ";", parameters.get(0));
	}

	@Test
	public void load() {
		Element root_mock = context.mock(Element.class, "root");
		Element element1_mock = context.mock(Element.class, "element1");
		Element element2_mock = context.mock(Element.class, "element2");
		List<Element> elements = new ArrayList<Element>();
		elements.add(element1_mock);
		elements.add(element2_mock);

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChildren(AgentConfiguration.TAG_AGENT);
				will(returnValue(elements));

				oneOf(element1_mock).getChild(AgentConfiguration.TAG_NAME);

				oneOf(element1_mock).getChild(AgentConfiguration.TAG_CLASS_NAME);

				oneOf(element1_mock).getChild(AgentConfiguration.TAG_PARAMETERS);

				oneOf(element2_mock).getChild(AgentConfiguration.TAG_NAME);

				oneOf(element2_mock).getChild(AgentConfiguration.TAG_CLASS_NAME);

				oneOf(element2_mock).getChild(AgentConfiguration.TAG_PARAMETERS);
			}
		});

		agentConfigurations.load(root_mock);
		Assert.assertEquals(2, agentConfigurations.agentConfigurations.size());
	}
}
