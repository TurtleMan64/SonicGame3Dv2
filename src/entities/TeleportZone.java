package entities;

import models.TexturedModel;
import toolbox.ConvenientMethods;

import org.lwjgl.util.vector.Vector3f;

import engineTester.MainGameLoop;

public class TeleportZone extends Entity
{
	private Vector3f teleportLocation;
	private float camYawTarget;
	private float camPitchTarget;
	//private static TexturedModel[] springTexturedModels = ConvenientMethods.loadModel("Models/Box/", "Box");
	
	public TeleportZone(Vector3f location, Vector3f teleportTarget, float newYaw, float newPitch, float size) 
	{
		//super(springTexturedModels, location, 0, 0, 0, size);
		super(location, 0, 0, 0, size);
		setVisibility(false);
		teleportLocation = teleportTarget;
		camYawTarget = newYaw;
		camPitchTarget = newPitch;
	}
	
	@Override
	public void step()
	{
		float rad = getScale()/2;
		Ball player = MainGameLoop.gamePlayer;
		
		if(player.getX() > getX()-rad && player.getX() < getX() + rad &&
		        player.getZ() > getZ()-rad && player.getZ() < getZ() + rad &&
		        player.getY() > getY()     && player.getY() < getY() + rad+rad)
		{
			player.setPosition(teleportLocation);
			player.setCameraAngles(camYawTarget, camPitchTarget);
			player.stopMoving();
		}
	}
	
	public void respawn()
	{
		super.respawn();
		setVisibility(false);
	}
}
