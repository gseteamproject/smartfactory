package basicClasses;

/*Basic Material bought from the Procurement Market

 */

public class Paint extends Good {
	private String color;

    public Paint(String color){
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }


}
