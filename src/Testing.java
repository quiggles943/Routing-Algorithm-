import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class Testing {
static ArrayList<Point2D> cities = new ArrayList<Point2D>();
static Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
static ArrayList<Point2D> cities2 = new ArrayList<Point2D>();
static Map<Integer,Point2D> result2 = new HashMap<Integer,Point2D>();
static ArrayList<Point2D> cities3 = new ArrayList<Point2D>();
static Map<Integer,Point2D> result3 = new HashMap<Integer,Point2D>();
static ArrayList<Point2D> cities4 = new ArrayList<Point2D>();
static Map<Integer,Point2D> result4 = new HashMap<Integer,Point2D>();
static long tStart, tEnd, tDelta;
	public static void main(String[] args) {
		
		String flie = "berlin52.tsp";

		//normal nearest neighbour
		tStart = System.currentTimeMillis();
		cities = LoadCities.loadTSPLib(flie);
		result= NearestNeighbour.nearestNeighbour(cities);
		tEnd = System.currentTimeMillis();
		tDelta = tEnd - tStart;
		System.out.println("Nearest Neighbour \nTime taken: "+tDelta+"ms");
		System.out.println("Distance:" + NearestNeighbour.routeLength(result)+"\n");
		
		//nearest neighbour with random start points
		tStart = System.currentTimeMillis();
		cities2 = LoadCities.loadTSPLib(flie);
		result2 = RandomNearestNeighbour.randomNearestNeighbour(cities2, 10);
		tEnd = System.currentTimeMillis();
		tDelta = tEnd - tStart;
		System.out.println("Random Start Nearest Neighbour \nTime taken: "+tDelta+"ms");
		System.out.println("Distance:" + NearestNeighbour.routeLength(result2)+"\n");
		
		//hybrid nearest neighbour
		tStart = System.currentTimeMillis();
		cities4 = LoadCities.loadTSPLib(flie);
		result4= HybidNearestNeighbour.calculate(cities4);
		result4 = HybidNearestNeighbour.swap(result4);
		tEnd = System.currentTimeMillis();
		tDelta = tEnd - tStart;
		System.out.println("Hybrid Nearest Neighbour \nTime taken: "+tDelta+"ms");
		System.out.println("Distance:" +HybidNearestNeighbour.routeLength(result4) +"\n");
	}

}
