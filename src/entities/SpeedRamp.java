package entities;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

public class SpeedRamp extends CollideableObject
{
	private static CollisionModel cmOriginal = null;
	private static TexturedModel[] modelRamp = null;
	private float power;
	
	public SpeedRamp(Vector3f position, float rotY, float rotZ, float power)
	{
		super(modelRamp, cmOriginal, position, 0.0f, rotY, rotZ, 1);
		updateCollisionModelWithZ();
		CollisionChecker.addCollideModel(collideTransformed);
		this.power = power;
	}
	
	@Override
	public void step()
	{
		super.step();
		
		if (Math.abs(getX()-MainGameLoop.gameCamera.getX()) > MainGameLoop.ENTITY_RENDER_DIST)
		{
			setVisibility(false);
		}
		else
		{
			if (Math.abs(getZ()-MainGameLoop.gameCamera.getZ()) > MainGameLoop.ENTITY_RENDER_DIST)
			{
				setVisibility(false);
			}
			else
			{
				setVisibility(true);
				
				if (collideTransformed.playerIsOn)
				{
					float dx = (float)(power*Math.cos(Math.toRadians(getRotY())));
					float dz = (float)(power*Math.sin(Math.toRadians(getRotY())));
					player.increaseGroundSpeed(dx, dz);
				}
			}
		}
	}
	
	public static void allocateStaticModels()
	{
		if (modelRamp == null)
		{
			modelRamp = ConvenientMethods.loadModel("Models/Ramp/", "Ramp");
		}
		if (cmOriginal == null)
		{
			cmOriginal = OBJFileLoader.loadCollisionOBJ("Models/Ramp/", "Ramp");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelRamp != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelRamp);
			modelRamp = null;
		}
		cmOriginal = null;
	}
}
