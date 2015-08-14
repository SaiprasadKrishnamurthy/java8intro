package hellolambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by sai on 14/08/2015.
 */

interface ApplePickingStrategy {
    boolean shouldPick(Apple apple);
}

public class ApplePicker {

    private static List<Apple> applesByColor(final List<Apple> allApples, final String color) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : allApples) {
            if (apple.getColor().equalsIgnoreCase(color)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    private static List<Apple> applesByMinWeight(final List<Apple> allApples, final int weight) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : allApples) {
            if (apple.getWeightInGrams() >= weight) {
                apples.add(apple);
            }
        }
        return apples;
    }

    private static List<Apple> applesByStrategy(final List<Apple> allApples, final ApplePickingStrategy strategy) {
        List<Apple> apples = new ArrayList<>();
        for (Apple apple : allApples) {
            if (strategy.shouldPick(apple)) {
                apples.add(apple);
            }
        }
        return apples;
    }

    private static List<Apple> applesMatchingPredicate(final List<Apple> allApples, final Predicate<Apple> applePredicate) {
        return allApples.stream()
                .filter(applePredicate)
                .collect(Collectors.toList());
    }


    // Apples by color.
    public static void requirement1_apples_by_color() {
        System.out.println("\n ------------------- Apples by color ----------------------- ");
        // I want all green apples.
        List<Apple> greenApples = applesByColor(allApples(), "green");
        System.out.println(greenApples);

        // I want all red apples.
        List<Apple> redApples = applesByColor(allApples(), "red");
        System.out.println(redApples);
    }

    // Apples by color.
    public static void requirement2_apples_by_min_weight() {
        System.out.println("\n ------------------- Apples by min weight ----------------------- ");


        // I want all apples weighing more than 20 g.
        List<Apple> applesWeighingMoreThanTwentyGrams = applesByMinWeight(allApples(), 20);
        System.out.println(applesWeighingMoreThanTwentyGrams);

        // I want all apples weighing more than 50 g.
        List<Apple> applesWeighingMoreThanFiftyGrams = applesByMinWeight(allApples(), 50);
        System.out.println(applesWeighingMoreThanFiftyGrams);
    }

    // Apples by color.
    public static void requirement1_apples_by_color_strategy() {
        System.out.println("\n ------------------- Apples by color (with strategy) ----------------------- ");
        List<Apple> apples = applesByStrategy(allApples(), new ApplePickingStrategy() {
            @Override
            public boolean shouldPick(final Apple apple) {
                return apple.getColor().equals("red");
            }
        });
        System.out.println(apples);
    }

    // Apples by color.
    public static void requirement1_apples_by_min_weight_strategy() {
        System.out.println("\n ------------------- Apples by min weight (with strategy) ----------------------- ");
        List<Apple> apples = applesByStrategy(allApples(), new ApplePickingStrategy() {
            @Override
            public boolean shouldPick(final Apple apple) {
                return apple.getWeightInGrams() >= 20;
            }
        });
        System.out.println(apples);
    }

    public static void main(String[] args) {
        requirement1_apples_by_color();
        requirement2_apples_by_min_weight();

        // TRADITIONAL REFACTOR
        // Create a strategy
        requirement1_apples_by_color_strategy();
        requirement1_apples_by_min_weight_strategy();

        // Still verbose and not elegant.

        // Apples by color (requirement)
        List<Apple> redApples = applesMatchingPredicate(allApples(), apple -> apple.getColor().equalsIgnoreCase("red"));
        List<Apple> greenApples = applesMatchingPredicate(allApples(), apple -> apple.getColor().equalsIgnoreCase("green"));

        System.out.println("Red apples (Lambda): "+redApples);
        System.out.println("Green apples (Lambda): "+greenApples);

        // Apples by min weight
        List<Apple> applesWeighingMoreThanTwentyGrams = applesMatchingPredicate(allApples(), apple -> apple.getWeightInGrams() >= 20);
        System.out.println("Apples with min weight of 20 grams (Lambda): "+applesWeighingMoreThanTwentyGrams);

        // And more...
        List<Apple> myPick = applesMatchingPredicate(allApples(), apple -> apple.getWeightInGrams() >= 50 && apple.getColor().equals("green"));
        System.out.println("My fav pick: "+myPick);


    }

    public static List<Apple> allApples() {
        return Arrays.asList(new Apple("red", 30), new Apple("pink", 60), new Apple("green", 50), new Apple("red", 70), new Apple("red", 60));
    }


}
