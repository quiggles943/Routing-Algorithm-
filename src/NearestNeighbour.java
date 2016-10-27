import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class NearestNeighbour {
	ArrayList<Point2D> cities = new ArrayList<Point2D>();
	Map<Integer,Point2D> result = new HashMap<Integer,Point2D>();
	
	public NearestNeighbour() {
		Point2D currentCity = cities.remove(0);
		int i = 0;
		do
		{
			result.put(i,currentCity);
			int distance = Integer.MAX_VALUE;
			for (Point2D city: cities)
			{
				
			}
		}while(cities.size() > 0);
	}
	

}
