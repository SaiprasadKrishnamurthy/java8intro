package hellolambda;

/**
 * Created by sai on 14/08/2015.
 */
public class Apple {
    private final String color;
    private final int weightInGrams;


    public Apple(final String color, final int weightInGrams) {
        this.color = color;
        this.weightInGrams = weightInGrams;
    }

    public String getColor() {
        return color;
    }

    public int getWeightInGrams() {
        return weightInGrams;
    }

    @Override
    public String toString() {
        return "Apple{" +
                "color='" + color + '\'' +
                ", weightInGrams=" + weightInGrams +
                '}';
    }
}
