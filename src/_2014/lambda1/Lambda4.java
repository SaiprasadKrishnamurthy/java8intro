package _2014.lambda1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

class Employee {
	private final String name;
	private final String company;

	public Employee(String name, String company) {
		this.name = name;
		this.company = company;
	}

	public String getName() {
		return name;
	}

	public String getCompany() {
		return company;
	}

	@Override
	public String toString() {
		return name;
	}
}

// Collection handy.
public class Lambda4 {

	public static void map() {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "Sai");
		map.put(2, "Kris");

		// iterate through map.
		map.forEach((key, value) -> System.out.println("Key: " + key + ", Value: " + value));

		// put if absent.
		// old way.
		String existingValue = map.get(3);
		if (existingValue == null) {
			map.put(3, "Three");
		}
		
		// new way.
		map.computeIfAbsent(4, key -> "Four");

		System.out.println(map);
	}

	public static void collectionTransformations() {
		// dedupe a list ie transform a list to a set.
		List<Integer> input = Arrays.asList(1, 2, 3, 4, 5, 5, 6, 6, 7, 7, 8, 1, 2, 3, 4, 4, 4, 6, 5, 7, 9, 0, 0);

		Set<Integer> set = input.stream().collect(Collectors.toSet());
		System.out.println("Set: " + set);

		
		List<Employee> employees = Arrays.asList(new Employee("Sai", "PumpkinSoft"), 
				new Employee("Kumar", "PumpkinSoft"), 
				new Employee("Kasi", "MuskMelon inc"), 
				new Employee("Deepesh", "PineappleSoft"), 
				new Employee("Joe", "PineappleSoft"), 
				new Employee("Blog", "MuskMelon inc"), 
				new Employee("Jim", "MintLabs"));

		// Group employees by company name.
		// Old way.
		Map<String, List<Employee>> employeeGroup = new HashMap<>();
		for (Employee employee : employees) {
			List<Employee> existingBunch = employeeGroup.get(employee.getCompany());
			if (existingBunch == null) {
				existingBunch = new ArrayList<>();
			}
			existingBunch.add(employee);
			employeeGroup.put(employee.getCompany(), existingBunch);
		}
		System.out.println(employeeGroup);

		// attempt one.
		// employees.stream().collect(Collectors.groupingBy(employee ->
		// ((Employee)employee).getCompany()));

		// attempt two(cool!!!!).
		Map<String, List<Employee>> employeeGroup1 = employees.stream().collect(Collectors.groupingBy(Employee::getCompany));

		System.out.println(employeeGroup1);
	}

	public static void main(String[] args) {
		map();
		collectionTransformations();
	}

}
