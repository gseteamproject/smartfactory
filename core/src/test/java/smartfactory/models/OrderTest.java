package smartfactory.models;

import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import jade.domain.FIPAAgentManagement.DFAgentDescription;

public class OrderTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ServiceProvisioning testable;

	@Before
	public void setUp() {
		testable = new ServiceProvisioning();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void removeSelectedAgentFromAgentsList() {
		final DFAgentDescription agentDescription_mock = context.mock(DFAgentDescription.class);
		final List<DFAgentDescription> agentDescriptions_mock = context.mock(List.class);

		testable.agentDescription = agentDescription_mock;
		testable.agentsDescription = agentDescriptions_mock;

		context.checking(new Expectations() {
			{
				oneOf(agentDescriptions_mock).remove(agentDescription_mock);
			}
		});

		testable.removeSelectedAgentFromAgentsList();
	}
}
