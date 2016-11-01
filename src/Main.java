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
		
		//result = NearestNeighbour.nearestNeighbour(cities);
		//cities = LoadCities.loadTSPLib(fileLocation);
		//result2 = NearestNeighbour2.calculate(cities, 270);
		//cities = LoadCities.loadTSPLib(fileLocation);
		result3 = RandomNearestNeighbour.randomNearestNeighbour(cities, 1);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Calculations complete");
		//TSPIllustration.IllustrateAll(result3, 2);
		//double routeLength = NearestNeighbour.routeLength(result);
		//double routeLength2 = NearestNeighbour.routeLength(result2);
		double routeLength3 = NearestNeighbour.routeLength(result3);
		//System.out.println("The length of the nearest neighbour algorithm route is "+routeLength+"\n");
		//System.out.println("The length of the improved nearest neighbour algorithm route is "+routeLength2);
		System.out.println("The length of the  nearest neighbour algorithm route with random starting points is "+routeLength3);
		//TSPIllustration.IllustrateRoute(result3, (float) 2, fileLocation+" Route");

	}

}
