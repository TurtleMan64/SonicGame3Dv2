package entities.GHILL;

import models.TexturedModel;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Entity;

public class GHillFlower extends Entity
{	
	private static TexturedModel[] modelTop = null;
	private static TexturedModel[] modelBase = null;
	
	private Entity petals;
	private int timer = 0;
	
	public GHillFlower(Vector3f position, float rotation, float scale)
	{
		super(modelBase, position, 0.0f, rotation, 0.0f, scale);
		petals = new Entity(modelTop, new Vector3f(position), 0, rotation, 0, scale);
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
				int val = timer % 340;
				/*
				if (val < 120)
				{
					petals.setY(getY());
				}
				else if (val < 160)
				{
					petals.setY(getY()+(val-120)/13.3333f);
				}
				else if (val < 300)
				{
					petals.setY(getY()+3);
				}
				else
				{
					petals.setY(getY()+3-(val-300)/13.3333f);
				}
				*/
				if (val == 0)
				{
					petals.setY(getY());
				}
				else if (val == 120 || val == 300)
				{
					petals.setY(getY()+1.5f);
				}
				else if (val == 160)
				{
					petals.setY(getY()+3f);
				}
			}
		}
		timer++;
	}
	
	public static void allocateStaticModels()
	{
		if (modelTop == null)
		{
			modelTop = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillPFlowerTop");
		}
		if (modelBase == null)
		{
			modelBase = ConvenientMethods.loadModel("Models/GreenHillZone/", "GreenHillPFlowerBase");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelTop != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelTop);
			modelTop = null;
		}
		if (modelBase != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelBase);
			modelBase = null;
		}
	}
}

