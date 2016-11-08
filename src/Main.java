import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
	static Thread thread2;
	static String fileLocation;
	static ArrayList<Point2D> cities = new ArrayList<Point2D>();
	static Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
	static Map<Integer,Point2D> result2 = new HashMap<Integer,Point2D>();
	
	public static void main(String[] args) {
		basic();
		Thread thread = new Thread() {
		    public void run() {
		    	hybridNearestNeighbour();
		    }
		};
		thread2 = new Thread() {
		    public void run() {
		    	boolean notEntered = true;
		    	do{
		    	String stopped = null;
		    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		    	System.out.println("Enter x to stop at any time");
		    	try {
					stopped = reader.readLine();
				} catch (IOException e) {
					e.printStackTrace();
				}
		    	if (stopped.equalsIgnoreCase("x"))
		    	{
		    		HybidNearestNeighbour.stop();
		    		//HybidNearestNeighbour.cont = false;
		    		notEntered = false;
		    	}
		    	}while(notEntered);
		    	
		    }
		};
		
		thread.start();
		
		
		

	}
	
	public static void hybridNearestNeighbour(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file to load");
		try {
			fileLocation = reader.readLine();
			cities = LoadCities.loadTSPLib(fileLocation);
			thread2.start();
			result = HybidNearestNeighbour.calculate(cities);
			double routeLength = HybidNearestNeighbour.routeLength(result);
			System.out.println("The length of the  nearest neighbour algorithm route with pathfinding is "+routeLength);
			TSPIllustration.IllustrateRoute(result, (float)5, fileLocation+" Route wth pathfinding");
			//TSPIllustration.IllustrateAll(result, (float) 3, "Route step");
			result = HybidNearestNeighbour.swap(result);
			double routeLength2 = HybidNearestNeighbour.routeLength(result);
			System.out.println("The length of the  nearest neighbour algorithm route with pathfinding and swapping is "+routeLength2);
			TSPIllustration.IllustrateRoute(result, (float)5, fileLocation+" Route wth pathfinding and swap");
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void proof(){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file to load");
		try {
			fileLocation = reader.readLine();
		
		cities = LoadCities.loadTSPLib(fileLocation);
		result = NearestNeighbour.nearestNeighbour(cities);
		cities = LoadCities.loadTSPLib(fileLocation);
		result2 = HybidNearestNeighbour.calculate(cities);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Calculations complete");

		double routeLength = NearestNeighbour.routeLength(result);
		double routeLength3 = HybidNearestNeighbour.routeLength(result2);

		System.out.println("The length of the  nearest neighbour algorithm route is "+routeLength);
		System.out.println("The length of the  nearest neighbour algorithm route with pathfinding is "+routeLength3);
		TSPIllustration.IllustrateRoute(result, (float) 2, fileLocation+" Route");
		TSPIllustration.IllustrateRoute(result2, (float)2, fileLocation+" Route wth pathfinding");
	}
	
	public static void basic()
	{
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file to load");
		try {
			fileLocation = reader.readLine();
		
		cities = LoadCities.loadTSPLib(fileLocation);
		result = NearestNeighbour.nearestNeighbour(cities);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Calculations complete");

		double routeLength = NearestNeighbour.routeLength(result);
		System.out.println("The length of the  nearest neighbour algorithm route is "+routeLength);
		TSPIllustration.IllustrateRoute(result, (float) 5, fileLocation+" Route");
	}

}
