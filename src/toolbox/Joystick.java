package toolbox;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.JOptionPane;

import org.lwjgl.input.Controller;


public class Joystick 
{
	private static Controller joystick = null;
	
	public static int BUTTON_A = 0;
	public static int BUTTON_X = 1;
	public static int BUTTON_B = 2;
	public static int BUTTON_Y = 3;
	public static int BUTTON_RB = 5;
	public static int BUTTON_LB = 4;
	public static int BUTTON_SELECT = 6;
	public static int BUTTON_START = 7;
	
	public static int STICK_LX = 1;
	public static float STICK_LX_SCALE = 1;
	public static int STICK_LY = 0;
	public static float STICK_LY_SCALE = 1;
	public static int STICK_RX = 3;
	public static float STICK_RX_SCALE = 1;
	public static int STICK_RY = 2;
	public static float STICK_RY_SCALE = 1;
	
	public static float STICK_LXDEADZONE = 0.1f;
	public static float STICK_LYDEADZONE = 0.1f;
	public static float STICK_RXDEADZONE = 0.1f;
	public static float STICK_RYDEADZONE = 0.1f;
	
	public static int TRIGGER_L = 4;
	public static float LT_NEUTRAL = 0;
	public static float LT_MAX = 1;
	public static float LT_RANGE = 1;
	public static int TRIGGER_R = 4;
	public static float RT_NEUTRAL = 0;
	public static float RT_MAX = -1;
	public static float RT_RANGE = -1;
	
	public static float TRIGGER_DEADZONE = 0.3f;
	
	private static boolean previousButtons[] = {false};
	private static boolean buttonsPressed[] = {false};
	
	private static int approxXLeft = 0;
	private static int approxXLeftPrevious = 0;
	private static int approxYLeft = 0;
	private static int approxYLeftPrevious = 0;
	
	public static void setJoystick(Controller newJoystick)
	{
		joystick = newJoystick; 
		buttonsPressed = new boolean[joystick.getButtonCount()];
		previousButtons = new boolean[joystick.getButtonCount()];//joystick.getButtonCount()
		//for(int i = 0; i < joystick.getButtonCount(); i++)
		{
			//System.out.println(joystick.getButtonName(i));
		}
	}
	
	public static void poll()
	{
		joystick.poll();
		int count = joystick.getButtonCount();
		//previousButtons[0] = buttonsPressed[0];
		//buttonsPressed[0] = joystick.isButtonPressed(0);
		//System.out.print(buttonsPressed[0]);
		for(int i = count-1; i >= 0; i--)
		{
			previousButtons[i] = buttonsPressed[i];
			buttonsPressed[i] = joystick.isButtonPressed(i);
			//if (buttonsPressed[i] && !previousButtons[i])
			{
				//System.out.println("button "+i+" pressed");
			}
			//if (buttonsPressed[i])
			{
				//System.out.println("	button "+i+" pressed");
			}
		}
		
		//for(int i = 0; i < count; i++)
		{
			//if (buttonsPressed[i])
			{
				//System.out.print("1");
			}
			//else
			{
				//System.out.print("0");
			}
		}
		//System.out.println();
		
		//System.out.println("      Z Axis = "+joystick.getZAxisValue());
		//System.out.println("Right Z Axis = "+joystick.getRZAxisValue());
		//System.out.println("Total Axis = "+joystick.getAxisCount());
		//System.out.println();
		
		approxXLeftPrevious = approxXLeft;
		approxXLeft = Math.round(getXLeft());
		approxYLeftPrevious = approxYLeft;
		approxYLeft = Math.round(getYLeft());
		
		//System.out.println("");
		//System.out.println();
	}
	
	public static boolean joystickExists()
	{
		return (joystick != null);
	}
	
	//public static void setDeadZone(float newThreshold)
	//{
		//joystick.setXAxisDeadZone(newThreshold);
		//joystick.setYAxisDeadZone(newThreshold);
		//joystick.setRXAxisDeadZone(newThreshold);
		//joystick.setRYAxisDeadZone(newThreshold);
		//STICK_DEADZONE = newThreshold;
	//}
	
	public static float getXLeft()
	{
		float x = joystick.getAxisValue(STICK_LX)*STICK_LX_SCALE;
		float y = joystick.getAxisValue(STICK_LY)*STICK_LY_SCALE;
		if (Math.abs(x) < STICK_LXDEADZONE) x = 0;
		if (Math.abs(y) < STICK_LYDEADZONE) y = 0;
		
		if(x+y == -2)
		{
			x = 0;
		}
		
		float mag = (float)Math.sqrt(x*x+y*y);
		if(mag > 1)
		{
			x = x/mag;
		}
		return x;
	}
	
	public static float getYLeft()
	{
		float x = joystick.getAxisValue(STICK_LX)*STICK_LX_SCALE;
		float y = joystick.getAxisValue(STICK_LY)*STICK_LY_SCALE;
		if (Math.abs(x) < STICK_LXDEADZONE) x = 0;
		if (Math.abs(y) < STICK_LYDEADZONE) y = 0;
		
		if(x+y == -2)
		{
			y = 0;
		}
		
		float mag = (float)Math.sqrt(x*x+y*y);
		if(mag > 1)
		{
			y = y/mag;
		}
		return y;
	}
	
	public static float getXRight()
	{
		float x = joystick.getAxisValue(STICK_RX)*STICK_RX_SCALE;
		float y = joystick.getAxisValue(STICK_RY)*STICK_RY_SCALE;
		if (Math.abs(x) < STICK_RXDEADZONE) x = 0;
		if (Math.abs(y) < STICK_RYDEADZONE) y = 0;
		
		if(x+y == -2.0f)
		{
			x = 0;
		}
		
		float mag = (float)Math.sqrt(x*x+y*y);
		if(mag > 1)
		{
			x = x/mag;
		}
		return x;
	}
	
	public static float getYRight()
	{
		float x = joystick.getAxisValue(STICK_RX)*STICK_RX_SCALE;
		float y = joystick.getAxisValue(STICK_RY)*STICK_RY_SCALE;
		if (Math.abs(x) < STICK_RXDEADZONE) x = 0;
		if (Math.abs(y) < STICK_RYDEADZONE) y = 0;
		
		if(x+y == -2.0f)
		{
			y = 0;
		}
		
		float mag = (float)Math.sqrt(x*x+y*y);
		if(mag > 1)
		{
			y = y/mag;
		}
		return y;
	}
	
	public static float getZAxis()
	{
		//return joystick.getZAxisValue();
		return 0;
	}
	
	public static float getLTrigger()
	{
		float rawValue = (joystick.getAxisValue(TRIGGER_L)-LT_NEUTRAL)/LT_RANGE;
		return (rawValue > TRIGGER_DEADZONE) ? rawValue : 0;
	}
	
	public static float getRTrigger()
	{
		float rawValue = (joystick.getAxisValue(TRIGGER_R)-RT_NEUTRAL)/RT_RANGE;
		return (rawValue > TRIGGER_DEADZONE) ? rawValue : 0;
	}
	
	//public static float getTrigger(int index)
	{
		//return joystick.getAxisValue(index);
	}
	
	public static boolean getIsButtonPressed(int buttonIndex)
	{
		return (joystickExists() && buttonsPressed[buttonIndex]);
	}
	
	public static boolean getButtonJustPressed(int buttonIndex)
	{
		return (joystickExists() && (buttonsPressed[buttonIndex] && !previousButtons[buttonIndex]));
	}
	
	public static int getYAxisApproxClicked()
	{
		return (approxYLeft != approxYLeftPrevious) ? approxYLeft : 0;
	}
	
	public static int getXAxisApproxClicked()
	{
		return (approxXLeft != approxXLeftPrevious) ? approxXLeft : 0;
	}
	
	public static float getDPadX()
	{
		return joystick.getPovX();
	}
	
	public static float getDPadY()
	{
		return joystick.getPovY();
	}
	
	public static void clearControllerButtons()
	{
		if (joystick == null) return;
		
		int count = joystick.getButtonCount();
		
		for (int i = count-1; i >= 0; i--)
		{
			previousButtons[i] = false;
			buttonsPressed[i] = false;
		}
	}
	
	public static void readSetupFile()
	{
		try
		{
			//InputStream is = null;
	    	InputStreamReader isr = null;
	    	try
	        {
	        	//is = Class.class.getResourceAsStream("/res/Controller/Mapping.txt");
	        	//if (is == null)
	        	//{
	        		//System.out.println("Couldnt load input stream: '/res/Controller/Mapping.txt'");
	        	//}
	        	//isr = new InputStreamReader(is);
	    		
	    		String fileName = "Data/ControllerConfig.ini";
	            FileInputStream inStream = new FileInputStream(new File(fileName));
	            isr = new InputStreamReader(inStream);
	        }
	        catch (NullPointerException e)
	        {
	        	System.out.println("Couldnt load input stream: 'Data/ControllerConfig.ini'");
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
					if (info[0].equals("A"))
					{
						BUTTON_A = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("B"))
					{
						BUTTON_B = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("X"))
					{
						BUTTON_X = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("Y"))
					{
						BUTTON_Y = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("Start"))
					{
						BUTTON_START = Integer.parseInt(info[1]);
					}
					else if (info[0].equals("Stick_LX"))
					{
						STICK_LX = Integer.parseInt(info[1]);
						STICK_LXDEADZONE = Float.parseFloat(info[2]);
						STICK_LX_SCALE = Float.parseFloat(info[3]);
					}
					else if (info[0].equals("Stick_LY"))
					{
						STICK_LY = Integer.parseInt(info[1]);
						STICK_LYDEADZONE = Float.parseFloat(info[2]);
						STICK_LY_SCALE = Float.parseFloat(info[3]);
					}
					else if (info[0].equals("Stick_RX"))
					{
						STICK_RX = Integer.parseInt(info[1]);
						STICK_RXDEADZONE = Float.parseFloat(info[2]);
						STICK_RX_SCALE = Float.parseFloat(info[3]);
					}
					else if (info[0].equals("Stick_RY"))
					{
						STICK_RY = Integer.parseInt(info[1]);
						STICK_RYDEADZONE = Float.parseFloat(info[2]);
						STICK_RY_SCALE = Float.parseFloat(info[3]);
					}
					else if (info[0].equals("Trigger_L"))
					{
						TRIGGER_L = Integer.parseInt(info[1]);
						LT_NEUTRAL = Float.parseFloat(info[2]);
						LT_MAX = Float.parseFloat(info[3]);
						LT_RANGE = LT_MAX-LT_NEUTRAL;
						TRIGGER_DEADZONE = Float.parseFloat(info[4]);
					}
					else if (info[0].equals("Trigger_R"))
					{
						TRIGGER_R = Integer.parseInt(info[1]);
						RT_NEUTRAL = Float.parseFloat(info[2]);
						RT_MAX = Float.parseFloat(info[3]);
						RT_RANGE = RT_MAX-RT_NEUTRAL;
						TRIGGER_DEADZONE = Float.parseFloat(info[4]);
					}
				}
			}
			
			reader.close();
		}
		catch (Exception e)
		{
			System.out.println("Problem when trying to read 'Data/ControllerConfig.ini'");
			e.printStackTrace();
			System.out.println("Going to use the default button mappings instead...");
		}
	}
}
