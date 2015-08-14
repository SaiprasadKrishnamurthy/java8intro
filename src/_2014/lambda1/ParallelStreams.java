package _2014.lambda1;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreams {
	
	private static Integer doubleIt(Integer n) {
		try {
			System.out.println("Double it called: " + n);
			Thread.sleep(1000);
			return n * 2;
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private static void seq() {
		List<Integer> inputs = IntStream.range(1, 10).boxed().collect(Collectors.toList());
		Integer result = inputs.stream().map(ParallelStreams::doubleIt).reduce(0, (current, accumulator) -> current + accumulator);

		System.out.println("Sequential Result: " + result);
	}
	
	private static void par() {
		List<Integer> inputs = IntStream.range(1, 10).boxed().collect(Collectors.toList());
		Integer result = inputs.parallelStream().map(ParallelStreams::doubleIt).reduce(0, (current, accumulator) -> current + accumulator);

		System.out.println("Parallel Result: " + result);
	}

	public static void main(String[] args) {
//		seq();
		par();
	}

}
