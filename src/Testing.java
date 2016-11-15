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
static long tStart, tEnd, tDelta;
	public static void main(String[] args) {
		for(int i=0;i<=10;i++)
		{
			tStart = System.currentTimeMillis();
			cities = LoadCities.loadTSPLib("ulysses22.tsp");
			result= NearestNeighbour.nearestNeighbour(cities);
			tEnd = System.currentTimeMillis();
			tDelta = tEnd - tStart;
			System.out.println("Time taken: "+tDelta+"ms");
			
			tStart = System.currentTimeMillis();
			cities2 = LoadCities.loadTSPLib("ulysses22.tsp");
			result2 = NearestNeighbour.nearestNeighbour(cities);
			tEnd = System.currentTimeMillis();
			tDelta = tEnd - tStart;
			System.out.println("Time taken: "+tDelta+"ms");
			
			
			tStart = System.currentTimeMillis();
			cities3 = LoadCities.loadTSPLib("ulysses22.tsp");
			result3= HybidNearestNeighbour.calculate(cities);
			tEnd = System.currentTimeMillis();
			tDelta = tEnd - tStart;
			System.out.println("Time taken: "+tDelta+"ms");
		}
	}

}
