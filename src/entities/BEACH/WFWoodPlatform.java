package entities.BEACH;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import engineTester.MainGameLoop;
import entities.CollideableObject;
import models.TexturedModel;
import objConverter.OBJFileLoader;
import toolbox.AudioRes;
import toolbox.ConvenientMethods;

public class WFWoodPlatform extends CollideableObject
{
	private static CollisionModel cmOriginal = null;//OBJFileLoader.loadCollisionOBJ("Models/WF/", "WoodSpin");
	private static TexturedModel[] models = null;//ConvenientMethods.loadModel("Models/WF/", "WoodSpin", MainGameLoop.gameLoader);
	private static float yRotRate = 1f;
	private int timer;
	
	public WFWoodPlatform(Vector3f position) 
	{
		super(models, cmOriginal, position);
		timer = 0;
	}
	
	@Override
	public void step()
	{
		setRotY((getRotY()+360) % 360);
		if((getRotY() == 0 || getRotY() == 180) && timer < 120)
		{
			timer++;
			yRotRate = 0;
			AudioRes.stopSound(44);
		}
		else
		{
			yRotRate = 1;
			increaseRotation(0, yRotRate, 0);
			timer = 0;
			AudioRes.playSoundLoop(44, getPosition());
		}
		setRotY(getRotY() % 360);
		updateCollisionModel();
		if(collideTransformed.playerIsOn && yRotRate != 0)
		{
			//calculate x and z displacements from rotation + players distance from center of rotation
			float xDiff = player.getX()-getX();
			float zDiff = player.getZ()-getZ();
			double angleRad = Math.toRadians(yRotRate);
			float newX = (float)((xDiff)*Math.cos(angleRad) - (zDiff)*Math.sin(angleRad));
			float newZ = -(float)(-(zDiff)*Math.cos(angleRad) - (xDiff)*Math.sin(angleRad));
			
			float xdis = xDiff-newX;
			float zdis = zDiff-newZ;
			player.setDisplacement(xdis, 0, zdis);
			//player.setAirVel(xdis, 0, zdis);
			player.increaseRotation(0, yRotRate, 0);
		}
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		timer = 0;
	}
}
