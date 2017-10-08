package entities.BEACH;

import models.TexturedModel;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Entity;

public class KoopaBeachWaterfall extends Entity
{	
	private static TexturedModel[] modelWaterfall = null;
	
	public KoopaBeachWaterfall()
	{
		super(modelWaterfall, new Vector3f(0,0,0));
	}
	
	@Override
	public void step()
	{
		//if (Math.abs(getX()-MainGameLoop.gameCamera.getX()) > MainGameLoop.ENTITY_RENDER_DIST)
		{
			//setVisibility(false);
		}
		//else
		{
			//if (Math.abs(getZ()-MainGameLoop.gameCamera.getZ()) > MainGameLoop.ENTITY_RENDER_DIST)
			{
				//setVisibility(false);
			}
			//else
			{
				setVisibility(true);
			}
		}
	}
	
	public static void allocateStaticModels()
	{
		if (modelWaterfall == null)
		{
			modelWaterfall = ConvenientMethods.loadModel("Models/KoopaTroopaBeach/", "KoopaBeachWaterfall");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelWaterfall != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelWaterfall);
			modelWaterfall = null;
		}
	}
}

