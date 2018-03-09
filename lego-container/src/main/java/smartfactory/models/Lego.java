package smartfactory.models;

import lejos.hardware.Brick;
import lejos.hardware.BrickFinder;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.utility.Delay;

public class Lego extends Resource {

	Brick ev3;

	RegulatedMotor motor;

	public Lego() {
		super();
		ev3 = BrickFinder.getLocal();
		motor = new EV3MediumRegulatedMotor(ev3.getPort("B"));
		motor.setSpeed(300);
	}

	@Override
	public void execute(String operationName) {
		super.execute(operationName);

		motor.startSynchronization();
		motor.forward();
		motor.endSynchronization();

		Delay.msDelay(5000);

		motor.startSynchronization();
		motor.stop(true);
		motor.endSynchronization();
	}

	@Override
	public boolean willExecute(String operationName) {
		return super.willExecute(operationName);
	}

	@Override
	public void status(String operationName) {
		super.status(operationName);
	}

	@Override
	public void terminate(String operationName) {
		super.terminate(operationName);

		motor.startSynchronization();
		motor.stop(true);
		motor.endSynchronization();
	}
}
