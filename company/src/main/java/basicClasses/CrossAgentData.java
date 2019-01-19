package basicClasses;

import java.util.ArrayList;
import java.util.List;

public class CrossAgentData {

	// TODO : temporary class for further refactoring

	// queue for procurement orders
	public static List<Order> procurementQueue = new ArrayList<Order>();

	// creating storage for raw materials
	public static MaterialStorage materialStorage = new MaterialStorage();

	// creating list of orders
	public static List<Order> orderQueue = new ArrayList<Order>();

	// creating storage for products
	public static ProductStorage warehouse = new ProductStorage();
}
