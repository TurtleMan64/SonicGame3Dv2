package entities;

import org.lwjgl.util.vector.Vector3f;

import audio.AudioSources;
import engineTester.MainGameLoop;
import models.TexturedModel;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class Dashpad extends Entity
{
	private static TexturedModel[] dashpadTexturedModels = null;
	
	private float power;
	private Ball player;
	private float hitRadius;
	private int cooldownTimer;
	private static final int cooldownTimerMax = 30;
	private static final int audioTimerMax = 40;
	private int audioTimer;
	
	public Dashpad(Vector3f position, float rotX, float rotY, float rotZ, float scale, float collisionRadius, float myPower, Ball player)
	{
		super(dashpadTexturedModels, position, rotX, rotY, rotZ, scale);
		this.power = myPower;
		this.player = player;
		cooldownTimer = 0;
		audioTimer = 0;
		this.hitRadius = collisionRadius;
	}
	
	@Override
	public void step()
	{
		cooldownTimer = Math.max(cooldownTimer-1, 0);
		audioTimer -= 1;
		if(audioTimer < 0)
		{
			audioTimer = audioTimerMax;
			//AudioSources.play(11, getPosition());
		}
		
		//if(Math.sqrt(Math.pow(player.getX()-super.getX(),2) + Math.pow(player.getY()-super.getY(),2) + Math.pow(player.getZ()-super.getZ(),2)) < springRadius)
		if(player.getX() > getX()-hitRadius-player.getHitboxHorizontal() && player.getX() < getX()+hitRadius+player.getHitboxHorizontal() &&
		   player.getZ() > getZ()-hitRadius-player.getHitboxHorizontal() && player.getZ() < getZ()+hitRadius+player.getHitboxHorizontal() &&
		   player.getY() > getY()-hitRadius-player.getHitboxVertical() && player.getY() < getY()+hitRadius)
		{
			if(cooldownTimer == 0)
			{
				float xOff = (float)(Math.cos(Math.toRadians(getRotY()))*Math.cos(Math.toRadians(getRotZ())));
				float zOff = (float)(-Math.sin(Math.toRadians(getRotY()))*Math.cos(Math.toRadians(getRotZ())));
				//float yOff = (float)(Math.sin(Math.toRadians(getRotZ())));
				
				player.setX(getX());
				player.setY(getY());
				player.setZ(getZ());
				
				player.setxVelAir(0);
				player.setzVelAir(0);
				player.setyVel(0);
				player.setxVel(0);
				player.setzVel(0);
				Ball.relativeXVel = xOff*power;
				Ball.relativeZVel = zOff*power;
				player.setHoverCount(0);
				
				AudioSources.play(12, getPosition());
				
				cooldownTimer = cooldownTimerMax;
			}
		}
	}

	public float getPower() 
	{
		return power;
	}

	public void setPower(float newPower) 
	{
		this.power = newPower;
	}
	
	public static void allocateStaticModels()
	{
		if (dashpadTexturedModels == null)
		{
			dashpadTexturedModels = ConvenientMethods.loadModel("Models/Dashpad/", "Dashpad");
		}
	}
	
	public static void freeStaticModels()
	{
		if (dashpadTexturedModels != null)
		{
			MainGameLoop.gameLoader.deleteModel(dashpadTexturedModels);
			dashpadTexturedModels = null;
		}
	}
}
