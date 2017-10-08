package entities.GHILL;

import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.CollideableObject;

public class GHillTotem extends CollideableObject
{
	private static CollisionModel cmOriginal = null;
	private static TexturedModel[] modelTotem = null;
	
	public GHillTotem(Vector3f position, float rotation, float scale)
	{
		super(modelTotem, cmOriginal, position, 0.0f, rotation, 0.0f, scale);
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
		if (modelTotem == null)
		{
			modelTotem = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillTotem");
		}
		if (cmOriginal == null)
		{
			cmOriginal = OBJFileLoader.loadCollisionOBJ("Models/GreenHillZone/", "GreenHillTotemCollision");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelTotem != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelTotem);
			modelTotem = null;
		}
		cmOriginal = null;
	}
}

