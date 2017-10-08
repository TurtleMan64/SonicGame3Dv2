package entities;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import models.TexturedModel;
import toolbox.ConvenientMethods;

public class ChainChomp extends Entity
{
	private static TexturedModel[] modelTemp = null;//ConvenientMethods.loadModel("Models/ChainChomp/", "ChainChomp", MainGameLoop.gameLoader);
	
	public ChainChomp(Vector3f position) 
	{
		super(modelTemp, position);
		setVisibility(true);
		setRotY(90);
	}
	
	@Override
	public void step()
	{
		
	}

	@Override
	public void respawn()
	{
		super.respawn();
		setVisibility(true);
		setRotY(270);
	}
	
	@Override
	public void despawn()
	{
		
	}
}
