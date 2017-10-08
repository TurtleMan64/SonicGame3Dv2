package entities.GHILL;

import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.CollideableObject;

public class GHillFloatingPlatform extends CollideableObject
{
	private static CollisionModel cmOriginal = null;
	private static TexturedModel[] modelPlatform = null;
	
	public GHillFloatingPlatform(Vector3f position, float rotation, float scale)
	{
		super(modelPlatform, cmOriginal, position, 0.0f, rotation, 0.0f, scale);
		updateCollisionModel();
		CollisionChecker.addCollideModel(collideTransformed);
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
			}
		}
	}
	
	public static void allocateStaticModels()
	{
		if (modelPlatform == null)
		{
			modelPlatform = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillFallingPlatform");
		}
		if (cmOriginal == null)
		{
			cmOriginal = OBJFileLoader.loadCollisionOBJ("Models/GreenHillZone/", "GreenHillFallingPlatform");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelPlatform != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelPlatform);
			modelPlatform = null;
		}
		cmOriginal = null;
	}
}

