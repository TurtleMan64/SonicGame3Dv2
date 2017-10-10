package toolbox;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import engineTester.MainGameLoop;
import renderEngine.DisplayManager;

public class Input
{
	public static boolean jumpInput = false;
	public static boolean actionInput = false;
	public static boolean action2Input = false;
	public static boolean shoulderInput = false;
	public static boolean selectInput = false;
	public static boolean specialInput = false;
	public static boolean previousJumpInput = false;
	public static boolean previousActionInput = false;
	public static boolean previousAction2Input = false;
	public static boolean previousShoulderInput = false;
	public static boolean previousSelectInput = false;
	public static boolean previousSpecialInput = false;
	
	public static boolean startInput = false;
	public static boolean previousStartInput = false;
	
	public static int zoomInput = 0;
	
	public static float movementInputX = 0;
	public static float movementInputY = 0;
	
	public static float cameraInputX = 0;
	public static float cameraInputY = 0;
	
	public static float mousePreviousX = 0;
	public static float mousePreviousY = 0;
	
	public static void init()
	{
		
	}
	
	public static void refresh()
	{
		previousJumpInput = jumpInput;
		previousActionInput = actionInput;
		previousAction2Input = action2Input;
		previousShoulderInput = shoulderInput;
		previousSelectInput = selectInput;
		previousSpecialInput = specialInput;
		
		previousStartInput = startInput;
		
		jumpInput = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_A)))
		{
			jumpInput = true;
		}
		
		actionInput = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_C) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_B)))
		{
			actionInput = true;
		}
		
		action2Input = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_F) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_X)))
		{
			action2Input = true;
		}
		
		shoulderInput = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_COMMA) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_LB)))
		{
			shoulderInput = true;
		}
		
		selectInput = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_TAB)) //|| (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_SELECT)))
		{
			selectInput = true;
		}
		
		specialInput = false;
		if (Keyboard.isKeyDown(Keyboard.KEY_X) || (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_Y)))
		{
			specialInput = true;
		}
		
		startInput = false;
		if (Joystick.joystickExists() && Joystick.getIsButtonPressed(Joystick.BUTTON_START))
		{
			startInput = true;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RETURN))
		{
			startInput = true;
		}
		
		movementInputX = 0;
		movementInputY = 0;
		if (Keyboard.isKeyDown(Keyboard.KEY_W))
		{
			movementInputY = -1;
		}
		else
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_S))
			{
				movementInputY = 1;
			}
			else
			{
				movementInputY = 0;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_A))
		{
			movementInputX = -1;
		}
		else
		{
			if (Keyboard.isKeyDown(Keyboard.KEY_D))
			{
				movementInputX = 1;
			}
			else
			{
				movementInputX = 0;
			}
		}
		
		if (Math.abs(movementInputX*movementInputY) == 1)
		{
			movementInputX = (float)(movementInputX*0.70710678118);
			movementInputY = (float)(movementInputY*0.70710678118);
		}
		
		if ((Joystick.joystickExists() && Joystick.getXLeft() != 0) || (Joystick.joystickExists() && Joystick.getYLeft() != 0))
		{
			movementInputX = Joystick.getXLeft();
			movementInputY = Joystick.getYLeft();
		}
		
		cameraInputY = 0;
		cameraInputX = 0;
		if (MainGameLoop.freeMouse == false)
		{
			cameraInputY += -0.25f*(Mouse.getY()-mousePreviousY);
			cameraInputX += 0.25f*(Mouse.getX()-mousePreviousX);
			Mouse.setCursorPosition(DisplayManager.getWidth()/2, DisplayManager.getHeight()/2);
			mousePreviousX = Mouse.getX();
			mousePreviousY = Mouse.getY();
		}
		
		if ((Joystick.joystickExists() && Joystick.getXRight() != 0) || (Joystick.joystickExists() && Joystick.getYRight() != 0))
		{
			cameraInputY += 2.5f*(Joystick.getYRight());
			cameraInputX += 2.5f*(Joystick.getXRight());
		}
		
		if (Joystick.joystickExists())
		{
			cameraInputX += 2*(Joystick.getLTrigger()-Joystick.getRTrigger());
		}
		
		if (Mouse.hasWheel())
		{
			zoomInput = Mouse.getDWheel()/10;
		}
		if (Joystick.joystickExists())
		{
			zoomInput+=Joystick.getDPadY();
		}
	}
}
