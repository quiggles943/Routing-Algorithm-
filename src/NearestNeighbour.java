import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import java.awt.*;
import java.io.File;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;


public class NearestNeighbour {
	static ArrayList<Point2D> cities = new ArrayList<Point2D>();
	static Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
	
	public static Map<Integer, Point2D> nearestNeighbour(ArrayList<Point2D> points) {
		cities = points;
		Point2D currentCity = cities.remove(0);
		int i = 0;
		result.put(i,currentCity);
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
			result.put(i,currentCity);
			i++;
		}
		result.put(i, result.get(0));
		return result;
	}
	
	
	static double routeLength(Map<Integer, Point2D> route)
	{
		double distance = 0;
		for(int i=0;i< route.size()-1; i++)
		{
			distance += route.get(i).distance(route.get(i+1));
		}
		return distance;
	}

}
