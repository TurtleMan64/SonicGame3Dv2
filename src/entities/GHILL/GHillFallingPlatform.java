package entities.GHILL;

import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.CollideableObject;

public class GHillFallingPlatform extends CollideableObject
{
	private static CollisionModel cmOriginal = null;
	private static TexturedModel[] modelPlatform = null;
	private int timer;
	
	public GHillFallingPlatform(Vector3f position, float rotation, float scale)
	{
		super(modelPlatform, cmOriginal, position, 0.0f, rotation, 0.0f, scale);
		updateCollisionModel();
		CollisionChecker.addCollideModel(collideTransformed);
		timer = 24;
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
				if (collideTransformed.playerIsOn && timer == 24)
				{
					timer = 23;
				}
				
				if (timer < 24)
				{
					timer--;
				}
				
				if (timer == 0)
				{
					AudioSources.play(31, getPosition());
				}
				
				if (timer < 0)
				{
					increasePosition(0, timer/13f, 0);
					updateCollisionModel();
				}
				
				if (timer == -240)
				{
					CollisionChecker.removeCollideModel(collideTransformed);
				}
				
				if (timer < -600)
				{
					MainGameLoop.gameEntitiesToDelete.add(this);
				}
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

