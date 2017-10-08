package entities;

import org.lwjgl.util.vector.Vector3f;

import collision.CollisionChecker;
import collision.CollisionModel;
import engineTester.MainGameLoop;
import models.TexturedModel;

public class CollideableObject extends Entity
{
	protected CollisionModel collideModelOriginal;
	public CollisionModel collideTransformed;
	protected static Ball player;
	
	public CollideableObject(TexturedModel[] models, CollisionModel cmOriginal, Vector3f position, float xrot, float yrot, float zrot, float scale) 
	{
		super(models, position, xrot, yrot, zrot, scale);
		collideModelOriginal = cmOriginal;
		collideTransformed = new CollisionModel();
		player = MainGameLoop.gamePlayer;
	}
	
	public CollideableObject(TexturedModel[] models, CollisionModel cmOriginal, Vector3f position) 
	{
		super(models, position);
		collideModelOriginal = cmOriginal;
		collideTransformed = new CollisionModel();
		player = MainGameLoop.gamePlayer;
	}
	
	protected void updateCMJustPosition()
	{
		collideModelOriginal.transformModel(collideTransformed, getPosition());
	}
	
	protected void updateCollisionModel()
	{
		collideModelOriginal.transformModel(collideTransformed, getPosition(), -getRotY());
	}
	
	protected void updateCollisionModelWithZ()
	{
		collideModelOriginal.transformModel(collideTransformed, getPosition(), -getRotY(), getRotZ());
	}
	
	@Override
	public void respawn()
	{
		super.respawn();
		//add collision model to list
		//CollisionChecker.addCollideModel(collideTransformed);
		//setVisibility(true);
	}
	
	@Override
	public void despawn()
	{
		super.despawn();
		//remove collision model from list
		//CollisionChecker.removeCollideModel(collideTransformed);
		//setVisibility(false);
	}
	
	//public static void setPlayer(Ball player)
	//{
		//CollideableObject.player = player;
	//}
}
