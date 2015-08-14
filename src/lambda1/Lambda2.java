package lambda1;

import java.util.stream.IntStream;

// Variable captures.
public class Lambda2 {

	private StringBuilder builder = new StringBuilder();

	private void instanceVariableCapture() {
		builder.append("Sai");

		// for loop from 0 to 10
		IntStream.range(0, 10).forEach(n -> builder.append(n));

		System.out.println(builder);
	}

	private void localVariableCapture() {
		StringBuilder localBuilder = new StringBuilder();

		// for loop from 0 to 10
		IntStream.range(0, 10).forEach(n -> {
			// can't do this they are final.
			// localBuilder = new StringBuilder();
				localBuilder.append(n);
			});
		System.out.println(localBuilder);
	}

	public static void main(String[] args) {
		Lambda2 instance = new Lambda2();
		instance.instanceVariableCapture();
		instance.localVariableCapture();
	}

}
