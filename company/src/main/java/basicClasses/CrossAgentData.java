package basicClasses;

import java.util.ArrayList;
import java.util.List;

public class CrossAgentData {

	// queue for procurement orders
	public static List<Order> procurementQueue = new ArrayList<Order>();

	// creating storage for raw materials
	public static MaterialStorage materialStorage = new MaterialStorage();
}
