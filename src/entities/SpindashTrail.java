package entities;

import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;

public class SpindashTrail// extends Entity
{
	//private static float maxScale = 0.5f;
	
	//private int lifeCount;
	//private int lifeCountMax;
	//private int colourID;
	
	//private static TexturedModel[] modelTrail = null;//ConvenientMethods.loadModel("Models/Ball/", "Ball", MainGameLoop.gameLoader);
	
	public SpindashTrail(Vector3f position, int life, int colourID)
	{
		//super(modelTrail, position, 0, 0, 0, 1);
		//lifeCount = life;
		//lifeCountMax = life;
		
		//setVisibility(false);
		
		switch (colourID)
		{
			case 0: 
				new Particle(ParticleResources.textureLightBlueTrail, 
					new Vector3f(position),
					new Vector3f(0, 0, 0),
					0, life, 0, 10, -(10.0f/life));
				break;
				
			case 1: 
				new Particle(ParticleResources.textureBlueTrail, 
					new Vector3f(position),
					new Vector3f(0, 0, 0),
					0, life, 0, 10, -(10.0f/life));
				break;
				
			case 2: 
				new Particle(ParticleResources.textureBlackTrail, 
					new Vector3f(position),
					new Vector3f(0, 0, 0),
					0, life, 0, 10, -(10.0f/life));
				break;
				
			case 3: 
				new Particle(ParticleResources.textureGrayTrail, 
					new Vector3f(position),
					new Vector3f(0, 0, 0),
					0, life, 0, 10, -(10.0f/life));
				break;
				
			case 4: 
				new Particle(ParticleResources.textureLightBlueTrail, 
					new Vector3f(position),
					new Vector3f(0, 0, 0),
					0, life, 0, 10, -(10.0f/life));
				break;
				
			default:
				break;
		}
	}
	
	public SpindashTrail(Vector3f position, float xVel, float yVel, float zVel, float gravity, int life, float size, int colourID)
	{
		//super(modelTrail, position, 0, 0, 0, 1);
		//lifeCount = life;
		//lifeCountMax = life;
		
		//setVisibility(false);
		
		switch (colourID)
		{
			case 0: 
				new Particle(ParticleResources.textureLightBlueTrail, 
					new Vector3f(position),
					new Vector3f(xVel, yVel, zVel),
					gravity, life, 0, size, -(size/life));
				break;
				
			case 1: 
				new Particle(ParticleResources.textureBlueTrail, 
					new Vector3f(position),
					new Vector3f(xVel, yVel, zVel),
					gravity, life, 0, size, -(size/life));
				break;
				
			case 2: 
				new Particle(ParticleResources.textureBlackTrail, 
					new Vector3f(position),
					new Vector3f(xVel, yVel, zVel),
					gravity, life, 0, size, -(size/life));
				break;
				
			case 3: 
				new Particle(ParticleResources.textureGrayTrail, 
					new Vector3f(position),
					new Vector3f(xVel, yVel, zVel),
					gravity, life, 0, size, -(size/life));
				break;
				
			case 4: 
				new Particle(ParticleResources.textureLightBlueTrail, 
					new Vector3f(position),
					new Vector3f(xVel, yVel, zVel),
					gravity, life, 0, size, -(size/life));
				break;
				
			default:
				break;
		}
	}
	
	//@Override
	//public void step()
	//{
		//lifeCount--;
		//setScale((maxScale*lifeCount)/lifeCountMax);
		
		//if (lifeCount < 0)
		//{
			//setVisibility(false);
			//MainGameLoop.gameEntitiesToDelete.add(this);
		//}
	//}
}

