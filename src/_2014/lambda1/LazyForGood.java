package _2014.lambda1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.function.Supplier;

class TrafficReport {
	private final String area;
	private final String city;
	private final String report;

	public TrafficReport(String area, String city, String report) {
		super();
		this.area = area;
		this.city = city;
		this.report = report;
	}

	public String getArea() {
		return area;
	}

	public String getCity() {
		return city;
	}

	public String getReport() {
		return report;
	}

	@Override
	public String toString() {
		return "TrafficReport [area=" + area + ", city=" + city + ", report=" + report + "]";
	}
}

class TrafficReportPublishRequest {
	private Random random = new Random();
	private List<TrafficReport> trafficReportsEager;
	private Supplier<List<TrafficReport>> trafficReportsLazy;
	private List<TrafficReport> trafficReports;

	public List<TrafficReport> getTrafficReportsEager() {
		return trafficReports;
	}

	public void setTrafficReportsEager(List<TrafficReport> trafficReportsEager) {
		this.trafficReportsEager = trafficReportsEager;
	}

	public List<TrafficReport> getTrafficReportsLazy() {
		return trafficReports;
	}

	public void setTrafficReportsLazy(Supplier<List<TrafficReport>> trafficReportsLazy) {
		this.trafficReportsLazy = trafficReportsLazy;
	}

	public List<TrafficReport> getTrafficReports() {
		if (trafficReportsLazy != null) {
			return trafficReportsLazy.get();
		} else {
			return trafficReportsEager;
		}
	}

	public boolean shouldPublish() {
		return random.nextInt(100) % 2 == 0;
	}
}

public class LazyForGood {

	// 3rd party webservice expensive operation.
	private static List<TrafficReport> getTrafficReport(String city) {
		System.out.println("$$$$$$$$$$$$$ Invoked 3rd party get traffic report for city: " + city);
		return Arrays.asList(new TrafficReport("M25", city, "chock-a-block"));
	}

	// 3rd party api to publish.
	private static void publishTopTrafficReport(final TrafficReportPublishRequest publishReportRequest) {
		if (publishReportRequest.shouldPublish()) {
			System.out.println("Invoked publish traffic report : ");
			System.out.println("Publishing Traffic Report: " + publishReportRequest.getTrafficReports());
		} else {
			System.out.println("------------- Not required to publish -------------- ");
		}
	}

	private static void publishTrafficReportDelegate(Supplier<List<TrafficReport>> reports) {
		TrafficReportPublishRequest trafficReportPublishRequest = new TrafficReportPublishRequest();
		trafficReportPublishRequest.setTrafficReportsLazy(reports);
		publishTopTrafficReport(trafficReportPublishRequest);
	}

	public static void eager() {
		String city = "London";
		List<TrafficReport> collectedReports = getTrafficReport(city);

		System.out.println(" --------------------- Performing a lot of internal operations --------------------- ");
		System.out.println("---------------------- About to publish the Traffic Report --------------------- ");
		TrafficReportPublishRequest trafficReportPublishRequest = new TrafficReportPublishRequest();
		trafficReportPublishRequest.setTrafficReportsEager(collectedReports);

		publishTopTrafficReport(trafficReportPublishRequest);

	}

	public static void lazy() {
		String city = "London";

		Supplier<List<TrafficReport>> collectedReports = () -> getTrafficReport(city);

		System.out.println(" --------------------- Performing a lot of internal operations --------------------- ");
		System.out.println("---------------------- About to publish the Traffic Report --------------------- ");

		publishTrafficReportDelegate(collectedReports);
	}

	public static void main(String[] args) {
		eager();
		lazy();
	}

}
