package entities.GHILL;


import models.TexturedModel;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Entity;

public class GHillGrass extends Entity
{	
	private static TexturedModel[] model = null;
	
	public GHillGrass(Vector3f position, float rotation, float scale)
	{
		super(model, position, 0.0f, rotation, 0.0f, scale);
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
		if (model == null)
		{
			model = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillGrass");
		}
	}
	
	public static void freeStaticModels()
	{
		if (model != null)
		{
			MainGameLoop.gameLoader.deleteModel(model);
			model = null;
		}
	}
}


