package entities.BEACH;

import models.TexturedModel;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Entity;

public class BeachUmbrella extends Entity
{	
	private static TexturedModel[] modelUmbrella1 = null;
	private static TexturedModel[] modelUmbrella2 = null;
	
	public BeachUmbrella(Vector3f position, float xrot, float yrot, float zrot, float type)
	{
		super(position, xrot, yrot, zrot, 0.8f);
		if (type != 0)
		{
			setModels(modelUmbrella1);
		}
		else
		{
			setModels(modelUmbrella2);
		}
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
		if (modelUmbrella1 == null)
		{
			modelUmbrella1 = ConvenientMethods.loadModel("Models/EmeraldCoast/", "Umbrella");
		}
		if (modelUmbrella2 == null)
		{
			modelUmbrella2 = ConvenientMethods.loadModel("Models/EmeraldCoast/", "Umbrella2");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelUmbrella1 != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelUmbrella1);
			modelUmbrella1 = null;
		}
		if (modelUmbrella2 != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelUmbrella2);
			modelUmbrella2 = null;
		}
	}
}

