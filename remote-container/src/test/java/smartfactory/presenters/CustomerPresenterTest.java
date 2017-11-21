package smartfactory.presenters;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.agents.CustomerAgent;
import smartfactory.views.CustomerView;

public class CustomerPresenterTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	CustomerView view_mock;
	CustomerAgent agent_mock;

	CustomerPresenter testable;

	@Before
	public void setUp() {
		agent_mock = context.mock(CustomerAgent.class);
		view_mock = context.mock(CustomerView.class);

		testable = new CustomerPresenter(agent_mock, view_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void show() {
		context.checking(new Expectations() {
			{
				oneOf(view_mock).setVisible(true);
			}
		});

		testable.show();
	}

	@Test
	public void hide() {
		context.checking(new Expectations() {
			{
				oneOf(view_mock).dispose();
			}
		});

		testable.hide();
	}

	@Test
	public void destroy() {
		context.checking(new Expectations() {
			{
				oneOf(agent_mock).doDelete();
			}
		});

		testable.destroy();
	}

	@Test
	public void createOrder() {
		context.checking(new Expectations() {
			{
				oneOf(agent_mock).createOrder();
			}
		});

		testable.createOrder();
	}
}
