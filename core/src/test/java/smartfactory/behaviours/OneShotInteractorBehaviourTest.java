package smartfactory.behaviours;

import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.interactors.OneShotInteractor;

public class OneShotInteractorBehaviourTest {
	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	OneShotInteractor interactor_mock;

	OneShotInteractorBehaviour interactorBehaviour;

	@Before
	public void setUp() {
		interactor_mock = context.mock(OneShotInteractor.class);

		interactorBehaviour = new OneShotInteractorBehaviour(interactor_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void action() {
		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).execute();
			}
		});

		interactorBehaviour.action();
	}

	@Test
	public void onEnd() {
		final int transition = 1;

		context.checking(new Expectations() {
			{
				oneOf(interactor_mock).next();
				will(returnValue(transition));
			}
		});

		Assert.assertEquals(transition, interactorBehaviour.onEnd());
	}
}
