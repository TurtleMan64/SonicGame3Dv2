package entities.BEACH;

import models.TexturedModel;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import entities.Ball;
import entities.Entity;

public class BeachFlatWater extends Entity
{	
	private static TexturedModel[] modelWater = null;
	
	public BeachFlatWater()
	{
		super(modelWater, new Vector3f(0,0,0));
	}
	
	@Override
	public void step()
	{
		if (MainGameLoop.gameCamera.getZ() > 2000)
		{
			setVisibility(true);
			Ball player = MainGameLoop.gamePlayer;
			
			if (player.getX() > -721.26 && player.getX() < 378.71 &&
				player.getZ() > 4567.68 && player.getZ() < 5667.667 &&
				player.getY() < 576)
			{
				player.setInWater(581);
			}
			else if (player.getX() > 420.37 && player.getX() < 1212.35 &&
					player.getZ() > 5713 && player.getZ() < 6505 &&
					player.getY() < 586)
			{
				player.setInWater(590);
			}
		}
		else
		{
			setVisibility(false);
		}
	}
	
	public static void allocateStaticModels()
	{
		if (modelWater == null)
		{
			modelWater = ConvenientMethods.loadModel("Models/EmeraldCoast/", "FlatWater");
		}
	}
	
	public static void freeStaticModels()
	{
		if (modelWater != null)
		{
			MainGameLoop.gameLoader.deleteModel(modelWater);
			modelWater = null;
		}
	}
}

