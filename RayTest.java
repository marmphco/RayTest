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
		//Vector3 lightpos = new Vector3(0, -10, 0);
        //PointLight light = new PointLight(new Vector3(0, -10, 0), 100);
        
		double planeSize = 5;
		
		BufferedImage buffer = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		ArrayList<RenderObject> renderObjects = new ArrayList<RenderObject>();
		renderObjects.add(new Sphere(new Vector3(1, 1 , 10), 5));
        renderObjects.add(new Sphere(new Vector3(-5, -4 , 7), 3.5));
		renderObjects.add(new Sphere(new Vector3(0, -1 , 15), 5));
        
        ArrayList<Light> lights = new ArrayList<Light>();
        lights.add(new PointLight(new Vector3(0, -10, 0), 100));
        lights.add(new PointLight(new Vector3(5, 10, 0), 100)); //this light is causing some interesting specular artifacts
		
		double[][] depthBuffer = new double[width][height];
		 
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				double xPos = (double)(x - width/2) * aspect * planeSize / (double)(width/2);
				double yPos = (double)(y - height/2) * planeSize / (double)(height/2);
				Vector3 cameradir = new Vector3(xPos, yPos, 0).subtract(camerapos).normalize();
				depthBuffer[x][y] = Double.MAX_VALUE;
				
				for(RenderObject object : renderObjects)
				{
					double depth = object.intersectionDepth(cameradir, camerapos);
					if (depth > 0) {depthBuffer[x][y] = Math.min(depthBuffer[x][y], depth);}
				}
			}
		}
		
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				double xPos = (double)(x - width/2) * aspect * planeSize / (double)(width/2);
				double yPos = (double)(y - height/2) * planeSize / (double)(height/2);
				Vector3 cameradir = new Vector3(xPos, yPos, 0).subtract(camerapos).normalize();
				Vector3 color = new Vector3();
				
				for(RenderObject object : renderObjects)
				{
					
					double depth = object.intersectionDepth(cameradir, camerapos);
					Vector3 intersection = camerapos.add(cameradir.scale(depth));
					if(depth > 0) 
					{
                        for(Light light : lights)
                        {
                            //double attenuation = 4.0 / (1.0 + 0.1 * distance + 0.05 * distance * distance); //attenutators should be placed in lights
							if (depth <= depthBuffer[x][y] + 0.01)
							{
								Vector3 lightDirection = light.directionAtPoint(intersection).negative().normalize(); //points away from surface
								Vector3 cameraDirection = camerapos.subtract(intersection).normalize();
								Vector3 halfv = lightDirection.add(cameraDirection).normalize();
								Vector3 normal = object.normalAtPoint(intersection);
                            
								double dvalue = Math.max(0.0, lightDirection.dot(normal)) * light.intensityAtPoint(intersection);//attenuation;
								double svalue = Math.pow(Math.max(0.0, halfv.dot(normal)), 50.0);
                            
								color = color.add(new Vector3(svalue * 127 + dvalue * 128));
							}
                        }
						color.x = Math.max(0, Math.min(255, color.x));
						color.y = Math.max(0, Math.min(255, color.y));
						color.z = Math.max(0, Math.min(255, color.z));
					}
				
				}
				
				buffer.setRGB(x, y, (255 << 24) | ((int)color.x << 16) | ((int)color.y << 8) | ((int)color.z << 0));
			}
		}
		
		saveImage(buffer, "RayTest.png");
		
	}
	
	public static boolean saveImage(BufferedImage image, String fileName)
	{	
		try 
		{
			File file = new File(fileName);
			ImageIO.write(image, "png", file);
			return true;
		}
		catch(IOException e) 
		{
			System.out.println("Unable to write image, sorry.");
			return false;
		}
	}
	
}