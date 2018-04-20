package basicClasses;

/* Interface for the 2 Types of Storages (Products and Material)

 */

public interface iStorage {
    boolean add(Good good);
    boolean remove(Good good);
}
