import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Map;

import javax.imageio.ImageIO;


public class TSPIllustration {
	static Map<Integer, Point2D> cities;
	static int scale;
	static BufferedImage image;
	public TSPIllustration() {
		
	}
	
	public static void Illustrate(Map<Integer, Point2D> points, int scalingFactor){
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
			image = new BufferedImage(((width+20)*scale),((height+20)*scale),BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = image.createGraphics();

			g.setColor(Color.blue);
			for (int i=0; i< cities.size(); i++)
			{
				g.drawOval((int)((cities.get(i).getX())*scale),(int)((cities.get(i).getY())*scale),6,6);
			}
			
			
			for (int i=0; i< cities.size()-1; i++)
			{
				g.setColor(Color.red);
				g.drawLine((int)(cities.get(i).getX()*scale), (int)(cities.get(i).getY()*scale), (int)(cities.get(i+1).getX()*scale), (int)(cities.get(i+1).getY())*scale);
				createImage(image,g, "step"+(i+1));
			}
			createImage(image,g,"Route");
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
