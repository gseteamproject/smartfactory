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

public class ProcessConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessConfiguration testable;

	@Before
	public void setUp() {
		testable = new ProcessConfiguration();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void load() {
		Element root_mock = context.mock(Element.class, "root");

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.PROCESS_OPERATIONS);
				will(returnValue(null));
			}
		});

		testable.load(root_mock);
	}

	@Test
	public void loadProcessOperationConfigurations() {
		Element root_mock = context.mock(Element.class, "root");
		Element operations_mock = context.mock(Element.class, ConfigurationTag.PROCESS_OPERATIONS);
		Element operation_mock = context.mock(Element.class, ConfigurationTag.PROCESS_OPERATION);
		List<Element> operations = new ArrayList<Element>();
		operations.add(operation_mock);

		context.checking(new Expectations() {
			{
				oneOf(root_mock).getChild(ConfigurationTag.PROCESS_OPERATIONS);
				will(returnValue(operations_mock));

				oneOf(operations_mock).getChildren(ConfigurationTag.PROCESS_OPERATION);
				will(returnValue(operations));

				oneOf(operation_mock).getChild(ConfigurationTag.PROCESS_OPERATION_NAME);
			}
		});

		testable.loadProcessOperationConfigurations(root_mock);
		Assert.assertEquals(operations.size(), testable.operationConfigurations.size());
	}
}
