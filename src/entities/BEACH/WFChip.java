package entities.BEACH;
import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.CollideableObject;
import entities.Player;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

public class WFChip extends CollideableObject
{	
	private static CollisionModel cmOriginal1 = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "Chip1");
	private static TexturedModel[] models1 = null;//ConvenientMethods.loadModel("Models/WF/", "Chip1", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal2 = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "Chip2");
	private static TexturedModel[] models2 = null;//ConvenientMethods.loadModel("Models/WF/", "Chip2", MainGameLoop.gameLoader);
	private boolean index;
	private boolean destroyed;
	
	public WFChip(Vector3f position, boolean index) 
	{
		super(index ? models1 : models2, index ? cmOriginal1 : cmOriginal2, position);
		this.index = index;
		destroyed = false;
	}

	@Override
	public void step()
	{
		if(collideTransformed.playerIsOn)
		{
			//if(player is shooting out of a canon)
			//if(player.getPreviousSpeed() > 2)
			{
				destroyed = true;
				CollisionChecker.removeCollideModel(collideTransformed);
				setVisibility(false);
			}
		}
		
		updateCMJustPosition();
	}
	
	
	@Override
	public void respawn()
	{
		//super.respawn();
		//add collision model to list
		CollisionChecker.addCollideModel(collideTransformed);
		setVisibility(true);
		destroyed = false;
	}
	
	@Override
	public void despawn()
	{
		//super.despawn();
		//remove collision model from list
		if(!destroyed)
		{
			CollisionChecker.removeCollideModel(collideTransformed);
		}
		setVisibility(false);
	}
}
