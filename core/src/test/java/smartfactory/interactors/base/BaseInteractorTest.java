package smartfactory.interactors.base;

import org.jmock.Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import smartfactory.dataStores.BaseDataStore;

public class BaseInteractorTest {

	private final Mockery context = new Mockery() {
		{
			this.setImposteriser(ClassImposteriser.INSTANCE);
		}
	};

	BaseInteractor testable;

	BaseDataStore dataStore_mock;

	@Before
	public void setUp() {
		dataStore_mock = context.mock(BaseDataStore.class);

		testable = new BaseInteractor(dataStore_mock);
	}

	@After
	public void tearDown() {
		context.assertIsSatisfied();
	}

	@Test
	public void constructor() {
	}
}
