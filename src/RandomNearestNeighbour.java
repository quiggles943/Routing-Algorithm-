import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class RandomNearestNeighbour {

		static Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
		public static Map<Integer, Point2D> randomNearestNeighbour(ArrayList<Point2D> points, int iterations) {
			UniqueRandomNumbers.setup(points.size());
			Map<Integer,Point2D> tempresult = new HashMap<Integer,Point2D>(points.size());
			result = nearestNeighbour(points,0);
			double  distance = routeLength(result);
			for (int i=0;i<iterations;i++)
			{
				tempresult = nearestNeighbour(points, UniqueRandomNumbers.getNumber());
				if(routeLength(tempresult)<distance)
				{
					result = tempresult;
				}
			}
			return result;
		}
	
	public static Map<Integer, Point2D> nearestNeighbour(ArrayList<Point2D> points, int startCity) {
		ArrayList<Point2D> cities = new ArrayList<Point2D>(points);
		Map<Integer,Point2D> results = new HashMap<Integer,Point2D>();
		Point2D currentCity = cities.remove(startCity);
		int i = 0;
		results.put(i,currentCity);
		while(cities.size() > 0)
		{
			Point2D closest = null;
			
			double  distance = Double.MAX_VALUE;
			for (Point2D city: cities)
			{
				if(currentCity.distance(city) < distance)
				{
					closest = city;
					distance = currentCity.distance(city);
				}
			}
			cities.remove(closest);
			currentCity = closest;
			results.put(i,currentCity);
			i++;
		}
		results.put(i, results.get(0));
		return results;
	}
	
	
	static double routeLength(Map<Integer,Point2D> route)
	{
		double distance = 0;
		for(int i=0;i< route.size()-1; i++)
		{
			distance += route.get(i).distance(route.get(i+1));
		}
		return distance;
	}
	
	    

}
