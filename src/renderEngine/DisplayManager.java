package renderEngine;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Controllers;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.PixelFormat;
import org.lwjgl.opengl.ContextAttribs;

public class DisplayManager 
{
	private static int WIDTH = 160*8; //1280
	private static int HEIGHT = 90*8; //720
	private static final int FPS_CAP = 60;
	private static int SAMPLES = 4;
	private static boolean VSYNC = false;
	private static boolean FULLSCREEN = false;
	
	private static long lastFrameTime;
	private static long delta;
	
	private static int F_Width = 1920;
	private static int F_Height = 1080;
	private static int F_BPP = 32;
	private static int F_Frequency = 60;
	
	
	public static void createDisplay()
	{
		loadDataFile();
		/*
		ContextAttribs attribs = 
				new ContextAttribs(3,2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		
		try 
		{
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			//PixelFormat(Alpha Bits, Depth Bits, Stencil Bits, Samples)
			Display.create(new PixelFormat(8, 8, 0, 8), attribs);
			Display.setTitle("");
			Display.setVSyncEnabled(false);
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
		}
		*/
		//ContextAttribs attribs = 
		//		new ContextAttribs(3, 2)
		//		.withForwardCompatible(true)
		//		.withProfileCore(true);
		
		try 
		{
			DisplayMode[] modes = Display.getAvailableDisplayModes();
			String[] names = new String[modes.length];
			
			int defaultIndex = 0;
			 
			for (int i=0; i < modes.length; i++)
			{
			    DisplayMode current = modes[i];
			    //System.out.println(current.getWidth() + "x" + current.getHeight() + "x" +
			    //                     current.getBitsPerPixel() + " " + current.getFrequency() + "Hz");
			    
			    names[i] = (current.getWidth() + "x" + current.getHeight() + "x" +
                        current.getBitsPerPixel() + " " + current.getFrequency() + "Hz");
			    
			    if (current.getWidth() == F_Width &&
			    	current.getHeight() == F_Height &&
			    	current.getBitsPerPixel() == F_BPP &&
			    	current.getFrequency() == F_Frequency)
			    {
			    	defaultIndex = i;
			    }
			}
			
			
			
			if (FULLSCREEN)
			{
				String modeToUse = (String) JOptionPane.showInputDialog(null,
				        "Select Fullscreen Display Mode:",
				        "Display Mode",
				        JOptionPane.QUESTION_MESSAGE,
				        null,
				        names,
				        names[defaultIndex]);
				
				if (modeToUse == null)
				{
					Display.setFullscreen(false);
					Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
				}
				else
				{
					for (int i = 0; i < names.length; i++)
					{
						if (modeToUse.equals(modes[i].getWidth() + "x" + modes[i].getHeight() + "x" +
								modes[i].getBitsPerPixel() + " " + modes[i].getFrequency() + "Hz"))
						{
							Display.setFullscreen(true);
							Display.setDisplayModeAndFullscreen(modes[i]);
							break;
						}
					}
				}
			}
			else
			{
				Display.setFullscreen(false);
				Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			}
			
			if (VSYNC)
			{
				Display.setVSyncEnabled(true);
			}
			else
			{
				Display.setVSyncEnabled(false);
			}
			//PixelFormat(Alpha Bits, Depth Bits, Stencil Bits, Samples)
			//Display.create(new PixelFormat(8, 8, 0, 8), attribs); //makes shadows work for piner
			Display.create(new PixelFormat().withDepthBits(24));
			//Display.create(new PixelFormat(), attribs);
			//Display.create();
			Display.setTitle("version 0.0009");
			System.out.println("Using OpenGL version "+GL11.glGetString(GL11.GL_VERSION));
			GL11.glEnable(GL13.GL_MULTISAMPLE);
			//Display.setLocation(2100, 64);
		} 
		catch (LWJGLException e) 
		{
			e.printStackTrace();
		}
		
		GL11.glViewport(0, 0, WIDTH, HEIGHT);
		lastFrameTime = getCurrentTime();
	}
	
	public static void updateDisplay()
	{
		Display.sync(FPS_CAP);
		Display.update();
		long currentFrameTime = getCurrentTime();
		delta = (currentFrameTime - lastFrameTime);
		lastFrameTime = currentFrameTime;
		//System.out.println((delta*60)/(1000.0));
		//System.out.println(Sys.getTimerResolution());
	}
	
	public static float getFrameTimeSeconds()
	{
		return delta;
	}
	
	public static double getFactor()
	{
		return (delta*60)/(1000.0);
	}
	
	public static void closeDisplay()
	{
		Display.destroy();
	}
	
	public static int getWidth()
	{
		return WIDTH;
	}
	
	public static int getHeight()
	{
		return HEIGHT;
	}
	
	public static int getSamples()
	{
		return SAMPLES;
	}
	
	//In milliseconds
	private static long getCurrentTime()
	{
		return (Sys.getTime()*1000)/Sys.getTimerResolution();
	}
	
	private static void loadDataFile()
	{
		try
		{
	    	InputStreamReader isr = null;
	    	try
	        {
	    		String fileName = "Data/DisplaySettings.ini";
	            FileInputStream inStream = new FileInputStream(new File(fileName));
	            isr = new InputStreamReader(inStream);
	        }
	        catch (NullPointerException e)
	        {
	        	System.out.println("Couldnt load input stream: 'Data/DisplaySettings.ini'");
	        	e.printStackTrace();
	            return;
	        }
	    	
	        BufferedReader breader = new BufferedReader(isr);
			
			Scanner reader = new Scanner(breader);
			
			boolean loop = true;
			while (loop)
			{
				if (!reader.hasNextLine())
				{
					loop = false;
					break;
				}
				String line = reader.nextLine();
				if (line == null)
				{
					loop = false;
					break;
				}
				String[] info = line.split(" ");
				if (info.length >= 2)
				{
					if (info[0].equals("Width"))
					{
						WIDTH = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("Height"))
					{
						HEIGHT = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("Anti-Aliasing_Samples"))
					{
						SAMPLES = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("F_Width"))
					{
						F_Width = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("F_Height"))
					{
						F_Height = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("F_BitsPerPixel"))
					{
						F_BPP = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("F_Frequency"))
					{
						F_Frequency = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("VSync"))
					{
						if (info[1].equals("on"))
						{
							VSYNC = true;
						}
						else
						{
							VSYNC = false;
						}
					}
					else if (info[0].equals("Fullscreen"))
					{
						if (info[1].equals("on"))
						{
							FULLSCREEN = true;
						}
						else
						{
							FULLSCREEN = false;
						}
					}
				}
			}
			
			reader.close();
		}
		catch (Exception e)
		{
			System.out.println("Problem when trying to read 'Data/DisplaySettings.ini'");
			e.printStackTrace();
		}
	}
}
