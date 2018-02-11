package smartfactory.interactors.process;

import java.util.ArrayList;
import java.util.List;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import smartfactory.dataStores.ProcessDataStore;
import smartfactory.interactors.process.FindAgentsProvidingService;
import smartfactory.models.ProcessOperation;
import smartfactory.platform.AgentPlatform;

public class FindAgentsProvidingServiceTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessDataStore dataStore_mock;

	FindAgentsProvidingService testable;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(ProcessDataStore.class);

		testable = new FindAgentsProvidingService(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void execute() {
		final String requiredServiceName = "serviceName";
		final ProcessOperation operation_mock = context.mock(ProcessOperation.class);
		operation_mock.serviceName = requiredServiceName;
		final List<DFAgentDescription> agentDescriptions = new ArrayList<DFAgentDescription>();
		agentDescriptions.add(new DFAgentDescription());
		final AgentPlatform jadePlatform_mock = context.mock(AgentPlatform.class);

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProcessOperation();
				will(returnValue(operation_mock));

				oneOf(dataStore_mock).getAgentPlatform();
				will(returnValue(jadePlatform_mock));

				oneOf(jadePlatform_mock).search(with(new TypeSafeMatcher<DFAgentDescription>() {

					@Override
					public void describeTo(Description description) {
						description.appendText("serviceDescription template doesn't match");
					}

					@Override
					protected boolean matchesSafely(DFAgentDescription item) {
						ServiceDescription serviceDesription = (ServiceDescription) item.getAllServices().next();
						if (serviceDesription.getName().equalsIgnoreCase(requiredServiceName)) {
							return true;
						}
						return false;
					}
				}));
				will(returnValue(agentDescriptions));

				oneOf(dataStore_mock).getProcessOperation();
				will(returnValue(operation_mock));

				/*
				 * // TODO add matcher for Order .setAgentsProvidingService(with(new
				 * TypeSafeMatcher<List<DFAgentDescription>>() {
				 * 
				 * @Override public void describeTo(Description description) {
				 * description.appendText("agentDescription doesn't match"); }
				 * 
				 * @Override protected boolean matchesSafely(List<DFAgentDescription> item) { if
				 * (item.get(0) != agentDescriptions[0]) { return false; } return true; } }));
				 */
			}
		});

		testable.execute();
	}

	@Test
	public void next() {
		final ProcessOperation operation_mock = context.mock(ProcessOperation.class);
		final int isAgentsFound = ProcessOperation.AgentsFound;

		context.checking(new Expectations() {
			{
				oneOf(dataStore_mock).getProcessOperation();
				will(returnValue(operation_mock));

				oneOf(operation_mock).isAgentsFound();
				will(returnValue(isAgentsFound));
			}
		});

		Assert.assertEquals(isAgentsFound, testable.next());
	}
}
