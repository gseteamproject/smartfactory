package smartfactory.configuration;

import org.jdom2.Element;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ProcessOperationConfigurationTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	ProcessOperationConfiguration testable;

	@Before
	public void setUp() {
		testable = new ProcessOperationConfiguration();
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void load() {
		final String name = "name";
		Element operation_mock = context.mock(Element.class, ConfigurationTag.PROCESS_OPERATION);
		Element name_mock = context.mock(Element.class, ConfigurationTag.PROCESS_OPERATION_NAME);

		context.checking(new Expectations() {
			{
				oneOf(operation_mock).getChild(ConfigurationTag.PROCESS_OPERATION_NAME);
				will(returnValue(name_mock));

				oneOf(name_mock).getTextTrim();
				will(returnValue(name));
			}
		});

		testable.load(operation_mock);
		Assert.assertEquals(name, testable.name);
	}

	@Test
	public void getName() {
		Assert.assertEquals(testable.name, testable.getName());
	}
}
