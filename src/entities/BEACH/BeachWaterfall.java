package entities.BEACH;

import models.TexturedModel;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Entity;

public class BeachWaterfall extends Entity
{	
	private static TexturedModel[] modelWaterfall = null;
	
	public BeachWaterfall(Vector3f position, float rotation, float scale)
	{
		super(modelWaterfall, position, 0.0f, rotation, 0.0f, scale);
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
		if (modelWaterfall == null)
		{
			modelWaterfall = ConvenientMethods.loadModel("Models/EmeraldCoast/", "WaterfallDouble");
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

