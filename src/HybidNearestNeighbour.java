import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class HybidNearestNeighbour {
	static boolean cont = true;
	public HybidNearestNeighbour() {

	}
	
	
	public static Map<Integer, Point2D> calculate(ArrayList<Point2D> points) {
		Map<Integer,Point2D> shortestresult = new HashMap<Integer,Point2D>();
		double shortestDistance = Double.MAX_VALUE;
		int a = 1;
		do{
			Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
			ArrayList<Point2D> cities = new ArrayList<Point2D>(points);
			Point2D currentCity = cities.remove(0);
			int i = 0;
			result.put(i,currentCity);
			while(cities.size() > 0)
			{
				CopyOnWriteArrayList<Point2D> tempcities = new CopyOnWriteArrayList<Point2D>(cities);
				double  distance = Double.MAX_VALUE;
				Point2D[] closest = new Point2D[3];
				
				for (Point2D city: tempcities)
				{
					if(currentCity.distance(city) < distance)
					{					
						closest[0] = city;				
						distance = currentCity.distance(city);
						tempcities.remove(closest[0]);
					}
				}
				double  distance2 = Double.MAX_VALUE;
				for (Point2D city: tempcities)
				{
					if(currentCity.distance(city) < distance2)
					{
						closest[1] = city;				
						distance2 = currentCity.distance(city);
						tempcities.remove(closest[1]);
					}
				}
				double  distance3 = Double.MAX_VALUE;
				for (Point2D city: tempcities)
				{
					if(currentCity.distance(city) < distance3)
					{
						closest[2] = city;				
						distance3 = currentCity.distance(city);
						tempcities.remove(closest[2]);
					}
				}
				double routeDistance = Double.MAX_VALUE;
				Point2D closestCity = null;
				for(int j=0;j<=2; j++)
				{
					double pathDistance = pathfinding(cities, closest[j], a);
					if(pathDistance <routeDistance)
					{
						closestCity = closest[j];
						routeDistance = pathDistance;
					}
				}
				cities.remove(closestCity);
				currentCity = closestCity;
				result.put(i,closestCity);
				i++;
			}
			result.put(i, result.get(0));
			if(routeLength(result)<shortestDistance)
			{
				shortestDistance = routeLength(result);
				shortestresult = result;
			}
			if(a == result.size())
			{
				cont = false;
			}
			a++;
		}while(cont);
		//shortestresult = swap(shortestresult);
		return shortestresult;
	}
	
	public static double pathfinding(ArrayList<Point2D> points, Point2D startingPoint, int stepsAhead) {
		Map<Integer,Point2D> tempresult = new HashMap<Integer,Point2D>();
		ArrayList<Point2D> tempcities = new ArrayList<Point2D>(points);
		int steps = stepsAhead;
		if(points.size()<stepsAhead)
		{
			steps = points.size();
		}
		if(startingPoint == null)
		{
			return Double.MAX_VALUE;
		}
		Point2D currentCity = startingPoint;
		int i = 0;
		tempresult.put(i,currentCity);
		tempcities.remove(currentCity);
		while(i<steps)
		{
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
			tempresult.put(i,currentCity);
			i++;
		}
		double result = routeLength(tempresult);
		return result;
	}
	
	static double routeLength(Map<Integer, Point2D> route)
	{
		double distance = 0;
		for(int i=0;i< route.size()-1; i++)
		{
			if(route.get(i+1) != null)
			{
				distance += route.get(i).distance(route.get(i+1));
			}
		}
		return distance;
	}
	
	static Map<Integer, Point2D> swap(Map<Integer, Point2D> route)
	{
		Map<Integer,Point2D> temproute = new HashMap<Integer,Point2D>(route);
		Map<Integer,Point2D> switchingroute = new HashMap<Integer,Point2D>(route);
		int swaps = switchingroute.size()/4;
		
		for (int i = 2;i<swaps-1; i+=2)
		{
			Point2D farleft = switchingroute.get(i-1);
			Point2D left = switchingroute.get(i);
			Point2D right = switchingroute.get(i+1);
			Point2D farright = switchingroute.get(i+2);
			
			if(farleft.distance(right)<farleft.distance(left))
			{
				switchingroute.put(i, right);
				switchingroute.put(i+1, left);
			}
			else{
				switchingroute.put(i, left);
				switchingroute.put(i+1, right);
			}
			if(farright.distance(farleft)<farright.distance(right))
			{
				switchingroute.put(i-1, farright);
				switchingroute.put(i+2, farleft);
			}
			else{
				switchingroute.put(i-1, farleft);
				switchingroute.put(i+2, farright);
				
			}
			if(switchingroute.get(i).distance(switchingroute.get(i+2))< switchingroute.get(i).distance(switchingroute.get(i+1)))
			{
				Point2D temp = switchingroute.get(i+2);
				switchingroute.put(i+2, switchingroute.get(i+1));
				switchingroute.put(i+1, temp);
				
			}
			
			
			if(routeLength(switchingroute)<routeLength(temproute))
			{
				temproute=switchingroute;
			}
			
			
		}
		
		//if(routeLength(temproute)<routeLength(route))
		//{
			return temproute;
		//}
		//else return route;
	}
	
	static void stop(){
		cont = false;
	}



}
