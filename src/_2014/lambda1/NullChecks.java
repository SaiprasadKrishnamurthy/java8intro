package _2014.lambda1;

import java.util.Arrays;
import java.util.List;

class Fruit {
	private final String name;

	public Fruit(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fruit other = (Fruit) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}

}

public class NullChecks {

	private static void null1() {
		List<Fruit> fruitBasket = Arrays.asList(new Fruit("Mango"), new Fruit("Apple"));

		Fruit matchedFruit = fruitBasket.stream().filter(NullChecks::saisFavourite).findFirst().orElse(defaultChoice());

		

	}

	private static boolean saisFavourite(Fruit fruit) {
		return fruit.getName().equals("Orange");
	}

	private static Fruit defaultChoice() {
		return new Fruit("Watermelon");
	}

	public static void main(String[] args) {
		NullChecks.null1();
	}

}
