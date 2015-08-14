package lambda1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapReduce {

	private static void map1() {
		List<Integer> input = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		
		List<Integer> doubledInput = input.stream().map(n -> n * 2).collect(Collectors.toList());
		
		System.out.println("Mapped doubled input: "+doubledInput);
	}
	
	private static void mapReduce() {
		List<Integer> input = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
		Integer sumOfDoubles = input.stream().map(n -> n * 2).reduce((curr, total) -> curr + total).get();
		System.out.println("Mapped Reduce Sum of doubles : "+sumOfDoubles);
	}
	
	public static void main(String[] args) {
		map1();
		mapReduce();
	}
}
