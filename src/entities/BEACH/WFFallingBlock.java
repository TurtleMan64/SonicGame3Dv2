package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.CollideableObject;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class WFFallingBlock extends CollideableObject
{
	private static TexturedModel[] modelPost = null;//ConvenientMethods.loadModel("Models/WF/", "FallingBlock", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "FallingBlock");
	private int timer;
	
	public WFFallingBlock(Vector3f position) 
	{
		super(modelPost, cmOriginal, position);
		timer = 31;
	}
	
	@Override
	public void step()
	{
		if(timer < 31 && timer > -1000)
		{
			timer -= 1;
		}
		if(collideTransformed.playerIsOn && timer == 31)
		{
			timer = 30;
		}
		if(timer < 0 && timer > -100)
		{
			increasePosition(0, timer/10f, 0);
		}
		if(timer == -1000)
		{
			setPosition(getSpawnPosition());
			timer = 31;
		}
		if(timer == 0)
		{
			AudioRes.playSound(54, 1, getPosition());
		}
		updateCMJustPosition();
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		timer = 31;
	}
}
