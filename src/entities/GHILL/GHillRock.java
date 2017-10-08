package entities.GHILL;

import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.CollideableObject;

public class GHillRock extends CollideableObject
{
	private static CollisionModel cmOriginal = null;
	private static TexturedModel[] modelRock = null;
	
	public GHillRock(Vector3f position, float rotation, float scale)
	{
		super(modelRock, cmOriginal, position, 0.0f, rotation, 0.0f, scale);
		updateCollisionModel();
		CollisionChecker.addCollideModel(collideTransformed);
	}
	
	@Override
	public void step()
	{
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
		if (modelRock == null)
		{
			modelRock = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillRock");
		}
		if (cmOriginal == null)
		{
			cmOriginal = OBJFileLoader.loadCollisionOBJ("Models/GreenHillZone/", "GreenHillRockCollision");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelRock != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelRock);
			modelRock = null;
		}
		cmOriginal = null;
	}
}

