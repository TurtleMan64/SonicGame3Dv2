package entities;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import engineTester.MainGameLoop;
import models.TexturedModel;
import toolbox.ConvenientMethods;

public class Cheese extends Entity
{
	private static TexturedModel[] modelBody = null;//ConvenientMethods.loadModel("Models/Cheese/", "Cheese", MainGameLoop.gameLoader);
	
	private int timer;
	
	public Cheese(Vector3f position) 
	{
		super(modelBody, position);
		super.setVisibility(true);
		timer = 0;
	}
	
	@Override
	public void step()
	{
		timer++;
		
		if(timer % 80 == 0)
		{
			AudioSources.play(13+(int)(Math.random()*12), getPosition());
		}
	}
}
