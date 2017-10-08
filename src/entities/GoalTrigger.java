package entities;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;
import models.TexturedModel;
import toolbox.ConvenientMethods;

public class GoalTrigger extends Entity
{
	private float hitboxH;
	private float hitboxV;
	private static Ball player;
	
	//private static TexturedModel[] models = ConvenientMethods.loadModel("Models/Box/", "Box");
	
	public GoalTrigger(Vector3f position, float scale) 
	{
		super(position, 0, 0, 0, scale);
		//setVisibility(true);
		hitboxH = scale/2;
		hitboxV = scale/2;
		player = MainGameLoop.gamePlayer;
		GoalSign.addTrigger();
	}
	
	public void step()
	{
		if (player.getX() > getX()-hitboxH-player.getHitboxHorizontal() && player.getX() < getX()+hitboxH+player.getHitboxHorizontal() &&
		   player.getZ() > getZ()-hitboxH-player.getHitboxHorizontal() && player.getZ() < getZ()+hitboxH+player.getHitboxHorizontal() &&
		   player.getY() > getY()-hitboxV-player.getHitboxVertical() && player.getY() < getY()+hitboxV)
		{
			GoalSign.removeTrigger();
			MainGameLoop.gameEntitiesToDelete.add(this);
		}
	}
}
