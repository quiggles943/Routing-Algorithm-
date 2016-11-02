import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Map;

import javax.imageio.ImageIO;


public class TSPIllustration {
	static Map<Integer, Point2D> cities;
	static float scale;
	static BufferedImage image;
	public TSPIllustration() {
		
	}
	
	public static void IllustrateAll(Map<Integer, Point2D> points, float scalingFactor, String fileName){
		scale = scalingFactor;
		cities = points;
		int height = 0;
		int width = 0;
		for (int i=0; i< cities.size(); i++)
		{
			if(cities.get(i).getX()> width)
			{
				width = (int)cities.get(i).getX();
			}
			if(cities.get(i).getY()> height)
			{
				height = (int)cities.get(i).getY();
			}
		}
		image = new BufferedImage((int)((width+20)*scale),(int)((height+20)*scale),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0,(int)((width+20)*scale),(int)((height+20)*scale));

		g.setColor(Color.blue);
		for (int i=0; i< cities.size(); i++)
		{
			int x = (int)((cities.get(i).getX())*scale);
			int y =(int)((cities.get(i).getY())*scale);
			if(scale <3)
			{
				g.fillOval((x-3),(y-3),6,6);
			}else{
				g.fillOval((int)(x-(1*scale)),(int)(y-(1*scale)),(int)(2*scale),(int)(2*scale));
			}
		}
		
		
		for (int i=0; i< cities.size()-1; i++)
		{
			g.setColor(Color.red);
			if(scale <3)
			{
				g.setStroke(new BasicStroke((float) 2));
			}else{
				g.setStroke(new BasicStroke((scale)/2));
			}
			g.drawLine((int)(cities.get(i).getX()*scale), (int)(cities.get(i).getY()*scale), (int)(cities.get(i+1).getX()*scale), (int)((cities.get(i+1).getY())*scale));
			createImage(image,g,fileName +" Step "+(i+1));
		}
		createImage(image,g,fileName);
	}
	
	public static void IllustrateRoute(Map<Integer, Point2D> points, float scalingFactor, String fileName){
		scale = scalingFactor;
		cities = points;
		int height = 0;
		int width = 0;
		for (int i=0; i< cities.size(); i++)
		{
			if(cities.get(i).getX()> width)
			{
				width = (int)cities.get(i).getX();
			}
			if(cities.get(i).getY()> height)
			{
				height = (int)cities.get(i).getY();
			}
		}
		image = new BufferedImage((int)((width+20)*scale),(int)((height+20)*scale),BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = image.createGraphics();
		g.setColor(Color.white);
		g.fillRect(0,0,(int)((width+20)*scale),(int)((height+20)*scale));

		g.setColor(Color.blue);
		for (int i=0; i< cities.size(); i++)
		{
			int x = (int)((cities.get(i).getX())*scale);
			int y =(int)((cities.get(i).getY())*scale);
			if(scale <3)
			{
				g.fillOval((x-3),(y-3),6,6);
			}else{
				g.fillOval((int)(x-(1*scale)),(int)(y-(1*scale)),(int)(2*scale),(int)(2*scale));
			}
		}
		
		
		for (int i=0; i< cities.size()-1; i++)
		{
			g.setColor(Color.red);
			if(scale <3)
			{
				g.setStroke(new BasicStroke((float) 1.5));
			}else{
				g.setStroke(new BasicStroke((scale)/2));
			}
			g.drawLine((int)(cities.get(i).getX()*scale), (int)(cities.get(i).getY()*scale), (int)(cities.get(i+1).getX()*scale), (int)((cities.get(i+1).getY())*scale));
		}
		createImage(image,g,fileName);
}
			
		public static void createImage(BufferedImage image,Graphics2D g, String filename)
		{
				
			try{
				File out = new File(filename+".png");
				ImageIO.write(image, "png", out);
			}catch(Exception e){
				System.err.println(e);
			}
			System.out.println("Image "+filename+" created");
		}
	

}
