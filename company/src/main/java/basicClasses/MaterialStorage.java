package basicClasses;

import java.util.ArrayList;

public class MaterialStorage implements iStorage{

    private ArrayList<Stone> stones;
    private ArrayList<Paint> paints;

    public MaterialStorage() {
        stones = new ArrayList<>();
        paints = new ArrayList<>();
    }

    public int getAmountOfPaint(String color) {
        int count = 0;
        for (int a = 0; a < this.paints.size(); a++) {
            try {
                if (this.paints.get(a).getColor().equals(color)) {
                    count++;
                }
            } catch (java.lang.NullPointerException e) {
                return count;
            }

        }
        return count;
    }

    public int getAmountOfStones(Double size) {
        int count = 0;
        for (int a = 0; a < this.stones.size(); a++) {

            try {
                if (this.stones.get(a).getSize() == size) {
                    count++;
                }
            } catch (java.lang.NullPointerException e) {
                return count;
            }
        }
        return count;
    }

    @Override
    public boolean add(Good good) {

        if(good instanceof  Stone){
           return stones.add((Stone) good);
        }
        else if (good instanceof Paint){
           return paints.add((Paint) good);
        }
        else
            return false;

    }

    @Override
    public boolean remove(Good good) {
        if(good instanceof  Stone){
            return stones.remove((Stone) good);
        }
        else if (good instanceof Paint){
            return paints.remove((Paint) good);
        }
        else
            return false;
    }
}
