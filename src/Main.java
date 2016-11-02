import java.awt.geom.Point2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {
	
	static String fileLocation;
	static ArrayList<Point2D> cities = new ArrayList<Point2D>();
	static Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
	static Map<Integer,Point2D> result2 = new HashMap<Integer,Point2D>();
	static Map<Integer,Point2D> result3 = new HashMap<Integer,Point2D>();
	
	public static void main(String[] args) {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file to load");
		try {
			fileLocation = reader.readLine();
		
		cities = LoadCities.loadTSPLib(fileLocation);
		result = NearestNeighbour.nearestNeighbour(cities);
		cities = LoadCities.loadTSPLib(fileLocation);
		result2 = RandomNearestNeighbour.randomNearestNeighbour(cities, 22);
		cities = LoadCities.loadTSPLib(fileLocation);
		result3 = HybidNearestNeighbour.calculate(cities, 280);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Calculations complete");

		double routeLength = NearestNeighbour.routeLength(result);
		double routeLength3 = HybidNearestNeighbour.routeLength(result3);

		System.out.println("The length of the  nearest neighbour algorithm route is "+routeLength);
		System.out.println("The length of the  nearest neighbour algorithm route with pathfinding is "+routeLength3);
		TSPIllustration.IllustrateRoute(result, (float) 20, fileLocation+" Route");
		TSPIllustration.IllustrateRoute(result3, (float)20, fileLocation+" Route wth pathfinding");
		//TSPIllustration.IllustrateAll(result, (float) 0.8, fileLocation+" Route");
		//TSPIllustration.IllustrateAll(result3, (float) 0.8, fileLocation+" Route wth pathfinding");

	}

}
