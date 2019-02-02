package basicClasses;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

/* Orders are Product-Orders (Red Small Stones or Big Blue Stones and so on) from Customers which
   are safed in this Class and are used by different Agents
 */

public class Order implements Serializable {
	private static final long serialVersionUID = 8348788729049247785L;

	public int id;
	public List<OrderPart> orderList = new ArrayList<OrderPart>();
	public long deadline;
	public int price;
	public String agent;

	public Order() {
	}

	public List<Product> getProducts() {
		List<Product> list = new ArrayList<Product>();
		for (OrderPart part : orderList) {
			list.add(part.getProduct());
		}
		return list;
	}

	public boolean addGood(Good good, int amount) {
		if (amount > 0) {
			OrderPart part = new OrderPart(good);
			part.setAmount(amount);
			boolean count = false;
			for (OrderPart partInList : orderList) {
				if (partInList.getGood().equals(good)) {
					partInList.setAmount(amount + 1);
					count = true;
				}
			}
			if (!count) {
				return orderList.add(part);
			}
		}
		return false;
	}

	public int getID() {
		return id;
	}

	public int getAmountByProduct(Product product) {
		int amount = 0;
		for (OrderPart part : orderList) {
			if (part.getProduct().equals(product)) {
				amount = part.getAmount();
			}
		}
		return amount;
	}

	public String getTextOfOrder() {
		String text = "";
		for (OrderPart part : orderList) {

			Integer value = part.getAmount();

			if (part.getProduct() != null) {

				Product key = part.getProduct();
				text += value.toString() + " " + key.getColor() + " stone of size " + key.getSize() + "; ";
			}
			if (part.getPaint() != null) {

				Paint key = part.getPaint();
				text += value.toString() + " portions of " + key.getColor() + " color; ";
			}
			if (part.getStone() != null) {

				Stone key = part.getStone();
				text += value.toString() + " stone of size " + key.getSize() + "; ";
			}

		}
		return text;
	}

	public int searchInList(List<Order> list) {
		int index = -1;
		for (int idx = 0; idx < list.size(); idx++) {
			Order order = list.get(idx);
			if (order.id == this.id) {
				index = idx;
			}
		}
		return index;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;

		Order order = (Order) o;

		if (this.id != order.id || order.orderList.size() != this.orderList.size()) {
			return false;
		}

		outter_cycle: for (OrderPart orderPart : order.orderList) {
			for (OrderPart thisOrderPart : this.orderList) {
				if (orderPart.equals(thisOrderPart)) {
					continue outter_cycle;
				}
			}
			return false;
		}

		return true;
	}

	private static final Gson gsonBuilder = new Gson();

	public String toJson() {
		return gsonBuilder.toJson(this);
	}

	public static Order fromJson(String jsonString) {
		return gsonBuilder.fromJson(jsonString, Order.class);
	}
}
