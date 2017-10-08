package entities;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

public class WoodPost extends CollideableObject
{
	private static TexturedModel[] modelPost = null;//ConvenientMethods.loadModel("Models/Post/", "Post", MainGameLoop.gameLoader);
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/Post/", "Post");
	//private int timer;
	
	public WoodPost(Vector3f position) 
	{
		super(modelPost, cmOriginal, position);
		//timer = 0;
	}
	
	@Override
	public void step()
	{
		//timer = Math.max(0, timer-1);
		//if(timer == 0)
		{
			//if(player.getIsGroundPounding() && collideTransformed.playerIsOn && player.getyVel() < 0)
			{
				increasePosition(0, -4, 0);
				//timer = 60;
			}
		}
		updateCMJustPosition();
	}
}
