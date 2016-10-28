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
	
	public static void main(String[] args) {
		int scale = 2;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file to load");
		try {
			fileLocation = reader.readLine();
		
		//fileLocation = "D:/berlin52.tsp";
		cities = LoadCities.loadTSPLib("D:/"+fileLocation);
		result = NearestNeighbour.nearestNeighbour(cities);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Calculations complete");
		TSPIllustration.Illustrate(result, 10);
		double routeLength = NearestNeighbour.routeLength(result);
		System.out.println("The length of the route is "+routeLength);
	}

}
