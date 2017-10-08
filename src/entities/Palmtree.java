package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

import toolbox.ConvenientMethods;
import engineTester.MainGameLoop;

public class Palmtree extends Entity
{
	private static TexturedModel[] palmtreeModel = null;
	
	public Palmtree(Vector3f position, float xrot, float yrot, float zrot, float scale) 
	{
		super(palmtreeModel, position, xrot, yrot, zrot, scale);
	}
	
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
		if (palmtreeModel == null)
		{
			palmtreeModel = ConvenientMethods.loadModel("Models/Palmtree/", "Palmtree");
		}
	}
	
	public static void freeStaticModels()
	{
		if (palmtreeModel != null)
		{
			MainGameLoop.gameLoader.deleteModel(palmtreeModel);
			palmtreeModel = null;
		}
	}
}
