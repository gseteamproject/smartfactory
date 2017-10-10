package smartfactory.models;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import smartfactory.services.Services;

public class BlockTest {

	Block block;

	@Before
	public void setUp() {
		block = new Block();
	}

	@Test
	public void moveToNextState() {
		Assert.assertEquals(BlockState.initial, block.state);
		block.moveToNextState();

		Assert.assertEquals(BlockState.stored, block.state);
		block.moveToNextState();

		Assert.assertEquals(BlockState.dirty, block.state);
		block.moveToNextState();

		Assert.assertEquals(BlockState.clean, block.state);
		block.moveToNextState();

		Assert.assertEquals(BlockState.painted, block.state);
		block.moveToNextState();

		Assert.assertEquals(BlockState.packed, block.state);
		block.moveToNextState();

		Assert.assertEquals(BlockState.packed, block.state);
	}

	@Test
	public void getRequiredServiceName_initial() {
		block.state = BlockState.initial;
		Assert.assertEquals(Services.store, block.getRequiredServiceName());
	}

	@Test
	public void getRequiredServiceName_stored() {
		block.state = BlockState.stored;
		Assert.assertEquals(Services.recognition, block.getRequiredServiceName());
	}

	@Test
	public void getRequiredServiceName_dirty() {
		block.state = BlockState.dirty;
		Assert.assertEquals(Services.cleaning, block.getRequiredServiceName());
	}

	@Test
	public void getRequiredServiceName_clean() {
		block.state = BlockState.clean;
		Assert.assertEquals(null, block.getRequiredServiceName());
	}

	@Test
	public void isInTheLastState_initial() {
		block.state = BlockState.initial;
		Assert.assertEquals(Product.IsNotInTheLastState, block.isInTheLastState());
	}

	@Test
	public void isInTheLastState_stored() {
		block.state = BlockState.stored;
		Assert.assertEquals(Product.IsNotInTheLastState, block.isInTheLastState());
	}

	@Test
	public void isInTheLastState_dirty() {
		block.state = BlockState.dirty;
		Assert.assertEquals(Product.IsNotInTheLastState, block.isInTheLastState());
	}

	@Test
	public void isInTheLastState_clean() {
		block.state = BlockState.clean;
		Assert.assertEquals(Product.IsInTheLastState, block.isInTheLastState());
	}
}
