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
		int totalCalc;
		int a = 1;
		do{
			Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
			ArrayList<Point2D> cities = new ArrayList<Point2D>(points);
			totalCalc = cities.size();
			Point2D currentCity = cities.remove(0);
			int i = 0;
			result.put(i,currentCity);
			
			while(cities.size() > 0)
			{
				CopyOnWriteArrayList<Point2D> tempcities = new CopyOnWriteArrayList<Point2D>(cities);
				double  distance = Double.MAX_VALUE;
				Point2D[] closest = new Point2D[3];
				
				//get the closest city to the current
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
				//get the second closest city to the current
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
				//get the third closest city to the current
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
				//find which has the shortest route for the number of steps ahead
				for(int j=0;j<=2; j++)
				{
					double pathDistance = pathfinding(cities, closest[j], a);
					if(pathDistance <routeDistance)
					{
						closestCity = closest[j];
						routeDistance = pathDistance;
					}
				}
				//remove the best next city
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
			System.out.println("completed "+a+" of "+totalCalc);
		}while(cont);
		return shortestresult;
		
	}
	
	public static double pathfinding(ArrayList<Point2D> points, Point2D startingPoint, int stepsAhead) {
		Map<Integer,Point2D> tempresult = new HashMap<Integer,Point2D>();
		ArrayList<Point2D> tempcities = new ArrayList<Point2D>(points);
		int steps = stepsAhead;
		//if the number of cities left is less than the steps to look ahead
		if(points.size()<stepsAhead)
		{
			steps = points.size();
		}
		//if there is no point to start at
		if(startingPoint == null)
		{
			return Double.MAX_VALUE;
		}
		Point2D currentCity = startingPoint;
		int i = 0;
		tempresult.put(i,currentCity);
		tempcities.remove(currentCity);
		//while there are still steps to look ahead
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
//calculate the length of the route
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
	
	//swap sub-routine
	static Map<Integer, Point2D> swap(Map<Integer, Point2D> route)
	{
		Map<Integer,Point2D> temproute = new HashMap<Integer,Point2D>(route);
		Map<Integer,Point2D> switchingroute = new HashMap<Integer,Point2D>(route);
		int swaps = switchingroute.size()/4;
		
		for (int i = 2;i<swaps-1; i+=2)
		{
			System.out.println("optimizing solution "+ (i/2)+"/"+((swaps/2)-1));
			
			//if the first point is closer to the third point than the second point
			if(switchingroute.get(i-1).distance(switchingroute.get(i+1))<switchingroute.get(i-1).distance(switchingroute.get(i)))
			{
				Point2D temp = switchingroute.get(i);
				switchingroute.put(i, switchingroute.get(i+1));
				switchingroute.put(i+1, temp);
			}
			//if the fourth point is closer to the first point than the third point
			if(switchingroute.get(i+2).distance(switchingroute.get(i-1))<switchingroute.get(i+2).distance(switchingroute.get(i+1)))
			{
				Point2D temp = switchingroute.get(i-1);
				switchingroute.put(i-1, switchingroute.get(i+2));
				switchingroute.put(i+2, temp);
			}
			//if the second point is closer to the fourth point than the third point
			if(switchingroute.get(i).distance(switchingroute.get(i+2))< switchingroute.get(i).distance(switchingroute.get(i+1)))
			{
				Point2D temp = switchingroute.get(i+2);
				switchingroute.put(i+2, switchingroute.get(i+1));
				switchingroute.put(i+1, temp);
				
			}
			
			//if the route is shorter than the normal route
			if(routeLength(switchingroute)<routeLength(temproute))
			{
				temproute=switchingroute;
			}
			
			
		}
		
			return temproute;
	}
	
	//stops the program after the current calculation
	static void stop(){
		cont = false;
	}



}
