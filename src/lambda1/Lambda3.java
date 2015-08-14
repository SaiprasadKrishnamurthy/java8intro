package lambda1;

import java.util.stream.IntStream;

// Laziness of streams.
public class Lambda3 {

	public static Integer doubleIt(Integer n) {
		System.out.println("Doubling "+n);
		return n * 2;
	}
	
	public static boolean greaterThan5(Integer n) {
		System.out.println("GreaterThan5 "+n);
		return n > 5;
	}
	
	public static boolean isEven(Integer n) {
		System.out.println("FilterEven "+n);
		return n % 2 == 0;
	}
	
	public static void lazy1() {
		/*
		 * Input: 1,2,3,4,5,6,7,8,9,10
		 * Filter: 6,8,10
		 * Map: 12,16,20
		 * FindFirst: 12
		 */
		Integer result = IntStream.range(0, 10)
				.filter(Lambda3::greaterThan5)
				.filter(Lambda3::isEven)
				.map(Lambda3::doubleIt)
				.findFirst().getAsInt();
		
		System.out.println("Result: "+result);
	}

	public static void main(String[] args) {
		lazy1();
	}

}
