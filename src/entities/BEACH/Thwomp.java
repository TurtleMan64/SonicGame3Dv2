package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.MovingPlatform;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class Thwomp extends MovingPlatform
{
	private static TexturedModel[] modelBody = null;//ConvenientMethods.loadModel("Models/Thwomp/", "Thwomp", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/Thwomp/", "Thwomp");
	
	private int timer;
	private int timerOffset;
	
	public Thwomp(Vector3f position, float yRot, int timerOffset) 
	{
		super(modelBody, cmOriginal, position);
		setSpawnRotY(yRot);
		timer = timerOffset;
		this.timerOffset = timerOffset;
	}
	
	@Override
	public void step()
	{
		super.step();
		
		timer = (timer+1)%330;
		
		if(timer < 90)
		{
			if(timer == 0)
			{
				AudioRes.playSound(43, 1, getPosition());
			}
			setPosition(getSpawnPosition());
			yVel = 0;
		}
		else if(timer < 210)
		{
			yVel = 0.25f;
		}
		else if(timer < 307)
		{
			yVel = 0;
		}
		else
		{
			yVel-=0.1f;
			
			//if(player.getY()+player.getHeadHeight() > getY() && player.getY() < getY()+5 &&
			//   player.getX() > getX()-16 && player.getX() < getX()+16 &&
			//   player.getZ() > getZ()-16 && player.getZ() < getZ()+16)
			//{
				//player.takeDamage(getPosition(), 3);
			//}
		}
		
		increasePosition(xVel, yVel, zVel);
		updateCollisionModel();
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		timer = timerOffset;
	}
}
