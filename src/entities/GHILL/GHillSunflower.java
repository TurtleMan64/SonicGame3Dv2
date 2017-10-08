package entities.GHILL;

import models.TexturedModel;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Entity;

public class GHillSunflower extends Entity
{	
	private static TexturedModel[] modelTop = null;
	private static TexturedModel[] modelTop2 = null;
	private static TexturedModel[] modelBase = null;
	
	private Entity petals;
	private int timer = 0;
	
	public GHillSunflower(Vector3f position, float rotation, float scale)
	{
		super(modelBase, position, 0.0f, rotation, 0.0f, scale);
		petals = new Entity(modelTop, new Vector3f(getX(), getY()+25, getZ()), 0, rotation, 0, scale);
		MainGameLoop.gameEntitiesToAdd.add(petals);
	}
	
	@Override
	public void step()
	{
		if (Math.abs(getX()-MainGameLoop.gameCamera.getX()) > MainGameLoop.ENTITY_RENDER_DIST)
		{
			setVisibility(false);
			petals.setVisibility(false);
		}
		else
		{
			if (Math.abs(getZ()-MainGameLoop.gameCamera.getZ()) > MainGameLoop.ENTITY_RENDER_DIST)
			{
				setVisibility(false);
				petals.setVisibility(false);
			}
			else
			{
				setVisibility(true);
				petals.setVisibility(true);

				if ((timer/20) %2 == 0)
				{
					petals.setModels(modelTop);
				}
				else
				{
					petals.setModels(modelTop2);
				}
			}
		}
		timer++;
	}
	
	public static void allocateStaticModels()
	{
		if (modelTop == null)
		{
			modelTop = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillSunflowerTop");
		}
		if (modelTop2 == null)
		{
			modelTop2 = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillSunflowerTop2");
		}
		if (modelBase == null)
		{
			modelBase = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillSunflowerBase");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelTop != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelTop);
			modelTop = null;
		}
		if (modelTop2 != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelTop2);
			modelTop2 = null;
		}
		if (modelBase != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelBase);
			modelBase = null;
		}
	}
}

