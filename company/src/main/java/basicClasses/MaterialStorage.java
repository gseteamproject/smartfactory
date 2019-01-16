package basicClasses;

import java.util.ArrayList;
import java.util.List;

public class MaterialStorage implements iStorage {

	// private List<Stone> stones;
	// private List<Paint> paints;
	private List<Good> materials;

	public MaterialStorage() {
		// stones = new ArrayList<>();
		// paints = new ArrayList<>();
		materials = new ArrayList<>();
	}

	public List<Stone> getStones() {
		List<Stone> stones = new ArrayList<>();
		for (Good good : materials) {
			if (good instanceof Stone) {
				stones.add((Stone) good);
			}
		}
		return stones;
	}

	public List<Paint> getPaints() {
		List<Paint> paints = new ArrayList<>();
		for (Good good : materials) {
			if (good instanceof Paint) {
				paints.add((Paint) good);
			}
		}
		return paints;
	}

	public int getAmountOfPaint(String color) {
		int count = 0;
		// for (int a = 0; a < this.paints.size(); a++) {
		// try {
		// if (this.paints.get(a).getColor().equals(color)) {
		// count++;
		// }
		// } catch (java.lang.NullPointerException e) {
		// return count;
		// }
		//
		// }
		for (Good good : materials) {
			if (good instanceof Paint) {
				try {
					if (((Paint) good).getColor().equals(color)) {
						count++;
					}
				} catch (java.lang.NullPointerException e) {
					return count;
				}
			}
		}
		return count;
	}

	public int getAmountOfStones(Double size) {
		int count = 0;
		// for (int a = 0; a < this.stones.size(); a++) {
		//
		// try {
		// if (this.stones.get(a).getSize() == size) {
		// count++;
		// }
		// } catch (java.lang.NullPointerException e) {
		// return count;
		// }
		// }
		for (Good good : materials) {
			if (good instanceof Stone) {
				try {
					if (((Stone) good).getSize() == size) {
						count++;
					}
				} catch (java.lang.NullPointerException e) {
					return count;
				}
			}
		}
		return count;
	}

	@Override
	public boolean add(Good good) {
		// if (good instanceof Stone) {
		// return stones.add((Stone) good);
		// } else if (good instanceof Paint) {
		// return paints.add((Paint) good);
		// } else
		// return false;
		if (good instanceof Stone) {
			return materials.add((Stone) good);
		} else if (good instanceof Paint) {
			return materials.add((Paint) good);
		} else
			return false;
	}

	@Override
	public boolean addAll(List<Good> list) {
		// if (good instanceof Stone) {
		// return stones.add((Stone) good);
		// } else if (good instanceof Paint) {
		// return paints.add((Paint) good);
		// } else
		// return false;
		return materials.addAll(list);
	}

	@Override
	public boolean remove(Good good) {
		// if (good instanceof Stone) {
		// for (Stone stone : stones) {
		// if (stone.equals((Stone) good)) {
		// return stones.remove(stone);
		// }
		// }
		// } else if (good instanceof Paint) {
		// for (Paint paint : paints) {
		// if (paint.equals((Paint) good)) {
		// return paints.remove(paint);
		// }
		// }
		// } else
		// return false;
		// return false;

		for (Good goodExists : materials) {
			if (goodExists.equals(good)) {
				return materials.remove(goodExists);
			}
		}
		return false;
	}

	@Override
	public boolean remove(Product product) {
		List<Good> goods = new ArrayList<>();
		goods.add(product.getPaint());
		goods.add(product.getStone());
		return removeAll(goods);
	}

	@Override
	public boolean removeAll(List<Good> list) {
		int count = 0;
		for (Good good : list) {
			if (this.remove(good)) {
				count++;
			}
		}
		return count == list.size() ? true : false;
	}
}
