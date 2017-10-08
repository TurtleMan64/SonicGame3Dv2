package entities;

import models.TexturedModel;
import particles.Particle;
import particles.ParticleResources;

import org.lwjgl.util.vector.Vector3f;

import renderEngine.Loader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;
import toolbox.ParticleManager;

import engineTester.MainGameLoop;

public class Star extends Entity
{
	private Player player;
	private int starIndex;
	private float hitboxH;
	private float hitboxV;
	private static TexturedModel[] modelYellow = null;//ConvenientMethods.loadModel("Models/Star/", "StarYellow", MainGameLoop.gameLoader);
	private static TexturedModel[] modelBlue = null;//ConvenientMethods.loadModel("Models/Star/", "StarBlue", MainGameLoop.gameLoader);
	private boolean isYellow;
	private float currBrightness;
	private Light currLight;
	private Vector3f originalPosition;
	private float clock;
	
	public Star(Vector3f position, int starIndex, Player player) 
	{
		super(modelYellow, position, 0, 0, 0, 1);
		super.setVisibility(true);
		this.player = player;
		hitboxH = 3;
		hitboxV = 4;
		this.starIndex = starIndex;
		isYellow = true;
		currBrightness = 1;
		currLight = MainGameLoop.gameLights.get(2+(starIndex%4));
		originalPosition = new Vector3f();
		originalPosition.set(position);
		clock = 0;
	}
	
	public void step()
	{
		//AudioRes.playSoundRepeat(4, super.getPosition());
		if(super.getVisibility() == true && player.getX() > super.getX()-hitboxH-player.getHitboxHorizontal() && player.getX() < super.getX()+hitboxH+player.getHitboxHorizontal() &&
		   player.getZ() > super.getZ()-hitboxH-player.getHitboxHorizontal() && player.getZ() < super.getZ()+hitboxH+player.getHitboxHorizontal() &&
		   player.getY() > super.getY()-hitboxV-player.getHitboxVertical() && player.getY() < super.getY()+hitboxV)
		{
			MainGameLoop.starList[starIndex] = true;
			super.setVisibility(false);
			//MainGameLoop.gameEntitiesToDelete.add(this);
			player.grabStar();
			AudioRes.playSound(18, 1, getPosition());
		}
		
		if(super.getVisibility() == true)
		{
			if(isYellow)
			{
				new Particle(ParticleResources.textureSparkleYellow, 
						new Vector3f(getX() + 8*(float)Math.random() -4,
									 getY() + 8*(float)Math.random() -4,
									 getZ() + 8*(float)Math.random() -4), 
						new Vector3f(0, -0.2f, 0), 0, 30, 0, 3, 0);
			}
			else
			{
				new Particle(ParticleResources.textureSparkleBlue, 
						new Vector3f(getX() + 8*(float)Math.random() -4,
									 getY() + 8*(float)Math.random() -4,
									 getZ() + 8*(float)Math.random() -4), 
						new Vector3f(0, -0.2f, 0), 0, 30, 0, 3, 0);
			}
			clock +=0.1f;
			super.setY(originalPosition.y+(1*(float)(Math.sin(clock))));
		}
		else
		{
			currBrightness+=0.013f;
			currLight.setAttenuationX(currBrightness);
		}
		super.increaseRotation(0, 3, 0);
	}
	
	public void respawn()
	{
		super.respawn();
		super.setVisibility(true);
		currBrightness = 1;
		if(MainGameLoop.starList[starIndex] == false)
		{
			//super.setModels(modelYellow);
			currLight.setColour(new Vector3f(1.0f, 1.0f, 0.0f));
			isYellow = true;
		}
		else
		{
			//super.setModels(modelBlue);
			currLight.setColour(new Vector3f(0.0f, 0.3f, 1.0f));
			isYellow = false;
		}
		currLight.setPosition(super.getPosition());
		currLight.setAttenuationX(currBrightness);
		clock = 0;
	}
}
