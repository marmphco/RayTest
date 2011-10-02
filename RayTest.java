import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.ArrayList;
//testing push OMG ROFL
public class RayTest
{
	public static void main(String[] args) throws IOException
	{
		int width = 640, height = 480;
		double aspect = (double)width / (double)height;
		double planeSize = 5;
		double trololo = Math.sqrt(-1);
		System.out.println(trololo);
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		Vector3 camerapos = new Vector3(0, 0, -5);
		ArrayList<RenderObject> renderObjects = new ArrayList<RenderObject>();
		renderObjects.add(new Sphere(new Vector3(1, 1 , 10), 5));
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				double xPos = (double)(x - width/2) * aspect * planeSize / (double)(width/2);
				double yPos = (double)(y - height/2) * planeSize / (double)(height/2);
				Vector3 cameradir = new Vector3(xPos, yPos, 0).subtract(camerapos).normalize();
				Vector3 color = new Vector3();
				
				for(RenderObject r : renderObjects)
				{
					
					Vector3 intersection = r.intersectionWithRay(cameradir);
					if(new Double(intersection.x).isNaN()) 
					{
						
					}
					else
					{
						color = new Vector3(0, 0, 255);
					}
				
				}
				
				image.setRGB(x, y, (255 << 24) | ((int)color.x << 16) | ((int)color.y << 8) | ((int)color.z << 0));
			}
		}
		
		try 
		{
			File file = new File("RayTest.png");
			ImageIO.write(image, "png", file);
		}
		catch(IOException e) 
		{
			System.out.println("Unable to write image, sorry.");
		}
		
	}
}