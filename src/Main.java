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
		//basic();
		
		//this thread runs the algorithm
		Thread thread = new Thread() {
		    public void run() {
		    	hybridNearestNeighbour(true, true);
		    }
		};
		
		//this thread allows the user to stop the program once the path it is currently calculating is finished
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
		    		notEntered = false;
		    	}
		    	}while(notEntered);
		    	
		    }
		};
		
		thread.start();
		
		
		

	}
	
	public static void hybridNearestNeighbour(boolean swaps, boolean illustration){
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Please enter a file to load");
		try {
			//reads in the file location from the command line
			fileLocation = reader.readLine();
			cities = LoadCities.loadTSPLib(fileLocation);
			thread2.start();
			//runs the calculation
			result = HybidNearestNeighbour.calculate(cities);
			if(swaps)
			{
				//runs the swap sub-routine
				result = HybidNearestNeighbour.swap(result);
			}
			double routeLength = HybidNearestNeighbour.routeLength(result);
			System.out.println("The length of the  nearest neighbour algorithm route with pathfinding is "+routeLength);
			if(illustration)
			{
				//draws the final route into an image file
				TSPIllustration.IllustrateRoute(result, (float)0.5, fileLocation+" Route wth pathfinding");
			}
			
			}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	//this runs the standard nearest neighbour and the hybrid nearest neighbour to allow a comparison
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
	
	
	//this runs and then outputs the standard nearest neighbour
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
