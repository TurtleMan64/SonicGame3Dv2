package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.MovingPlatform;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

public class WFMovingFloor extends MovingPlatform
{
	private static TexturedModel[] modelPost = null;//ConvenientMethods.loadModel("Models/WF/", "MovingFloor", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "MovingFloor");
	private int timer;
	private int sign;
	
	public WFMovingFloor(Vector3f position) 
	{
		super(modelPost, cmOriginal, position);
		timer = 0;
		sign = 1;
	}
	
	@Override
	public void step()
	{
		super.step();
		if(timer < 0)
		{
			timer++;
			if(timer == 0)
			{
				timer = 90;
				sign = -sign;
			}
		}
		else if(timer > 0)
		{
			timer--;
			increasePosition(0.5f*sign, 0, 0);
		}
		else
		{
			timer  = -(int)(Math.floor(Math.random()*120)+30);
		}
		
		super.updateCMJustPosition();
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		timer = 0;
		sign = 1;
	}
}
