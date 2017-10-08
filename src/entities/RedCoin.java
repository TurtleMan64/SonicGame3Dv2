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

public class RedCoin extends Entity
{
	private float hitboxH;
	private float hitboxV;
	public static TexturedModel[] coinModel = null;//ConvenientMethods.loadModel("Models/Coin/", "CoinRedIndent", MainGameLoop.gameLoader);
	public static Player player;
	public static int currentCollected;
	
	public RedCoin(Vector3f position) 
	{
		super(coinModel, position, 0, 0, 0, 1);
		super.setVisibility(true);
		hitboxH = 3;
		hitboxV = 3;
	}
	
	public void step()
	{
		//AudioRes.playSoundRepeat(4, super.getPosition());
		if(super.getVisibility() == true && player.getX() > super.getX()-hitboxH-player.getHitboxHorizontal() && player.getX() < super.getX()+hitboxH+player.getHitboxHorizontal() &&
		   player.getZ() > super.getZ()-hitboxH-player.getHitboxHorizontal() && player.getZ() < super.getZ()+hitboxH+player.getHitboxHorizontal() &&
		   player.getY() > super.getY()-hitboxV-player.getHitboxVertical() && player.getY() < super.getY()+hitboxV)
		{
			super.setVisibility(false);
			//MainGameLoop.gameEntitiesToDelete.add(this);
			AudioRes.playSound(52, 1, getPosition());
			for(int i = 0; i < 10; i++)
			{	
				new Particle(ParticleResources.textureSparkleRed, 
						new Vector3f(getX(),getY(),getZ()), 
						new Vector3f((float)Math.random()-0.5f, 
								     (float)(Math.random()*0.3+0.2f), 
								     (float)Math.random()-0.5f), 
						0.05f, 60, 0, 4, 0);
			}
			currentCollected++;
		}
		super.increaseRotation(0, 5, 0);
	}
	
	public void respawn()
	{
		super.respawn();
		super.setVisibility(true);
		currentCollected = 0;
	}
	
	public static void setStaticObjects(Player myPlayer)
	{
		player = myPlayer;
		currentCollected = 0;
	}
}
