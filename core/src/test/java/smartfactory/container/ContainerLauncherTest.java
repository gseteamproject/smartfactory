package smartfactory.container;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.configuration.Configuration;

public class ContainerLauncherTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	Configuration configuration_mock;
	JADEPlatform jade_mock;

	ContainerLauncher containerLauncher;

	@Before
	public void setUp() {
		configuration_mock = context.mock(Configuration.class);
		jade_mock = context.mock(JADEPlatform.class);

		containerLauncher = new ContainerLauncher(jade_mock, configuration_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void start() {
		String commandLineParameters[] = new String[0];

		context.checking(new Expectations() {
			{
				oneOf(configuration_mock).load();

				oneOf(configuration_mock).getStartupParameters();
				will(returnValue(commandLineParameters));

				oneOf(jade_mock).launch(commandLineParameters);
			}
		});

		containerLauncher.start();
	}
}
