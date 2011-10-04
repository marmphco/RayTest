import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import java.util.ArrayList;

public class RayTest
{
	public static void main(String[] args) throws IOException
	{
		int width = 640, height = 480;
		double aspect = (double)width / (double)height;
		
		Vector3 camerapos = new Vector3(0, 0, -5);
		Vector3 lightpos = new Vector3(0, -10, 0);
		double planeSize = 5;
		
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
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
					
					Vector3 intersection = r.intersectionWithRay(cameradir, camerapos);
					if(new Double(intersection.x).isNaN()) 
					{
						
					}
					else
					{
						double distance = lightpos.subtract(intersection).magnitude();
						double attenuation = 4.0 / (1.0 + 0.1 * distance + 0.05 * distance * distance);
						
						Vector3 light = lightpos.subtract(intersection).normalize();
						Vector3 camera = camerapos.subtract(intersection).normalize();
						Vector3 halfv = light.add(camera).normalize();
						Vector3 normal = r.normalAtPoint(intersection);
						
						double dvalue = Math.max(0.0, light.dot(normal)) * attenuation;
						double svalue = Math.pow(Math.max(0.0, halfv.dot(normal)), 50.0);
						
						color = new Vector3(svalue * 127 + dvalue * 128);
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