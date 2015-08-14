package lambda1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Hello Lambda
 * 
 * @author Sai
 *
 */
public class Lambda1 {

	public static void iteration() {
		List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);

		// Java 5+ way (imperative, external iteration).
		for (Integer i : input) {
			System.out.println(i);
		}

		System.out.println("---------------------------------------------");

		
		
		// refinement 1: (using functional interface, but no lambda yet)
		input.forEach(new Consumer<Integer>() {

			@Override
			public void accept(Integer n) {
				System.out.println(n);
			}
		});
		System.out.println("---------------------------------------------");

		
		
		// refinement 2: (using lambda with functional interfaces)
		Consumer<Integer> consumer = n -> System.out.println(n);
		
		input.forEach(consumer);

		System.out.println("---------------------------------------------");

		// refinement 3: (using lambda expressions)
		input.forEach(n -> System.out.println(n));

		// refinement 4: (using lambda expressions, with method references
		// (Cool!)
		input.forEach(System.out::println);
	}

	public static void filter() {
		List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18);

		List<Integer> filtered = new ArrayList<>();

		// Java 5+ way.
		for (Integer i : input) {
			if (i % 2 == 0) {
				filtered.add(i);
			}
		}
		System.out.println("Filtered List (Imperative Way): " + filtered);

		System.out.println("---------------------------------------------");

		// Refinement 1: With functional interfaces, No Lambda expressions.
		Predicate<Integer> filterFunction = new Predicate<Integer>() {
			@Override
			public boolean test(Integer n) {
				return n % 2 == 0;
			}
		};
		System.out.println("Filtered List (Using functional interfaces but No Lambdas): " + input.stream().filter(filterFunction).collect(Collectors.toList()));

		System.out.println("---------------------------------------------");

		// Refinement 2: With functional interfaces and Lambda expressions
		// visible.
		Predicate<Integer> filterFunction1 = n -> n % 2 == 0;
		System.out.println("Filtered List (Using visible functional interfaces and Lambdas): " + input.stream().filter(filterFunction1).collect(Collectors.toList()));

		System.out.println("---------------------------------------------");

		// Refinement 3: With only Lambda expressions visible.
		System.out.println("Filtered List (Using only visible Lambda expression): " + input.stream().filter(n -> n % 2 == 0).collect(Collectors.toList()));

		System.out.println("---------------------------------------------");

		// Refinement 4: With no Lambda expressions and use method references.
		System.out.println("Filtered List (Using No Lambdas but method references): " + input.stream().filter(Lambda1::onlyEvenNumbers).collect(Collectors.toList()));
		System.out.println("---------------------------------------------");
	}

	private static boolean onlyEvenNumbers(Integer n) {
		return n % 2 == 0;
	}

	public static void main(String[] args) {
		iteration();
		filter();
	}
}
