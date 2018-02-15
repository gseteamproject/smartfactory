package smartfactory.interactors.process;

import java.util.Arrays;
import java.util.List;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.core.AID;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import smartfactory.interactors.process.SelectAgentToPerformService;
import smartfactory.models.ProcessOperation;
import smartfactory.utility.AgentDataStore;

public class SelectAgentToPerformServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	AgentDataStore dataStore_mock;

	SelectAgentToPerformService testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(AgentDataStore.class);

		testable = new SelectAgentToPerformService(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final DFAgentDescription agentDescription = new DFAgentDescription();
		agentDescription.setName(new AID("test-agent", AID.ISGUID));
		final List<DFAgentDescription> agentsProvidingService = Arrays
				.asList(new DFAgentDescription[] { agentDescription });
		final ProcessOperation serviceProvisioning_mock = context.mock(ProcessOperation.class);
		serviceProvisioning_mock.agentsDescription = agentsProvidingService;

		context.checking(new Expectations() {
			{
				exactly(2).of(dataStore_mock).getProcessOperation();
				will(returnValue(serviceProvisioning_mock));
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		final ProcessOperation operation_mock = context.mock(ProcessOperation.class);
		final int isAgentSelected = ProcessOperation.AgentSelected;

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProcessOperation();
				will(returnValue(operation_mock));

				oneOf(operation_mock).isAgentSelected();
				will(returnValue(isAgentSelected));
			}
		});

		Assert.assertEquals(isAgentSelected, testable.next());
	}
}
