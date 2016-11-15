import java.awt.geom.Point2D;
import java.util.*;

public class NearestNeighbour2 {
	//this is nearest neighbour but with an n step look into the future to determine shorter paths
	static ArrayList<Point2D> cities = new ArrayList<Point2D>();
	static Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
	ArrayList<Point2D> results = new ArrayList<Point2D>();
	
	static Map<Double,Point2D> distances = new LinkedHashMap<Double,Point2D>();
	
	static Point2D startingPoint;
	
	public static Map<Integer, Point2D> calculate(ArrayList<Point2D> points, int steps)
	{		
		
		
		cities = points;
		startingPoint = cities.remove(0);
		int i =0;
		result.put(i,startingPoint);
		i++;
		while(cities.size() > 0)
		{
			Point2D city = bestRoute(startingPoint,steps);
			startingPoint = city;
			cities.remove(city);
			
			result.put(i,city);
			i++;
		}
		result.put(i, result.get(0));
		
		return result;
	}
	
	public static Point2D closestCity(Point2D start)
	{
		ArrayList<Point2D> tempcities = new ArrayList<Point2D>(cities);
		Point2D currentCity = start;
		Point2D closest = null;
		double  distance = Double.MAX_VALUE;
		for (Point2D city: tempcities)
		{
			if(currentCity.distance(city) < distance)
			{
				closest = city;
				distance = currentCity.distance(city);
			}
		}
		tempcities.remove(closest);
		currentCity = closest;	
		return closest;
	}
	
	public static double[] closestCityKeys(Point2D start)
	{
		double[] keys = new double[cities.size()];
		ArrayList<Point2D> tempcities = new ArrayList<Point2D>(cities);
		Point2D currentCity = start;
		Point2D closest = null;
		double  distance = Double.MAX_VALUE;
		int i =0;
		for (Point2D city: tempcities)
		{
			distance = currentCity.distance(city);
			keys[i] = distance;
			distances.put(distance, city);
			if(currentCity.distance(city) < distance)
			{
				closest = city;				
				
				
			}
			i++;
		}
		tempcities.remove(closest);
		currentCity = closest;	
		return keys;
	}
	
	public static Point2D bestRoute(Point2D start, int steps)
	{
		double[] distance = new double[3];
		double[] sortKeys = closestCityKeys(start);
		double[] shortestKeys = new double[steps];
		int[] shortKeys = new int[steps];
		double [] tempKeys = sortKeys;
		if(sortKeys.length<steps)
		{
			steps = sortKeys.length;
		}
		
		for(int m=0;m<steps;m++)
		{
			shortestKeys[m] = smallestKey(tempKeys);
			shortKeys[m] = smallestIndex(tempKeys);
			tempKeys[shortKeys[m]] = Double.MAX_VALUE;
		}

			for(int i = 0; i<3; i++)
			{
				
				Point2D city = distances.get(shortestKeys[i]);
				for(int j=0;j< steps; j++)
				{
					Point2D closeCity = city;
					Point2D closestCity = closestCity(closeCity);
					distance[i] += city.distance(closestCity);
					city = closestCity;
				}
			}

		int  in = smallestIndex(distance);
		return distances.get(shortestKeys[in]);
		
	}
	
	
	public static int smallestIndex(double[] values){
		double small = values[0];
		int index = 0;
		for (int i = 0; i < values.length; i++)
		    if (values[i] < small)
		    {
		        small = values[i];
		        index = i;
		    }
		return index;
	}
	
	public static double smallestKey(double[] values){
		double small = values[0];
		for (int i = 0; i < values.length; i++)
		    if (values[i] < small)
		    {
		        small = values[i];
		    }
		return small;
	}
	
}
