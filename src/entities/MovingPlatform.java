package entities;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionModel;
import models.TexturedModel;

public class MovingPlatform extends CollideableObject
{
	protected float xVel;
	protected float yVel;
	protected float zVel;
	protected float previousX;
	protected float previousY;
	protected float previousZ;
	
	public MovingPlatform(TexturedModel[] models, CollisionModel cmOriginal, Vector3f position) 
	{
		super(models, cmOriginal, position);
		xVel = 0.0f;
		yVel = 0.0f;
		zVel = 0.0f;
		previousX = 0;
		previousY = 0;
		previousZ = 0;
	}
	
	@Override
	public void step()
	{
		if (collideTransformed.playerIsOn)
		{
			//player.setAirVel(getX()-previousX, getY()-previousY, getZ()-previousZ);
			player.setDisplacement(getX()-previousX, getY()-previousY, getZ()-previousZ);
		}
		
		previousX = getX();
		previousY = getY();
		previousZ = getZ();
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		xVel = 0.0f;
		yVel = 0.0f;
		zVel = 0.0f;
		previousX = 0;
		previousY = 0;
		previousZ = 0;
	}
}
