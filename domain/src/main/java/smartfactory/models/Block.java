package smartfactory.models;

import smartfactory.services.Services;

public class Block extends Product {

	public BlockState state;

	public Block() {
		state = BlockState.initial;
	}

	@Override
	public String getRequiredServiceName() {
		switch (state) {
		case initial:
			return Services.store;
		case stored:
			return Services.recognition;
		default:
			return super.getRequiredServiceName();
		}
	}

	@Override
	public void moveToNextState() {
		switch (state) {
		case initial:
			state = BlockState.stored;
			break;
		case stored:
			state = BlockState.dirty;
			break;
		case dirty:
			state = BlockState.clean;
			break;
		case clean:
			state = BlockState.painted;
			break;
		case painted:
			state = BlockState.packed;
			break;
		default:
			break;
		}
	}

	@Override
	public int isInTheLastState() {
		if (state == BlockState.initial) {
			return IsNotInTheLastState;
		}
		if (state == BlockState.stored) {
			return IsNotInTheLastState;
		}
		return IsInTheLastState;
	}
}
