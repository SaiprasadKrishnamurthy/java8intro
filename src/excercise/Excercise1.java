package excercise;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Excercise1 {

	public static void main(String[] args) {
		
		List<String> names = Arrays.asList("Sai", "Kasi", "Kumar", "Ram");

		// requirement 1
		printNames(n -> n.length() > 3, names);
		printNames(n -> n.toUpperCase().startsWith("S"), names);

	}

	private static void printNames(final Predicate<String> filterFunction, List<String> names) {
		names.stream().filter(filterFunction)
					  .forEach(System.out::println);
	}
	


}
