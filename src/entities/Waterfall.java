package entities;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import engineTester.MainGameLoop;
import models.TexturedModel;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class Waterfall extends Entity
{
	private static TexturedModel[] springTexturedModels = null;
	private float springPower;
	private Ball player;
	private float springRadius;
	private int cooldownTimer;
	private static final int cooldownTimerMax = 25;
	
	public Waterfall(Vector3f position, float rotX, float rotY, float rotZ, float scale, float collisionRadius, float myPower, Ball player)
	{
		super(springTexturedModels, position, rotX, rotY, rotZ, scale);
		this.springPower = myPower;
		this.player = player;
		this.springRadius = collisionRadius;
		cooldownTimer = 0;
	}
	
	@Override
	public void step()
	{
		cooldownTimer = Math.max(cooldownTimer-1, 0);
		
		//if(Math.sqrt(Math.pow(player.getX()-super.getX(),2) + Math.pow(player.getY()-super.getY(),2) + Math.pow(player.getZ()-super.getZ(),2)) < springRadius)
		if(player.getX() > getX()-springRadius-player.getHitboxHorizontal() && player.getX() < getX()+springRadius+player.getHitboxHorizontal() &&
		   player.getZ() > getZ()-springRadius-player.getHitboxHorizontal() && player.getZ() < getZ()+springRadius+player.getHitboxHorizontal() &&
		   player.getY() > getY()-springRadius-player.getHitboxVertical() && player.getY() < getY()+springRadius)
		{
			if (cooldownTimer == 0)
			{
				float xOff = (float)(Math.cos(Math.toRadians(getRotY()))*Math.cos(Math.toRadians(getRotZ())));
				float zOff = (float)(-Math.sin(Math.toRadians(getRotY()))*Math.cos(Math.toRadians(getRotZ())));
				float yOff = (float)(Math.sin(Math.toRadians(getRotZ())));
				
				player.setX(getX()+xOff*2);
				player.setY(getY()+yOff*2);
				player.setZ(getZ()+zOff*2);
				player.setOnPlane(false);
				
				player.setxVelAir(xOff*springPower);
				player.setzVelAir(zOff*springPower);
				player.setyVel(yOff*springPower);
				player.setxVel(0);
				player.setzVel(0);
				Ball.relativeXVel = 0;
				Ball.relativeZVel = 0;
				player.setHoverCount(0);
				
				player.setCanMove(false);
				
				//AudioRes.playSound(54, 1+(springPower*0.008f), getPosition());
				AudioSources.play(10, getPosition(), 1+(springPower*0.008f));
				
				cooldownTimer = cooldownTimerMax;
			}
		}
		
		if (cooldownTimer == 1)
		{
			player.setCanMove(true);
		}
	}

	public float getSpringPower() 
	{
		return springPower;
	}

	public void setSpringPower(float newPower) 
	{
		this.springPower = newPower;
	}
	
	public static void allocateStaticModels()
	{
		if (springTexturedModels == null)
		{
			springTexturedModels = ConvenientMethods.loadModel("Models/Spring/", "Spring");
		}
	}
	
	public static void freeStaticModels()
	{
		if (springTexturedModels != null)
		{
			MainGameLoop.gameLoader.deleteModel(springTexturedModels);
			springTexturedModels = null;
		}
	}
}
