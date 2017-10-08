package entities;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import models.TexturedModel;
import objConverter.OBJFileLoader;

public class RotatingPlatform extends CollideableObject
{
	private float yRotRate;
	
	public RotatingPlatform(TexturedModel[] models, CollisionModel cmOriginal, Vector3f position, float yRotRate) 
	{
		super(models, cmOriginal, position);
		this.yRotRate = yRotRate;
	}

	@Override
	public void step()
	{
		increaseRotation(0, yRotRate, 0);
		setRotY(getRotY() % 360);
		super.updateCollisionModel();
		if(collideTransformed.playerIsOn)
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
}
