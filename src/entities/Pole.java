package entities;

import models.TexturedModel;

import org.lwjgl.util.vector.Vector3f;

public class Pole extends Entity
{	
	protected float height;
	protected boolean hasPlayer;
	protected static Camera camera;
	protected static Player player;
	protected int soundIndex;
	
	public Pole(TexturedModel[] model, Vector3f position, float height, int soundIndex)
	{
		super(model, position, 0, 0, 0, 1);
		this.height = height;
		hasPlayer = false;
		this.soundIndex = soundIndex;
		
		super.setVisibility(true);
		if(model == null)
		{
			super.setVisibility(false);
		}
	}
	
	@Override
	public void step()
	{
		boolean tempCheck = false;
		
		if(player.getInAir() && player.getX() >= super.getX()-4 && player.getX() < super.getX()+4)
		{
			if(player.getZ() >= super.getZ()-4 && player.getZ() < super.getZ()+4)
			{
				if(player.getY() >= super.getY()-8 && player.getY() <= super.getY()+height)
				{
					player.setX(super.getX());
					player.setZ(super.getZ());
					tempCheck = true;
					if(player.getOnPole() == false)
					{
						player.grabPole(this, soundIndex);
						hasPlayer = true;
					}
				}
			}
		}
		
		
		if(hasPlayer == true && tempCheck == false)
		{
			hasPlayer = false;
		}
	}
	
	public static void setCamera(Camera newCamera)
	{
		camera = newCamera;
	}
	
	public static void setPlayer(Player newPlayer)
	{
		player = newPlayer;
	}
	
	public float getHeight()
	{
		return height;
	}
}

