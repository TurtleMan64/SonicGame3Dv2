package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.MovingPlatform;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class WFFaceBlockSmall extends MovingPlatform
{
	private static TexturedModel[] modelPost = null;//ConvenientMethods.loadModel("Models/WF/", "FaceBlockSmall", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "FaceBlockSmall");
	private int timer;
	private int sign;
	
	public WFFaceBlockSmall(Vector3f position) 
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
				timer = 100;
				sign = -sign;
				AudioRes.playSound(42, 1, getPosition());
			}
		}
		else if(timer > 0)
		{
			timer--;
			increasePosition(0.375f*sign, 0, 0);
		}
		else
		{
			timer  = -(int)(Math.floor(Math.random()*120)+30);
		}
		
		updateCMJustPosition();
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		timer = 0;
		sign = 1;
	}
}
