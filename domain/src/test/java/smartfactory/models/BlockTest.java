package smartfactory.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.services.Services;

public class BlockTest {

	BlockProcess testable;

	@Before
	public void setUp() {
		testable = new BlockProcess();
	}

	@Test
	public void moveToNextState() {
		Assert.assertEquals(BlockState.initial, testable.state);
		testable.moveToNextState();

		Assert.assertEquals(BlockState.stored, testable.state);
		testable.moveToNextState();

		Assert.assertEquals(BlockState.dirty, testable.state);
		testable.moveToNextState();

		Assert.assertEquals(BlockState.clean, testable.state);
		testable.moveToNextState();

		Assert.assertEquals(BlockState.painted, testable.state);
		testable.moveToNextState();

		Assert.assertEquals(BlockState.packed, testable.state);
		testable.moveToNextState();

		Assert.assertEquals(BlockState.packed, testable.state);
	}

	@Test
	public void getRequiredServiceName_initial() {
		testable.state = BlockState.initial;
		Assert.assertEquals(Services.store, testable.getRequiredServiceName());
	}

	@Test
	public void getRequiredServiceName_stored() {
		testable.state = BlockState.stored;
		Assert.assertEquals(Services.recognition, testable.getRequiredServiceName());
	}

	@Test
	public void getRequiredServiceName_dirty() {
		testable.state = BlockState.dirty;
		Assert.assertEquals(Services.cleaning, testable.getRequiredServiceName());
	}

	@Test
	public void getRequiredServiceName_clean() {
		testable.state = BlockState.clean;
		Assert.assertEquals(Services.painting, testable.getRequiredServiceName());
	}

	@Test
	public void getRequiredServiceName_painted() {
		testable.state = BlockState.painted;
		Assert.assertEquals(Services.packing, testable.getRequiredServiceName());
	}

	@Test
	public void getRequiredServiceName_packed() {
		testable.state = BlockState.packed;
		Assert.assertEquals(null, testable.getRequiredServiceName());
	}

	@Test
	public void isInTheLastState_initial() {
		testable.state = BlockState.initial;
		Assert.assertEquals(Process.IsNotInTheLastState, testable.isInTheLastState());
	}

	@Test
	public void isInTheLastState_stored() {
		testable.state = BlockState.stored;
		Assert.assertEquals(Process.IsNotInTheLastState, testable.isInTheLastState());
	}

	@Test
	public void isInTheLastState_dirty() {
		testable.state = BlockState.dirty;
		Assert.assertEquals(Process.IsNotInTheLastState, testable.isInTheLastState());
	}

	@Test
	public void isInTheLastState_clean() {
		testable.state = BlockState.clean;
		Assert.assertEquals(Process.IsNotInTheLastState, testable.isInTheLastState());
	}

	@Test
	public void isInTheLastState_painted() {
		testable.state = BlockState.painted;
		Assert.assertEquals(Process.IsNotInTheLastState, testable.isInTheLastState());
	}

	@Test
	public void isInTheLastState_packed() {
		testable.state = BlockState.packed;
		Assert.assertEquals(Process.IsInTheLastState, testable.isInTheLastState());
	}
}
