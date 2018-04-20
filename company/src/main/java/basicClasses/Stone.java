package basicClasses;

/* Basic Material bought from the Procurement Market

 */

public class Stone extends Good {
    private double size;

    public Stone (double size){
        this.size = size;
    }


    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }




}
