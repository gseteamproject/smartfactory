package basicClasses;

import java.util.List;

/* Interface for the 2 Types of Storages (Products and Material)

 */

public interface iStorage {
	boolean add(Good good);

	boolean addAll(List<Good> list);

	boolean remove(Good good);

	boolean remove(Product product);

	boolean removeAll(List<Good> list);
}
